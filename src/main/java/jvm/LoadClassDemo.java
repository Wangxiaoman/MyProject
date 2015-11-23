package jvm;
/**
 * 
* @ClassName: A 
* @Description: 
* 对象实例化的时候，静态块初始化调用构造函数，构造函数中又调用了该实例的方法，该类无法加载。
* @author xiaoman 
* @date 2015年11月17日 上午11:22:28 
*
 */
abstract class A{
	public A(){
		list();
	}
	
	public void list(){
		test();
	}
	
	abstract void test();
}

class B extends A{
	
	private final static B instance = new B();
	
	public static B getInstance(){
		return instance;
	}

	@Override
	void test() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		instance.test1();
	}
	
	public void test1(){
		System.out.println("test1");
	}
	
}

public class LoadClassDemo {
	public static void main(String[] args) {
		
		B.getInstance().test();
		
//		new Thread(){
//			public void run() {
//				try {
//					Thread.sleep(300);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				
//				B.getInstance().test();
//			};
//		}.start();
		
	}
}
