package test;

import negocio.Mongo;

import java.util.List;
import static java.util.Arrays.asList;

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
		
		Mongo mongo = Mongo.getInstanciaMongo();
		
		mongo.agregarDomicilios("domicilio", domicilios);
		mongo.mostrar("domicilio");
		
		mongo.getMongoClient().close();
		
		
	}

}
