package compress;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/** 
 *   <B>说       明</B>:压缩器。
 *   <p>用于文件的压缩和解压。
 *   整合多种压缩形式的实现。如:ZIP、RAR、7Z、TAR等等。
 *   <p>本类用于隐藏具体实现，为压缩和解压文件功能提供统一访问入口。
 *   <p>用法如下:
 *   <pre>
 *   Compressor zipCompressor = Compressor.getInstance(CompressFormat.ZIP);
 *   File srcFile = new File("D:/a/a.txt");
 *   File destFile = new File("D:/a/a.zip");
 *   zipCompressor.compress(srcFile, destFile);
 *   </pre>
 *   
 *   <p>注:GZIP格式压缩器支持单个文件的压缩，所以调用方法compress(File[] srcFiles, File destFile)
 *   及compress(String[] srcFilePaths, String destFilePath)会抛出UnsupportedOperationException异常。
 *   
 *   @see CompressFormat
 *   @see CompressorProvider
 *  
 *
 * @author  作  者  名：WuHong<br/>
 *		   E-mail ：wuhong@vrvmail.com.cn

 * @version 版   本  号：V1.0<br/>
 *          创建时间：2013年9月29日 下午1:13:31 
 */
public abstract class Compressor {
	
	/**
	 * 缓冲区大小。
	 * 子类可使用这个值作为读写流时的缓冲区大小。
	 */
	protected static final int BUFFER_SIZE = 8192;

	/**
	 * 压缩器提供者。
	 */
	private static final CompressorProvider PROVIDER = new DefaultCompressorProvider();

	/**
	 * 根据压缩文件格式，获取相应的压缩器实例。
	 * 
	 * @param compressFormat 压缩格式。
	 * @return
	 *      压缩格式对应的压缩器实例。
	 * @throws IllegalArgumentException 如果文件压缩格式为空。
	 * @throws NoSuchCompressorException 如果没有找到相应格式的压缩器实例。
	 */
	public static Compressor getInstance(CompressFormat compressFormat){
		Assert.notNull(compressFormat,"文件压缩格式不能为空!");
		return PROVIDER.getCompressor(compressFormat.getName());
	}
	
	private static final Map<String, CompressFormat> CF_HOLDER;
	
	static{
		Map<String, CompressFormat> map = new HashMap<String, CompressFormat>(8, 1.0f);
		map.put("gz", CompressFormat.GZIP);
		map.put("rar", CompressFormat.RAR);
		map.put("7z", CompressFormat.SEVENZ);
		map.put("tar", CompressFormat.TAR);
		map.put("zip", CompressFormat.ZIP);
		map.put("war", CompressFormat.WAR);
		CF_HOLDER = Collections.unmodifiableMap(map);
	}
	
	/**
	 * 通过文件名称猜测来确定压缩器实例。
	 * 
	 * @param fileName 压缩文件名称。
	 * @return
	 *      与压缩文件名称相关的压缩器实例。
	 * @throws IllegalArgumentException 如果文件压缩格式为空。
	 * @throws NoSuchCompressorException 如果没有找到相应格式的压缩器实例。
	 */
	public static Compressor guessInstance(String fileName){
		Assert.isNotBlank(fileName, "文件名不能为空!");
		//获取文件后缀。
		if(fileName.indexOf(".") == -1){
			throw new IllegalArgumentException("文件["+fileName+"]不是压缩文件或者不是当前所支持的压缩文件类型!");
		}
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		suffix = suffix.toLowerCase();
		//查找相应的实例。
		CompressFormat compressFormat = CF_HOLDER.get(suffix);
		if(compressFormat == null){
			throw new IllegalArgumentException("文件["+fileName+"]不是压缩文件或者不是当前所支持的压缩文件类型!");
		}
		return PROVIDER.getCompressor(compressFormat.getName());
	}
	
	/**
	 * <p>将原始文件压缩到当前目录下。
	 * <p>压缩文件名称与原始文件名称相同。
	 * @param srcFile 要进行压缩的原始文件。
	 * @throws IllegalArgumentException 如果参数不合法。
	 * @throws IllegalStateException 如果压缩过程中出现任何异常。
	 */
	public void compress(File srcFile){
		Assert.notNull(srcFile, "要进行压缩的原始文件不能为空!");
		String fileName = getFileName(srcFile);
		String destFilePath = srcFile.getParent() + "/" + fileName + getSuffix();
		File destFile = new File(destFilePath);
		compress(srcFile, destFile);
	}

