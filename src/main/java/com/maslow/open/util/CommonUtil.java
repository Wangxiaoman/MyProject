package com.maslow.open.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

/**
 * 
 * @author wangxiaoman
 * 通用方法
 */
public class CommonUtil {
	
	/**
	 * 根据appkey、secret、参数生成sgin
	 * @param appKey
	 * @param secret
	 * @param paramMap
	 * @return
	 * 
	 * sha1(appkey+params+secret)
	 */
	public static String getSign(String appkey,String secret,Map<String,String> paramMap){
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
		
		return sign;
	}
	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public static Properties getPropetiesByName(String fileName){
		Properties pro = null;
		FileInputStream fis = null;
        try {
            pro = new Properties();
            String filePath = CommonUtil.class.getResource("/").getPath()+fileName;
            fis = new FileInputStream(filePath);
            pro.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
            pro = null;
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return pro;
	}
}
