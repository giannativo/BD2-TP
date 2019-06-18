package negocio;

import com.mongodb.client.MongoClients;
import org.bson.Document;
import com.mongodb.Block;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Mongo {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		try {
			MongoClient mongoClient = MongoClients.create();
			MongoDatabase database= mongoClient.getDatabase("local");
			MongoCollection<Document> coll = database.getCollection("startup_log");
			coll.find(
					
					).forEach(printBlock);
			mongoClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static Block<Document> printBlock = new Block<Document>() {
	       @Override
	       public void apply(final Document document) {
	           System.out.println(document.toJson());
	       }
	};
}
