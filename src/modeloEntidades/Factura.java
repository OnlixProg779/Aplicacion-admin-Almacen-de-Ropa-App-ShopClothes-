package modeloEntidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the factura database table.
 * 
 */
@Entity
@Table(name="factura")
@NamedQuery(name="Factura.findAll", query="SELECT f FROM Factura f")
public class Factura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=50)
	private String numerofactura;

	@Column(name="cliente_cedula", nullable=false, length=13)
	private String clienteCedula;

	private Boolean credito;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date fecha;

	private double iva;

	@Column(nullable=false)
	private double subtotal;

	@Column(nullable=false)
	private double total;

	public Factura() {
	}

	public Factura(String numerofactura, String clienteCedula, Boolean credito, Date fecha, double iva, double subtotal,
			double total) {
		super();
		this.numerofactura = numerofactura;
		this.clienteCedula = clienteCedula;
		this.credito = credito;
		this.fecha = fecha;
		this.iva = iva;
		this.subtotal = subtotal;
		this.total = total;
	}

	public String getNumerofactura() {
		return this.numerofactura;
	}

	public void setNumerofactura(String numerofactura) {
		this.numerofactura = numerofactura;
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

	public double getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}