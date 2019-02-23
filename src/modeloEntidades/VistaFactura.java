package modeloEntidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the vista_factura database table.
 * 
 */
@Entity
@Table(name="vista_factura")
@NamedQuery(name="VistaFactura.findAll", query="SELECT v FROM VistaFactura v")
public class VistaFactura implements Serializable, Comparable<VistaFactura> {
	private static final long serialVersionUID = 1L;

	@Column(length=50)
	private String apellido;

	@Column(name="cliente_cedula", length=13)
	private String clienteCedula;

	private Boolean credito;

	@Column(length=100)
	private String direccion;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private double iva;

	@Column(length=50)
	private String nombre;

	@Column(length=50)
	private String numerofactura;

	private double subtotal;

	@Column(length=50)
	private String telefono;

	private double total;

	public VistaFactura() {
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getClienteCedula() {
		return this.clienteCedula;
	}

	public void setClienteCedula(String clienteCedula) {
		this.clienteCedula = clienteCedula;
	}

	public Boolean getCredito() {
		return this.credito;
	}

	public void setCredito(Boolean credito) {
		this.credito = credito;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getIva() {
		return this.iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumerofactura() {
		return this.numerofactura;
	}

	public void setNumerofactura(String numerofactura) {
		this.numerofactura = numerofactura;
	}

	public double getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public int compareTo(VistaFactura o) {
		// TODO Auto-generated method stub
		return numerofactura.compareTo(o.numerofactura);
	}

}