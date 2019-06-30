package test;

import java.util.Date;

import negocio.Mongo;

// 5.	Ranking de ventas de productos, total de la cadena y por sucursal, entre fechas, por monto.

public class TestConsulta5 {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Mongo mongo = Mongo.getInstanciaMongo();
			
		Date fechaInicial = new Date(119,5,1,0,0);
		Date fechaFinal = new Date(119,5,18,24,0);
		
		System.out.println("Consulta 5");
		
		//Ranking de la cadena por monto de producto
		mongo.rankingProductoPorMontoEntreFechas(fechaInicial, fechaFinal);
		//Ranking de las sucursales por monto de producto
		mongo.rankingProductoPorSucursalYMontoEntreFechas(fechaInicial, fechaFinal);
		
		mongo.getMongoClient().close();		

	}

}
