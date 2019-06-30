package test;

import java.util.Date;

import negocio.Mongo;

// 2.	Detalle y totales de ventas para la cadena completa y por sucursal, por obra social o privados entre fechas.

public class TestConsulta2 {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Mongo mongo = Mongo.getInstanciaMongo();
			
		Date fechaInicial = new Date(119,5,1,0,0);
		Date fechaFinal = new Date(119,5,18,24,0);
		
		System.out.println("Consulta 2");
		
		//Total y detalle de toda la cadena por ObraSocial
		mongo.totalPorObraSocialEntreFechas(fechaInicial, fechaFinal);
		mongo.detallePorObraSocialEntreFechas(fechaInicial, fechaFinal);
		
		//Total y detalle por Sucursal por ObraSocial
		mongo.totalPorObraSocialYPorSucursalEntreFechas(fechaInicial, fechaFinal);
		mongo.detallePorObraSocialYPorSucursalEntreFechas(fechaInicial, fechaFinal);
		
		mongo.getMongoClient().close();		

	}

}