	/**
	 * 将原始文件压缩到当前目录下。
	 * 
	 * @param srcFilePath 要进行压缩的原始文件路径。
	 * @throws IllegalArgumentException 如果参数不合法。
	 * @throws IllegalStateException 如果压缩过程中出现任何异常。
	 */
	public void compress(String srcFilePath){
		Assert.isNotBlank(srcFilePath, "要进行压缩的原始文件路径不能为空!");
		compress(new File(srcFilePath));
	}

	/**
	 * <p>将原始文件压缩到指定目标文件。
	 * 
	 * @param srcFile 要进行压缩的原始文件。
	 * @param destFile 要压缩到的目标文件。
	 * @throws IllegalArgumentException 如果参数不合法。
	 * @throws IllegalStateException 如果压缩过程中出现任何异常。
	 */
	public void compress(File srcFile, File destFile){
		compress(srcFile, destFile, null);
	}
	
	/**
	 * <p>将原始文件压缩到指定目标文件。
	 * 
	 * @param srcFile 要进行压缩的原始文件。
	 * @param destFile 要压缩到的目标文件。
	 * @param config 配置。
	 * @throws IllegalArgumentException 如果参数不合法。
	 * @throws IllegalStateException 如果压缩过程中出现任何异常。
	 */
	public void compress(File srcFile, File destFile, Config config){
		compress(new File[]{srcFile}, destFile, config);
	}

	/**
	 * <p>将原始文件压缩到指定目标文件。
	 * 
	 * @param srcFilePath 要进行压缩的原始文件路径。
	 * @param destFilePath 要压缩到的目标文件。
	 * @throws IllegalArgumentException 如果参数不合法。
	 * @throws IllegalStateException 如果压缩过程中出现任何异常。
	 */
	public void compress(String srcFilePath, String destFilePath){
		Assert.isNotBlank(srcFilePath, "要进行压缩的原始文件路径不能为空!");
		Assert.isNotBlank(destFilePath, "要压缩到的目标文件路径不能为空!");
		File srcFile = new File(srcFilePath);
		File destFile = new File(destFilePath);
		compress(srcFile, destFile, null);
	}

	/**
	 * <p>将多个原始文件压缩到指定目标文件。
	 * 
	 * @param srcFiles 要进行压缩的原始文件。
	 * @param destFile 要压缩到的目标文件。
	 * @throws IllegalArgumentException 如果参数不合法。
	 * @throws IllegalStateException 如果压缩过程中出现任何异常。
	 */
	public void compress(File[] srcFiles, File destFile){
		compress(srcFiles, destFile, null);
	}
	
	/**
	 * <p>将多个原始文件压缩到指定目标文件。
	 * 
	 * @param srcFiles 要进行压缩的原始文件。
	 * @param destFile 要压缩到的目标文件。
	 * @param config 配置。
	 * @throws IllegalArgumentException 如果参数不合法。
	 * @throws IllegalStateException 如果压缩过程中出现任何异常。
	 */
	public void compress(File[] srcFiles, File destFile, Config config){
		if(config == null){
			config = Config.DEFAULT_CONFIG;
		}else{
			//验证。
			if(config.isEncryptFlag() && !isEncryptionSupported()){
				throw new IllegalArgumentException("类型"+this.getClass().getName()+"不支持加密压缩解压文件!");
			}
			if(config.isEncryptFlag() && StringUtils.isBlank(config.getPassword())){
				throw new IllegalArgumentException("加密压缩文件时,密码不能为空!");
			}
		}
		compress(srcFiles, destFile, config.isEncryptFlag(), config.getPassword());
	}

