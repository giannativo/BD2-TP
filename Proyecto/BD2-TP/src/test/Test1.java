package test;

import negocio.Mongo;

public class Test1 {

	public static void main(String[] args) {
		Mongo mongo = Mongo.getInstanciaMongo();
		mongo.mostrar("local","startup_log");
	}

}
