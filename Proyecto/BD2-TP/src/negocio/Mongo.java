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
	public void totalPorObraSocialEntreFechas(Date fecha1, Date fecha2) {
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
	
	@SuppressWarnings("deprecation")
	public void totalPorTipoDeProductoEntreFechas(Date fecha1, Date fecha2) {
		try {
			MongoDatabase database = this.getMongoClient().getDatabase(this.base);			
			MongoCollection<Document> coll = database.getCollection("venta");
			System.out.println("\nTotal por tipo de producto");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2))),
							Aggregates.unwind("$lstItems"),
							Aggregates.project(
						              Projections.fields(
					                      Projections.excludeId(),
										  Projections.include("lstItems.precio"),
										  Projections.include("lstItems.producto.tipoProducto")
						              )),
							Aggregates.group("$lstItems.producto.tipoProducto", Accumulators.sum("total", "$lstItems.precio"))
					)).forEach(mostrarTotalProducto);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void detallePorTipoDeProductoEntreFechas(Date fecha1, Date fecha2) {
		try {
			MongoDatabase database = this.getMongoClient().getDatabase(this.base);			
			MongoCollection<Document> coll = database.getCollection("venta");
			System.out.println("\nDetalle de ventas con tipo de producto: farmacia");
			coll.aggregate(
					asList(
							Aggregates.unwind("$lstItems"),
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), eq("lstItems.producto.tipoProducto","farmacia"))),
							Aggregates.project(
						              Projections.fields(
					                      Projections.excludeId(),
										  Projections.include("nroTicket"),
										  Projections.include("lstItems.producto.precio"),
										  Projections.include("lstItems.producto.descripcion"),
										  Projections.include("lstItems.producto.tipoProducto"),
										  Projections.include("lstItems.cantidad")
						              ))
					)).forEach(printBlock);	
			System.out.println("\nDetalle de ventas con tipo de producto: perfumeria");
			coll.aggregate(
					asList(
							Aggregates.unwind("$lstItems"),
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), eq("lstItems.producto.tipoProducto","perfumeria"))),
							Aggregates.project(
						              Projections.fields(
					                      Projections.excludeId(),
										  Projections.include("nroTicket"),
										  Projections.include("lstItems.producto.precio"),
										  Projections.include("lstItems.producto.descripcion"),
										  Projections.include("lstItems.producto.tipoProducto"),
										  Projections.include("lstItems.cantidad")
						              ))
					)).forEach(printBlock);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void totalPorTipoDeProductoYPorSucursalEntreFechas(Date fecha1, Date fecha2) {
		try {
			MongoDatabase database = this.getMongoClient().getDatabase(this.base);			
			MongoCollection<Document> coll = database.getCollection("venta");
			System.out.println("\nTotal Sucursal 1 por tipo de producto");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0001-"))),
							Aggregates.unwind("$lstItems"),
							Aggregates.project(
						              Projections.fields(
					                      Projections.excludeId(),
										  Projections.include("lstItems.precio"),
										  Projections.include("lstItems.producto.tipoProducto")
						              )),
							Aggregates.group("$lstItems.producto.tipoProducto", Accumulators.sum("total", "$lstItems.precio"))
					)).forEach(mostrarTotalProducto);
			System.out.println("\nTotal Sucursal 2 por tipo de producto");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0002-"))),
							Aggregates.unwind("$lstItems"),
							Aggregates.project(
						              Projections.fields(
					                      Projections.excludeId(),
										  Projections.include("lstItems.precio"),
										  Projections.include("lstItems.producto.tipoProducto")
						              )),
							Aggregates.group("$lstItems.producto.tipoProducto", Accumulators.sum("total", "$lstItems.precio"))
					)).forEach(mostrarTotalProducto);	
			System.out.println("\nTotal Sucursal 3 por tipo de producto");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0003-"))),
							Aggregates.unwind("$lstItems"),
							Aggregates.project(
						              Projections.fields(
					                      Projections.excludeId(),
										  Projections.include("lstItems.precio"),
										  Projections.include("lstItems.producto.tipoProducto")
						              )),
							Aggregates.group("$lstItems.producto.tipoProducto", Accumulators.sum("total", "$lstItems.precio"))
					)).forEach(mostrarTotalProducto);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void detallePorTipoDeProductoYPorSucursalEntreFechas(Date fecha1, Date fecha2) {
		try {
			MongoDatabase database = this.getMongoClient().getDatabase(this.base);			
			MongoCollection<Document> coll = database.getCollection("venta");
//			Sucursal 1
			System.out.println("\nDetalle de ventas Sucursal 1 con tipo de producto: farmacia");
			coll.aggregate(
					asList(
							Aggregates.unwind("$lstItems"),
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), eq("lstItems.producto.tipoProducto","farmacia"),regex("nroTicket","0001-"))),
							Aggregates.project(
						              Projections.fields(
					                      Projections.excludeId(),
										  Projections.include("nroTicket"),
										  Projections.include("lstItems.producto.precio"),
										  Projections.include("lstItems.producto.descripcion"),
										  Projections.include("lstItems.producto.tipoProducto"),
										  Projections.include("lstItems.cantidad")
						              ))
					)).forEach(printBlock);	
			System.out.println("\nDetalle de ventas Sucursal 1 con tipo de producto: perfumeria");
			coll.aggregate(
					asList(
							Aggregates.unwind("$lstItems"),
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), eq("lstItems.producto.tipoProducto","perfumeria"),regex("nroTicket","0001-"))),
							Aggregates.project(
						              Projections.fields(
					                      Projections.excludeId(),
										  Projections.include("nroTicket"),
										  Projections.include("lstItems.producto.precio"),
										  Projections.include("lstItems.producto.descripcion"),
										  Projections.include("lstItems.producto.tipoProducto"),
										  Projections.include("lstItems.cantidad")
						              ))
					)).forEach(printBlock);	
			
