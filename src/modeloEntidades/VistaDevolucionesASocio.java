package modeloEntidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the vista_devoluciones_a_socios database table.
 * 
 */
@Entity
@Table(name="vista_devoluciones_a_socios")
@NamedQuery(name="VistaDevolucionesASocio.findAll", query="SELECT v FROM VistaDevolucionesASocio v")
public class VistaDevolucionesASocio implements Serializable, Comparable<VistaDevolucionesASocio> {
	private static final long serialVersionUID = 1L;

	private Integer cantidad;

	private double costo;

	@Column(length=100)
	private String descripcion;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private Integer idnotaentrega;

	private Integer idproducto;

	@Column(length=50)
	private String producto;

	@Column(length=50)
	private String socio;

	public VistaDevolucionesASocio() {
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public double getCosto() {
		return this.costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getIdnotaentrega() {
		return this.idnotaentrega;
	}

	public void setIdnotaentrega(Integer idnotaentrega) {
		this.idnotaentrega = idnotaentrega;
	}

	public Integer getIdproducto() {
		return this.idproducto;
	}

	public void setIdproducto(Integer idproducto) {
		this.idproducto = idproducto;
	}

	public String getProducto() {
		return this.producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getSocio() {
		return this.socio;
	}

	public void setSocio(String socio) {
		this.socio = socio;
	}

	@Override
	public int compareTo(VistaDevolucionesASocio o) {
		// TODO Auto-generated method stub
		return idproducto - o.idproducto;
	}

}