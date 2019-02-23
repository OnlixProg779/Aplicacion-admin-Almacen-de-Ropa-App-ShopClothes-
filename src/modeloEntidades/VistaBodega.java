package modeloEntidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the vista_bodega database table.
 * 
 */
@Entity
@Table(name="vista_bodega")
@NamedQuery(name="VistaBodega.findAll", query="SELECT v FROM VistaBodega v")
public class VistaBodega implements Serializable, Comparable<VistaBodega> {
	private static final long serialVersionUID = 1L;

	@Column(length=50)
	private String categoria;

	private double costo;

	@Column(length=100)
	private String detalle;

	private Integer idproducto;

	private Integer idsocio;

	@Column(length=50)
	private String nombre;

	private Integer stock;

	public VistaBodega() {
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public double getCosto() {
		return this.costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public String getDetalle() {
		return this.detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Integer getIdproducto() {
		return this.idproducto;
	}

	public void setIdproducto(Integer idproducto) {
		this.idproducto = idproducto;
	}

	public Integer getIdsocio() {
		return this.idsocio;
	}

	public void setIdsocio(Integer idsocio) {
		this.idsocio = idsocio;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getStock() {
		return this.stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	@Override
	public int compareTo(VistaBodega o) {
		// TODO Auto-generated method stub
		return idproducto - o.idproducto;
	}

}