package test;

import static java.util.Arrays.asList;

import java.util.List;

import modelo.Cliente;
import modelo.Domicilio;
import modelo.Localidad;
import modelo.Producto;
import modelo.Provincia;

public class TestCargaDeDatos {

	public static void main(String[] args) {
		//Localidades//
		List<Localidad> localidades = asList(
		        new Localidad(1,"Adrogue"),
		        new Localidad(2,"Lomas de Zamora"),
		        new Localidad(3,"Temperley"),
		        new Localidad(4,"Banfield"),
		        new Localidad(5,"Lanus")
		);
		
		
		//Provincia//
		List<Provincia> provincias = asList(
				new Provincia(1,"Buenos Aires")
				
				);
		
		
		//Domicilio//
		
		List<Domicilio> domicilios = asList(
				new Domicilio(1,"cordero","540",new Localidad(2,"Adrogue"),new Provincia(1,"Buenos Aires"))
				);
		//Productos//
		 
		 
		
		 
		 
		 
	
		 List<Producto> productos = asList(
				 
				 
		 new Producto(1,"medicamento","Omeprazol","Klonal",111,258),
		 new Producto(2,"medicamento","Clonazepam","Klonal",112,150),
		 new Producto(3,"medicamento","Ibuprofeno","Klonal",113,200),
		 new Producto(4,"medicamento","Buscapina","Bernabo",114,123),
		 new Producto(5,"medicamento","Sertal","Bernabo",115,240),
		 new Producto(6,"medicamento","Amoxicilina","Bernabo",116,100),
		 new Producto(7,"medicamento","Aspirina","Laboratorios Isa",117,78),
		 new Producto(8,"medicamento","Alprazolam","Laboratorios Isa",118,50),
		 new Producto(9,"medicamento","Paracetamol","Laboratorios Isa",119,350),
		 new Producto(10,"medicamento","Enalapril","Laboratorio Eczane Pharma",120,235),
		 new Producto(11,"perfumeria","desodorante",null,111,258),
		 new Producto(12,"perfumeria","jabón",null,112,150),
		 new Producto(13,"perfumeria","shampoo",null,113,200));

	}
	
	    //Clientes//
	    List<Cliente> clientes = asList(
			new Cliente(1,"Pereyra","Mauro",38301299,new Domicilio(1,"cordero","540",new Localidad(2,"Adrogue"),new Provincia(1,"Buenos Aires")) ,"OSDE","101"),
			new Cliente(2,"Pereyra","Mauro",38301300,new Domicilio(1,"cordero","540",new Localidad(2,"Adrogue"),new Provincia(1,"Buenos Aires")) ,"OSDE","102"),
			new Cliente(3,"Pereyra","Mauro",38301301,new Domicilio(1,"cordero","540",new Localidad(2,"Adrogue"),new Provincia(1,"Buenos Aires")) ,"OSDE","103"),
			new Cliente(4,"Pereyra","Mauro",38301302,new Domicilio(1,"cordero","540",new Localidad(2,"Adrogue"),new Provincia(1,"Buenos Aires")) ,"OSDE","104"),
			new Cliente(5,"Pereyra","Mauro",38301303,new Domicilio(1,"cordero","540",new Localidad(2,"Adrogue"),new Provincia(1,"Buenos Aires")) ,"OSDE","105"),
			new Cliente(6,"Pereyra","Mauro",38301304,new Domicilio(1,"cordero","540",new Localidad(2,"Adrogue"),new Provincia(1,"Buenos Aires")) ,"OSDE","106"),
			new Cliente(7,"Pereyra","Mauro",38301305,new Domicilio(1,"cordero","540",new Localidad(2,"Adrogue"),new Provincia(1,"Buenos Aires")) ,"OSDE","107"),
			new Cliente(8,"Pereyra","Mauro",38301306,new Domicilio(1,"cordero","540",new Localidad(2,"Adrogue"),new Provincia(1,"Buenos Aires")) ,"OSDE","108"),
			new Cliente(9,"Pereyra","Mauro",38301307,new Domicilio(1,"cordero","540",new Localidad(2,"Adrogue"),new Provincia(1,"Buenos Aires")) ,"OSDE","109"),
			new Cliente(10,"Pereyra","Mauro",38301308,new Domicilio(1,"cordero","540",new Localidad(2,"Adrogue"),new Provincia(1,"Buenos Aires")) ,"OSDE","110")
			
			);
	
	
	
	public static void agregarProvincia(Provincia provincia) {
		
	}
	
	public static void agregarLocalidad(Localidad localidad) {
		
	}
	
	public static void agregarProducto(Producto producto) {
		
	}
	
    public static void agregarDomicilio(Domicilio domicilio) {
		
	}

}
