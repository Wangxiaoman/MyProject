package com.maslow.open.sdk;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import com.maslow.open.util.CommonUtil;
import com.maslow.open.util.HttpUtils;
import com.maslow.open.util.SHA1Util;



public class DemoApiTool {
	
	public static String getData(String url,String appkey,String secret, Map<String, String> paramMap){
		String sign = CommonUtil.getSign(appkey, secret, paramMap);
		paramMap.put("sign", sign);
		paramMap.put("appkey", appkey);
		try {
			return HttpUtils.postContent(url, paramMap, "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 直接获取数据，不通过封装好的接口
	 * @param url
	 * @param appkey
	 * @param secret
	 * @param paramMap
	 * @return
	 */
	public static String getData(){
		//1、appkey、secret、url
		String url = "http://channel.maslow.cn/api/test";
		
		String appkey = "7897123";
		String secret = "f2ef00892980764e25999408359e597d";
		
		
		//2、组织参数
		Map<String, String> paramMap = new HashMap<String,String>();
		paramMap.put("param1", "param1");
		paramMap.put("param2", "param2");
		paramMap.put("param3", "param3");
		
		//3、获取sign
		StringBuilder stringBuilder = new StringBuilder();  
		  
		// 对参数名进行字典排序  
		String[] keyArray = paramMap.keySet().toArray(new String[0]);  
		Arrays.sort(keyArray);  
		// 拼接有序的参数名-值串  
		stringBuilder.append(appkey);  
		for (String key : keyArray)  
		{  
		    stringBuilder.append(key).append(paramMap.get(key));  
		}  
		String codes = stringBuilder.append(secret).toString();  
		String sign = SHA1Util.toSHA1(codes).toUpperCase();
		
		
		//4、把appkey和sign放到map中
		paramMap.put("appkey", appkey);
		paramMap.put("sign", sign);
		
		//5、通过httpclient发送数据接口
		
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
			for (String key : paramMap.keySet()) {
				nvps.add(new BasicNameValuePair(key, paramMap.get(key)));
			}
			httppost.setHeader("User-Agent",
					"Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.2)");
			httppost.setHeader("Referer", url);
			httppost.getParams().setBooleanParameter(
					CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
			httppost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
			HttpResponse response = httpClient.execute(httppost);
			return EntityUtils.toString(response.getEntity(), "utf-8");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		
		return null;
	}
	
	
	public static void main(String[] args) {
		System.out.println(getData());
	}
	
	
	
	
}
