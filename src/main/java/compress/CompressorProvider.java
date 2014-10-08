package compress;
/** 
 *   <B>说       明</B>:压缩器提供者。
 *
 * @author  作  者  名：WuHong<br/>
 *		   E-mail ：wuhong@vrvmail.com.cn
 
 * @version 版   本  号：V1.0<br/>
 *          创建时间：2013年9月29日 下午1:15:08 
 */
interface CompressorProvider {

	/**
	 * 通过标识(压缩格式)获取压缩器。
	 * 
	 * @param key 压缩器标识。
	 * @return
	 *      相应的压缩器。
	 * @throws IllegalArgumentException 如果key为空。
	 * @throws NoSuchCompressorException 如果根据key找不到对应的压缩器实例。
	 */
	Compressor getCompressor(String key);
	
}
