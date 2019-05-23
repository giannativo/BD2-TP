package modelo;

public class Localidad {
	private int idLocalidad;
	private String localidad;
	
	public Localidad(int idLocalidad, String localidad) {
		super();
		this.idLocalidad = idLocalidad;
		this.localidad = localidad;
	}

	public int getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(int idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	
}
