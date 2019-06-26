package modelo;

import modelo.Domicilio;
import modelo.ObraSocial;

public class Cliente {
	private int idPersona;
	private String apellido;
	private String nombre;
	private int dni;
	private Domicilio domicilio;
	private ObraSocial obraSocial;
	
	public Cliente(int idPersona, String apellido, String nombre, int dni, Domicilio domicilio, ObraSocial obraSocial) {
		super();
		this.idPersona = idPersona;
		this.apellido = apellido;
		this.nombre = nombre;
		this.dni = dni;
		this.domicilio = domicilio;
		this.obraSocial = obraSocial;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}

	public ObraSocial getObraSocial() {
		return obraSocial;
	}

	public void setObraSocial(ObraSocial obraSocial) {
		this.obraSocial = obraSocial;
	}	
	
}
