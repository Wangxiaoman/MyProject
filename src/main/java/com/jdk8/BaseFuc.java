/**
 * 
 */
package com.jdk8;

import java.sql.Date;
import java.time.Clock;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import io.netty.util.internal.chmv8.ForkJoinPool;

/**
 * @author linkedme
 *
 */
public class BaseFuc {
    public static void main(String[] args) {
        defaultInter();
        sort();
        funcInter();
        InnerfuncInter();
        showTime();
        pool();
    }
    
    // 1、default 接口实现
    public static void defaultInter(){
        Math math = new Math(){
            @Override
            public int add(int a, int b) {
                return a + b;
            }
        };
        
        System.out.println(math.sub(2, 1));
        System.out.println(math.add(2, 1));
    }
    
    // 2、 lambda表达式
    public static void sort(){
        List<String> list = Arrays.asList("a","n","b","d","c","t");
        
        Collections.sort(list,(a,b)->a.compareTo(b));
        System.out.println(list);
    }
    
    
    // 3、 函数式接口实现
    public static void funcInter(){
        Convert<String,Integer> convertInteger = Integer::valueOf;
        System.out.println(convertInteger.convert("1234"));
        Convert<String,Double> convertDouble = Double::valueOf;
        System.out.println(convertDouble.convert("12.34"));
    }
    
    // 4、内置函数式接口
    public static void InnerfuncInter(){
        Predicate<String> predicate = s -> s.length() > 5;
        System.out.println(predicate.test("1234"));
        System.out.println(predicate.test("123456"));
        
        Function<String, Integer> funcToInteger = Integer::valueOf;
        System.out.println(funcToInteger.apply("1234"));
        Function<String,Integer> funcWith = funcToInteger.andThen(Integer::valueOf).andThen(Power::power2);
        System.out.println(funcWith.apply("1234"));
        
        Supplier<Person> personSupplier = Person::new;
        System.out.println(personSupplier.get());
        
        Consumer<Person> consumer = p -> System.out.println("hello:"+p.getName());
        consumer.accept(personSupplier.get());
        
    }
    
    // 5、时间
    public static void showTime(){
        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock.millis());
        Instant instant = clock.instant();
        System.out.println(Date.from(instant));
    }
    
    // 6、并行流
    public static void pool(){
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        System.out.println(commonPool.getParallelism());
        System.out.println(commonPool.getPoolSize());
        
        Arrays.asList("a1", "a2", "b1", "c2", "c1")
        .parallelStream() // 利用ForkJoinPool来执行
        .filter(s -> {
            System.out.format("filter: %s [%s]\n",
                s, Thread.currentThread().getName());
            return true;
        })
        .map(s -> {
            System.out.format("map: %s [%s]\n",
                s, Thread.currentThread().getName());
            return s.toUpperCase();
        })
        .forEach(s -> System.out.format("forEach: %s [%s]\n",
            s, Thread.currentThread().getName()));
    }
}






// 1、接口中包含default实现
interface Math{
    int add(int a,int b);
    default int sub(int a,int b){
        return a - b;
    }
}

//3、函数式接口定义
@FunctionalInterface
interface Convert<F, T> {
 T convert(F from);
}

class Power{
    static int power2(int a){
        return a*a;
    }
}

class Person{
    private String name;
    public Person(){this.name = "default";}
    public Person(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}