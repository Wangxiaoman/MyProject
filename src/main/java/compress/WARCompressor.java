package compress;

import java.io.File;

/** 
 *   <B>说       明</B>:WAR格式文件压缩器。
 *
 * @author  作  者  名：WuHong<br/>
 *		   E-mail ：wuhong@vrvmail.com.cn
 
 * @version 版   本  号：V1.0<br/>
 *          创建时间：2014年5月15日 上午9:44:51 
 *          
 * @see ZIPCompressor
 */
public class WARCompressor extends Compressor{
	
	/*
	 * war格式文件可利用zip压缩器解压,但war格式文件不支持加密等特性，
	 * 所以单独建立war压缩器，内部委托给zip压缩器进行解压。
	 */
	
	private ZIPCompressor innerCompressor;
	
	public WARCompressor(ZIPCompressor zipCompressor) {
		Assert.notNull(zipCompressor, "ZIP文件压缩器不能为null!");
		this.innerCompressor = zipCompressor;
	}

	@Override
	protected void doCompress(File[] srcFiles, File destFile,
			boolean needPassword, String password) {
		//目前不提供打包成war的操作。
		throw new UnsupportedOperationException("不支持WAR格式文件压缩操作!");
	}

	@Override
	protected void doDecompress(File srcFile, File destFile,
			boolean needPassword, String password) {
		innerCompressor.doDecompress(srcFile, destFile, false, "");
	}

	@Override
	protected String getSuffix() {
		return ".war";
	}

	@Override
	public boolean isEncryptionSupported() {
		//不支持加密。
		return false;
	}

	@Override
	public boolean isFileEncrypted(File file) {
		//不支持加密。
		return false;
	}

}
