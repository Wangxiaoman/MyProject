package compress;
/** 
 *   <B>说       明</B>:配置对象。
 *
 * @author  作  者  名：WuHong<br/>
 *		   E-mail ：wuhong@vrvmail.com.cn
 
 * @version 版   本  号：V1.0<br/>
 *          创建时间：2014年4月24日 上午11:50:13 
 */
public class Config {
	
	/**
	 * 默认配置，不加密。
	 */
	public static final Config DEFAULT_CONFIG = new Config();

	/**
	 * 加密标识，表示是否加密。
	 */
	private boolean encryptFlag = false;
	
	/**
	 * 密码。
	 */
	private String password;
	
	public Config() {
	}
	
	public Config(boolean encryptFlag, String password) {
		super();
		this.encryptFlag = encryptFlag;
		this.password = password;
	}

	public boolean isEncryptFlag() {
		return encryptFlag;
	}

	public void setEncryptFlag(boolean encryptFlag) {
		this.encryptFlag = encryptFlag;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
