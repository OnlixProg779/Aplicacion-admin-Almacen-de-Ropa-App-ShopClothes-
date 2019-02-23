package modeloEntidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the devolucionesasocios database table.
 * 
 */
@Entity
@Table(name="devolucionesasocios")
@NamedQuery(name="Devolucionesasocio.findAll", query="SELECT d FROM Devolucionesasocio d")
public class Devolucionesasocio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(nullable=false)
	private Integer cantidad;

	@Column(nullable=false, length=100)
	private String descripcion;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date fecha;

	@Column(nullable=false)
	private Integer idnotaentrega;

	@Column(nullable=false)
	private Integer idproducto;

	public Devolucionesasocio() {
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
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

}