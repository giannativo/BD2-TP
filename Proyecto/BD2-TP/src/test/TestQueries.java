package test;

import java.util.Date;

import negocio.Mongo;

public class TestQueries {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Mongo.getInstanciaMongo().traerVentasEntreFechasPorSucursal("0001", new Date(119,5,1), new Date(119,5,4));
	}

}
