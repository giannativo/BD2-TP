package negocio;

import org.bson.Document;
import com.mongodb.Block;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.List;

import modelo.*;

public class Mongo {
	private static Mongo instanciaMongo;
	protected MongoClient mongoClient;
	protected String base;
	
	public static Mongo getInstanciaMongo() {
		if ( instanciaMongo == null ) {
			instanciaMongo = new Mongo();
		}
		return instanciaMongo;
	}
	
	protected Mongo() {	
		CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),fromProviders(PojoCodecProvider.builder().automatic(true).build()));
		MongoClientSettings settings = MongoClientSettings.builder().codecRegistry(pojoCodecRegistry).build();
		MongoClient mongoClient = MongoClients.create(settings);
		this.setMongoClient(mongoClient);
		this.base = "bd2tp";
	}
	
	public MongoClient getMongoClient() {
		return mongoClient;
	}

	public void setMongoClient(MongoClient mongoClient) {
		this.mongoClient = mongoClient;
	}
	
	@SuppressWarnings("deprecation")
	public void mostrar(String colleccion) {
		try {
			MongoDatabase database = this.getMongoClient().getDatabase(this.base);			
			MongoCollection<Document> coll = database.getCollection(colleccion);
			coll.find().forEach(printBlock);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void agregarLocalidad(String colleccion, Localidad localidad) {
		try{
			MongoDatabase database = this.getMongoClient().getDatabase(this.base);
			MongoCollection<Localidad> collection = database.getCollection(colleccion, Localidad.class);
			collection.insertOne(localidad);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void agregarLocalidades(String colleccion, List<Localidad> localidades) {
		try{
			MongoDatabase database = this.getMongoClient().getDatabase(this.base);
			MongoCollection<Localidad> collection = database.getCollection(colleccion, Localidad.class);
			collection.insertMany(localidades);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void agregarDomicilios(String colleccion, List<Domicilio> domicilios) {
		try{
			MongoDatabase database = this.getMongoClient().getDatabase(this.base);
			MongoCollection<Domicilio> collection = database.getCollection(colleccion, Domicilio.class);
			collection.insertMany(domicilios);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Convertir e imprimir document como json
	public Block<Document> printBlock = new Block<Document>() {
	       @Override
	       public void apply(final Document document) {
	           System.out.println(document.toJson());
	       }
	};

	public <E> void agregarDocumentos(String colleccion, List<E> documentos) {
		try{
			MongoDatabase database = this.getMongoClient().getDatabase(this.base);
			MongoCollection<E> collection = database.getCollection(colleccion, E.class);
			collection.insertMany(documentos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
