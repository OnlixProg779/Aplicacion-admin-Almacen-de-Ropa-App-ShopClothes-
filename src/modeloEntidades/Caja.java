package modeloEntidades;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the caja database table.
 * 
 */
@Entity
@Table(name="caja")
@NamedQuery(name="Caja.findAll", query="SELECT c FROM Caja c")
public class Caja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer idcaja;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date fecha;

	@Column(nullable=false)
	private Time hora;

	@Column(name="pk_ventafactura_fk", length=50)
	private String pkVentafacturaFk;

	@Column(length=100)
	private String referencia;

	@Column(nullable=false)
	private double valor;

	public Caja() {
	}

	public Caja(Date fecha, Time hora, String pkVentafacturaFk, String referencia, double valor) {
		super();
		this.fecha = fecha;
		this.hora = hora;
		this.pkVentafacturaFk = pkVentafacturaFk;
		this.referencia = referencia;
		this.valor = valor;
	}

	public Integer getIdcaja() {
		return this.idcaja;
	}

	public void setIdcaja(Integer idcaja) {
		this.idcaja = idcaja;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Time getHora() {
		return this.hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public String getPkVentafacturaFk() {
		return this.pkVentafacturaFk;
	}

	public void setPkVentafacturaFk(String pkVentafacturaFk) {
		this.pkVentafacturaFk = pkVentafacturaFk;
	}

	public String getReferencia() {
		return this.referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}