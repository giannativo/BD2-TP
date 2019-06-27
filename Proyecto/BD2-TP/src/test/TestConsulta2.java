package test;

import java.util.Date;

import negocio.Mongo;

public class TestConsulta2 {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Mongo mongo = Mongo.getInstanciaMongo();
		
		Date fechaInicial = new Date(119,5,1,0,0);
		Date fechaFinal = new Date(119,5,18,24,0);
		
		//Total y detalle de toda la cadena
//		mongo.totalEntreFechas(fechaInicial, fechaFinal);
//		mongo.detalleEntreFechas(fechaInicial, fechaFinal);
		
		//Total y detalle por Sucursal
//		mongo.totalPorSucursalEntreFechas(fechaInicial, fechaFinal);
//		mongo.detallePorSucursalEntreFechas(fechaInicial, fechaFinal);
		
		mongo.getMongoClient().close();		

	}

}
