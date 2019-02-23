package modeloEntidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the vista_detallefactura database table.
 * 
 */
@Entity
@Table(name="vista_detallefactura")
@NamedQuery(name="VistaDetallefactura.findAll", query="SELECT v FROM VistaDetallefactura v")
public class VistaDetallefactura implements Serializable, Comparable<VistaDetallefactura> {
	private static final long serialVersionUID = 1L;

	private Integer cantidad;

	private Integer idproducto;

	@Column(length=50)
	private String nombre;

	@Column(length=50)
	private String numerofactura;

	private double preciototal;

	private double preciounitario;

	public VistaDetallefactura() {
	}

	public VistaDetallefactura(Integer cantidad, Integer idproducto, String nombre, String numerofactura,
			double preciototal, double preciounitario) {
		super();
		this.cantidad = cantidad;
		this.idproducto = idproducto;
		this.nombre = nombre;
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

	@Override
	public int compareTo(VistaDetallefactura o) {
		// TODO Auto-generated method stub
		return idproducto - o.idproducto;
	}

}