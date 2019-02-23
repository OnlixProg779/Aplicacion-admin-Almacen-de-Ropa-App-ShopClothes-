package modeloEntidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the abonos database table.
 * 
 */
@Entity
@Table(name="abonos")
@NamedQuery(name="Abono.findAll", query="SELECT a FROM Abono a")
public class Abono implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer idabonos;

	@Column(length=100)
	private String descripcion;

	@Column(name="pk_cuentas_por_cobrar_fk", nullable=false)
	private Integer pkCuentasPorCobrarFk;

	@Column(nullable=false)
	private double valor;

	public Abono() {
	}

	public Abono(String descripcion, Integer pkCuentasPorCobrarFk, double valor) {
		super();
		this.descripcion = descripcion;
		this.pkCuentasPorCobrarFk = pkCuentasPorCobrarFk;
		this.valor = valor;
	}

	public Integer getIdabonos() {
		return this.idabonos;
	}

	public void setIdabonos(Integer idabonos) {
		this.idabonos = idabonos;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getPkCuentasPorCobrarFk() {
		return this.pkCuentasPorCobrarFk;
	}

	public void setPkCuentasPorCobrarFk(Integer pkCuentasPorCobrarFk) {
		this.pkCuentasPorCobrarFk = pkCuentasPorCobrarFk;
	}

	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}