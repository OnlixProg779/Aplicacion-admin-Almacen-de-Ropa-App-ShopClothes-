package modeloEntidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the vista_detalle_notaentrega database table.
 * 
 */
@Entity
@Table(name="vista_detalle_notaentrega")
@NamedQuery(name="VistaDetalleNotaentrega.findAll", query="SELECT v FROM VistaDetalleNotaentrega v")
public class VistaDetalleNotaentrega implements Serializable, Comparable<VistaDetalleNotaentrega> {
	private static final long serialVersionUID = 1L;

	private Integer cantidad;

	private double costo;

	private Integer idnotaentrega;

	private Integer idproducto;

	@Column(length=50)
	private String nombre;

	public VistaDetalleNotaentrega() {
	}

	public VistaDetalleNotaentrega(Integer cantidad, double costo, Integer idnotaentrega, Integer idproducto,
			String nombre) {
		super();
		this.cantidad = cantidad;
		this.costo = costo;
		this.idnotaentrega = idnotaentrega;
		this.idproducto = idproducto;
		this.nombre = nombre;
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public int compareTo(VistaDetalleNotaentrega o) {
		// TODO Auto-generated method stub
		return idproducto - o.idproducto;
	}

}