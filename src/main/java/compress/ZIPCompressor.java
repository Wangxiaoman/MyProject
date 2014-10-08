package compress;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Enumeration;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.lang3.StringUtils;

/** 
 *   <B>说       明</B>:ZIP格式文件压缩器。
 *   <p>支持加密码。
 *
 * @author  作  者  名：WuHong<br/>
 *		   E-mail ：wuhong@vrvmail.com.cn

 * @version 版   本  号：V1.0<br/>
 *          创建时间：2013年9月29日 下午3:06:39 
 */
class ZIPCompressor extends Compressor{

	@Override
	protected void doCompress(File[] srcFiles, File destFile,
			boolean needPassword, String password) {
		if(needPassword){
			doCompressWithPW(srcFiles, destFile, password);
		}else{
			ZipArchiveOutputStream zaos = null;
			try {
				zaos = new ZipArchiveOutputStream(destFile);
				zaos.setUseZip64(Zip64Mode.AsNeeded);
				//建立一个栈来存放文件夹。
				ArrayDeque<ArchiveFile> dirStack = new ArrayDeque<ArchiveFile>();
				for(File srcFile : srcFiles){
					if(srcFile.isDirectory()){
						//将文件夹入栈
						ArchiveFile archiveFile = new ArchiveFile();
						archiveFile.setFile(srcFile);
						archiveFile.setPath("");
						dirStack.addFirst(archiveFile);
						ArchiveFile dirAFile = null;
						while(//文件夹出栈
								(dirAFile = dirStack.pollLast()) 
								!= null){
							//压缩文件夹
							doCompressDir(dirAFile,zaos);
							String archiveFilePath = dirAFile.getPath();
							//获取文件夹下所有子文件
							File[] subFiles = dirAFile.getFile().listFiles();
							for(File subFile : subFiles){
								if(subFile.isDirectory()){
									//如果子文件夹是目录，入栈。
									ArchiveFile subArchiveFile = new ArchiveFile();
									subArchiveFile.setFile(subFile);
									subArchiveFile.setPath(archiveFilePath + dirAFile.getFileName() + "/");
									dirStack.addFirst(subArchiveFile);
								}else{
									//否则，压缩
									doCompressFile(subFile,zaos,archiveFilePath + dirAFile.getFileName() + "/");
								}
							}
						}
					}else{
						doCompressFile(srcFile,zaos,"");
					}
					zaos.closeArchiveEntry();
				}
				zaos.finish();
			} catch (IOException e) {
				e.printStackTrace();
				throw new IllegalStateException(e);
			} finally{
				if(zaos != null){
					try {
						zaos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * 压缩一个文件夹
	 * 
	 * @param srcFile
	 * @param zaos
	 * @throws IOException
	 */
	private void doCompressDir(ArchiveFile archiveFile, ZipArchiveOutputStream zaos) throws IOException {
		ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(archiveFile.getFile(), archiveFile.getArchiveFileName());
		zaos.putArchiveEntry(zipArchiveEntry);
	}

	/**
	 * 压缩一个文件
	 * 
	 * @param srcFile
	 * @param zaos
	 * @throws IOException
	 */
	private void doCompressFile(File srcFile, ZipArchiveOutputStream zaos, String pathBase) throws IOException {
		ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(srcFile, pathBase + srcFile.getName());
		zaos.putArchiveEntry(zipArchiveEntry);
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		try{
			fis = new FileInputStream(srcFile);
			bis = new BufferedInputStream(fis);
			byte[] buffer = new byte[BUFFER_SIZE];
			int length = -1;
			while((length = bis.read(buffer)) != -1){
				zaos.write(buffer, 0, length);
			}
		}catch(IOException exception){
			if(bis != null){
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/** 
	 * 使用给定密码压缩指定文件或文件夹到指定位置. 
	 *
	 * @param srcFiles 要压缩的文件或文件夹
	 * @param destFile 压缩文件
	 * @param password 压缩使用的密码 
	 */  
	private void doCompressWithPW(File[] srcFiles, File destFile, String password) {  
		ZipParameters parameters = new ZipParameters();  
		parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);           // 压缩方式  
		parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);    // 压缩级别  
		if (!StringUtils.isEmpty(password)) {  
			parameters.setEncryptFiles(true);  
			parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD); // 加密方式  
			parameters.setPassword(password.toCharArray());  
		}  
		try {  
			ZipFile zipFile = new ZipFile(destFile);
			for (File srcFile : srcFiles) {
				if (srcFile.isDirectory()) {  
					zipFile.addFolder(srcFile, parameters);  
				} else {  
					zipFile.addFile(srcFile, parameters);  
				}
			}
		} catch (ZipException e) {  
			e.printStackTrace();  
		}  
	}  

	@Override
	protected void doDecompress(File srcFile, File destDir, boolean needPassword,
			String password) {
		if(needPassword){
			try {  
				ZipFile zipFile = new ZipFile(srcFile);
				zipFile.setPassword(password);
				zipFile.extractAll(destDir.getCanonicalPath());
			} catch(ZipException e) {  
				e.printStackTrace();  
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}else{
			org.apache.commons.compress.archivers.zip.ZipFile zipFile = null;
			try {
				zipFile = new org.apache.commons.compress.archivers.zip.ZipFile(srcFile,"GBK");
				Enumeration<ZipArchiveEntry> enumeration = zipFile.getEntriesInPhysicalOrder();
				while (enumeration.hasMoreElements()) {
					ZipArchiveEntry zipArchiveEntry = (ZipArchiveEntry) enumeration
							.nextElement();
					//如果是文件夹,创建文件夹。
					if(zipArchiveEntry.isDirectory()){
						File dir = new File(destDir, zipArchiveEntry.getName());
						dir.mkdirs();
					}
					//如果是文件，创建文件并写入数据。
					else{
						createFileFromArchiveEntry(zipFile, destDir, zipArchiveEntry);
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(zipFile != null){
					try {
						zipFile.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * 根据压缩文件中的Entry信息创建文件。
	 * 
	 * @param zipFile 压缩文件。
	 * @param destBaseDir 目标根路径。
	 * @param zipArchiveEntry 压缩记录。
	 */
	private void createFileFromArchiveEntry(org.apache.commons.compress.archivers.zip.ZipFile zipFile,
			File destBaseDir, ZipArchiveEntry zipArchiveEntry) {
		File file = new File(destBaseDir, zipArchiveEntry.getName());
		//如果父目录不存在,创建父目录。
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		InputStream in = null;
		try {
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			in = zipFile.getInputStream(zipArchiveEntry);
			IOUtils.copy(in, bos, BUFFER_SIZE);
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected String getSuffix() {
		return ".zip";
	}

	//辅助类
	private class ArchiveFile{
		private File file;
		private String path;
		public File getFile() {
			return file;
		}
		public void setFile(File file) {
			this.file = file;
		}
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public String getArchiveFileName(){
			return path + file.getName();
		}
		public String getFileName(){
			return file.getName();
		}
	}

	@Override
	public boolean isEncryptionSupported() {
		return true;
	}

	@Override
	public boolean isFileEncrypted(File file) {
		try {
			ZipFile zipFile = new ZipFile(file);
			return zipFile.isEncrypted();
		} catch (ZipException e) {
			e.printStackTrace();
			return false;
		}
	}

}
