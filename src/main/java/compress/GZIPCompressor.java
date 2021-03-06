package compress;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.compress.utils.IOUtils;

/** 
 *   <B>说       明</B>:GZIP格式文件压缩器。
 *   利用apache commons compress实现
 *
 * @author  作  者  名：chenjinlong<br/>
 *		   E-mail ：chenjinlong@vrvmail.com.cn
 
 * @version 版   本  号：V1.0<br/>
 *          创建时间：2013年9月30日 上午9:38:01 
 */
class GZIPCompressor extends Compressor{
	
	/**
	 * GZIP不支持多个文件压缩
	 */
	@Override
	public void compress(File[] srcFiles, File destFile) {
		throw new UnsupportedOperationException("GZIP不支持多个文件压缩");
	}
	
	/**
	 * GZIP不支持多个文件压缩
	 */
	@Override
	public void compress(String[] srcFilePaths, String destFilePath) {
		throw new UnsupportedOperationException("GZIP不支持多个文件压缩");
	}

	@Override
	protected void doCompress(File[] srcFiles, File destFile,
			boolean needPassword, String password) {
		GzipCompressorOutputStream gos = null;
		BufferedInputStream bis = null;
		try{
		    gos = new GzipCompressorOutputStream(new BufferedOutputStream(new FileOutputStream(destFile)));
		    int length = -1;   
		    byte buffer[] = new byte[BUFFER_SIZE];  
		    if(srcFiles != null && srcFiles.length > 0){
		    	for(File file : srcFiles){
		    		FileInputStream fis = null;
		    		try{
		    			fis = new FileInputStream(file);
		    			bis = new BufferedInputStream (fis);
			    		while ((length = bis.read(buffer, 0, BUFFER_SIZE)) != -1) {   
			    			gos.write(buffer, 0, length);
			    		} 
		    		}catch(Exception e){
		    			if(bis != null){
		    				bis.close();
		    			}
		    			if(fis != null){
		    				fis.close();
		    			}
		    		}
		    		gos.flush();
		    	}
		    }
		    gos.close();
		}catch(Exception exce){
			exce.printStackTrace();
		}finally{
			if(gos != null){
				try {
					gos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	protected void doDecompress(File srcFile, File destFile,
			boolean needPassword, String password) {
		GzipCompressorInputStream gis = null;  
		BufferedOutputStream bos = null;  
        try {  
        	FileInputStream fis = new FileInputStream(srcFile);
        	BufferedInputStream bis = new BufferedInputStream(fis);
        	//SystemUtils.getFileSeparator()
        	String destPath = destFile.getAbsolutePath()+"/"+srcFile.getName().substring(0, srcFile.getName().lastIndexOf("."));
            gis = new GzipCompressorInputStream(bis);  
            bos = new BufferedOutputStream(new FileOutputStream(destPath), BUFFER_SIZE);  
            IOUtils.copy(gis, bos);  
        }catch(IOException ioe){
        	ioe.printStackTrace();
        }
        finally {  
        	try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }  
	}

	@Override
	protected String getSuffix() {
		return ".gz";
	}

	@Override
	protected String getFileName(File file) {
		return file.getName();
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
