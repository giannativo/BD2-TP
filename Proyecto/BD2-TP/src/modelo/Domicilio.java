package modelo;

import modelo.Localidad;
import modelo.Provincia;

public class Domicilio {
	private int idDomicilio;
	private String calle;
	private String numero;
	private Localidad localidad;
	private Provincia provincia;
	
	public Domicilio(int idDomicilio, String calle, String numero, Localidad localidad, Provincia provincia) {
		super();
		this.idDomicilio = idDomicilio;
		this.calle = calle;
		this.numero = numero;
		this.localidad = localidad;
		this.provincia = provincia;
	}

	public int getIdDomicilio() {
		return idDomicilio;
	}

	public void setIdDomicilio(int idDomicilio) {
		this.idDomicilio = idDomicilio;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	
	
	
}
