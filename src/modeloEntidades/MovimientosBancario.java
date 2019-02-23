package modeloEntidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the movimientos_bancarios database table.
 * 
 */
@Entity
@Table(name="movimientos_bancarios")
@NamedQuery(name="MovimientosBancario.findAll", query="SELECT m FROM MovimientosBancario m")
public class MovimientosBancario implements Serializable,Comparable<MovimientosBancario> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_movimientos", unique=true, nullable=false)
	private Integer idMovimientos;

	@Column(length=50)
	private String codigo;

	private double deposito;

	@Column(length=100)
	private String descripcion;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date fecha;

	@Column(nullable=false)
	private Integer idcuenta;

	private double retiro;

	@Column(nullable=false)
	private double saldo;

	public MovimientosBancario() {
	}

	public Integer getIdMovimientos() {
		return this.idMovimientos;
	}

	public void setIdMovimientos(Integer idMovimientos) {
		this.idMovimientos = idMovimientos;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public double getDeposito() {
		return this.deposito;
	}

	public void setDeposito(double deposito) {
		this.deposito = deposito;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public double getRetiro() {
		return this.retiro;
	}

	public void setRetiro(double retiro) {
		this.retiro = retiro;
	}

	public double getSaldo() {
		return this.saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	@Override
	public int compareTo(MovimientosBancario o) {
		// TODO Auto-generated method stub
		return idMovimientos - o.idMovimientos;
	}

}