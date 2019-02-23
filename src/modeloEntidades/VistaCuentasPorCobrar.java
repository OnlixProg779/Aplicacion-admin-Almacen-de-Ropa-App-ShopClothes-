package modeloEntidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the vista_cuentas_por_cobrar database table.
 * 
 */
@Entity
@Table(name="vista_cuentas_por_cobrar")
@NamedQuery(name="VistaCuentasPorCobrar.findAll", query="SELECT v FROM VistaCuentasPorCobrar v")
public class VistaCuentasPorCobrar implements Serializable, Comparable<VistaCuentasPorCobrar> {
	private static final long serialVersionUID = 1L;

	@Column(name="cliente_cedula", length=13)
	private String clienteCedula;

	@Column(length=100)
	private String descripcion;

	@Column(length=50)
	private String estado;

	@Column(name="idcuenta_por_cobrar")
	private Integer idcuentaPorCobrar;

	@Column(length=50)
	private String numerofactura;

	private double valorpendiente;

	public VistaCuentasPorCobrar() {
	}

	public String getClienteCedula() {
		return this.clienteCedula;
	}

	public void setClienteCedula(String clienteCedula) {
		this.clienteCedula = clienteCedula;
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

	public Integer getIdcuentaPorCobrar() {
		return this.idcuentaPorCobrar;
	}

	public void setIdcuentaPorCobrar(Integer idcuentaPorCobrar) {
		this.idcuentaPorCobrar = idcuentaPorCobrar;
	}

	public String getNumerofactura() {
		return this.numerofactura;
	}

	public void setNumerofactura(String numerofactura) {
		this.numerofactura = numerofactura;
	}

	public double getValorpendiente() {
		return this.valorpendiente;
	}

	public void setValorpendiente(double valorpendiente) {
		this.valorpendiente = valorpendiente;
	}

	@Override
	public int compareTo(VistaCuentasPorCobrar o) {
		// TODO Auto-generated method stub
		return idcuentaPorCobrar - o.idcuentaPorCobrar;
	}

}