	/**
	 * <p>将多个原始文件压缩到指定目标文件中。
	 * 
	 * @param srcFilePaths 要进行压缩的原始文件路径。
	 * @param destFilePath 要压缩到的目标文件路径。
	 * @throws IllegalArgumentException 如果参数不合法。
	 * @throws IllegalStateException 如果压缩过程中出现任何异常。
	 */
	public void compress(String[] srcFilePaths, String destFilePath){
		Assert.notEmpty(srcFilePaths, "要进行压缩的原始文件路径数组不能为空!");
		File[] srcFiles = new File[srcFilePaths.length];
		for(int i=0; i<srcFiles.length; i++){
			Assert.isNotBlank(srcFilePaths[i], "要进行压缩的原始文件路径不能为空!");
			srcFiles[i] = new File(srcFilePaths[i]);
		}
		Assert.isNotBlank(destFilePath, "要压缩到的目标文件不能为空!");
		File destFile = new File(destFilePath);
		compress(srcFiles, destFile, null);
	}

	/**
	 * 将多个原始文件压缩到指定目标文件中。
	 * 
	 * @param srcFiles 要进行压缩的原始文件。
	 * @param destFile 要压缩到的目标文件。
	 * @param needPassword 是否需要密码。(给压缩文件添加密码)
	 * @param password 密码。
	 * @throws IllegalArgumentException 如果参数不合法。
	 * @throws IllegalStateException 如果压缩过程中发生任何异常。
	 */
	private void compress(File[] srcFiles, File destFile, boolean needPassword, String password){
		Assert.notEmpty(srcFiles, "要进行压缩的原始文件不能为空!");
		Assert.notNull(destFile, "要压缩到的目标文件不能为空!");
		for(File srcFile : srcFiles){
			if(srcFile == null){
				throw new IllegalArgumentException("要进行压缩的原始文件不能为空!");
			}
			if(!srcFile.exists()){
				throw new IllegalArgumentException("要进行压缩的原始文件["+srcFile.getAbsolutePath()+"]不存在!");
			}
		}
		//如果目标文件已存在，抛出异常。
		if(destFile.exists()){
			throw new IllegalStateException("要压缩到的目标文件["+destFile.getAbsolutePath()+"]已经存在!");
		}
		doCompress(srcFiles, destFile, needPassword, password);
	}

	/**
	 * 将多个原始文件压缩到指定目标文件下。
	 * 
	 * @param srcFiles 要进行压缩的原始文件。
	 * @param destFile 要压缩到的目标文件。
	 * @param needPassword 是否需要密码。(给压缩文件添加密码)
	 * @param password 密码。
	 * @throws IllegalArgumentException 如果参数不合法。
	 * @throws IllegalStateException 如果解压过程中发生任何异常。
	 */
	protected abstract void doCompress(File[] srcFiles, File destFile, boolean needPassword, String password);

	/**
	 * 将原始文件解压到当前目录下。
	 * 
	 * @param srcFile 需要解压的原始文件。
	 * @throws IllegalArgumentException 如果参数不合法。
	 * @throws IllegalStateException 如果解压过程中发生任何异常。
	 */
	public void decompress(File srcFile){
		Assert.notNull(srcFile, "要解压的压缩文件不能为空!");
		decompress(srcFile, srcFile.getParentFile());
	}
	
	/**
	 * 将原始文件解压到当前目录下。
	 * 
	 * @param srcFilePath 需要解压的原始文件路径。 
	 * @throws IllegalArgumentException 如果参数不合法。
	 * @throws IllegalStateException 如果解压过程中发生任何异常。
	 */
	public void decompress(String srcFilePath){
		Assert.isNotBlank(srcFilePath, "要进行解压的原始文件路径不能为空!");
		decompress(new File(srcFilePath));
	}
	
	/**
	 * 将原始文件解压到指定目录下。
	 * 
	 * @param srcFile 需要解压的原始文件。
	 * @param destDir 解压到的目录文件。
	 * @throws IllegalArgumentException 如果参数不合法。
	 * @throws IllegalStateException 如果解压过程中发生任何异常。
	 */
	public void decompress(File srcFile, File destDir){
		decompress(srcFile, destDir, null);
	}
	
