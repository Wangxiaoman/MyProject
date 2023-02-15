package com.thread;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {
    public static void test() throws Exception {
        CompletableFuture<String> f = new CompletableFuture<>();
        f.complete("abc");
        System.out.println(f.get());
    }
    
    public static void testRunAsync() throws Exception {
        CompletableFuture.runAsync(() ->{System.out.print("run->");}).thenRun(()->{System.out.println("run");});
        CompletableFuture.runAsync(() ->{System.out.print("run->");}).thenAccept(a -> {System.out.println("accpet:"+(a==null?"null":a));});
        CompletableFuture<String> f = CompletableFuture.runAsync(() ->{System.out.print("run->");}).thenApply(a -> ("accpet:"+(a==null?"null":a.toString())));
        System.out.println(f.get());
    }
    
    public static void testApplyAsync() throws Exception {
        CompletableFuture.supplyAsync(() -> "a").thenRun(()->{System.out.println("supply->thenRun");});
        CompletableFuture.supplyAsync(() -> "a").thenAccept(a -> {System.out.println("supply->accept:" + a);});
        CompletableFuture<String> f = CompletableFuture.supplyAsync(() -> "a").thenApply(a -> ("supply->apply:"+a));
        System.out.println(f.get());
    }
    
    public static void testApplyPip() throws Exception {
        CompletableFuture<String> f = CompletableFuture.supplyAsync(() -> "a").thenApplyAsync(a -> a+"b").thenApplyAsync(b -> b+"c");
        System.out.println(f.get());
    }
    
    public static void testConcurrent() throws Exception {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> "a");
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> "b");
        CompletableFuture<String> f3 = CompletableFuture.supplyAsync(() -> "c");
        
        f1.thenAcceptBothAsync(f2, (a,b) -> {System.out.println("no return:"+a+b);});
        CompletableFuture<String> f = f1.thenCombine(f2,(a,b) -> ("return:"+a+b));
        System.out.println(f.get());
        
        f1.runAfterBoth(f2, () -> {System.out.println("a、b both finish, continue");});
        f1.runAfterEither(f2, () -> {System.out.println("a、b any finish, continue");});
    
        CompletableFuture<Void> ff= CompletableFuture.allOf(f1,f2,f3);
        ff.join();
    }
    
    
    public static void main(String[] args) throws Exception {
        testRunAsync();
        System.out.println("-----------");
        testApplyAsync();
        System.out.println("-----------");
        testApplyPip();
        System.out.println("-----------");
        testConcurrent();
    }
}
