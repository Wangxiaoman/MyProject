package com.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

/**
 * http访问的相关工具方法.
 * 
 */
public class HttpUtils {
	private static final int BUFFER_SIZE = 1024;
	public static final String USER_AGENT = "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.4) Gecko/20091016 Firefox/3.5.4";

	private static final String ANALYTICS_API_URL = "http://wifi.meituan.com/analytics/api/";

	/**
	 * 抓取指定url的内容.
	 * 
	 * @param url
	 * @param encoding
	 *            网页编码.
	 * @return
	 */
	public static String getContent(String url) {
		return getContent(url, "UTF-8");
	}

	/**
	 * 抓取指定url的内容.
	 * 
	 * @param url
	 * @param encoding
	 *            网页编码.
	 * @return
	 */
	public static String getContent(String url, String encoding) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		httpClient
				.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(
						3, false));
		httpClient.getParams().setIntParameter(
				HttpConnectionParams.CONNECTION_TIMEOUT, 60000);
		httpClient.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT,
				60000);

		String content = getContent(httpClient, url, encoding);
		httpClient.getConnectionManager().shutdown();
		return content;
	}


	public static byte[] getContentBytes(String url) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		httpClient
				.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(
						3, false));
		httpClient.getParams().setIntParameter(
				HttpConnectionParams.CONNECTION_TIMEOUT, 60000);
		httpClient.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT,
				60000);

		byte[] content = getContentBytes(httpClient, url);
		httpClient.getConnectionManager().shutdown();
		return content;
	}

	public static byte[] getContentBytes(DefaultHttpClient httpClient,
			String url) {
		byte[] content = null;
		try {
			HttpGet httpget;
			httpget = new HttpGet(url);
			httpget.setHeader("User-Agent", USER_AGENT);
			HttpResponse response = httpClient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (response.getStatusLine().getStatusCode() == 200) {
				content = EntityUtils.toByteArray(entity);
			}
			EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	public static String getContent(HttpClient httpClient, String url,
			String encoding) {
		String content = null;
		try {
			HttpGet httpget;
			httpget = new HttpGet(url);
			httpget.setHeader("User-Agent", USER_AGENT);
			HttpResponse response = httpClient.execute(httpget);
			HttpEntity entity = response.getEntity();
			content = EntityUtils.toString(entity, encoding);
			EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();;
		}
		return content;
	}

	public static String getContent(DefaultHttpClient httpClient, String url,
			String encoding) {
		String content = null;
		try {
			HttpGet httpget;
			httpget = new HttpGet(url);
			httpget.setHeader("User-Agent", USER_AGENT);
			HttpResponse response = httpClient.execute(httpget);
			HttpEntity entity = response.getEntity();
			content = EntityUtils.toString(entity, encoding);
			EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	public static String getContent(DefaultHttpClient httpClient, String url,
			String encoding, Map<String, String> headers) {
		String content = null;
		try {
			HttpGet httpget;
			httpget = new HttpGet(url);
			httpget.setHeader("User-Agent", USER_AGENT);
			for (String key : headers.keySet()) {
				httpget.setHeader(key, headers.get(key));
			}
			HttpResponse response = httpClient.execute(httpget);
			HttpEntity entity = response.getEntity();
			content = EntityUtils.toString(entity, encoding);
			EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	/**
	 * 用于手动处理重定向.
	 * 
	 * @param url
	 * @return
	 */
	public static String getRedirectUrl(String url) {
		return getRedirectUrl(url, null);
	}

	/**
	 * 用于手动处理重定向.
	 * 
	 * @param url
	 * @return
	 */
	public static String getRedirectUrl(String url, String referer) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		httpClient
				.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(
						3, false));
		httpClient.getParams().setIntParameter(
				HttpConnectionParams.CONNECTION_TIMEOUT, 15000);
		httpClient.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT,
				15000);

		try {
			url = getRedirectUrl(httpClient, url, referer);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return url;
	}

	/**
	 * 用于手动处理重定向.
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String getRedirectUrl(DefaultHttpClient httpClient, String url)
			throws IOException {
		return getRedirectUrl(httpClient, url, null);
	}

	/**
	 * 用于手动处理重定向.
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String getRedirectUrl(DefaultHttpClient httpClient,
			String url, String referer) throws IOException {
		return getRedirectUrl(httpClient, url, referer, null);
	}

	/**
	 * 用于手动处理重定向.
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String getRedirectUrl(DefaultHttpClient httpClient,
			String url, String referer, String cookieStr) throws IOException {
		httpClient.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS,
				false);

		HttpGet httpget = new HttpGet(url);
		try {
			httpget.setHeader("User-Agent", USER_AGENT);
			if (StringUtils.isNotEmpty(referer)) {
				httpget.setHeader("Referer", referer);
			}
			if (StringUtils.isNotEmpty(cookieStr)) {
				httpget.setHeader("Cookie", cookieStr);
			}
			HttpResponse response = httpClient.execute(httpget);
			int code = response.getStatusLine().getStatusCode();
			if (code == 302 || code == 301) {
				url = response.getFirstHeader("Location").getValue();
			}
		} catch (IOException e) {
			throw e;
		} finally {
			httpget.abort();
		}
		return url;
	}

	/**
	 * 下載文件.
	 * 
	 * @param filepath
	 * @param url
	 * @throws IOException
	 */
	public static long getFile(String filepath, String url) throws IOException {
		try {
			return getFile(filepath, url, null);
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * 下載文件.
	 * 
	 * @param filepath
	 * @param url
	 * @param referer
	 * @throws IOException
	 */
	public static long getFile(String filepath, String url, String referer)
			throws IOException {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		httpClient
				.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(
						3, false));
		httpClient.getParams().setIntParameter(
				HttpConnectionParams.CONNECTION_TIMEOUT, 60000);
		httpClient.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT,
				60000);
		try {
			return getFile(httpClient, filepath, url, referer);
		} catch (IOException e) {
			throw e;
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
	}

	/**
	 * 下載文件.
	 * 
	 * @param httpClient
	 * @param filepath
	 * @param url
	 * @param referer
	 * @throws IOException
	 */
	public static long getFile(DefaultHttpClient httpClient, String filepath,
			String url, String referer) throws IOException {
		File dir = new File(filepath).getParentFile();
		if (!dir.exists()) {
			dir.mkdirs();
		}
		OutputStream out = new FileOutputStream(filepath);
		try {
			return getFile(httpClient, out, url, referer);
		} catch (IOException e) {
			throw e;
		} finally {
			out.close();
		}
	}

	/**
	 * 下載文件.
	 * 
	 * @param httpClient
	 * @param out
	 * @param url
	 * @param referer
	 * @throws IOException
	 */
	public static long getFile(DefaultHttpClient httpClient, OutputStream out,
			String url, String referer) throws IOException {
		HttpGet httpget;
		httpget = new HttpGet(url);
		httpget.setHeader("User-Agent", USER_AGENT);
		if (StringUtils.isNotEmpty(referer)) {
			httpget.setHeader("Referer", referer);
		}
		HttpResponse response = httpClient.execute(httpget);
		InputStream in = response.getEntity().getContent();
		int len = 0;
		long size = 0;
		byte[] b = new byte[BUFFER_SIZE];
		while ((len = in.read(b)) != -1) {
			out.write(b, 0, len);
			size += len;
		}
		return size;
	}

	/**
	 * post 数据到指定地址，并获取返回结果.
	 * 
	 * @throws IOException
	 */
	public static String postContent(String url, Map<String, String> data,
			String encoding) throws IOException {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		// 重试
		httpClient
				.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(
						3, false));
		// 超时
		httpClient.getParams().setIntParameter(
				HttpConnectionParams.CONNECTION_TIMEOUT, 10000);
		httpClient.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT,
				10000);
		HttpPost httppost = new HttpPost(url);
		try {
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (String key : data.keySet()) {
				nvps.add(new BasicNameValuePair(key, data.get(key)));
			}
			httppost.setHeader("User-Agent",
					"Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.2)");
			httppost.setHeader("Referer", url);
			httppost.getParams().setBooleanParameter(
					CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
			httppost.setEntity(new UrlEncodedFormEntity(nvps, encoding));
			HttpResponse response = httpClient.execute(httppost);
			return EntityUtils.toString(response.getEntity(), encoding);

		} catch (IOException e) {
			throw e;
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
	}

	public static Map<String, Object> photoGarbageIdsMap(String str) {
		Map<String, Object> map = new HashMap<String, Object>();
		String s[] = str.split(",");
		for (String ss : s) {
			map.put(ss.split(":")[0], ss.split(":")[1]);
		}
		return map;
	}

	public static String getRedirectedUrl(String url) {
		String tmpurl = null;
		while (true) {
			tmpurl = getRedirectUrl(url, null);
			System.out.println("tmpurl:" + tmpurl);
			if (url.equals(tmpurl)) {
				break;
			} else {
				url = tmpurl;
			}
		}
		return url;
	}

	/**
	 * 抓取指定url的内容.
	 * 
	 * @param url
	 * @param encoding
	 *            网页编码.
	 * @return
	 */
	public static String getContentWithHeader(String url,
			Map<String, String> header, String encoding) throws Exception {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		httpClient
				.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(
						3, false));
		httpClient.getParams().setIntParameter(
				HttpConnectionParams.CONNECTION_TIMEOUT, 60000);
		httpClient.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT,
				60000);
		String content = null;
		try {
			HttpGet httpget;
			httpget = new HttpGet(url);
			httpget.setHeader("User-Agent", USER_AGENT);
			for (Map.Entry<String, String> entry : header.entrySet()) {
				httpget.setHeader(entry.getKey(), entry.getValue());
			}

			HttpResponse response = httpClient.execute(httpget);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				content = EntityUtils.toString(entity, encoding);
				EntityUtils.consume(entity);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		httpClient.getConnectionManager().shutdown();
		return content;
	}

	/**
	 * post 数据到指定地址，并获取返回结果.
	 * 
	 * @throws IOException
	 */
	public static String postContentWithHeader(String url,
			Map<String, String> data, Map<String, String> header,
			String encoding) throws IOException {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		// 重试
		httpClient
				.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(
						3, false));
		// 超时
		httpClient.getParams().setIntParameter(
				HttpConnectionParams.CONNECTION_TIMEOUT, 10000);
		httpClient.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT,
				10000);
		HttpPost httppost = new HttpPost(url);
		try {
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (String key : data.keySet()) {
				nvps.add(new BasicNameValuePair(key, data.get(key)));
			}
			httppost.setHeader("User-Agent",
					"Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.2)");
			httppost.setHeader("Referer", url);
			for (Map.Entry<String, String> entry : header.entrySet()) {
				httppost.setHeader(entry.getKey(), entry.getValue());
			}

			httppost.getParams().setBooleanParameter(
					CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
			httppost.setEntity(new UrlEncodedFormEntity(nvps, encoding));
			HttpResponse response = httpClient.execute(httppost);
			if (response.getStatusLine().getStatusCode() == 200) {
				return EntityUtils.toString(response.getEntity(), encoding);
			} else {
				return null;
			}

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
	}

	public static Long getMdcPoiIdFromDianpingId(String url) {
		// http://www.dianping.com/shop/3053623
		Long dianPingId = null;

		DefaultHttpClient httpClient = new DefaultHttpClient();
		// 重试
		httpClient
				.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(
						3, false));
		// 超时
		httpClient.getParams().setIntParameter(
				HttpConnectionParams.CONNECTION_TIMEOUT, 10000);
		httpClient.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT,
				10000);

		HttpGet httpget = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = httpClient.execute(httpget);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				dianPingId = Long
						.valueOf(url.substring(url.lastIndexOf("/") + 1));
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}finally {
			httpClient.getConnectionManager().shutdown();
		}

		// 获取mdcPoiId
		String analyticsUrl = ANALYTICS_API_URL + "getMdcPoiIdByDPId/"
				+ dianPingId;
		HttpGet httpgetAnalytics = new HttpGet(analyticsUrl);
		DefaultHttpClient httpClientAnalytics = new DefaultHttpClient();
		Long mdcPoiId = null;

		try {
			response = httpClientAnalytics.execute(httpgetAnalytics);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				String content = EntityUtils.toString(entity, "utf8");
				EntityUtils.consume(entity);
				mdcPoiId = Long.valueOf(content);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}

		return mdcPoiId;
	}

	public static void main(String[] args) {
		String url = "http://www.dianping.com/shop/17458440";
		Long id = getMdcPoiIdFromDianpingId(url);
		System.out.println(id);
	}
}
