package io.vertx.blog.first;

import io.vertx.blog.model.Person;
import io.vertx.blog.model.SuccessResp;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class MyFirstVerticle extends AbstractVerticle{


	@SuppressWarnings("unused")
	public void start(Future<Void> fut) {
		Router router = Router.router(vertx);

		router.route("/").handler(context -> {
			context.response().end("Hello, Vert.x!");
		});

		//very important line. This wraps the whole HTTP request and sets it to the RoutingContext object. Need it for POST and PUT
		router.route().handler(BodyHandler.create());
		router.post("/add/person").handler(routingContext -> {
			postHandler(routingContext);
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

	private void postHandler(RoutingContext context){
		Person p = Json.decodeValue(context.getBodyAsString(), Person.class);
		context.response()
		.setStatusCode(200)
		.putHeader("Content-type", "application/json;charset=UTF-8")
		.end(constuctSuccessResponse(p));
	}

	private String constuctSuccessResponse(Person person){
		SuccessResp resp = new SuccessResp();
		resp.setStatusCd("00");
		resp.setStatusDesc("Sucess");
		resp.setStatusDetail(person.getName()+" "+person.getLastName()+" has been added successfully.");
		return Json.encode(resp);
	}
}
