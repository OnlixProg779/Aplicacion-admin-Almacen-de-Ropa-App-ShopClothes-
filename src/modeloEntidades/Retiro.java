package modeloEntidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the retiros database table.
 * 
 */
@Entity
@Table(name="retiros")
@NamedQuery(name="Retiro.findAll", query="SELECT r FROM Retiro r")
public class Retiro implements Serializable, Comparable<Retiro> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer idretiros;

	@Column(length=100)
	private String descripcion;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date fecha;

	@Column(nullable=false)
	private Integer idcuenta;

	@Column(nullable=false)
	private double valor;

	public Retiro() {
	}

	public Retiro(String descripcion, Date fecha, Integer idcuenta, double valor) {
		super();
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.idcuenta = idcuenta;
		this.valor = valor;
	}

	public Integer getIdretiros() {
		return this.idretiros;
	}

	public void setIdretiros(Integer idretiros) {
		this.idretiros = idretiros;
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

	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public int compareTo(Retiro o) {
		// TODO Auto-generated method stub
		return idretiros-o.idretiros;
	}

}