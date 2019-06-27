package negocio;

import org.bson.Document;
import com.mongodb.Block;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Projections;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import static com.mongodb.client.model.Filters.*;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static java.util.Arrays.asList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
		Logger.getLogger("org.mongodb.driver").setLevel(Level.OFF);
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
	
	@SuppressWarnings("deprecation")
	public void mostrarFechasVentas() {
		try {
			MongoDatabase database = this.getMongoClient().getDatabase(this.base);			
			MongoCollection<Document> coll = database.getCollection("venta");
			coll.find().forEach(mostrarFecha);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void fechasEntre(Date fecha1, Date fecha2) {
		try {
			MongoDatabase database = this.getMongoClient().getDatabase(this.base);			
			MongoCollection<Document> coll = database.getCollection("venta");
			coll.find(
					and(gte("fecha",fecha1), lte("fecha",fecha2))
				).forEach(mostrarFecha);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void detallePorSucursalEntreFechas(Date fecha1, Date fecha2) {
		try {
			MongoDatabase database = this.getMongoClient().getDatabase(this.base);			
			MongoCollection<Document> coll = database.getCollection("venta");
			System.out.println("\nDetalle de ventas Sucursal 1");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0001")))
					  )
				).forEach(mostrarDetalleVenta);		
			System.out.println("\nDetalle de ventas Sucursal 2");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0002")))
					  )
				).forEach(mostrarDetalleVenta);		
			System.out.println("\nDetalle de ventas Sucursal 3");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0003")))
					  )
				).forEach(mostrarDetalleVenta);		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void detalleEntreFechas(Date fecha1, Date fecha2) {
		try {
			MongoDatabase database = this.getMongoClient().getDatabase(this.base);			
			MongoCollection<Document> coll = database.getCollection("venta");
			System.out.println("\nDetalle de todas las ventas");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2)))
					  )
				).forEach(mostrarDetalleVenta);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void totalEntreFechas(Date fecha1, Date fecha2) {
		try {
			MongoDatabase database = this.getMongoClient().getDatabase(this.base);			
			MongoCollection<Document> coll = database.getCollection("venta");
			System.out.println("\nTotal de las ventas de la cadena");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2))),
							Aggregates.group("", Accumulators.sum("total", "$total")),
							Aggregates.project(
						              Projections.fields(
						                    Projections.excludeId(),
						                    Projections.include("total")
						              )
						         )
					  )
				).forEach(mostrarTotalVenta);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void totalPorSucursalEntreFechas(Date fecha1, Date fecha2) {
		try {
			MongoDatabase database = this.getMongoClient().getDatabase(this.base);			
			MongoCollection<Document> coll = database.getCollection("venta");
			System.out.println("\nTotal de las ventas de la Sucursal 1");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0001"))),
							Aggregates.group("", Accumulators.sum("total", "$total")),
							Aggregates.project(
						              Projections.fields(
						                    Projections.excludeId(),
						                    Projections.include("total")
						              )
						         )
					  )
				).forEach(mostrarTotalVenta);	
			System.out.println("\nTotal de las ventas de la Sucursal 2");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0002"))),
							Aggregates.group("", Accumulators.sum("total", "$total")),
							Aggregates.project(
						              Projections.fields(
						                    Projections.excludeId(),
						                    Projections.include("total")
						              )
						         )
					  )
				).forEach(mostrarTotalVenta);
			System.out.println("\nTotal de las ventas de la Sucursal 3");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0003"))),
							Aggregates.group("", Accumulators.sum("total", "$total")),
							Aggregates.project(
						              Projections.fields(
						                    Projections.excludeId(),
						                    Projections.include("total")
						              )
						         )
					  )
				).forEach(mostrarTotalVenta);	
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
	
	public void agregarVentas(String colleccion, List<Venta> ventas) {
		try{
			MongoDatabase database = this.getMongoClient().getDatabase(this.base);
			MongoCollection<Venta> collection = database.getCollection(colleccion, Venta.class);
			collection.insertMany(ventas);
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
	// Mostrar solo fecha en formato Date
	public Block<Document> mostrarFecha = new Block<Document>() {
	       @Override
	       public void apply(final Document document) {
	           System.out.println(document.get("fecha"));
	       }
	};
	// Mostrar detalle venta
		public Block<Document> mostrarDetalleVenta = new Block<Document>() {
		       @Override
		       public void apply(final Document document) {
		    	   System.out.println(
		        		   "NroTicket: "+document.get("nroTicket") +
		        		   " Fecha: "+document.get("fecha") +
		        		   " Total: $ "+document.get("total")
		    			   );
		       }
		};
	// Mostrar total 
			public Block<Document> mostrarTotalVenta = new Block<Document>() {
			       @Override
			       public void apply(final Document document) {
			    	   System.out.println("Total: $ "+document.get("total"));
			       }
			};

}
