package test;

import java.util.Date;

import negocio.Mongo;

// 4.	Detalle y totales de ventas de productos, total de la cadena y por sucursal, entre fechas, diferenciados entre farmacia y perfumería.

public class TestConsulta4 {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Mongo mongo = Mongo.getInstanciaMongo();
			
		Date fechaInicial = new Date(119,5,1,0,0);
		Date fechaFinal = new Date(119,5,18,24,0);
		
		System.out.println("Consulta 4");
		
		//Total y detalle de toda la cadena por tipo de producto
		mongo.totalPorTipoDeProductoEntreFechas(fechaInicial, fechaFinal);
		mongo.detallePorTipoDeProductoEntreFechas(fechaInicial, fechaFinal);
		
		//Total y detalle por Sucursal por tipo de producto
		mongo.totalPorTipoDeProductoYPorSucursalEntreFechas(fechaInicial, fechaFinal);
		mongo.detallePorTipoDeProductoYPorSucursalEntreFechas(fechaInicial, fechaFinal);
		
		mongo.getMongoClient().close();		

	}

}
