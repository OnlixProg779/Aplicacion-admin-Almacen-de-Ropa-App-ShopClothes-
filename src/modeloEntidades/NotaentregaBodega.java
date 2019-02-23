package modeloEntidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the notaentrega_bodega database table.
 * 
 */
@Entity
@Table(name="notaentrega_bodega")
@NamedQuery(name="NotaentregaBodega.findAll", query="SELECT n FROM NotaentregaBodega n")
public class NotaentregaBodega implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(nullable=false)
	private Integer cantidad;

	private double costo;

	@Column(nullable=false)
	private Integer idnotaentrega;

	@Column(nullable=false)
	private Integer idproducto;

	public NotaentregaBodega() {
	}
	
	public NotaentregaBodega(Integer cantidad, double costo, Integer idproducto) {
		super();
		this.cantidad = cantidad;
		this.costo = costo;
		this.idproducto = idproducto;
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

}