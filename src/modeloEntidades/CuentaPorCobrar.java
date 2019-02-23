package modeloEntidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cuenta_por_cobrar database table.
 * 
 */
@Entity
@Table(name="cuenta_por_cobrar")
@NamedQuery(name="CuentaPorCobrar.findAll", query="SELECT c FROM CuentaPorCobrar c")
public class CuentaPorCobrar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idcuenta_por_cobrar", unique=true, nullable=false)
	private Integer idcuentaPorCobrar;

	@Column(length=100)
	private String descripcion;

	@Column(nullable=false, length=50)
	private String estado;

	@Column(name="n_factura", nullable=false, length=50)
	private String nFactura;

	@Column(nullable=false)
	private double valorpendiente;

	public CuentaPorCobrar() {
	}

	public Integer getIdcuentaPorCobrar() {
		return this.idcuentaPorCobrar;
	}

	public void setIdcuentaPorCobrar(Integer idcuentaPorCobrar) {
		this.idcuentaPorCobrar = idcuentaPorCobrar;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNFactura() {
		return this.nFactura;
	}

	public void setNFactura(String nFactura) {
		this.nFactura = nFactura;
	}

	public double getValorpendiente() {
		return this.valorpendiente;
	}

	public void setValorpendiente(double valorpendiente) {
		this.valorpendiente = valorpendiente;
	}

}