package test;

import java.util.Date;

import negocio.Mongo;

public class TestConsulta3 {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Mongo mongo = Mongo.getInstanciaMongo();
		
		Date fechaInicial = new Date(119,5,1,0,0);
		Date fechaFinal = new Date(119,5,18,24,0);
		
		System.out.println("Consulta 3");
		
		//Total y detalle de toda la cadena por Medio de Pago
		mongo.totalPorMedioDePagoEntre(fechaInicial, fechaFinal);
		mongo.detallePorMedioDePagoEntreFechas(fechaInicial, fechaFinal);
		
		//Total y detalle por Sucursal por Medio de Pago
		mongo.totalPorMedioDePagoYPorSucursalEntreFechas(fechaInicial, fechaFinal);
		mongo.detallePorMedioDePagoYPorSucursalEntreFechas(fechaInicial, fechaFinal);
		
		mongo.getMongoClient().close();	
	}

}
