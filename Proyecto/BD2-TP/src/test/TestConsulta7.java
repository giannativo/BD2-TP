package test;

import java.util.Date;

import negocio.Mongo;

// 7.	Ranking de clientes por compras, total de la cadena y por sucursal, entre fechas, por monto.

public class TestConsulta7 {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Mongo mongo = Mongo.getInstanciaMongo();
			
		Date fechaInicial = new Date(119,5,1,0,0);
		Date fechaFinal = new Date(119,5,18,24,0);
		
		System.out.println("Consulta 7");
		
		//Ranking de clientes de la cadena por montos
		mongo.rankingClientesPorMontoEntreFechas(fechaInicial, fechaFinal);
		//Ranking de clientes de las sucursales por montos
		mongo.rankingClientesPorSucursalYMontoEntreFechas(fechaInicial, fechaFinal);
		
		mongo.getMongoClient().close();		

	}

}
