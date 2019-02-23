package modeloEntidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the depositos database table.
 * 
 */
@Entity
@Table(name="depositos")
@NamedQuery(name="Deposito.findAll", query="SELECT d FROM Deposito d")
public class Deposito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer iddeposito;

	private Integer cheque;

	@Column(length=50)
	private String depositante;

	@Column(length=100)
	private String descripcion;

	private double efectivo;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date fecha;

	@Column(nullable=false)
	private Integer idcuenta;

	@Column(name="n_deposito", length=50)
	private String nDeposito;

	@Column(nullable=false)
	private double total;

	public Deposito() {
	}


	
	public Deposito(Integer cheque, String depositante, String descripcion, double efectivo, Date fecha,
			Integer idcuenta, String nDeposito) {
		super();
		this.cheque = cheque;
		this.depositante = depositante;
		this.descripcion = descripcion;
		this.efectivo = efectivo;
		this.fecha = fecha;
		this.idcuenta = idcuenta;
		this.nDeposito = nDeposito;
	}


	public Integer getIddeposito() {
		return this.iddeposito;
	}

	public void setIddeposito(Integer iddeposito) {
		this.iddeposito = iddeposito;
	}

	public Integer getCheque() {
		return this.cheque;
	}

	public void setCheque(Integer cheque) {
		this.cheque = cheque;
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

	public Integer getIdcuenta() {
		return this.idcuenta;
	}

	public void setIdcuenta(Integer idcuenta) {
		this.idcuenta = idcuenta;
	}

	public String getNDeposito() {
		return this.nDeposito;
	}

	public void setNDeposito(String nDeposito) {
		this.nDeposito = nDeposito;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}