package vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class MyVerticle extends AbstractVerticle {
    private EventBusReceiveVerticle second = new EventBusReceiveVerticle("name");

    @Override
    public void start(Future<Void> startFuture) {
        System.out.println("MyVerticle started!");
        vertx.deployVerticle(second);
    }

    public void stopSecond() throws Exception {
        second.stop();
    }

    @Override
    public void stop(Future stopFuture) throws Exception {
        System.out.println("MyVerticle stopped!");
    }
}
