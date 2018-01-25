package com;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
	
    public static void main( String[] args ){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        
        Iterator<String> iter = list.iterator();
        while(iter.hasNext()){
//            if(iter.next().equals("1")){
            iter.remove();
//            }
        }
        
//        for(String s : list){
//            list.remove(s);
//        }
        
        
    }
    
    public static void test() {  
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();    
        for (int i = 0; i < urls.length; i++) {    
            System.out.println(urls[i].toExternalForm());    
        }   
    }
    
    
}
