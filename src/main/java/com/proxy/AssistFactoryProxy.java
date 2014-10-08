package com.proxy;

import java.lang.reflect.Method;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;
/**
 * 
 * @author wangxiaoman
 * 工厂创建动态代理例子,和jdk、cglib的方式类似
 *
 */
public class AssistFactoryProxy {
	
	public static void main(String[] args) throws Exception {
		 // 创建代理工厂  
	    ProxyFactory proxyFactory = new ProxyFactory();  

	    // 设置被代理类已实现接口(可选)  
	    // proxyFactory.setInterfaces(new Class[] {});  

	    // 设置被代理类的类型  
	    proxyFactory.setSuperclass(SimpleClass.class);  

	    // 创建代理类型的Class  
	    Class<ProxyObject> proxyClass = proxyFactory.createClass();  

	    // 注意若然父类没有无参构造函数,需要使用createClass方法的重載  
	    // RayTest proxyTest = (RayTest) proxyFactory.create(new Class[] {  
	    // Integer.class }, new Object[] { 0 });  

	    SimpleClass proxyTest = (SimpleClass) proxyClass.newInstance();  

	    ((ProxyObject) proxyTest).setHandler(new MethodHandler() {  

	        // 真实主題  
	    	SimpleClass test = new SimpleClass();  

	        @Override  
	        public Object invoke(Object self, Method thisMethod,  
	                Method proceed, Object[] args) throws Throwable {  
	            String before = "before:";  
	            System.out.println(before);
	            Object str = thisMethod.invoke(test, args);  
	            String after = "after!"; 
	            System.out.println(after);
	            return before + str + after;  
	        }  
	    });
	    
	    proxyTest.execute();
	}
	
}
