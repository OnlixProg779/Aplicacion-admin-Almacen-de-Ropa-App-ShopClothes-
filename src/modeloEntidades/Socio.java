package modeloEntidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the socios database table.
 * 
 */
@Entity
@Table(name="socios")
@NamedQuery(name="Socio.findAll", query="SELECT s FROM Socio s")
public class Socio implements Serializable,Comparable<Socio> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer idsocio;

	@Column(nullable=false, length=50)
	private String nombre;

	@Column(length=50)
	private String referencia;

	@Column(length=13)
	private String ruc;

	private List<Telefono> telefonos; 
	public Socio() {
	}

	public Socio(Integer idsocio, String nombre, String referencia, String ruc) {
		super();
		this.idsocio = idsocio;
		this.nombre = nombre;
		this.referencia = referencia;
		this.ruc = ruc;
		
	}

	public Socio(String nombre, String referencia, String ruc) {
		super();
		this.nombre = nombre;
		this.referencia = referencia;
		this.ruc = ruc;
	}

	public Socio(Integer idsocio) {
		super();
		this.idsocio = idsocio;
	}
	
	
	
	public Socio(String nombre) {
		super();
		this.nombre = nombre;
	}

	public List<Telefono> getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(List<Telefono> telefonos) {
		this.telefonos = telefonos;
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

	public String getReferencia() {
		return this.referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getRuc() {
		return this.ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	
	
	@Override
	public String toString() {
		return nombre;
	}

	@Override
	public int compareTo(Socio o) {
		// TODO Auto-generated method stub
		return idsocio-o.idsocio;
	}

}