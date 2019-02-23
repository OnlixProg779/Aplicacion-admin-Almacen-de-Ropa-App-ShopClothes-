package modeloEntidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the vista_cheques database table.
 * 
 */
@Entity
@Table(name="vista_cheques")
@NamedQuery(name="VistaCheque.findAll", query="SELECT v FROM VistaCheque v")
public class VistaCheque implements Serializable,Comparable<VistaCheque> {
	private static final long serialVersionUID = 1L;

	@Column(name="cheque_banco", length=50)
	private String chequeBanco;

	@Column(length=100)
	private String descripcion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_deposito")
	private Date fechaDeposito;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_ingreso")
	private Date fechaIngreso;

	private Integer idcheque;

	@Column(name="n_cheque")
	private Integer nCheque;

	@Column(name="n_factura", length=50)
	private String nFactura;

	@Column(name="titular_cuenta", length=50)
	private String titularCuenta;

	private double valor;

	public VistaCheque() {
	}

	public String getChequeBanco() {
		return this.chequeBanco;
	}

	public void setChequeBanco(String chequeBanco) {
		this.chequeBanco = chequeBanco;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaDeposito() {
		return this.fechaDeposito;
	}

	public void setFechaDeposito(Date fechaDeposito) {
		this.fechaDeposito = fechaDeposito;
	}

	public Date getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Integer getIdcheque() {
		return this.idcheque;
	}

	public void setIdcheque(Integer idcheque) {
		this.idcheque = idcheque;
	}

	public Integer getNCheque() {
		return this.nCheque;
	}

	public void setNCheque(Integer nCheque) {
		this.nCheque = nCheque;
	}

	public String getNFactura() {
		return this.nFactura;
	}

	public void setNFactura(String nFactura) {
		this.nFactura = nFactura;
	}

	public String getTitularCuenta() {
		return this.titularCuenta;
	}

	public void setTitularCuenta(String titularCuenta) {
		this.titularCuenta = titularCuenta;
	}

	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public int compareTo(VistaCheque o) {
		// TODO Auto-generated method stub
		return idcheque-o.idcheque;
	}

}