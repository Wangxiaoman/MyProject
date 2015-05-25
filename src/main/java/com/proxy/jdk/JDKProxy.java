package com.proxy.jdk;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import sun.misc.ProxyGenerator;

import com.proxy.Simple;
import com.proxy.SimpleClass;

/**
 * 
 * @author wangxiaoman
 *	利用jdk的代理实现Aop，但是只能代理接口
 *
 */
public class JDKProxy implements InvocationHandler {
	private Object target;  
    /** 
     * 绑定委托对象并返回一个代理类 
     * @param target 
     * @return 
     */  
    public Object bind(Object target) {  
    	
        this.target = target;  
        //取得代理对象  
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),  
                target.getClass().getInterfaces(), this);   //要绑定接口(这是一个缺陷，cglib弥补了这一缺陷)  
    }  

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)throws Throwable {
		
		Object result=null;  
        System.out.println("jdk 事务开始");  
        //执行方法  
        result=method.invoke(target, args);
        System.out.println("jdk 事务结束");  
        return result;  
	}
	
	
	public static void main(String[] args) {
		JDKProxy jp = new JDKProxy();
		Simple s = (Simple) jp.bind(new SimpleClass());
		s.execute();
		System.out.println(s.toString());
		writeProxyClassToHardDisk("/Users/wangxiaoman/Documents/$Proxy11.class");
	}
	
	
	
	
	public static void writeProxyClassToHardDisk(String path) {  
        // 第一种方法，这种方式在刚才分析ProxyGenerator时已经知道了  
        // System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", true);  
          
        // 第二种方法  
          
        // 获取代理类的字节码  
        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy11", SimpleClass.class.getInterfaces());  
          
        FileOutputStream out = null;  
          
        try {  
            out = new FileOutputStream(path);  
            out.write(classFile);  
            out.flush();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                out.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    } 
}







