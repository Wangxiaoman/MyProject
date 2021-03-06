package compress;
/** 
 *   <B>说       明</B>:没有找到文件压缩器时产生的异常。
 *
 * @author  作  者  名：WuHong<br/>
 *		   E-mail ：wuhong@vrvmail.com.cn
 
 * @version 版   本  号：V1.0<br/>
 *          创建时间：2013年9月29日 下午1:18:29 
 */
public class NoSuchCompressorException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSuchCompressorException() {
		super();
	}

	public NoSuchCompressorException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchCompressorException(String message) {
		super(message);
	}

	public NoSuchCompressorException(Throwable cause) {
		super(cause);
	}
	
}
