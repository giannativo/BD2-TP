package modelo;

import modelo.Domicilio;
import modelo.Empleado;
import java.util.List;;

public class Sucursal {
	private int idSucursal;
	private Domicilio domicilio;
	private List<Empleado> lstEmpleados;
	private Empleado encargado;
	
	public Sucursal(int idSucursal, Domicilio domicilio, List<Empleado> lstEmpleados, Empleado encargado) {
		super();
		this.idSucursal = idSucursal;
		this.domicilio = domicilio;
		this.lstEmpleados = lstEmpleados;
		this.encargado = encargado;
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

	public Empleado getEncargado() {
		return encargado;
	}

	public void setEncargado(Empleado encargado) {
		this.encargado = encargado;
	}
	
	
	
}
