package com.proxy;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtNewConstructor;
import javassist.CtNewMethod;

/**
 * 
 * @author wangxiaoman
 * 使用动态字节生成动态代理
 *
 */
public class AssistDymicProxy {
	public static void main(String[] args) throws Exception {
		//创建类池
		ClassPool cp = new ClassPool(true);
		
		String className = SimpleClass.class.getName();
		
		CtClass ct = cp.makeClass(className+"JavassitProxy");
		// 添加接口,可选  
        // ctClass.addInterface(classPool.get(RayTestInterface.class.getName())); 
		
		//添加父类
		ct.setSuperclass(cp.get(SimpleClass.class.getName()));
		//添加构造方法
		ct.addConstructor(CtNewConstructor.defaultConstructor(ct));
		
		//添加屬性，将原有对象作为新对象的一个属性
		ct.addField(CtField.make("public " + className + " real = new "+ className + "();", ct));
		//添加方法，动态调用，实现AOP
		
		ct.addMethod(CtNewMethod.make("public void execute(){ System.out.println(\"before:\");  real.execute();  System.out.println(\"after!\");}", ct));
	
		Class<SimpleClass> proxyClass = ct.toClass();
		
		//构建实例，执行方法
		proxyClass.newInstance().execute();
	}
}