//			Sucursal 2
			System.out.println("\nDetalle de ventas Sucursal 2 con tipo de producto: farmacia");
			coll.aggregate(
					asList(
							Aggregates.unwind("$lstItems"),
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), eq("lstItems.producto.tipoProducto","farmacia"),regex("nroTicket","0002-"))),
							Aggregates.project(
						              Projections.fields(
					                      Projections.excludeId(),
										  Projections.include("nroTicket"),
										  Projections.include("lstItems.producto.precio"),
										  Projections.include("lstItems.producto.descripcion"),
										  Projections.include("lstItems.producto.tipoProducto"),
										  Projections.include("lstItems.cantidad")
						              ))
					)).forEach(printBlock);	
			System.out.println("\nDetalle de ventas Sucursal 2 con tipo de producto: perfumeria");
			coll.aggregate(
					asList(
							Aggregates.unwind("$lstItems"),
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), eq("lstItems.producto.tipoProducto","perfumeria"),regex("nroTicket","0002-"))),
							Aggregates.project(
						              Projections.fields(
					                      Projections.excludeId(),
										  Projections.include("nroTicket"),
										  Projections.include("lstItems.producto.precio"),
										  Projections.include("lstItems.producto.descripcion"),
										  Projections.include("lstItems.producto.tipoProducto"),
										  Projections.include("lstItems.cantidad")
						              ))
					)).forEach(printBlock);	
