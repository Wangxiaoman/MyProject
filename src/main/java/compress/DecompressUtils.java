package compress;

import java.io.File;

/** 
 *   <B>说       明</B>:解压工具类。
 *   <p>提供便利方法,内部通过{@link Compressor}进行解压处理。
 *
 * @author  作  者  名：WuHong<br/>
 *		   E-mail ：wuhong@vrvmail.com.cn
 
 * @version 版   本  号：V1.0<br/>
 *          创建时间：2014年5月31日 下午2:20:10 
 *          
 * @see Compressor
 */
public class DecompressUtils {
	
	/**
	 * 解压文件。
	 * <p>该方法根据要进行解压的文件名称来猜测合适的压缩器进行文件解压。
	 * 
	 * @param srcFilePath 要进行解压的源文件路径。
	 * @param destDirPath 要解压到的目标目录路径。
	 * 
	 * @throws IllegalArgumentException 如果参数不合法。
	 * @throws NoSuchCompressorException 如果根据srcFile文件名称没有找到相关的文件压缩器。
	 */
	public static void decompress(String srcFilePath, String destDirPath){
		decompress(srcFilePath, destDirPath, null);
	}
	
	/**
	 * 解压文件。
	 * <p>该方法根据要进行解压的文件名称来猜测合适的压缩器进行文件解压。
	 * 
	 * @param srcFilePath 要进行解压的源文件路径。
	 * @param destDirPath 要解压到的目标目录路径。
	 * @param config 配置。 {@link Config}
	 * 
	 * @throws IllegalArgumentException 如果参数不合法。
	 * @throws NoSuchCompressorException 如果根据srcFile文件名称没有找到相关的文件压缩器。
	 */
	public static void decompress(String srcFilePath, String destDirPath, Config config){
		Assert.isNotBlank(srcFilePath, "要进行解压的文件路径不能为空!");
		Assert.isNotBlank(destDirPath, "要解压到的目标目录路径不能为空!");
		decompress(new File(srcFilePath), new File(destDirPath), config);
	}
	
	/**
	 * 解压文件。
	 * <p>该方法根据要进行解压的文件名称来猜测合适的压缩器进行文件解压。
	 * 
	 * @param srcFile 要进行解压的源文件。
	 * @param destDir 要解压到的目标目录。
	 * 
	 * @throws IllegalArgumentException 如果参数不合法。
	 * @throws NoSuchCompressorException 如果根据srcFile文件名称没有找到相关的文件压缩器。
	 */
	public static void decompress(File srcFile, File destDir){
		decompress(srcFile, destDir, null);
	}
	
	/**
	 * 解压文件。
	 * <p>该方法根据要进行解压的文件名称来猜测合适的压缩器进行文件解压。
	 * 
	 * @param srcFile 要进行解压的源文件。
	 * @param destDir 要解压到的目标目录。
	 * @param config 配置。 {@link Config}
	 * 
	 * @throws IllegalArgumentException 如果参数不合法。
	 * @throws NoSuchCompressorException 如果根据srcFile文件名称没有找到相关的文件压缩器。
	 */
	public static void decompress(File srcFile, File destDir, Config config){
		Assert.notNull(srcFile, "要进行解压的文件不能为null!");
		Assert.notNull(destDir, "要解压到的目标目录不能为null!");
		Compressor compressor = Compressor.guessInstance(srcFile.getName());
		compressor.decompress(srcFile, destDir, config);
	}

	private DecompressUtils(){}
	
}
