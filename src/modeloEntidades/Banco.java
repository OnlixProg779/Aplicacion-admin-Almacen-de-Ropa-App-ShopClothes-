package modeloEntidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the bancos database table.
 * 
 */
@Entity
@Table(name="bancos")
@NamedQuery(name="Banco.findAll", query="SELECT b FROM Banco b")
public class Banco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer idbanco;

	@Column(length=100)
	private String descripcion;

	@Column(nullable=false, length=50)
	private String nombre;

	public Banco() {
	}

	public Banco(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Integer getIdbanco() {
		return this.idbanco;
	}

	public void setIdbanco(Integer idbanco) {
		this.idbanco = idbanco;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return nombre;
	}

}