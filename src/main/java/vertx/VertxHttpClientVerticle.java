package vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientResponse;

public class VertxHttpClientVerticle extends AbstractVerticle {
    private HttpClient httpClient = null;

    @Override
    public void start() throws Exception {
        httpClient = vertx.createHttpClient();
    }

    public void getContent() {
        httpClient.getNow(80, "tutorials.jenkov.com", "/", new Handler<HttpClientResponse>() {

            @Override
            public void handle(HttpClientResponse httpClientResponse) {

                httpClientResponse.bodyHandler(new Handler<Buffer>() {
                    @Override
                    public void handle(Buffer buffer) {
                        System.out.println("Response (" + buffer.length() + "): ");
                        System.out.println(buffer.getString(0, buffer.length()));
                    }
                });
            }
        });
    }

}
