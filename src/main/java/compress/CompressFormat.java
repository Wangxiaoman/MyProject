package compress;
/** 
 *   <B>说       明</B>:压缩格式。
 *
 * @author  作  者  名：WuHong<br/>
 *		   E-mail ：wuhong@vrvmail.com.cn
 
 * @version 版   本  号：V1.0<br/>
 *          创建时间：2013年9月29日 下午1:31:58 
 */
public enum CompressFormat {
	
	ZIP("zip"),
	GZIP("gzip"),
	RAR("rar"),
	SEVENZ("7z"),
	TAR("tar"),
	WAR("war");

	/**
	 * 压缩格式名称。
	 */
	private String name;
	
	private CompressFormat(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
