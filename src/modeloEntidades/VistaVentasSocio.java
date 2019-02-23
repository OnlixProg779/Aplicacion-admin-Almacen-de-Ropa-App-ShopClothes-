package modeloEntidades;


import java.sql.Date;
import java.time.LocalDate;

public class VistaVentasSocio {

	private String numFactura;
	private String producto;
	private int cantidad;
	private double precioUnit;
	private double precioTot;
	private java.sql.Date fecha;
	private double costo;
	private double costotot;
	
	


	public VistaVentasSocio(String numFactura, String producto, int cantidad, double precioUnit, double precioTot,
			Date fecha, double costo, double costotot) {
		super();
		this.numFactura = numFactura;
		this.producto = producto;
		this.cantidad = cantidad;
		this.precioUnit = precioUnit;
		this.precioTot = precioTot;
		this.fecha = fecha;
		this.costo = costo;
		this.costotot = costotot;
	}


	public VistaVentasSocio() {
		super();
	}


	public double getCosto() {
		return costo;
	}


	public void setCosto(double costo) {
		this.costo = costo;
	}


	public double getCostotot() {
		return costotot;
	}


	public void setCostotot(double costotot) {
		this.costotot = costotot;
	}


	public String getNumFactura() {
		return numFactura;
	}


	public void setNumFactura(String numFactura) {
		this.numFactura = numFactura;
	}




	public String getProducto() {
		return producto;
	}


	public void setProducto(String producto) {
		this.producto = producto;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public double getPrecioUnit() {
		return precioUnit;
	}


	public void setPrecioUnit(double precioUnit) {
		this.precioUnit = precioUnit;
	}


	public double getPrecioTot() {
		return precioTot;
	}


	public void setPrecioTot(double precioTot) {
		this.precioTot = precioTot;
	}


	public java.sql.Date getFecha() {
		return fecha;
	}


	public void setFecha(java.sql.Date fecha) {
		this.fecha = fecha;
	}
	
	
}
