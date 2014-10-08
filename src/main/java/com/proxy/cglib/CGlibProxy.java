package com.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import com.proxy.Simple;
import com.proxy.SimpleClass;

/**
 * 
 * @author wangxiaoman
 *	cglib的原理和Assist的工厂方式类似，实现一个原目标类的一个子类，然后重写子类中的需要AOP的方法
 *	覆盖其中方法实现增强，但因为采用的是继承，所以不能对final修饰的类进行代理
 *
 */
public class CGlibProxy implements MethodInterceptor{
	private Object target;  
	  
    /** 
     * 创建代理对象 
     *  
     * @param target 
     * @return 
     */  
    public Object getInstance(Object target) {  
        this.target = target;  
        Enhancer enhancer = new Enhancer();  
        enhancer.setSuperclass(this.target.getClass());  
        // 回调方法  
        enhancer.setCallback(this);  
        // 创建代理对象  
        return enhancer.create();  
    }  
  
    @Override  
    // 回调方法  
    public Object intercept(Object obj, Method method, Object[] args,  
            MethodProxy proxy) throws Throwable {  
        System.out.println("cglib 事务开始");  
        proxy.invokeSuper(obj, args);  
        System.out.println("cglib 事务结束");  
        return null;  
    }  
    
    public static void main(String[] args) {
    	CGlibProxy cg = new CGlibProxy();
    	Simple s = (Simple) cg.getInstance(new SimpleClass());
    	s.execute();
	}
}
