package modeloEntidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the detallefactura database table.
 * 
 */
@Entity
@Table(name="detallefactura")
@NamedQuery(name="Detallefactura.findAll", query="SELECT d FROM Detallefactura d")
public class Detallefactura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(nullable=false)
	private Integer cantidad;

	@Column(nullable=false)
	private Integer idproducto;

	@Column(nullable=false, length=50)
	private String numerofactura;

	@Column(nullable=false)
	private double preciototal;

	@Column(nullable=false)
	private double preciounitario;

	public Detallefactura() {
	}

	public Detallefactura(Integer cantidad, Integer idproducto, String numerofactura, double preciototal,
			double preciounitario) {
		super();
		this.cantidad = cantidad;
		this.idproducto = idproducto;
		this.numerofactura = numerofactura;
		this.preciototal = preciototal;
		this.preciounitario = preciounitario;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getIdproducto() {
		return this.idproducto;
	}

	public void setIdproducto(Integer idproducto) {
		this.idproducto = idproducto;
	}

	public String getNumerofactura() {
		return this.numerofactura;
	}

	public void setNumerofactura(String numerofactura) {
		this.numerofactura = numerofactura;
	}

	public double getPreciototal() {
		return this.preciototal;
	}

	public void setPreciototal(double preciototal) {
		this.preciototal = preciototal;
	}

	public double getPreciounitario() {
		return this.preciounitario;
	}

	public void setPreciounitario(double preciounitario) {
		this.preciounitario = preciounitario;
	}

}