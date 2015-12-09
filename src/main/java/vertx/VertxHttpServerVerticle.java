package vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

public class VertxHttpServerVerticle extends AbstractVerticle {

	private HttpServer httpServer = null;

	@Override
	public void start() throws Exception {
		httpServer = vertx.createHttpServer();

		httpServer.requestHandler(new Handler<HttpServerRequest>() {
			@Override
			public void handle(HttpServerRequest request) {
				System.out.println("incoming request!");
				HttpServerResponse response = request.response();
				response.setStatusCode(200);
				response.headers()
				    .add("Content-Length", String.valueOf(57))
				    .add("Content-Type", "text/html");

				System.out.println(request.uri());
				if(request.path().equals("/test")){
					response.write("Vert.x is alive test!");
				}else{
					System.out.println(request.path());
					System.out.println(request.params().get("name"));
	
					if (request.method() == HttpMethod.POST) {
						request.handler(new Handler<Buffer>() {
							@Override
							public void handle(Buffer buffer) {
								
							}
						});
					}else{
						response.write("Vert.x is alive!");
					}
				}
				
				response.end();
			}
		});

		httpServer.listen(9800);
	};

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new VertxHttpServerVerticle());
	}
}
