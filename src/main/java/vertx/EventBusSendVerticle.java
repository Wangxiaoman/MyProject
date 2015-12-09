package vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;

public class EventBusSendVerticle extends AbstractVerticle {

	@Override
    public void start(Future<Void> startFuture) throws InterruptedException {
    	//广播
        vertx.eventBus().publish("anAddress", "message 2");
        
        //verticle依次来执行
        vertx.eventBus().send   ("anAddress", "message 1");
        Thread.sleep(500);
        vertx.eventBus().send   ("anAddress", "message 2");
        Thread.sleep(500);
        vertx.eventBus().send   ("anAddress", "message 3");
        Thread.sleep(500);
        vertx.eventBus().send   ("anAddress", "message 4");
    }
    
    
    public static void main(String[] args) throws InterruptedException {
    	//1、创建一个Vertx事例
    	Vertx vertx = Vertx.vertx();
    	
    	//2、创建Verticle事例
    	EventBusReceiveVerticle r1 = new EventBusReceiveVerticle("R1");
    	EventBusReceiveVerticle r2 = new EventBusReceiveVerticle("R2");
    	
    	//3、部署Verticle
    	vertx.deployVerticle(r1);
    	vertx.deployVerticle(r2);
    	
    	Thread.sleep(1000);
    	
    	EventBusSendVerticle s1 = new EventBusSendVerticle();
    	vertx.deployVerticle(s1);
    	
    	Thread.sleep(1000);
    	vertx.close();
	}
}

class EventBusReceiveVerticle extends AbstractVerticle{
	
	private String name;
	
	public EventBusReceiveVerticle(String name){
		this.name = name;
	}
	
	@Override
	//Verticle实例在被Vertx进行deploy之后，会调用start方法
    public void start(Future<Void> startFuture) {
        System.out.println("EventBusReceiveVerticle started!");
        
        //verticle通过eventBus进行通信
        vertx.eventBus().consumer("anAddress", message -> {
            System.out.println("Name:"+this.name+", received message.body() = " + message.body());
        });
    }

    @Override
    public void stop(Future stopFuture) throws Exception {
        System.out.println("EventBusReceiveVerticle stopped!");
    }
}
