package test;

import static java.util.Arrays.asList;
import java.util.List;
import java.util.Date;

import modelo.*;

public class TestSetDeDatos {

	public static void main(String[] args) {	
		// Localidades
		Localidad banfield= new Localidad(1,"Banfield");
		Localidad lanus= new Localidad(2,"Lanus");
		Localidad lomas= new Localidad(3,"Lomas de Zamora");
		
		// Provincia
		Provincia buenosaires= new Provincia(1,"Buenos Aires");
		
		// Encargados de las sucursales 
		Empleado encargado1 = new Empleado(1, 20341807817l, "Rodriguez", "Javier", 34180781, new Domicilio(1, "Gallo", "374", banfield, buenosaires), new ObraSocial(1,"Osde",12345));
		Empleado encargado2 = new Empleado(4, 20285479127l, "Pereyra", "Mauro", 28547912, new Domicilio(1, "Melo", "875", banfield, buenosaires), new ObraSocial(2,"Osde",12346));
		Empleado encargado3 = new Empleado(7, 20255472257l, "Nativo", "Gianlucua", 25547225, new Domicilio(1, "Molina Arrotea", "1875", lomas, buenosaires), new ObraSocial(3,"Galeno",12347));
		
		// Empleados Sucursal 1 
		List<Empleado> empleados1 = asList(
				encargado1,
				new Empleado(2, 20256548727l, "Gimenez", "Maria", 34180781, new Domicilio(2, "Granaderos", "456", banfield, buenosaires), new ObraSocial(4,"Osde",12348)),
				new Empleado(3, 20335486597l, "Lopez", "Pedro", 34180781, new Domicilio(3, "25 de Mayo", "1567", lanus, buenosaires), new ObraSocial(5,"Osde",12349))
			);
		// Empleados Sucursal 2
		List<Empleado> empleados2 = asList(
				encargado2,
				new Empleado(5, 20241234567l, "Garcia", "Laura", 34180781, new Domicilio(4, "Viamonte", "152", lanus, buenosaires), new ObraSocial(6,"Galeno",12350)),
				new Empleado(6, 20365213657l, "Gonzalez", "Martin", 34180781, new Domicilio(5, "Cerrito", "1678", lomas, buenosaires), new ObraSocial(7,"Galeno",12351))
			);
		// Empleados Sucursal 3
		List<Empleado> empleados3 = asList(
				encargado3,
				new Empleado(8, 20202549877l, "Fernandez", "Sofia", 34180781, new Domicilio(6, "Garibaldi", "1950", lomas, buenosaires), new ObraSocial(8,"Osde",12352)),
				new Empleado(9, 20213256547l, "Sanchez", "Nahuel", 34180781, new Domicilio(7, "Cangallo", "854", lomas, buenosaires), new ObraSocial(9,"Galeno",12353))
			);
		
		// Sucursales
		List<Sucursal> sucursales = asList(
				new Sucursal(1, new Domicilio(10, "25 de Mayo", "1000", lanus, buenosaires), empleados1, encargado1),
				new Sucursal(2, new Domicilio(11, "Av. Hipolito Yrigoyen", "3430", lomas, buenosaires), empleados2, encargado2),
				new Sucursal(3, new Domicilio(12, "Maipu", "400", banfield, buenosaires), empleados3, encargado3)			       
			);
		
		// Clientes
		List<Cliente> clientes = asList(
				new Cliente(1, "Listorti", "Jose", 15123547, new Domicilio(8, "Pedernera", "125", lomas, buenosaires), new ObraSocial(10,"Pami",12360)),
				new Cliente(2, "Fernandez", "Carla", 10659852, new Domicilio(9, "Cabrera", "1054", banfield, buenosaires), null),
				new Cliente(3, "Gimenez", "Osvaldo", 25632496, new Domicilio(10, "Lavalle", "324", banfield, buenosaires), new ObraSocial(12,"Osde",12361)),
				new Cliente(4, "Rodriguez", "Matias", 28659874, new Domicilio(11, "Pringles", "804", lanus, buenosaires), null),
				new Cliente(5, "Rivas", "Marcos", 38654123, new Domicilio(12, "Tucuman", "756", lanus, buenosaires), new ObraSocial(14,"Osde",12362)),
				new Cliente(6, "Garcia", "Mariana", 40215324, new Domicilio(13, "Madariaga", "1205", lanus, buenosaires), new ObraSocial(15,"Galeno",12363)),
				new Cliente(7, "Fiori", "Hector", 12548796, new Domicilio(14, "Rivera", "358", lomas, buenosaires), new ObraSocial(16,"Pami",12364)),
				new Cliente(8, "Pereyra", "Mirta", 12354789, new Domicilio(15, "Portela", "56", lomas, buenosaires), new ObraSocial(17,"Osde",12365)),
				new Cliente(9, "Tinelli", "Carlos", 30654877, new Domicilio(16, "Loria", "145", lomas, buenosaires), null),
				new Cliente(10, "Greco", "Lisandro", 36542369, new Domicilio(17, "Suipacha", "675", lanus, buenosaires), new ObraSocial(19,"Galeno",12366))
			);
		
		// Productos 
		List<Producto> productos = asList(
				new Producto(1,"medicamento","Omeprazol","Klonal",111,258f),
		 		new Producto(2,"medicamento","Clonazepam","Klonal",112,150f),
		 		new Producto(3,"medicamento","Ibuprofeno","Klonal",113,200f),
		 		new Producto(4,"medicamento","Buscapina","Bernabo",114,123f),
		 		new Producto(5,"medicamento","Sertal","Bernabo",115,240f),
		 		new Producto(6,"medicamento","Amoxicilina","Bernabo",116,100f),
		 		new Producto(7,"medicamento","Aspirina","Laboratorios Isa",117,78f),
		 		new Producto(8,"perfumeria","Shampoo","P&G",118,150f),
		 		new Producto(9,"perfumeria","Jabon","Unilever",119,45f),
		 		new Producto(10,"perfumeria","Desodorante","Unilever",120,80f)
			);
		
		// Items de Ventas de Sucursal 1
		List<ItemVenta> venta1 = asList(
				new ItemVenta(1, productos.get(0), 1, productos.get(0).getPrecio()),
				new ItemVenta(2, productos.get(1), 1, productos.get(1).getPrecio())
			);
		List<ItemVenta> venta2 = asList(
				new ItemVenta(3, productos.get(1), 1, productos.get(1).getPrecio()),
				new ItemVenta(4, productos.get(2), 1, productos.get(2).getPrecio())
			);
		List<ItemVenta> venta3 = asList(
				new ItemVenta(5, productos.get(3), 1, productos.get(3).getPrecio()),
				new ItemVenta(6, productos.get(2), 1, productos.get(2).getPrecio())
			);
		List<ItemVenta> venta4 = asList(
				new ItemVenta(7, productos.get(7), 1, productos.get(7).getPrecio()),
				new ItemVenta(8, productos.get(8), 2, productos.get(8).getPrecio())
			);
		List<ItemVenta> venta5 = asList(
				new ItemVenta(9, productos.get(1), 1, productos.get(1).getPrecio()),
				new ItemVenta(10, productos.get(2), 2, productos.get(2).getPrecio())
			);
		List<ItemVenta> venta6 = asList(
				new ItemVenta(11, productos.get(3), 1, productos.get(3).getPrecio())
			);
		List<ItemVenta> venta7 = asList(
				new ItemVenta(12, productos.get(4), 1, productos.get(4).getPrecio())
			);
		List<ItemVenta> venta8 = asList(
				new ItemVenta(13, productos.get(5), 1, productos.get(5).getPrecio()),
				new ItemVenta(14, productos.get(6), 1, productos.get(6).getPrecio()),
				new ItemVenta(15, productos.get(9), 1, productos.get(9).getPrecio())
			);
		List<ItemVenta> venta9 = asList(
				new ItemVenta(16, productos.get(5), 1, productos.get(5).getPrecio())
			);
		List<ItemVenta> venta10 = asList(
				new ItemVenta(17, productos.get(8), 2, productos.get(8).getPrecio())
			);
		List<ItemVenta> venta11 = asList(
				new ItemVenta(18, productos.get(2), 1, productos.get(2).getPrecio()),
				new ItemVenta(19, productos.get(5), 1, productos.get(5).getPrecio())
			);
		List<ItemVenta> venta12 = asList(
				new ItemVenta(20, productos.get(1), 1, productos.get(1).getPrecio()),
				new ItemVenta(21, productos.get(2), 2, productos.get(2).getPrecio())
			);
		List<ItemVenta> venta13 = asList(
				new ItemVenta(22, productos.get(7), 1, productos.get(7).getPrecio())
			);
		List<ItemVenta> venta14 = asList(
				new ItemVenta(23, productos.get(3), 1, productos.get(3).getPrecio()),
				new ItemVenta(24, productos.get(9), 2, productos.get(9).getPrecio())
			);
		List<ItemVenta> venta15 = asList(
				new ItemVenta(25, productos.get(4), 2, productos.get(4).getPrecio())
			);
		List<ItemVenta> venta16 = asList(
				new ItemVenta(26, productos.get(3), 1, productos.get(3).getPrecio())
			);
		List<ItemVenta> venta17 = asList(
				new ItemVenta(27, productos.get(2), 1, productos.get(2).getPrecio())
			);
		List<ItemVenta> venta18 = asList(
				new ItemVenta(28, productos.get(6), 1, productos.get(6).getPrecio())
			);
		List<ItemVenta> venta19 = asList(
				new ItemVenta(29, productos.get(2), 1, productos.get(2).getPrecio()),
				new ItemVenta(30, productos.get(6), 1, productos.get(6).getPrecio())
			);
		List<ItemVenta> venta20 = asList(
				new ItemVenta(31, productos.get(1), 1, productos.get(1).getPrecio())
			);
		List<ItemVenta> venta21 = asList(
				new ItemVenta(32, productos.get(0), 2, productos.get(0).getPrecio())
			);
		List<ItemVenta> venta22 = asList(
				new ItemVenta(34, productos.get(8), 3, productos.get(8).getPrecio())
			);
		List<ItemVenta> venta23 = asList(
				new ItemVenta(35, productos.get(0), 1, productos.get(0).getPrecio())
			);
		List<ItemVenta> venta24 = asList(
				new ItemVenta(37, productos.get(2), 1, productos.get(2).getPrecio()),
				new ItemVenta(38, productos.get(6), 1, productos.get(6).getPrecio())
			);
		
		
		// Ventas Sucursal 1
		List<Venta> ventas1 = asList(
				new Venta(1, new Date(), "0001-0001", 408f, "efectivo", venta1 , encargado1, empleados1.get(1)),
				new Venta(2, new Date(), "0001-0002", 350f, "efectivo", venta2 , encargado1, empleados1.get(1)),
				new Venta(3, new Date(), "0001-0003", 323f, "debito", venta3 , encargado1, empleados1.get(2)),
				new Venta(4, new Date(), "0001-0004", 240f, "debito", venta4 , encargado1, empleados1.get(3)),
				new Venta(5, new Date(), "0001-0005", 550f, "credito", venta5 , encargado1, empleados1.get(3)),
				new Venta(6, new Date(), "0001-0006", 123f, "efectivo", venta6 , encargado1, empleados1.get(3)),
				new Venta(7, new Date(), "0001-0007", 240f, "credito", venta7 , encargado1, empleados1.get(1)),
				new Venta(8, new Date(), "0001-0008", 223f, "efectivo", venta8 , encargado1, empleados1.get(2)),
				new Venta(9, new Date(), "0001-0009", 100f, "debito", venta9 , encargado1, empleados1.get(2)),
				new Venta(10, new Date(), "0001-0010", 90f, "efectivo", venta10 , encargado1, empleados1.get(1)),
				new Venta(11, new Date(), "0001-0011", 300f, "credito", venta11 , encargado1, empleados1.get(3)),
				new Venta(12, new Date(), "0001-0012", 550f, "efectivo", venta12 , encargado1, empleados1.get(3)),
				new Venta(13, new Date(), "0001-0013", 150f, "debito", venta13 , encargado1, empleados1.get(3)),
				new Venta(14, new Date(), "0001-0014", 283f, "credito", venta14 , encargado1, empleados1.get(1)),
				new Venta(15, new Date(), "0001-0015", 480f, "efectivo", venta15 , encargado1, empleados1.get(2)),
				new Venta(16, new Date(), "0001-0016", 123f, "debito", venta16 , encargado1, empleados1.get(2)),
				new Venta(17, new Date(), "0001-0017", 200f, "efectivo", venta17 , encargado1, empleados1.get(2)),
				new Venta(18, new Date(), "0001-0018", 78f, "efectivo", venta18 , encargado1, empleados1.get(1)),
				new Venta(19, new Date(), "0001-0019", 245f, "efectivo", venta19 , encargado1, empleados1.get(3)),
				new Venta(20, new Date(), "0001-0020", 150f, "debito", venta20 , encargado1, empleados1.get(3)),
				new Venta(21, new Date(), "0001-0021", 516f, "efectivo", venta21 , encargado1, empleados1.get(2)),
				new Venta(22, new Date(), "0001-0022", 135f, "credito", venta22 , encargado1, empleados1.get(1)),
				new Venta(23, new Date(), "0001-0023", 258f, "debito", venta23 , encargado1, empleados1.get(1)),
				new Venta(24, new Date(), "0001-0024", 280f, "efectivo", venta24 , encargado1, empleados1.get(2))
			);
		
		// Items de Ventas de Sucursal 1
				List<ItemVenta> venta25 = asList(
						new ItemVenta(1, productos.get(0), 1, productos.get(0).getPrecio())
					);
				List<ItemVenta> venta26 = asList(
						new ItemVenta(3, productos.get(9), 2, productos.get(9).getPrecio()),
						new ItemVenta(4, productos.get(1), 1, productos.get(1).getPrecio())
					);
				List<ItemVenta> venta27 = asList(
						new ItemVenta(5, productos.get(4), 1, productos.get(4).getPrecio()),
						new ItemVenta(6, productos.get(2), 1, productos.get(2).getPrecio())
					);
				List<ItemVenta> venta28 = asList(
						new ItemVenta(7, productos.get(7), 1, productos.get(7).getPrecio()),
						new ItemVenta(8, productos.get(8), 2, productos.get(8).getPrecio())
					);
				List<ItemVenta> venta29 = asList(
						new ItemVenta(9, productos.get(5), 1, productos.get(9).getPrecio()),
					);
				List<ItemVenta> venta30 = asList(
						new ItemVenta(11, productos.get(3), 1, productos.get(3).getPrecio())
					);
				List<ItemVenta> venta31 = asList(
						new ItemVenta(12, productos.get(4), 1, productos.get(4).getPrecio())
					);
				List<ItemVenta> venta32 = asList(
						new ItemVenta(13, productos.get(0), 1, productos.get(0).getPrecio()),
						new ItemVenta(14, productos.get(1), 1, productos.get(1).getPrecio()),
						new ItemVenta(15, productos.get(7), 1, productos.get(7).getPrecio())
					);
				List<ItemVenta> venta33 = asList(
						new ItemVenta(16, productos.get(5), 1, productos.get(5).getPrecio())
					);
				List<ItemVenta> venta34 = asList(
						new ItemVenta(17, productos.get(8), 2, productos.get(8).getPrecio())
					);
				List<ItemVenta> venta35 = asList(
						new ItemVenta(18, productos.get(1), 1, productos.get(1).getPrecio()),
						new ItemVenta(19, productos.get(6), 1, productos.get(6).getPrecio())
					);
				List<ItemVenta> venta36 = asList(
						new ItemVenta(20, productos.get(2), 1, productos.get(2).getPrecio()),
						new ItemVenta(21, productos.get(3), 2, productos.get(3).getPrecio())
					);
				List<ItemVenta> venta37 = asList(
						new ItemVenta(22, productos.get(7), 2, productos.get(7).getPrecio())
					);
				List<ItemVenta> venta38 = asList(
						new ItemVenta(23, productos.get(3), 1, productos.get(3).getPrecio()),
						new ItemVenta(24, productos.get(9), 1, productos.get(9).getPrecio())
					);
				List<ItemVenta> venta39 = asList(
						new ItemVenta(25, productos.get(4), 2, productos.get(4).getPrecio())
					);
				List<ItemVenta> venta40 = asList(
						new ItemVenta(26, productos.get(3), 2, productos.get(3).getPrecio())
					);
				List<ItemVenta> venta41 = asList(
						new ItemVenta(27, productos.get(2), 1, productos.get(2).getPrecio())
					);
				List<ItemVenta> venta42 = asList(
						new ItemVenta(28, productos.get(6), 1, productos.get(6).getPrecio())
					);
				List<ItemVenta> venta43 = asList(
						new ItemVenta(29, productos.get(1), 1, productos.get(1).getPrecio()),
						new ItemVenta(30, productos.get(8), 1, productos.get(8).getPrecio())
					);
				List<ItemVenta> venta44 = asList(
						new ItemVenta(31, productos.get(1), 1, productos.get(1).getPrecio())
					);
				List<ItemVenta> venta45 = asList(
						new ItemVenta(32, productos.get(0), 2, productos.get(0).getPrecio())
					);
				List<ItemVenta> venta46 = asList(
						new ItemVenta(34, productos.get(8), 3, productos.get(8).getPrecio())
					);
				List<ItemVenta> venta47 = asList(
						new ItemVenta(35, productos.get(0), 1, productos.get(0).getPrecio())
					);
				List<ItemVenta> venta48 = asList(
						new ItemVenta(37, productos.get(2), 1, productos.get(2).getPrecio()),
						new ItemVenta(38, productos.get(6), 1, productos.get(6).getPrecio())
					);
				List<ItemVenta> venta49 = asList(
						new ItemVenta(29, productos.get(0), 1, productos.get(0).getPrecio()),
						new ItemVenta(30, productos.get(9), 2, productos.get(9).getPrecio())
					);
				List<ItemVenta> venta50 = asList(
						new ItemVenta(31, productos.get(1), 1, productos.get(1).getPrecio())
					);
				List<ItemVenta> venta51 = asList(
						new ItemVenta(32, productos.get(7), 2, productos.get(7).getPrecio())
					);
				List<ItemVenta> venta52 = asList(
						new ItemVenta(34, productos.get(8), 3, productos.get(8).getPrecio())
					);
				List<ItemVenta> venta53 = asList(
						new ItemVenta(35, productos.get(0), 3, productos.get(0).getPrecio())
					);
				List<ItemVenta> venta54 = asList(
						new ItemVenta(37, productos.get(2), 1, productos.get(2).getPrecio()),
						new ItemVenta(38, productos.get(7), 3, productos.get(7).getPrecio())
					);
		
		// Ventas Sucursal 2
		List<Venta> ventas2 = asList(
				new Venta(25, new Date(), "0002-0025", 258f, "debito", venta25 , encargado2, empleados2.get(1)),
				new Venta(26, new Date(), "0002-0026", 310f, "debito", venta26 , encargado2, empleados2.get(1)),
				new Venta(27, new Date(), "0002-0027", 440f, "credito", venta27 , encargado2, empleados2.get(2)),
				new Venta(28, new Date(), "0002-0028", 240f, "credito", venta28 , encargado2, empleados2.get(2)),
				new Venta(29, new Date(), "0002-0029", 80f, "efectivo", venta29 , encargado2, empleados2.get(3)),
				new Venta(30, new Date(), "0002-0030", 123f, "efectivo", venta30 , encargado2, empleados2.get(3)),
				new Venta(31, new Date(), "0002-0031", 240f, "efectivo", venta31 , encargado2, empleados2.get(3)),
				new Venta(32, new Date(), "0002-0032", 558f, "debito", venta32 , encargado2, empleados2.get(1)),
				new Venta(33, new Date(), "0002-0033", 100f, "credito", venta33 , encargado2, empleados2.get(2)),
				new Venta(34, new Date(), "0002-0034", 90f, "credito", venta34 , encargado2, empleados2.get(2)),
				new Venta(35, new Date(), "0002-0035", 228f, "efectivo", venta35 , encargado2, empleados2.get(2)),
				new Venta(36, new Date(), "0002-0036", 446f, "debito", venta36 , encargado2, empleados2.get(1)),
				new Venta(37, new Date(), "0002-0037", 300f, "efectivo", venta37 , encargado2, empleados2.get(3)),
				new Venta(38, new Date(), "0002-0038", 203f, "efectivo", venta38 , encargado2, empleados2.get(3)),
				new Venta(39, new Date(), "0002-0039", 480f, "credito", venta39 , encargado2, empleados2.get(3)),
				new Venta(40, new Date(), "0002-0040", 246f, "credito", venta40 , encargado2, empleados2.get(1)),
				new Venta(41, new Date(), "0002-0041", 200f, "efectivo", venta41 , encargado2, empleados2.get(2)),
				new Venta(42, new Date(), "0002-0042", 78f, "efectivo", venta42 , encargado2, empleados2.get(2)),
				new Venta(43, new Date(), "0002-0043", 195f, "efectivo", venta43 , encargado2, empleados2.get(1)),
				new Venta(44, new Date(), "0002-0044", 150f, "debito", venta44 , encargado2, empleados2.get(3)),
				new Venta(45, new Date(), "0002-0045", 516f, "debito", venta45 , encargado2, empleados2.get(1)),
				new Venta(46, new Date(), "0002-0046", 135f, "efectivo", venta46 , encargado2, empleados2.get(2)),
				new Venta(47, new Date(), "0002-0047", 258f, "efectivo", venta47 , encargado2, empleados2.get(2)),
				new Venta(48, new Date(), "0002-0048", 278f, "efectivo", venta48 , encargado2, empleados2.get(2)),
				new Venta(49, new Date(), "0002-0049", 418f, "efectivo", venta49 , encargado2, empleados2.get(2)),
				new Venta(50, new Date(), "0002-0050", 150f, "debito", venta50 , encargado2, empleados2.get(3)),
				new Venta(51, new Date(), "0002-0051", 300f, "credito", venta51 , encargado2, empleados2.get(3)),
				new Venta(52, new Date(), "0002-0052", 135f, "debito", venta52 , encargado2, empleados2.get(1)),
				new Venta(53, new Date(), "0002-0053", 774f, "credito", venta53 , encargado2, empleados2.get(2)),
				new Venta(54, new Date(), "0002-0054", 650f, "credito", venta54 , encargado2, empleados2.get(1))
			);
		
		// Ventas Sucursal 3
		List<Venta> ventas3 = asList(
				new Venta(1),
				new Venta(2),
				new Venta(3),
				new Venta(4),
				new Venta(5),
				new Venta(6),
				new Venta(7),
				new Venta(8),
				new Venta(9),
				new Venta(10),
				new Venta(11),
				new Venta(12),
				new Venta(13),
				new Venta(14),
				new Venta(15),
				new Venta(16),
				new Venta(17),
				new Venta(18),
				new Venta(19),
				new Venta(20),
				new Venta(21),
				new Venta(22),
				new Venta(23),
				new Venta(24),
				new Venta(25),
				new Venta(26),
				new Venta(27),
				new Venta(28),
				new Venta(29),
				new Venta(30),
				new Venta(31),
				new Venta(32),
				new Venta(33),
				new Venta(34),
				new Venta(35),
				new Venta(36)
			);
				
	}
}
