package com.maslow.open.util;

import java.security.MessageDigest;
import java.util.Date;

/**
 * 
 * @author wangxiaoman
 * 1、根据appkey生成secret
 * 
 */
public class ServerAppUtil {
	public static final int APPKEY_BEGIN = 7897123;//appkey的起始值
	
	/**
	 * 根据appkey、channelId、time字符串连接到一起,做md5
	 * @param appkey
	 * @param channelId
	 * @return
	 */
	public static String getSecret(int appkey,int channelId){
		long time = new Date().getTime();
		String data = ""+appkey+time+channelId;
		
		try {
			return stringToMD5(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * 字符串MD5加密
	 * 
	 * @param s
	 * @return
	 */
	public static String stringToMD5(String str) {

		try {
			byte[] strTemp = str.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			return toHexString(mdTemp.digest());
		} catch (Exception e) {
			return null;
		}
	}
	
	private static String toHexString(byte[] md) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		int j = md.length;
		char str[] = new char[j * 2];
		for (int i = 0; i < j; i++) {
			byte byte0 = md[i];
			str[2 * i] = hexDigits[byte0 >>> 4 & 0xf];
			str[i * 2 + 1] = hexDigits[byte0 & 0xf];
		}
		return new String(str);
	}
	
	public static void main(String[] args) {
		System.out.println(getSecret(7897123,13));
	}
}
