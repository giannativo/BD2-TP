package modelo;

import modelo.Producto;

public class ItemVenta {
	private int idItemVenta;
	private Producto producto;
	private int cantidad;
	private float precio;
	
	public ItemVenta(int idItemVenta, Producto producto, int cantidad, float precio) {
		super();
		this.idItemVenta = idItemVenta;
		this.producto = producto;
		this.cantidad = cantidad;
		this.precio = precio;
	}

	public int getIdItemVenta() {
		return idItemVenta;
	}

	public void setIdItemVenta(int idItemVenta) {
		this.idItemVenta = idItemVenta;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
}
