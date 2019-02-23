package modeloEntidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the vista_notaentrega database table.
 * 
 */
@Entity
@Table(name="vista_notaentrega")
@NamedQuery(name="VistaNotaentrega.findAll", query="SELECT v FROM VistaNotaentrega v")
public class VistaNotaentrega implements Serializable, Comparable<VistaNotaentrega> {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private Integer idnotadeeentrega;

	private Integer idsocio;

	@Column(length=50)
	private String nombre;

	@Column(name="numero_nota_entrega", length=50)
	private String numeroNotaEntrega;

	@Column(length=50)
	private String receptor;

	public VistaNotaentrega() {
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getIdnotadeeentrega() {
		return this.idnotadeeentrega;
	}

	public void setIdnotadeeentrega(Integer idnotadeeentrega) {
		this.idnotadeeentrega = idnotadeeentrega;
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

	public String getNumeroNotaEntrega() {
		return this.numeroNotaEntrega;
	}

	public void setNumeroNotaEntrega(String numeroNotaEntrega) {
		this.numeroNotaEntrega = numeroNotaEntrega;
	}

	public String getReceptor() {
		return this.receptor;
	}

	public void setReceptor(String receptor) {
		this.receptor = receptor;
	}

	@Override
	public int compareTo(VistaNotaentrega o) {
		// TODO Auto-generated method stub
		return idnotadeeentrega - o.idnotadeeentrega;
	}

}