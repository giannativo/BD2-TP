package test;

import java.util.Date;

import negocio.Mongo;

// 8.	Ranking de clientes por compras, total de la cadena y por sucursal, entre fechas, por cantidad vendida.

public class TestConsulta8 {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Mongo mongo = Mongo.getInstanciaMongo();
			
		Date fechaInicial = new Date(119,5,1,0,0);
		Date fechaFinal = new Date(119,5,18,24,0);
		
		System.out.println("Consulta 8");
		
		//Ranking de clientes de la cadena por cantidad
		mongo.rankingClientesPorCantidadEntreFechas(fechaInicial, fechaFinal);
		//Ranking de clientes de las sucursales por cantidad
		mongo.rankingClientesPorSucursalYCantidadEntreFechas(fechaInicial, fechaFinal);
		
		mongo.getMongoClient().close();		

	}

}
