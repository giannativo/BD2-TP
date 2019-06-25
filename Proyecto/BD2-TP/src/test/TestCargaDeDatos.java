package test;

import modelo.Domicilio;
import modelo.Localidad;
import modelo.Producto;
import modelo.Provincia;

public class TestCargaDeDatos {

	public static void main(String[] args) {
		//Localidades//
		agregarLocalidad(new Localidad(1,"Adrogue"));
		agregarLocalidad(new Localidad(2,"Lomas de Zamora"));
		agregarLocalidad(new Localidad(3,"Temperley"));
		agregarLocalidad(new Localidad(4,"Banfield"));
		agregarLocalidad(new Localidad(5,"Lanus"));
		
		//Provincia//
		agregarProvincia(new Provincia(1,"Buenos Aires"));
		
		//Productos//
		 //Perfumeria//
		 
		 //Medicamento//
		 agregarProducto(new Producto(1,"medicamento","Omeprazol","Klonal",111,258));
		 agregarProducto(new Producto(2,"medicamento","Clonazepam","Klonal",112,150));
		 agregarProducto(new Producto(3,"medicamento","Ibuprofeno","Klonal",113,200));
		 agregarProducto(new Producto(4,"medicamento","Buscapina","Bernabo",114,123));
		 agregarProducto(new Producto(5,"medicamento","Sertal","Bernabo",115,240));
		 agregarProducto(new Producto(6,"medicamento","Amoxicilina","Bernabo",116,100));
		 agregarProducto(new Producto(7,"medicamento","Aspirina","Laboratorios Isa",117,78));
		 agregarProducto(new Producto(8,"medicamento","Alprazolam","Laboratorios Isa",118,50));
		 agregarProducto(new Producto(9,"medicamento","Paracetamol","Laboratorios Isa",119,350));
		 agregarProducto(new Producto(10,"medicamento","Enalapril","Laboratorio Eczane Pharma",120,235));

	}
	
	
	
	public static void agregarProvincia(Provincia provincia) {
		
	}
	
	public static void agregarLocalidad(Localidad localidad) {
		
	}
	
	public static void agregarProducto(Producto producto) {
		
	}

}
