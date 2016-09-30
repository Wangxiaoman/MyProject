package hystrix;

import java.util.concurrent.Future;

import rx.Observable;

public class HystrixExecute {
    
    //同步
    private static void sycExecute(){
        System.out.println(new CommandHelloWorld("World").execute());
        System.out.println(new CommandHelloWorld("Bob").execute());
    }
    
    //异步获取
    private static void asycExecute() throws Exception{
        Future<String> fWorld = new CommandHelloWorld("World").queue();
        Future<String> fBob = new CommandHelloWorld("Bob").queue();
        
        System.out.println(fWorld.get());
        System.out.println(fBob.get());
    }
    
    //响应式 异步非阻塞
    private static void asycAndnioExecute(){
        Observable<String> fWorld = new CommandHelloWorld("World").observe();
        Observable<String> fBob = new CommandHelloWorld("Bob").observe();

        fWorld.subscribe((v) -> {
            System.out.println("onNext: " + v);
        }, (exception) -> {
            exception.printStackTrace();
        });
        
        fBob.subscribe((v) -> {
            System.out.println("onNext: " + v);
        }, (exception) -> {
            exception.printStackTrace();
        });
    }
    
    public static void main(String[] args) {
        sycExecute();
        
        try {
            asycExecute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        asycAndnioExecute();
    }
}
