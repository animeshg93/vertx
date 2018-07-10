package vertx.helper;

import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;

import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;
import vertx.db.CouchbaseBucket;
import vertx.model.Person;
import vertx.model.SuccessResp;

public class VertxHTTPHelper {
	CouchbaseBucket bucket = new CouchbaseBucket();
	
	public void postHandler(RoutingContext context){
		Person p = Json.decodeValue(context.getBodyAsString(), Person.class);
		JsonObject doc = JsonObject.fromJson(Json.encode(p));
		bucket.getBucket().insert(JsonDocument.create("animesh", doc));
		
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
