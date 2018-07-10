package vertx.db;

import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;

public class CouchbaseEnvironment {
	
	private  DefaultCouchbaseEnvironment env;

	public  CouchbaseEnvironment(){
		env = DefaultCouchbaseEnvironment
				.builder()
				.build();
	}

	public DefaultCouchbaseEnvironment getEnv() {
		return env;
	}

	public void setEnv(DefaultCouchbaseEnvironment env) {
		this.env = env;
	}
}
