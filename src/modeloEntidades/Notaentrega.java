package modeloEntidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the notaentrega database table.
 * 
 */
@Entity
@Table(name="notaentrega")
@NamedQuery(name="Notaentrega.findAll", query="SELECT n FROM Notaentrega n")
public class Notaentrega implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer idnotadeeentrega;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date fecha;

	@Column(nullable=false)
	private Integer idsocio;

	@Column(name="numero_nota_entrega", length=50)
	private String numeroNotaEntrega;

	@Column(length=50)
	private String receptor;

	public Notaentrega() {
	}
	
	public Notaentrega(Integer idnotadeeentrega, Date fecha, Integer idsocio, String numeroNotaEntrega,
			String receptor) {
		super();
		this.idnotadeeentrega = idnotadeeentrega;
		this.fecha = fecha;
		this.idsocio = idsocio;
		this.numeroNotaEntrega = numeroNotaEntrega;
		this.receptor = receptor;
	}

	public Integer getIdnotadeeentrega() {
		return this.idnotadeeentrega;
	}

	public void setIdnotadeeentrega(Integer idnotadeeentrega) {
		this.idnotadeeentrega = idnotadeeentrega;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getIdsocio() {
		return this.idsocio;
	}

	public void setIdsocio(Integer idsocio) {
		this.idsocio = idsocio;
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

}