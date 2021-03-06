package compress; 

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;

/** 
 *   <B>说       明</B>:TAR格式文件压缩器。
 *
 * @author  作  者  名：LiangAnCai<br/>
 *		    E-mail ：liangancai@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2013年9月30日 下午3:21:28 
 */
class TARCompressor extends Compressor {

	@Override
	protected void doCompress(File[] srcFiles, File destFile,
			boolean needPassword, String password) {
		if (!needPassword) {
			TarArchiveOutputStream tarOutput = null;
			try {
				tarOutput = new TarArchiveOutputStream(new BufferedOutputStream(new FileOutputStream(destFile)),"GBK");
				for (File srcFile : srcFiles) {
					doCompressFile(srcFile, tarOutput, srcFile.getParent());
				}
			} catch (IOException e) {
				e.printStackTrace();
				throw new IllegalStateException(e);
			} finally {
				if (tarOutput != null) {
					try {
						tarOutput.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			throw new UnsupportedOperationException("TAR格式文件压缩器不支持加密操作！");
		}				
	}
	
	/**
	 * 压缩一个文件
	 * 
	 * @param srcFile
	 * @param tarOutput
	 * @param destFile
	 * @throws IOException
	 */
	private void doCompressFile(File srcFile, TarArchiveOutputStream tarOutput, String basePath) throws IOException {
		if (srcFile.isDirectory()) {// 是目录
			File[] listFile = srcFile.listFiles();// 得出目录下所有的文件对象
			TarArchiveEntry entry = new TarArchiveEntry(srcFile, srcFile.getPath().substring(basePath.length()+1));
			tarOutput.putArchiveEntry(entry);
			tarOutput.closeArchiveEntry();
			for (File tempFile : listFile) {
				doCompressFile(tempFile, tarOutput, basePath);// 递归
			}
		} else if (srcFile.isFile()) {// 是文件
			TarArchiveEntry entry = new TarArchiveEntry(srcFile, srcFile.getPath().substring(basePath.length()+1));
			tarOutput.putArchiveEntry(entry);
			FileInputStream fis = new FileInputStream(srcFile);
			BufferedInputStream bis = new BufferedInputStream(fis);
			byte[] buffer = new byte[BUFFER_SIZE];
			int length = -1;
			while ((length = bis.read(buffer)) != -1) {
				tarOutput.write(buffer, 0, length);
			}
			tarOutput.closeArchiveEntry();
			if(bis != null){
				bis.close();
			}
		}
	}

	@Override
	protected void doDecompress(File srcFile, File destFile,
			boolean needPassword, String password) {
		if (!needPassword) {
			TarArchiveInputStream tarInput = null;
			try {
				tarInput = new TarArchiveInputStream(new FileInputStream(srcFile));
				TarArchiveEntry entry = null;
				while( (entry = tarInput.getNextTarEntry()) != null ){
					//如果是文件夹
					if(entry.isDirectory()){
						File dir = new File(destFile, entry.getName());
						dir.mkdirs();
					}
					//如果是文件
					else{
						createFileFromTarArchiveEntry(tarInput, destFile, entry);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				throw new IllegalStateException(e);
			} finally {
				if (tarInput != null) {
					try {
						tarInput.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			throw new UnsupportedOperationException("TAR格式文件压缩器不支持加密操作！");
		}
	}
	
	/**
	 * 根据压缩文件中的Entry信息创建文件。
	 * 
	 * @param destBaseDir 目标根路径
	 * @param archiveEntry 压缩记录
	 */
	private void createFileFromTarArchiveEntry(TarArchiveInputStream tarInput, File destBaseDir,
			TarArchiveEntry tarArchiveEntry) {
		File file = new File(destBaseDir, tarArchiveEntry.getName());
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		try {
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			//获取压缩记录的内容长度(字节数)
			long size = tarArchiveEntry.getSize();
			if(size <= BUFFER_SIZE){
				IOUtils.copy(tarInput, bos, (int)size);
			}else{
				long leftSize = size;
				while(leftSize > 0){
					if(leftSize > BUFFER_SIZE){
						IOUtils.copy(tarInput, bos, BUFFER_SIZE);
					}else{
						IOUtils.copy(tarInput, bos, (int)leftSize);
					}
					leftSize -= BUFFER_SIZE;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				if(bos != null)
					bos.close();
				if(fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected String getSuffix() {
		return ".tar";
	}

	@Override
	public boolean isEncryptionSupported() {
		return false;
	}

	@Override
	public boolean isFileEncrypted(File file) {
		return false;
	}

}
 