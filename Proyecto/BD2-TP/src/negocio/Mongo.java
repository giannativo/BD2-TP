package negocio;

import com.mongodb.client.MongoClients;
import org.bson.Document;
import com.mongodb.Block;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Mongo {
	private static Mongo instanciaMongo;
	protected MongoClient mongoClient;
	
	public static Mongo getInstanciaMongo() {
		if ( instanciaMongo == null ) {
			instanciaMongo = new Mongo();
		}
		return instanciaMongo;
	}
	
	protected Mongo() {
		MongoClient mongoClient = MongoClients.create();
		this.setMongoClient(mongoClient);
	}
	
	public MongoClient getMongoClient() {
		return mongoClient;
	}

	public void setMongoClient(MongoClient mongoClient) {
		this.mongoClient = mongoClient;
	}

	@SuppressWarnings("deprecation")
	public void mostrar() {
		try {
			MongoDatabase database = this.getMongoClient().getDatabase("local");
			MongoCollection<Document> coll = database.getCollection("startup_log");
			coll.find().forEach(printBlock);
			mongoClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Block<Document> printBlock = new Block<Document>() {
	       @Override
	       public void apply(final Document document) {
	           System.out.println(document.toJson());
	       }
	};
}
