package modelo;

import java.util.Date;
import java.util.List;
import modelo.ItemVenta;
import modelo.Empleado;
import modelo.Cliente;

public class Venta {
	private int idVenta;
	private Date fecha;
	private String nroTicket;
	private float total;
	private String tipoPago;
	private List<ItemVenta> lstItems;
	private Empleado empleado_caja;
	private Empleado empleado_venta;
	private Cliente cliente;
	
	public Venta(int idVenta, Date fecha, String nroTicket, float total, String tipoPago, 
			List<ItemVenta> lstItems, Empleado empleado_caja, Empleado empleado_venta, Cliente cliente) {
		super();
		this.idVenta = idVenta;
		this.fecha = fecha;
		this.nroTicket = nroTicket;
		this.total = total;
		this.tipoPago = tipoPago;
		this.lstItems = lstItems;
		this.empleado_caja = empleado_caja;
		this.empleado_venta = empleado_venta;
		this.cliente = cliente;
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

	public String getNroTicket() {
		return nroTicket;
	}

	public void setNroTicket(String nroTicket) {
		this.nroTicket = nroTicket;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public String getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
