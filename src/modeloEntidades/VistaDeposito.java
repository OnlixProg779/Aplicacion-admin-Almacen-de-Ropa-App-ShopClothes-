package modeloEntidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the vista_depositos database table.
 * 
 */
@Entity
@Table(name="vista_depositos")
@NamedQuery(name="VistaDeposito.findAll", query="SELECT v FROM VistaDeposito v")
public class VistaDeposito implements Serializable,Comparable<VistaDeposito> {
	private static final long serialVersionUID = 1L;

	@Column(name="cheque_banco", length=50)
	private String chequeBanco;

	@Column(length=50)
	private String depositante;

	@Column(length=100)
	private String descripcion;

	@Column(length=100)
	private String detallecheque;

	private double efectivo;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private Integer iddeposito;

	@Column(name="n_cheque")
	private Integer nCheque;

	@Column(name="n_deposito", length=50)
	private String nDeposito;

	@Column(length=25)
	private String ncuenta;

	@Column(length=20)
	private String tipo;

	@Column(length=50)
	private String titular;

	private double total;

	private double valor;

	public VistaDeposito() {
	}

	public String getChequeBanco() {
		return this.chequeBanco;
	}

	public void setChequeBanco(String chequeBanco) {
		this.chequeBanco = chequeBanco;
	}

	public String getDepositante() {
		return this.depositante;
	}

	public void setDepositante(String depositante) {
		this.depositante = depositante;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDetallecheque() {
		return this.detallecheque;
	}

	public void setDetallecheque(String detallecheque) {
		this.detallecheque = detallecheque;
	}

	public double getEfectivo() {
		return this.efectivo;
	}

	public void setEfectivo(double efectivo) {
		this.efectivo = efectivo;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getIddeposito() {
		return this.iddeposito;
	}

	public void setIddeposito(Integer iddeposito) {
		this.iddeposito = iddeposito;
	}

	public Integer getNCheque() {
		return this.nCheque;
	}

	public void setNCheque(Integer nCheque) {
		this.nCheque = nCheque;
	}

	public String getNDeposito() {
		return this.nDeposito;
	}

	public void setNDeposito(String nDeposito) {
		this.nDeposito = nDeposito;
	}

	public String getNcuenta() {
		return this.ncuenta;
	}

	public void setNcuenta(String ncuenta) {
		this.ncuenta = ncuenta;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTitular() {
		return this.titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public int compareTo(VistaDeposito o) {
		// TODO Auto-generated method stub
		return iddeposito-o.iddeposito;
	}

}