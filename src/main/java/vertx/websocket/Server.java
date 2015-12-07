//package vertx.websocket;
//
//import io.vertx.core.AbstractVerticle;
//import io.vertx.example.util.Runner;
//
//public class Server extends AbstractVerticle {
//
//	// Convenience method so you can run it in your IDE
//	public static void main(String[] args) {
//		Runner.runExample(Server.class);
//	}
//
//	@Override
//	public void start() throws Exception {
//		vertx.createHttpServer()
//				.websocketHandler(ws -> ws.handler(ws::writeBinaryMessage))
//				.requestHandler(req -> {
//					if (req.uri().equals("/"))
//						req.response().sendFile("ws.html");
//				}).listen(8080);
//	}
//}