	/**
	 * 将原始文件解压到指定目录下。
	 * 
	 * @param srcFile 需要解压的原始文件。
	 * @param destDir 解压到的目录文件。
	 * @throws IllegalArgumentException 如果参数不合法。
	 * @throws IllegalStateException 如果解压过程中发生任何异常。
	 */
	public void decompress(File srcFile, File destDir, Config config){
		if(config == null){
			config = Config.DEFAULT_CONFIG;
		}else{
			//验证。
			if(config.isEncryptFlag() && !isEncryptionSupported()){
				throw new IllegalArgumentException("类型"+this.getClass().getName()+"不支持加密压缩解压文件!");
			}
			if(config.isEncryptFlag() && StringUtils.isBlank(config.getPassword())){
				throw new IllegalArgumentException("解压加密压缩文件时,密码不能为空!");
			}
		}
		decompress(srcFile, destDir, config.isEncryptFlag(), config.getPassword());
	}
	
	/**
	 * 将原始文件解压到指定目录下。
	 * 
	 * @param srcFilePath 需要解压的原始文件路径。
	 * @param destDirPath 解压到的目录文件路径。
	 * @throws IllegalArgumentException 如果参数不合法。
	 * @throws IllegalStateException 如果解压过程中发生任何异常。
	 */
	public void decompress(String srcFilePath, String destDirPath){
		Assert.isNotBlank(srcFilePath, "要进行解压的原始文件路径不能为空!");
		Assert.isNotBlank(destDirPath, "要解压到的目标目录路径不能为空!");
		File srcFile = new File(srcFilePath);
		File destDir = new File(destDirPath);
		decompress(srcFile, destDir);
	}
	
	/**
	 * 将原始文件解压到指定目录下。
	 * 
	 * @param srcFile 需要解压的原始文件。
	 * @param destDir 解压到的目录文件。
	 * @param needPassword 是否需要密码。
	 * @param password 密码。
	 * @throws IllegalArgumentException 如果参数不合法。
	 * @throws IllegalStateException 如果解压过程中发生任何异常。
	 */
	private void decompress(File srcFile, File destDir, boolean needPassword, String password){
		Assert.notNull(srcFile, "需要解压的原始文件不能为空!");
		Assert.notNull(destDir, "要解压到的目录文件不能为空!");
		if(!srcFile.exists()){
			throw new IllegalArgumentException("要进行解压的原始文件["+srcFile.getAbsolutePath()+"]不存在!");
		}
		if(needPassword){
			Assert.hasLength(password, "解压文件需要的密码不能为空!");
		}
		//如果要解压到的目录不存在，创建该目录。
		if(!destDir.exists()){
			destDir.mkdirs();
		}
		if(!destDir.isDirectory()){
			throw new IllegalArgumentException("要解压到的目录文件["+destDir.getAbsolutePath()+"]必须是一个目录!");
		}
		doDecompress(srcFile, destDir, needPassword, password);
	}
	
	/**
	 * 将原始文件解压到指定目录下。
	 * 
	 * @param srcFile 需要解压的原始文件。
	 * @param destDir 解压到的目录文件。
	 * @param needPassword 是否需要密码。
	 * @param password 密码。
	 * @throws IllegalArgumentException 如果参数不合法。
	 * @throws IllegalStateException 如果解压过程中发生任何异常。
	 */
	protected abstract void doDecompress(File srcFile, File destFile, boolean needPassword, String password);
	
	/**
	 * 获取压缩文件后缀名称。如:'.zip'
	 * 
	 * @return
	 *      压缩文件的后缀名称。
	 */
	protected abstract String getSuffix();
	
	/**
	 * 是否支持加密特性。
	 * 
	 * @return
	 *      是否支持加密特性。
	 */
	public abstract boolean isEncryptionSupported();
	
	/**
	 * 检查一个压缩文件是否为加密的(加密码压缩)压缩文件。
	 * <p>注：由于只有zip、7z、rar格式压缩文件才能进行加密压缩，
	 * 所以除了这3中文件可以判断外，其余格式文件均返回false。
	 * 
	 * @param file 要检查的目标文件。
	 * @return
	 *      如果file是加密的目标压缩文件，返回true；否则返回false；
	 */
	public abstract boolean isFileEncrypted(File file);
	
	/**
	 * 获取文件名称。
	 * 默认不带后缀，子类可覆盖此行为。
	 * 
	 * @param file 文件。
	 * @return
	 *      文件名称
	 */
	protected String getFileName(File file) {
		String name = file.getName();
		int dotIndex = name.indexOf(".");
		if(dotIndex < 0){
			dotIndex = name.length();
		}
		return name.substring(0, dotIndex);
	}
	
}
