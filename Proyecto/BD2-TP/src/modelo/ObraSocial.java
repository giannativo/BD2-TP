package modelo;

public class ObraSocial {
	private int idObraSocial;
	private String nombre;
	private int numeroAfiliado;
	
	public ObraSocial(int idObraSocial, String nombre, int numeroAfiliado) {
		super();
		this.idObraSocial = idObraSocial;
		this.nombre = nombre;
		this.numeroAfiliado = numeroAfiliado;
	}

	public int getIdObraSocial() {
		return idObraSocial;
	}

	public void setIdObraSocial(int idObraSocial) {
		this.idObraSocial = idObraSocial;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumeroAfiliado() {
		return numeroAfiliado;
	}

	public void setNumeroAfiliado(int numeroAfiliado) {
		this.numeroAfiliado = numeroAfiliado;
	} 
}
