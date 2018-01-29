package com;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.alibaba.fastjson.util.Base64;

/**
 * Hello world!
 *
 */
public class App {
	
    public static void main( String[] args ) throws UnsupportedEncodingException{
        String decodeStr = "WmnWiQADI+F7jEpgW5IA8pAGg9wISh/OztJI1A==";
        fast(decodeStr);
        System.out.println("---------------------");
        base64(decodeStr);
        System.out.println("---------------------");
        String test = "100";
        String encodeStr = java.util.Base64.getEncoder().encodeToString(test.getBytes());
        System.out.println("encodeStr:"+encodeStr);
        fast(encodeStr);
    }
    
    private static void fast(String decodeStr) throws UnsupportedEncodingException{
        byte[] decodedBytes = Base64.decodeFast(decodeStr);
        String[] chars = {"gbk", "iso8859-1", "utf-8","utf8", "ascii", "gb2312"};
        for(String charSet : chars){
            System.out.println("decodedBytes " + new String(decodedBytes,charSet));
        }
    }
    
    private static void base64(String decodeStr) throws UnsupportedEncodingException{
        byte[] decodedBytes = java.util.Base64.getDecoder().decode(decodeStr.getBytes());
        String[] chars = {"gbk", "unicode", "iso8859-1", "utf-8","utf8", "ascii", "gb2312"};
        for(String charSet : chars){
            System.out.println("decodedBytes " + new String(decodedBytes,charSet));
        }
    }
    
    
    public static void test() {  
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();    
        for (int i = 0; i < urls.length; i++) {    
            System.out.println(urls[i].toExternalForm());    
        }   
    }
    
    
}
