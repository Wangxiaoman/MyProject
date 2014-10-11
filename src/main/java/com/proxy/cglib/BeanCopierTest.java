package com.proxy.cglib;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;
import net.sf.cglib.core.DebuggingClassWriter;

/**
 * 
 * @author wangxiaoman
 * 允许同名，不同类型的属性进行拷贝，比如Date对象到String属性
 * BeanCopier：适用两个Pojo Bean之间，所有属性的全复制，两边的source和target的属性可以不一致,以setter方法为准，调用getter方法获取数据。
 * 
 */
public class BeanCopierTest {  
	  
    public static void main(String args[]) {  
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/wangxiaoman/1");  
        BeanCopier copier = BeanCopier.create(Source.class, Target.class, true);  
        Source from = new Source();  
        from.setValue(1);
        from.setDay(new Date());
  
        Target to = new Target();  
        Converter converter = new BigIntConverter();  
        copier.copy(from, to, converter); //使用converter类  
  
        System.out.println(to.getValue());  
        System.out.println(to.getDay());
    }  
}  
  
class BigIntConverter implements net.sf.cglib.core.Converter {  
  
    @Override  
    public Object convert(Object value, Class target, Object context) {  
        System.out.println(value.getClass() + " " + value); // from类中的value对象  
        System.out.println(target); // to类中的定义的参数对象  
        System.out.println(context.getClass() + " " + context); // String对象,具体的方法名  
        if (target.isAssignableFrom(BigInteger.class)) {  
            return new BigInteger(value.toString());  
        }else if(target.isAssignableFrom(String.class)){
        	SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        	return df.format(value);
        } else {  
            return value;  
        }  
    }  
} 

class Source{
	private int value;
	private Date day;
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
}

class Target{
	private int value;
	private String day;
	
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}
