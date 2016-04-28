package http;

import java.util.Map;

/**
 * HTTP请求配置
 * 
 * POST请求时参数的提交方式有两种：表单方式提交和字符串方式提交。表单提交时参数通过parameters传递，字符串方式提交时，
 * 参数通过entityString传递
 * 
 * @author zhangjiayu
 *
 */
public class Request {

	/**** HTTP请求方式 ****/
	public static final String METHOD_GET = "GET";
	public static final String METHOD_POST = "POST";
	public static final String METHOD_PUT = "PUT";
	public static final String METHOD_DELETE = "DELETE";

	/**** POST请求参数提交方式 ****/
	// public static final int POST_FORM_ENTITY = 0; // 表单方式提交
	// public static final int POST_STRING_ENTITY = 1; // 字符串方式提交

	/** 请求的url */
	private String url = null;

	/** 请求方式 */
	private String method = METHOD_POST;

	/** POST请求时参数的提交方式，默认为表单提交。表单提交时参数通过parameters传递，字符串方式提交时，参数通过entityString传递 */
	// private int entityPostMethod = 0;

	private int socketTimeout = 0;

	private int connectionTimeout = 0;

	private int connectionReqTimeout = 0;

	/** 参数键值对 */
	private Map<String, String> parameters = null;

	/** 当POST方式中表单提交方式为StringEntity时，此参数不能为空 */
	private String entityString;

	/** 请求头 */
	private Map<String, String> headers = null;

	String cookies = null;

	/** 编码方式 */
	private String charset = "UTF-8";

	/** 是否使用连接池 */
	private boolean usingPoolClient = false;

	/** 是否是HTTPS请求 */
	private boolean httpsRequest = false;

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCookies() {
		return cookies;
	}

	public void setCookies(String cookies) {
		this.cookies = cookies;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public int getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public int getConnectionReqTimeout() {
		return connectionReqTimeout;
	}

	public void setConnectionReqTimeout(int connectionReqTimeout) {
		this.connectionReqTimeout = connectionReqTimeout;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	// public int getEntityPostMethod() {
	// return entityPostMethod;
	// }
	//
	// public void setEntityPostMethod(int entityPostMethod) {
	// this.entityPostMethod = entityPostMethod;
	// }

	public boolean isUsingPoolClient() {
		return usingPoolClient;
	}

	public void setUsingPoolClient(boolean usingPoolClient) {
		this.usingPoolClient = usingPoolClient;
	}

	public boolean isHttpsRequest() {
		return httpsRequest;
	}

	public void setHttpsRequest(boolean httpsRequest) {
		this.httpsRequest = httpsRequest;
	}

	public String getEntityString() {
		return entityString;
	}

	public void setEntityString(String entityString) {
		this.entityString = entityString;
	}

}
