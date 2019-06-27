package negocio;

import org.bson.Document;
import com.mongodb.Block;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

//import jdk.nashorn.internal.runtime.regexp.RegExp;

import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import static com.mongodb.client.model.Filters.*;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static java.util.Arrays.asList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
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
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0001-")))
					  )
				).forEach(mostrarDetalleVenta);		
			System.out.println("\nDetalle de ventas Sucursal 2");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0002-")))
					  )
				).forEach(mostrarDetalleVenta);		
			System.out.println("\nDetalle de ventas Sucursal 3");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0003-")))
					  )
				).forEach(mostrarDetalleVenta);		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void detallePorObraSocialYPorSucursalEntreFechas(Date fecha1, Date fecha2) {
		try {
			MongoDatabase database = this.getMongoClient().getDatabase(this.base);			
			MongoCollection<Document> coll = database.getCollection("venta");
			System.out.println("\nDetalle de ventas Sucursal 1");
			System.out.println("\nPami:");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0001-"), eq("cliente.obraSocial.nombre","Pami")))
					  )
				).forEach(mostrarDetalleVenta);
			System.out.println("\nOsde:");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0001-"), eq("cliente.obraSocial.nombre","Osde")))
					  )
				).forEach(mostrarDetalleVenta);
			System.out.println("\nGaleno:");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0001-"), eq("cliente.obraSocial.nombre","Galeno")))
					  )
				).forEach(mostrarDetalleVenta);	
			System.out.println("\nPrivados:");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0001-"), eq("cliente.obraSocial.nombre",null)))
					  )
				).forEach(mostrarDetalleVenta);		
			System.out.println("\nDetalle de ventas Sucursal 2");
			System.out.println("\nPami:");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0002-"), eq("cliente.obraSocial.nombre","Pami")))
					  )
				).forEach(mostrarDetalleVenta);
			System.out.println("\nOsde:");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0002-"), eq("cliente.obraSocial.nombre","Osde")))
					  )
				).forEach(mostrarDetalleVenta);
			System.out.println("\nGaleno:");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0002-"), eq("cliente.obraSocial.nombre","Galeno")))
					  )
				).forEach(mostrarDetalleVenta);	
			System.out.println("\nPrivados:");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0002-"), eq("cliente.obraSocial.nombre",null)))
					  )
				).forEach(mostrarDetalleVenta);		
			System.out.println("\nDetalle de ventas Sucursal 3");
			System.out.println("\nPami:");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0003-"), eq("cliente.obraSocial.nombre","Pami")))
					  )
				).forEach(mostrarDetalleVenta);
			System.out.println("\nOsde:");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0003-"), eq("cliente.obraSocial.nombre","Osde")))
					  )
				).forEach(mostrarDetalleVenta);
			System.out.println("\nGaleno:");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0003-"), eq("cliente.obraSocial.nombre","Galeno")))
					  )
				).forEach(mostrarDetalleVenta);	
			System.out.println("\nPrivados:");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0003-"), eq("cliente.obraSocial.nombre",null)))
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
	public void detallePorObraSocialEntreFechas(Date fecha1, Date fecha2) {
		try {
			MongoDatabase database = this.getMongoClient().getDatabase(this.base);			
			MongoCollection<Document> coll = database.getCollection("venta");
			System.out.println("\nDetalle de ventas de Pami");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), eq("cliente.obraSocial.nombre","Pami")))
					  )
				).forEach(mostrarDetalleVenta);	
			System.out.println("\nDetalle de ventas de Osde");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), eq("cliente.obraSocial.nombre","Osde")))
					  )
				).forEach(mostrarDetalleVenta);	
			System.out.println("\nDetalle de ventas de Galeno");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), eq("cliente.obraSocial.nombre","Galeno")))
					  )
				).forEach(mostrarDetalleVenta);	
			System.out.println("\nDetalle de ventas de Privados");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), eq("cliente.obraSocial.nombre",null)))
					  )
				).forEach(mostrarDetalleVenta);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void totalPorSucursalEntreFechas(Date fecha1, Date fecha2) {
		try {
			MongoDatabase database = this.getMongoClient().getDatabase(this.base);			
			MongoCollection<Document> coll = database.getCollection("venta");
			System.out.println("\nTotal por Sucursal");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2))),
							Aggregates.project(
						              Projections.fields(
					                      Projections.excludeId(),
					                      Projections.include("total"),
					                      Projections.computed("sucursal", new Document("$substr", asList("$nroTicket", 0, 4)))
						            	)
						              ),
						    Aggregates.group("$sucursal", Accumulators.sum("total", "$total")),
						    Aggregates.sort(Sorts.ascending("_id"))
							)
				).forEach(mostrarTotalVentaSucursal);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void totalPorObraSocialYPorSucursalEntreFechas(Date fecha1, Date fecha2) {
		try {
			MongoDatabase database = this.getMongoClient().getDatabase(this.base);			
			MongoCollection<Document> coll = database.getCollection("venta");
			System.out.println("\nTotal por Sucursal. ObraSocial Pami");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), eq("cliente.obraSocial.nombre","Pami"))),
							Aggregates.project(
						              Projections.fields(
					            		  Projections.excludeId(),
					                      Projections.include("total"),
					                      Projections.computed("sucursal", new Document("$substr", asList("$nroTicket", 0, 4)))
						            	)
						              ),
						    Aggregates.group("$sucursal", Accumulators.sum("total", "$total")),
						    Aggregates.sort(Sorts.ascending("_id"))
							)
				).forEach(mostrarTotalVentaSucursal);	
			System.out.println("\nTotal por Sucursal. ObraSocial Osde");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), eq("cliente.obraSocial.nombre","Osde"))),
							Aggregates.project(
						              Projections.fields(
					            		  Projections.excludeId(),
					                      Projections.include("total"),
					                      Projections.computed("sucursal", new Document("$substr", asList("$nroTicket", 0, 4)))
						            	)
						              ),
						    Aggregates.group("$sucursal", Accumulators.sum("total", "$total")),
						    Aggregates.sort(Sorts.ascending("_id"))
							)
				).forEach(mostrarTotalVentaSucursal);	
			System.out.println("\nTotal por Sucursal. ObraSocial Galeno");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), eq("cliente.obraSocial.nombre","Galeno"))),
							Aggregates.project(
						              Projections.fields(
					            		  Projections.excludeId(),
					                      Projections.include("total"),
					                      Projections.computed("sucursal", new Document("$substr", asList("$nroTicket", 0, 4)))
						            	)
						              ),
						    Aggregates.group("$sucursal", Accumulators.sum("total", "$total")),
						    Aggregates.sort(Sorts.ascending("_id"))
							)
				).forEach(mostrarTotalVentaSucursal);	
			System.out.println("\nTotal por Sucursal. Sin ObraSocial (privados)");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), eq("cliente.obraSocial.nombre",null))),
							Aggregates.project(
						              Projections.fields(
					            		  Projections.excludeId(),
					                      Projections.include("total"),
					                      Projections.computed("sucursal", new Document("$substr", asList("$nroTicket", 0, 4)))
						            	)
						              ),
						    Aggregates.group("$sucursal", Accumulators.sum("total", "$total")),
						    Aggregates.sort(Sorts.ascending("_id"))
							)
				).forEach(mostrarTotalVentaSucursal);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void totalEntreFechas(Date fecha1, Date fecha2) {
		try {
			MongoDatabase database = this.getMongoClient().getDatabase(this.base);			
			MongoCollection<Document> coll = database.getCollection("venta");
			System.out.println("\nTotal cadena");
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
	public void totalPorObraSocialEntre(Date fecha1, Date fecha2) {
		try {
			MongoDatabase database = this.getMongoClient().getDatabase(this.base);			
			MongoCollection<Document> coll = database.getCollection("venta");
			System.out.println("\nTotal Pami");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), eq("cliente.obraSocial.nombre","Pami"))),
							Aggregates.group("", Accumulators.sum("total", "$total")),
							Aggregates.project(
						              Projections.fields(
					                      Projections.excludeId(),
					                      Projections.include("total")					                    
						            	)
						              )						    
							)
				).forEach(mostrarTotalVenta);	
			System.out.println("\nTotal Osde");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), eq("cliente.obraSocial.nombre","Osde"))),
							Aggregates.group("", Accumulators.sum("total", "$total")),
							Aggregates.project(
						              Projections.fields(
					                      Projections.excludeId(),
					                      Projections.include("total")					                    
						            	)
						              )						    
							)
				).forEach(mostrarTotalVenta);	
			System.out.println("\nTotal Galeno");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), eq("cliente.obraSocial.nombre","Galeno"))),
							Aggregates.group("", Accumulators.sum("total", "$total")),
							Aggregates.project(
						              Projections.fields(
					                      Projections.excludeId(),
					                      Projections.include("total")					                    
						            	)
						              )						    
							)
				).forEach(mostrarTotalVenta);	
			System.out.println("\nTotal Privados");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), eq("cliente.obraSocial.nombre",null))),
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

	
	@SuppressWarnings("deprecation")
	public void traerVentasEntreFechasPorSucursal(String codigoSucursal, Date fecha1, Date fecha2) {
		try {
			MongoDatabase database = this.getMongoClient().getDatabase(this.base);			
			MongoCollection<Document> coll = database.getCollection("venta");
			Document regQuery = new Document();
			regQuery.append("$regex", "^(?i)"+Pattern.quote(codigoSucursal));
			regQuery.append("$options", "i");
			Document findQuery = new Document();
			findQuery.append("nroTicket", regQuery);
			coll.find(
					and(findQuery, gte("fecha",fecha1), lte("fecha",fecha2))
				).forEach(printBlock);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
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
	// Mostrar total sucursal
			public Block<Document> mostrarTotalVentaSucursal = new Block<Document>() {
			       @Override
			       public void apply(final Document document) {
			    	   System.out.println("Sucursal: "+document.get("_id")
			    	   		+ " Total: $ "+document.get("total"));
			       }
			};
			
			
			@SuppressWarnings("deprecation")
			public void totalPorMedioDePagoEntre(Date fecha1, Date fecha2) {
				try {
					MongoDatabase database = this.getMongoClient().getDatabase(this.base);			
					MongoCollection<Document> coll = database.getCollection("venta");
					System.out.println("\nTotal Efectivo");
					coll.aggregate(
							asList(
									Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), eq("tipoPago","efectivo"))),
									Aggregates.group("", Accumulators.sum("total", "$total")),
									Aggregates.project(
								              Projections.fields(
							                      Projections.excludeId(),
							                      Projections.include("total")					                    
								            	)
								              )						    
									)
						).forEach(mostrarTotalVenta);	
					System.out.println("\nTotal Debito");
					coll.aggregate(
							asList(
									Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), eq("tipoPago","debito"))),
									Aggregates.group("", Accumulators.sum("total", "$total")),
									Aggregates.project(
								              Projections.fields(
							                      Projections.excludeId(),
							                      Projections.include("total")					                    
								            	)
								              )						    
									)
						).forEach(mostrarTotalVenta);	
					System.out.println("\nTotal Credito");
					coll.aggregate(
							asList(
									Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), eq("tipoPago","credito"))),
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
			public void detallePorMedioDePagoEntreFechas(Date fecha1, Date fecha2) {
				try {
					MongoDatabase database = this.getMongoClient().getDatabase(this.base);			
					MongoCollection<Document> coll = database.getCollection("venta");
					System.out.println("\nDetalle de ventas en Efectivo");
					coll.aggregate(
							asList(
									Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), eq("tipoPago","efectivo")))
							  )
						).forEach(mostrarDetalleVenta);	
					System.out.println("\nDetalle de ventas con Debito");
					coll.aggregate(
							asList(
									Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), eq("tipoPago","debito")))
							  )
						).forEach(mostrarDetalleVenta);	
					System.out.println("\nDetalle de ventas con Credito");
					coll.aggregate(
							asList(
									Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), eq("tipoPago","credito")))
							  )
						).forEach(mostrarDetalleVenta);		
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
			@SuppressWarnings("deprecation")
			public void totalPorMedioDePagoYPorSucursalEntreFechas(Date fecha1, Date fecha2) {
				try {
					MongoDatabase database = this.getMongoClient().getDatabase(this.base);			
					MongoCollection<Document> coll = database.getCollection("venta");
					System.out.println("\nTotal por Sucursal en Efectivo");
					coll.aggregate(
							asList(
									Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), eq("tipoPago","efectivo"))),
									Aggregates.project(
								              Projections.fields(
							            		  Projections.excludeId(),
							                      Projections.include("total"),
							                      Projections.computed("sucursal", new Document("$substr", asList("$nroTicket", 0, 4)))
								            	)
								              ),
								    Aggregates.group("$sucursal", Accumulators.sum("total", "$total")),
								    Aggregates.sort(Sorts.ascending("_id"))
									)
						).forEach(mostrarTotalVentaSucursal);	
					System.out.println("\nTotal por Sucursal con Debito");
					coll.aggregate(
							asList(
									Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), eq("tipoPago","debito"))),
									Aggregates.project(
								              Projections.fields(
							            		  Projections.excludeId(),
							                      Projections.include("total"),
							                      Projections.computed("sucursal", new Document("$substr", asList("$nroTicket", 0, 4)))
								            	)
								              ),
								    Aggregates.group("$sucursal", Accumulators.sum("total", "$total")),
								    Aggregates.sort(Sorts.ascending("_id"))
									)
						).forEach(mostrarTotalVentaSucursal);	
					System.out.println("\nTotal por Sucursal con Credito");
					coll.aggregate(
							asList(
									Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), eq("tipoPago","credito"))),
									Aggregates.project(
								              Projections.fields(
							            		  Projections.excludeId(),
							                      Projections.include("total"),
							                      Projections.computed("sucursal", new Document("$substr", asList("$nroTicket", 0, 4)))
								            	)
								              ),
								    Aggregates.group("$sucursal", Accumulators.sum("total", "$total")),
								    Aggregates.sort(Sorts.ascending("_id"))
									)
						).forEach(mostrarTotalVentaSucursal);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			@SuppressWarnings("deprecation")
			public void detallePorMedioDePagoYPorSucursalEntreFechas(Date fecha1, Date fecha2) {
				try {
					MongoDatabase database = this.getMongoClient().getDatabase(this.base);			
					MongoCollection<Document> coll = database.getCollection("venta");
					System.out.println("\nDetalle de ventas Sucursal 1");
					System.out.println("\nEfectivo:");
					coll.aggregate(
							asList(
									Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0001-"), eq("tipoPago","efectivo")))
							  )
						).forEach(mostrarDetalleVenta);
					System.out.println("\nDebito:");
					coll.aggregate(
							asList(
									Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0001-"), eq("tipoPago","debito")))
							  )
						).forEach(mostrarDetalleVenta);
					System.out.println("\nCredito:");
					coll.aggregate(
							asList(
									Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0001-"), eq("tipoPago","credito")))
							  )
						).forEach(mostrarDetalleVenta);			
					System.out.println("\nDetalle de ventas Sucursal 2");
					System.out.println("\nEfectivo:");
					coll.aggregate(
							asList(
									Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0002-"), eq("tipoPago","efectivo")))
							  )
						).forEach(mostrarDetalleVenta);
					System.out.println("\nDebito:");
					coll.aggregate(
							asList(
									Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0002-"), eq("tipoPago","debito")))
							  )
						).forEach(mostrarDetalleVenta);
					System.out.println("\nCredito:");
					coll.aggregate(
							asList(
									Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0002-"), eq("tipoPago","credito")))
							  )
						).forEach(mostrarDetalleVenta);	
					System.out.println("\nDetalle de ventas Sucursal 3");
					System.out.println("\nEfectivo:");
					coll.aggregate(
							asList(
									Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0003-"), eq("tipoPago","efectivo")))
							  )
						).forEach(mostrarDetalleVenta);
					System.out.println("\nDebito:");
					coll.aggregate(
							asList(
									Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0003-"), eq("tipoPago","debito")))
							  )
						).forEach(mostrarDetalleVenta);
					System.out.println("\nCredito:");
					coll.aggregate(
							asList(
									Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0003-"), eq("tipoPago","credito")))
							  )
						).forEach(mostrarDetalleVenta);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

}
