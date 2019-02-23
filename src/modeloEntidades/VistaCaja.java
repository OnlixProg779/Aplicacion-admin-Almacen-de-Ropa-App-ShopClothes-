package modeloEntidades;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the vista_caja database table.
 * 
 */
@Entity
@Table(name="vista_caja")
@NamedQuery(name="VistaCaja.findAll", query="SELECT v FROM VistaCaja v")
public class VistaCaja implements Serializable, Comparable<VistaCaja> {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private Time hora;

	private Integer idcaja;

	@Column(length=50)
	private String numerofactura;

	@Column(length=100)
	private String referencia;

	private double valor;

	public VistaCaja() {
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

	public Integer getIdcaja() {
		return this.idcaja;
	}

	public void setIdcaja(Integer idcaja) {
		this.idcaja = idcaja;
	}

	public String getNumerofactura() {
		return this.numerofactura;
	}

	public void setNumerofactura(String numerofactura) {
		this.numerofactura = numerofactura;
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

	@Override
	public int compareTo(VistaCaja o) {
		// TODO Auto-generated method stub
		return idcaja-o.idcaja;
	}

}