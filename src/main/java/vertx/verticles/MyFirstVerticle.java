package vertx.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import vertx.helper.VertxHTTPHelper;
import vertx.model.Person;
import vertx.model.SuccessResp;

public class MyFirstVerticle extends AbstractVerticle{
	
	VertxHTTPHelper helper = new VertxHTTPHelper();

	public void start(Future<Void> fut) {
		Router router = Router.router(vertx);

		router.route("/").handler(context -> {
			context.response().end("Hello, Vert.x!");
		});

		//very important line. This wraps the whole HTTP request and sets it to the RoutingContext object. Need it for POST and PUT
		router.route().handler(BodyHandler.create());
		router.post("/add/person").handler(routingContext -> {
			helper.postHandler(routingContext);
		});


		vertx
		.createHttpServer()
		.requestHandler(router::accept)
		.listen(
				// Retrieve the port from the configuration,
				// default to 8080.
				config().getInteger("http.port", 8080),
				result -> {
					if (result.succeeded()) {
						fut.complete();
					} else {
						fut.fail(result.cause());
					}
				}
				);
	}
}
