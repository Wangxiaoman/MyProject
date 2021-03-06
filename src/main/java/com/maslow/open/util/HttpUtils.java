package com.maslow.open.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

/**
 * http访问的相关工具方法.
 * @author wangxiaoman
 *
 */
public class HttpUtils {
	public static final String USER_AGENT = "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.4) Gecko/20091016 Firefox/3.5.4";
	
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
			e.printStackTrace();
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
				HttpConnectionParams.CONNECTION_TIMEOUT, 30000);
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
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK
					|| response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {
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
}
