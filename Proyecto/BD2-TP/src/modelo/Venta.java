package modelo;

import java.util.Date;
import java.util.List;
import modelo.ItemVenta;
import modelo.Empleado;

public class Venta {
	private int idVenta;
	private Date fecha;
	private int nroTicket;
	private float total;
	private int tipoPago;
	private int cantProductos;
	private List<ItemVenta> lstItems;
	private Empleado empleado_caja;
	private Empleado empleado_venta;
	
	public Venta(int idVenta, Date fecha, int nroTicket, float total, int tipoPago, int cantProductos,
			List<ItemVenta> lstItems, Empleado empleado_caja, Empleado empleado_venta) {
		super();
		this.idVenta = idVenta;
		this.fecha = fecha;
		this.nroTicket = nroTicket;
		this.total = total;
		this.tipoPago = tipoPago;
		this.cantProductos = cantProductos;
		this.lstItems = lstItems;
		this.empleado_caja = empleado_caja;
		this.empleado_venta = empleado_venta;
	}

	public int getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getNroTicket() {
		return nroTicket;
	}

	public void setNroTicket(int nroTicket) {
		this.nroTicket = nroTicket;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public int getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(int tipoPago) {
		this.tipoPago = tipoPago;
	}

	public int getCantProductos() {
		return cantProductos;
	}

	public void setCantProductos(int cantProductos) {
		this.cantProductos = cantProductos;
	}

	public List<ItemVenta> getLstItems() {
		return lstItems;
	}

	public void setLstItems(List<ItemVenta> lstItems) {
		this.lstItems = lstItems;
	}

	public Empleado getEmpleado_caja() {
		return empleado_caja;
	}

	public void setEmpleado_caja(Empleado empleado_caja) {
		this.empleado_caja = empleado_caja;
	}

	public Empleado getEmpleado_venta() {
		return empleado_venta;
	}

	public void setEmpleado_venta(Empleado empleado_venta) {
		this.empleado_venta = empleado_venta;
	}
	
	
}
