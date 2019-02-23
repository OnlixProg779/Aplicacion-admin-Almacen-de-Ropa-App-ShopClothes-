package modeloEntidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the bodega database table.
 * 
 */
@Entity
@Table(name="bodega")
@NamedQuery(name="Bodega.findAll", query="SELECT b FROM Bodega b")
public class Bodega implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer idproducto;

	private double costo;

	@Column(length=100)
	private String detalle;

	private Integer idcategoria;

	private Integer idsocio;

	@Column(length=50)
	private String nombre;

	private Integer stock;

	public Bodega() {
	}

	public Bodega(Integer idproducto, String nombre) {
		super();
		this.idproducto = idproducto;
		this.nombre = nombre;
	}



	public Bodega(Integer idproducto, double costo, String detalle, Integer idcategoria, Integer idsocio, String nombre,
			Integer stock) {
		super();
		this.idproducto = idproducto;
		this.costo = costo;
		this.detalle = detalle;
		this.idcategoria = idcategoria;
		this.idsocio = idsocio;
		this.nombre = nombre;
		this.stock = stock;
	}



	public Bodega(double costo, String detalle, Integer idcategoria, Integer idsocio, String nombre, Integer stock) {
		super();
		this.costo = costo;
		this.detalle = detalle;
		this.idcategoria = idcategoria;
		this.idsocio = idsocio;
		this.nombre = nombre;
		this.stock = stock;
	}

	public Integer getIdproducto() {
		return this.idproducto;
	}

	public void setIdproducto(Integer idproducto) {
		this.idproducto = idproducto;
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

	public Integer getIdcategoria() {
		return this.idcategoria;
	}

	public void setIdcategoria(Integer idcategoria) {
		this.idcategoria = idcategoria;
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

}