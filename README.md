1、jetty
利用java的Main方法直接启动jetty服务，两个参数分别为端口和war包地址

2、动态代理

blog:
http://blog.csdn.net/fenglibing/article/details/7080340

1）jdk
不用依赖其他包，主要是利用jdk中的反射来实现AOP，只能代理接口，不能代理继承类

2）cglib
依赖cglib包，利用类增加的方式。通过一个子类来实现目标类，然后覆盖需要AOP的方法实现代码增强，不局限代理接口.<br>
cglib封装了asm，可以在运行期动态生成新的class

3）javassist
利用动态字节码进行AOP，将原本类直接按照字节码读出，进行改写。如下面的例子，直接将目标类作为一个代理类的属性，然后新增加代理类的方法来实现

    //添加屬性，将原有对象作为新对象的一个属性
		ct.addField(CtField.make("public " + className + " real = new "+ className + "();", ct));
    
    //添加方法，动态调用，实现AOP
		ct.addMethod(CtNewMethod.make("public void execute(){ System.out.println(\"before:\"); "
				+ " real.execute();  System.out.println(\"after!\");}", ct));
