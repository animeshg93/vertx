package vertx.db;

import com.couchbase.client.java.Bucket;

public class CouchbaseBucket {
	
	CouchbaseDBCluster cluster;
	private Bucket bucket;
	
	public CouchbaseBucket(){
		cluster = new CouchbaseDBCluster();
		setBucket(cluster.getCluster().openBucket("people", "people"));
	}

	public Bucket getBucket() {
		return bucket;
	}

	public void setBucket(Bucket bucket) {
		this.bucket = bucket;
	}
}
