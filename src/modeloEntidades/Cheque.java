package modeloEntidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the cheques database table.
 * 
 */
@Entity
@Table(name="cheques")
@NamedQuery(name="Cheque.findAll", query="SELECT c FROM Cheque c")
public class Cheque implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer idcheque;

	@Column(name="cheque_banco", length=50)
	private String chequeBanco;

	@Column(length=100)
	private String descripcion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_deposito", nullable=false)
	private Date fechaDeposito;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_ingreso")
	private Date fechaIngreso;

	@Column(name="n_cheque")
	private Integer nCheque;

	@Column(name="pk_cuentaxcobrar_fk")
	private Integer pkCuentaxcobrarFk;

	@Column(name="titular_cuenta", length=50)
	private String titularCuenta;

	@Column(nullable=false)
	private double valor;

	public Cheque() {
	}

	public Cheque(String chequeBanco, String descripcion, Date fechaDeposito, Date fechaIngreso, Integer nCheque,
			Integer pkCuentaxcobrarFk, String titularCuenta, double valor) {
		super();
		this.chequeBanco = chequeBanco;
		this.descripcion = descripcion;
		this.fechaDeposito = fechaDeposito;
		this.fechaIngreso = fechaIngreso;
		this.nCheque = nCheque;
		this.pkCuentaxcobrarFk = pkCuentaxcobrarFk;
		this.titularCuenta = titularCuenta;
		this.valor = valor;
	}

	public Integer getIdcheque() {
		return this.idcheque;
	}

	public void setIdcheque(Integer idcheque) {
		this.idcheque = idcheque;
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

	public Integer getNCheque() {
		return this.nCheque;
	}

	public void setNCheque(Integer nCheque) {
		this.nCheque = nCheque;
	}

	public Integer getPkCuentaxcobrarFk() {
		return this.pkCuentaxcobrarFk;
	}

	public void setPkCuentaxcobrarFk(Integer pkCuentaxcobrarFk) {
		this.pkCuentaxcobrarFk = pkCuentaxcobrarFk;
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

}