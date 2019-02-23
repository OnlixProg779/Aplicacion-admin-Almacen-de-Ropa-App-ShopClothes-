package modeloEntidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the cuentas_por_pagar database table.
 * 
 */
@Entity
@Table(name="cuentas_por_pagar")
@NamedQuery(name="CuentasPorPagar.findAll", query="SELECT c FROM CuentasPorPagar c")
public class CuentasPorPagar implements Serializable,Comparable<CuentasPorPagar> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer idcuentaxpagar;

	@Column(length=100)
	private String descripcion;

	@Column(nullable=false, length=50)
	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date fecha;

	@Column(nullable=false)
	private double valor;

	public CuentasPorPagar() {
	}
	
	public CuentasPorPagar(String descripcion, String estado, Date fecha, double valor) {
		super();
		this.descripcion = descripcion;
		this.estado = estado;
		this.fecha = fecha;
		this.valor = valor;
	}
	
	public Integer getIdcuentaxpagar() {
		return this.idcuentaxpagar;
	}

	public void setIdcuentaxpagar(Integer idcuentaxpagar) {
		this.idcuentaxpagar = idcuentaxpagar;
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

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public int compareTo(CuentasPorPagar o) {
		// TODO Auto-generated method stub
		return idcuentaxpagar-o.idcuentaxpagar;
	}

}