package vertx.db;

import com.couchbase.client.java.CouchbaseCluster;

public class CouchbaseDBCluster {

	private  CouchbaseCluster cluster;
	CouchbaseEnvironment env;

	public CouchbaseDBCluster(){
		env = new CouchbaseEnvironment();
		cluster = CouchbaseCluster.create(env.getEnv(), "127.0.0.1");
	}

	public  CouchbaseCluster getCluster() {
		return cluster;
	}


	public void setCluster(CouchbaseCluster cluster) {
		this.cluster = cluster;
	}
}
