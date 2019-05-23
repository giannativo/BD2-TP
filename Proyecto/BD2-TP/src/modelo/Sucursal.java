package modelo;

import modelo.Domicilio;
import modelo.Empleado;
import java.util.List;;

public class Sucursal {
	private int idSucursal;
	private Domicilio domicilio;
	private List<Empleado> lstEmpleados;
	
	public Sucursal(int idSucursal, Domicilio domicilio, List<Empleado> lstEmpleados) {
		super();
		this.idSucursal = idSucursal;
		this.domicilio = domicilio;
		this.lstEmpleados = lstEmpleados;
	}

	public int getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}

	public List<Empleado> getLstEmpleados() {
		return lstEmpleados;
	}

	public void setLstEmpleados(List<Empleado> lstEmpleados) {
		this.lstEmpleados = lstEmpleados;
	}
	
	
}
