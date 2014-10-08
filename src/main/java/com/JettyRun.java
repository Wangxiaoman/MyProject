package com;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;

public class JettyRun {
	
	public static void main(String[] args) throws Exception {  
        // 实例化一个容器  
        Server server = new Server();  
  
        // 配置一个connector  
        Connector connector = new SelectChannelConnector();  
        connector.setPort(Integer.getInteger("jetty.port",8081).intValue());  
        server.setConnectors(new Connector[]  
        { connector });  
  
        String war = "/Users/wangxiaoman/workspace/maslow_web/target/maslow_web-1.0-SNAPSHOT.war";  
        String path = "/";  
  
        System.err.println(war + " " + path);  
  
        // 配置一个handler，这个handler会解析war包哦  
        WebAppContext webapp = new WebAppContext();  
        webapp.setContextPath(path);  
        webapp.setWar(war);
        server.setHandler(webapp);  
  
        // 启动容器，默认启动一个线程池。  
        server.start();  
        server.join();  
 } 
}
