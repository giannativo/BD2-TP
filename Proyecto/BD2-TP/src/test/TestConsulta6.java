package test;

import java.util.Date;

import negocio.Mongo;

// 6.	Ranking de ventas de productos, total de la cadena y por sucursal, entre fechas, por cantidad vendida.

public class TestConsulta6 {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Mongo mongo = Mongo.getInstanciaMongo();
			
		Date fechaInicial = new Date(119,5,1,0,0);
		Date fechaFinal = new Date(119,5,18,24,0);
		
		System.out.println("Consulta 6");
		
		//Ranking de la cadena por cantidad de producto
		mongo.rankingProductoPorCantidadEntreFechas(fechaInicial, fechaFinal);
		//Ranking de las sucursales por cantidad de producto
		mongo.rankingProductoPorSucursalYCantidadEntreFechas(fechaInicial, fechaFinal);
		
		mongo.getMongoClient().close();		

	}

}
