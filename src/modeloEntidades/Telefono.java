package modeloEntidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the telefonos database table.
 * 
 */
@Entity
@Table(name="telefonos")
@NamedQuery(name="Telefono.findAll", query="SELECT t FROM Telefono t")
public class Telefono implements Serializable {
	private static final long serialVersionUID = 1L;



	public Telefono(Integer idsocio, String referencia, String telefono) {
		super();
		this.idsocio = idsocio;
		this.referencia = referencia;
		this.telefono = telefono;
	}

	@Column(nullable=false)
	private Integer idsocio;

	@Column(length=50)
	private String referencia;

	@Column(nullable=false, length=50)
	private String telefono;

	public Telefono() {
	}

	public Integer getIdsocio() {
		return this.idsocio;
	}

	public void setIdsocio(Integer idsocio) {
		this.idsocio = idsocio;
	}

	public String getReferencia() {
		return this.referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}