//			Sucursal 3
			System.out.println("\nDetalle de ventas Sucursal 3 con tipo de producto: farmacia");
			coll.aggregate(
					asList(
							Aggregates.unwind("$lstItems"),
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), eq("lstItems.producto.tipoProducto","farmacia"),regex("nroTicket","0003-"))),
							Aggregates.project(
						              Projections.fields(
					                      Projections.excludeId(),
										  Projections.include("nroTicket"),
										  Projections.include("lstItems.producto.precio"),
										  Projections.include("lstItems.producto.descripcion"),
										  Projections.include("lstItems.producto.tipoProducto"),
										  Projections.include("lstItems.cantidad")
						              ))
					)).forEach(printBlock);	
			System.out.println("\nDetalle de ventas Sucursal 3 con tipo de producto: perfumeria");
			coll.aggregate(
					asList(
							Aggregates.unwind("$lstItems"),
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), eq("lstItems.producto.tipoProducto","perfumeria"),regex("nroTicket","0003-"))),
							Aggregates.project(
						              Projections.fields(
					                      Projections.excludeId(),
										  Projections.include("nroTicket"),
										  Projections.include("lstItems.producto.precio"),
										  Projections.include("lstItems.producto.descripcion"),
										  Projections.include("lstItems.producto.tipoProducto"),
										  Projections.include("lstItems.cantidad")
						              ))
					)).forEach(printBlock);	
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
			System.out.println("Agregadas ventas");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
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
	
	@SuppressWarnings("deprecation")
	public void rankingProductoPorMontoEntreFechas(Date fecha1, Date fecha2) {
		try {
			MongoDatabase database = this.getMongoClient().getDatabase(this.base);			
			MongoCollection<Document> coll = database.getCollection("venta");
			System.out.println("\nRanking de ventas de la cadena por monto de producto");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2))),
							Aggregates.unwind("$lstItems"),
							Aggregates.project(
						              Projections.fields(
					                      Projections.excludeId(),
										  Projections.include("lstItems.precio"),
										  Projections.include("lstItems.producto.descripcion")
						              )),
							Aggregates.group("$lstItems.producto.descripcion", Accumulators.sum("total", "$lstItems.precio")),
							Aggregates.sort(Sorts.descending("total"))
					)).forEach(mostrarRankingMonto);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void rankingProductoPorSucursalYMontoEntreFechas(Date fecha1, Date fecha2) {
		try {
			MongoDatabase database = this.getMongoClient().getDatabase(this.base);			
			MongoCollection<Document> coll = database.getCollection("venta");
			System.out.println("\nRanking de ventas de la Sucursal 1 por monto de producto");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0001-"))),
							Aggregates.unwind("$lstItems"),
							Aggregates.project(
						              Projections.fields(
					                      Projections.excludeId(),
										  Projections.include("lstItems.precio"),
										  Projections.include("lstItems.producto.descripcion")
						              )),
							Aggregates.group("$lstItems.producto.descripcion", Accumulators.sum("total", "$lstItems.precio")),
							Aggregates.sort(Sorts.descending("total"))
					)).forEach(mostrarRankingMonto);	
			System.out.println("\nRanking de ventas de la Sucursal 2 por monto de producto");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0002-"))),
							Aggregates.unwind("$lstItems"),
							Aggregates.project(
						              Projections.fields(
					                      Projections.excludeId(),
										  Projections.include("lstItems.precio"),
										  Projections.include("lstItems.producto.descripcion")
						              )),
							Aggregates.group("$lstItems.producto.descripcion", Accumulators.sum("total", "$lstItems.precio")),
							Aggregates.sort(Sorts.descending("total"))
					)).forEach(mostrarRankingMonto);	
			System.out.println("\nRanking de ventas de la Sucursal 3 por monto de producto");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0003-"))),
							Aggregates.unwind("$lstItems"),
							Aggregates.project(
						              Projections.fields(
					                      Projections.excludeId(),
										  Projections.include("lstItems.precio"),
										  Projections.include("lstItems.producto.descripcion")
						              )),
							Aggregates.group("$lstItems.producto.descripcion", Accumulators.sum("total", "$lstItems.precio")),
							Aggregates.sort(Sorts.descending("total"))
					)).forEach(mostrarRankingMonto);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void rankingProductoPorCantidadEntreFechas(Date fecha1, Date fecha2) {
		try {
			MongoDatabase database = this.getMongoClient().getDatabase(this.base);			
			MongoCollection<Document> coll = database.getCollection("venta");
			System.out.println("\nRanking de ventas de la cadena por cantidad de producto");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2))),
							Aggregates.unwind("$lstItems"),
							Aggregates.project(
						              Projections.fields(
					                      Projections.excludeId(),
										  Projections.include("lstItems.cantidad"),
										  Projections.include("lstItems.producto.descripcion")
						              )),
							Aggregates.group("$lstItems.producto.descripcion", Accumulators.sum("cantidad", "$lstItems.cantidad")),
							Aggregates.sort(Sorts.descending("cantidad"))
					)).forEach(mostrarRankingCantidad);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void rankingProductoPorSucursalYCantidadEntreFechas(Date fecha1, Date fecha2) {
		try {
			MongoDatabase database = this.getMongoClient().getDatabase(this.base);			
			MongoCollection<Document> coll = database.getCollection("venta");
			System.out.println("\nRanking de ventas de la Sucursal 1 por cantidad de producto");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0001-"))),
							Aggregates.unwind("$lstItems"),
							Aggregates.project(
						              Projections.fields(
					                      Projections.excludeId(),
										  Projections.include("lstItems.cantidad"),
										  Projections.include("lstItems.producto.descripcion")
						              )),
							Aggregates.group("$lstItems.producto.descripcion", Accumulators.sum("cantidad", "$lstItems.cantidad")),
							Aggregates.sort(Sorts.descending("cantidad"))
					)).forEach(mostrarRankingCantidad);	
			System.out.println("\nRanking de ventas de la Sucursal 2 por cantidad de producto");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0002-"))),
							Aggregates.unwind("$lstItems"),
							Aggregates.project(
						              Projections.fields(
					                      Projections.excludeId(),
										  Projections.include("lstItems.cantidad"),
										  Projections.include("lstItems.producto.descripcion")
						              )),
							Aggregates.group("$lstItems.producto.descripcion", Accumulators.sum("cantidad", "$lstItems.cantidad")),
							Aggregates.sort(Sorts.descending("cantidad"))
					)).forEach(mostrarRankingCantidad);	
			System.out.println("\nRanking de ventas de la Sucursal 3 por cantidad de producto");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0003-"))),
							Aggregates.unwind("$lstItems"),
							Aggregates.project(
						              Projections.fields(
					                      Projections.excludeId(),
										  Projections.include("lstItems.cantidad"),
										  Projections.include("lstItems.producto.descripcion")
						              )),
							Aggregates.group("$lstItems.producto.descripcion", Accumulators.sum("cantidad", "$lstItems.cantidad")),
							Aggregates.sort(Sorts.descending("cantidad"))
					)).forEach(mostrarRankingCantidad);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void rankingClientesPorMontoEntreFechas(Date fecha1, Date fecha2) {
		try {
			MongoDatabase database = this.getMongoClient().getDatabase(this.base);			
			MongoCollection<Document> coll = database.getCollection("venta");
			System.out.println("\nRanking de clientes de la cadena por monto");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2))),
							Aggregates.project(
						              Projections.fields(
					                      Projections.excludeId(),
					                      Projections.include("total"),
					                      Projections.include("cliente.nombre"),
					                      Projections.include("cliente.apellido"),
					                      Projections.include("cliente.dni")
						              )),
							Aggregates.group("$cliente.dni", Accumulators.sum("total", "$total")),
							Aggregates.sort(Sorts.descending("total"))
					)).forEach(mostrarRankingClienteMonto);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void rankingClientesPorSucursalYMontoEntreFechas(Date fecha1, Date fecha2) {
		try {
			MongoDatabase database = this.getMongoClient().getDatabase(this.base);			
			MongoCollection<Document> coll = database.getCollection("venta");
			System.out.println("\nRanking de clientes de la Sucursal 1 por monto");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0001-"))),
							Aggregates.project(
						              Projections.fields(
					                      Projections.excludeId(),
					                      Projections.include("total"),
					                      Projections.include("cliente.nombre"),
					                      Projections.include("cliente.apellido"),
					                      Projections.include("cliente.dni")
						              )),
							Aggregates.group("$cliente.dni", Accumulators.sum("total", "$total")),
							Aggregates.sort(Sorts.descending("total"))
					)).forEach(mostrarRankingClienteMonto);	
			System.out.println("\nRanking de clientes de la Sucursal 2 por monto");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0002-"))),
							Aggregates.project(
						              Projections.fields(
					                      Projections.excludeId(),
					                      Projections.include("total"),
					                      Projections.include("cliente.nombre"),
					                      Projections.include("cliente.apellido"),
					                      Projections.include("cliente.dni")
						              )),
							Aggregates.group("$cliente.dni", Accumulators.sum("total", "$total")),
							Aggregates.sort(Sorts.descending("total"))
					)).forEach(mostrarRankingClienteMonto);	
			System.out.println("\nRanking de clientes de la Sucursal 3 por monto");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0003-"))),
							Aggregates.project(
						              Projections.fields(
					                      Projections.excludeId(),
					                      Projections.include("total"),
					                      Projections.include("cliente.nombre"),
					                      Projections.include("cliente.apellido"),
					                      Projections.include("cliente.dni")
						              )),
							Aggregates.group("$cliente.dni", Accumulators.sum("total", "$total")),
							Aggregates.sort(Sorts.descending("total"))
					)).forEach(mostrarRankingClienteMonto);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void rankingClientesPorCantidadEntreFechas(Date fecha1, Date fecha2) {
		try {
			MongoDatabase database = this.getMongoClient().getDatabase(this.base);			
			MongoCollection<Document> coll = database.getCollection("venta");
			System.out.println("\nRanking de clientes de la cadena por cantidad");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2))),
							Aggregates.project(
						              Projections.fields(
					                      Projections.excludeId(),
					                      Projections.include("cliente.nombre"),
					                      Projections.include("cliente.apellido"),
					                      Projections.include("cliente.dni")
						              )),
							Aggregates.group("$cliente.dni", Accumulators.sum("cantidad", 1)),
							Aggregates.sort(Sorts.descending("cantidad"))
					)).forEach(mostrarRankingClienteCantidad);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public void rankingClientesPorSucursalYCantidadEntreFechas(Date fecha1, Date fecha2) {
		try {
			MongoDatabase database = this.getMongoClient().getDatabase(this.base);			
			MongoCollection<Document> coll = database.getCollection("venta");
			System.out.println("\nRanking de clientes de la Sucursal 1 por cantidad");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0001-"))),
							Aggregates.project(
						              Projections.fields(
					                      Projections.excludeId(),
					                      Projections.include("cliente.nombre"),
					                      Projections.include("cliente.apellido"),
					                      Projections.include("cliente.dni")
						              )),
							Aggregates.group("$cliente.dni", Accumulators.sum("cantidad", 1)),
							Aggregates.sort(Sorts.descending("cantidad"))
					)).forEach(mostrarRankingClienteCantidad);	
			System.out.println("\nRanking de clientes de la Sucursal 2 por cantidad");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0002-"))),
							Aggregates.project(
						              Projections.fields(
					                      Projections.excludeId(),
					                      Projections.include("cliente.nombre"),
					                      Projections.include("cliente.apellido"),
					                      Projections.include("cliente.dni")
						              )),
							Aggregates.group("$cliente.dni", Accumulators.sum("cantidad", 1)),
							Aggregates.sort(Sorts.descending("cantidad"))
					)).forEach(mostrarRankingClienteCantidad);	
			System.out.println("\nRanking de clientes de la Sucursal 3 por cantidad");
			coll.aggregate(
					asList(
							Aggregates.match(and(gte("fecha",fecha1), lte("fecha",fecha2), regex("nroTicket","0003-"))),
							Aggregates.project(
						              Projections.fields(
					                      Projections.excludeId(),
					                      Projections.include("cliente.nombre"),
					                      Projections.include("cliente.apellido"),
					                      Projections.include("cliente.dni")
						              )),
							Aggregates.group("$cliente.dni", Accumulators.sum("cantidad", 1)),
							Aggregates.sort(Sorts.descending("cantidad"))
					)).forEach(mostrarRankingClienteCantidad);	
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
	// Mostrar total venta
	public Block<Document> mostrarTotalVenta = new Block<Document>() {
	       @Override
	       public void apply(final Document document) {
	    	   System.out.println("Total: $ "+document.get("total"));
	       }
			};
	// Mostrar total por tipo de producto
	public Block<Document> mostrarTotalProducto = new Block<Document>() {
	       @Override
	       public void apply(final Document document) {
	    	   System.out.println("Tipo de producto: "+document.get("_id")+" Total: $ "+document.get("total"));
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
	// Mostrar Ranking por monto de producto
	public Block<Document> mostrarRankingMonto = new Block<Document>() {
	       @Override
	       public void apply(final Document document) {
	    	   System.out.println("Descripcion: "+document.get("_id")+" Total: $ "+document.get("total"));
	       }
	};
	// Mostrar Ranking por cantidad de producto
		public Block<Document> mostrarRankingCantidad = new Block<Document>() {
		       @Override
		       public void apply(final Document document) {
		    	   System.out.println("Descripcion: "+document.get("_id")+" Cantidad: "+document.get("cantidad"));
		       }
		};
	// Mostrar Ranking clientes por monto
			public Block<Document> mostrarRankingClienteMonto = new Block<Document>() {
			       @Override
			       public void apply(final Document document) {
			    	   System.out.println("DNI: "+document.get("_id")+" Total: "+document.get("total"));
			       }
			};
			
	// Mostrar Ranking clientes por cantidad
				public Block<Document> mostrarRankingClienteCantidad = new Block<Document>() {
				       @Override
				       public void apply(final Document document) {
				    	   System.out.println("DNI: "+document.get("_id")+" Total: "+document.get("cantidad"));
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
