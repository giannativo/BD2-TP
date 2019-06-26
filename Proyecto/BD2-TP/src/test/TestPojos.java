package test;

import negocio.Mongo;

import java.util.List;
import static java.util.Arrays.asList;
import java.util.Date;
import java.util.GregorianCalendar;

import modelo.*;

public class TestPojos {

	public static void main(String[] args) {
		List<Localidad> localidades = asList(
		        new Localidad(1,"Adrogue"),
		        new Localidad(2,"Banfield"),
		        new Localidad(3,"Lomas")
		);
		
		Localidad adrogue = new Localidad(1,"Adrogue");
		Localidad banfield = new Localidad(2,"Banfield");
		Localidad lomas = new Localidad(3,"Lomas");
		
		Provincia buenosaires = new Provincia(1,"Buenos Aires");
		
		List<Domicilio> domicilios = asList(
		       new Domicilio(1,"Gallo","374",banfield,buenosaires),
		       new Domicilio(2,"Alsina","1025",lomas,buenosaires),
		       new Domicilio(3,"Melo","354",adrogue,buenosaires)
		);
		
		@SuppressWarnings("deprecation")
		Date fecha1 = new Date(2019,5,24,12,0);
		@SuppressWarnings("deprecation")
		Date fecha2 = new Date(2019,5,25,8,0);
		@SuppressWarnings("deprecation")
		Date fecha3 = new Date(2019,5,25,16,0);		
		
		Producto prod1 = new Producto(1,"medicamento","Omeprazol","Klonal",111,258);
		Producto prod2 = new Producto(2,"medicamento","Clonazepam","Klonal",112,150);
		Producto prod3 = new Producto(3,"medicamento","Ibuprofeno","Klonal",113,200);
		
		List<ItemVenta> lstItems1 = asList(
			       new ItemVenta(1,prod1,1),
			       new ItemVenta(2,prod2,2)
			);
		
		Empleado empleado1 = new Empleado(1, 245454651, "Bala", "Carlitos", 34234234, new Domicilio(1,"Gallo","374",banfield,buenosaires),"Osde", "454515151");
		Empleado empleado2 = new Empleado(2, 245454652, "Bala", "Pepito", 34234235, new Domicilio(2,"Alsina","1025",lomas,buenosaires),"Osde", "454515152");
				
		List<Venta> ventas = asList(
				new Venta(1,fecha1,1,120.55f,1,3,lstItems1,empleado1,empleado2),
				new Venta(1,fecha2,1,130.55f,1,3,lstItems1,empleado1,empleado2),
				new Venta(1,fecha3,1,.55f,1,3,lstItems1,empleado1,empleado2)
			);
		
		Mongo mongo = Mongo.getInstanciaMongo();
		
		mongo.agregarVentas("venta", ventas);
		mongo.mostrar("venta");
		
		mongo.getMongoClient().close();		
	}

}
