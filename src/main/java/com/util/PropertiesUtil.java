package com.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
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
            String filePath = PropertiesUtil.class.getResource("/").getPath()+fileName;
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
