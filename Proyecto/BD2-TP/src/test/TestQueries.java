package test;

import java.util.Date;

import negocio.Mongo;

public class TestQueries {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Mongo.getInstanciaMongo().traerVentasEntreFechasPorSucursal("0001", new Date(119,5,1), new Date(119,5,4));
	}

}
