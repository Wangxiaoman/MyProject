package compress;
/** 
 *   <B>说       明</B>:Compressors
 *
 * @author  作  者  名：WuHong<br/>
 *		   E-mail ：wuhong@vrvmail.com.cn
 
 * @version 版   本  号：V1.0<br/>
 *          创建时间：2014年6月6日 下午2:41:56 
 */
public class Compressors {

	public static Compressor zipCompressor(){
		return Compressor.getInstance(CompressFormat.ZIP);
	}
	
	public static Compressor gzipCompressor(){
		return Compressor.getInstance(CompressFormat.GZIP);
	}
	
	public static Compressor rarCompressor(){
		return Compressor.getInstance(CompressFormat.RAR);
	}
	
	public static Compressor sevenzCompressor(){
		return Compressor.getInstance(CompressFormat.SEVENZ);
	}
	
	public static Compressor tarCompressor(){
		return Compressor.getInstance(CompressFormat.TAR);
	}
	
	public static Compressor warCompressor(){
		return Compressor.getInstance(CompressFormat.WAR);
	}
	
	private Compressors(){}
	
}
