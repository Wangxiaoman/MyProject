package compress;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import de.innosystec.unrar.Archive;
import de.innosystec.unrar.exception.RarException;
import de.innosystec.unrar.rarfile.FileHeader;


/** 
 *   <B>说       明</B>:RAR格式文件压缩器。
 *   <p>支持加密码。
 *
 * @author  作  者  名：WuHong<br/>
 *		   E-mail ：wuhong@vrvmail.com.cn
 
 * @version 版   本  号：V1.0<br/>
 *          创建时间：2013年9月29日 下午3:11:36 
 */
class RARCompressor extends Compressor{

	@Override
	protected void doCompress(File[] srcFiles, File destDir,
			boolean needPassword, String password) {
		throw new UnsupportedOperationException("不支持RAR类型压缩");
	}
	
	@Override
	protected void doDecompress(File srcFile, File destDir, boolean needPassword,
			String password) {
	        Archive archive = null;  
	        File out = null;  
	        File dir = null;  
	        FileOutputStream os = null;  
	        FileHeader fh = null;  
	        String path = "";
	        String dirPath = "";  
	        try {  
	            archive = new Archive(srcFile, needPassword ? password : null, false);
	        } catch (RarException e1) {  
	            e1.printStackTrace();  
	        } catch (IOException e1) {  
	            e1.printStackTrace();  
	        } 
	        if (archive != null) {  
	            try {  
	                fh = archive.nextFileHeader();  
	                while (fh != null) {  
	                	String fileName = "";  
	                	if(fh.isUnicode()){  
	                		fileName = fh.getFileNameW().trim();  
	                	}else{  
	                		fileName = fh.getFileNameString().trim();    
	                	} 
	                    path = (destDir.getAbsolutePath() +File.separator + fileName).replaceAll("\\\\", "/");  
	                    int end = path.lastIndexOf("/");  
	                    if (end != -1) {  
	                        dirPath = path.substring(0, end);  
	                    }  
	                    try {  
	                        dir = new File(dirPath);  
	                        if (!dir.exists()) {  
	                            dir.mkdirs();  
	                        }  
	                    } catch (RuntimeException e1) {  
	                        e1.printStackTrace();  
	                    } finally {  
	                        if (dir != null) {  
	                            dir = null;  
	                        }  
	                    }  
	                    out = new File(destDir.getAbsolutePath() +File.separator + fileName);  
	                    try {  
	                    	if(fh.isDirectory()){
	                    		if(out.exists()){
		                    		fh = archive.nextFileHeader();
		                    		continue;
	                    		}else{
	                    			out.mkdirs();
	                    		}
	                    	}else{
		                        os = new FileOutputStream(out);  
		                        archive.extractFile(fh, os);  
	                    	}
	                    } catch (FileNotFoundException e) {  
	                        e.printStackTrace();  
	                    } catch (RarException e) {  
	                        e.printStackTrace();  
	                    } finally {  
	                        if (os != null) {  
	                            try {  
	                                os.close();  
	                            } catch (IOException e) {  
	                                e.printStackTrace();  
	                            }  
	                        }  
	                        if (out != null) {  
	                            out = null;  
	                        }  
	                    }  
	                    fh = archive.nextFileHeader();  
	                }  
	            } catch (RuntimeException e) {  
	                e.printStackTrace();  
	            } finally {  
	                fh = null;  
	                if (archive != null) {  
	                    try {  
	                        archive.close();  
	                    } catch (IOException e) {  
	                        e.printStackTrace();  
	                    }  
	                }  
	            }  
	        }  
	}

	@Override
	protected String getSuffix() {
		return ".rar";
	}

	@Override
	public boolean isEncryptionSupported() {
		return true;
	}

	@Override
	public boolean isFileEncrypted(File file) {
		Archive archive = null;
		try {
			archive = new Archive(file,null,false);
			return archive.getFileHeaders().get(0).isEncrypted();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally{
			try {
				archive.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
