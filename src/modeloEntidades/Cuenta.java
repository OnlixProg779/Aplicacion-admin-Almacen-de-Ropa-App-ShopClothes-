package modeloEntidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cuenta database table.
 * 
 */
@Entity
@Table(name="cuenta")
@NamedQuery(name="Cuenta.findAll", query="SELECT c FROM Cuenta c")
public class Cuenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer idcuenta;

	@Column(nullable=false)
	private Integer idbanco;

	@Column(nullable=false, length=25)
	private String ncuenta;

	@Column(nullable=false, length=50)
	private String nombre;

	@Column(nullable=false, length=20)
	private String tipo;

	public Cuenta() {
	}

	public Cuenta(Integer idbanco, String ncuenta, String nombre, String tipo) {
		super();
		
		this.idbanco = idbanco;
		this.ncuenta = ncuenta;
		this.nombre = nombre;
		this.tipo = tipo;
	}

	public Integer getIdcuenta() {
		return this.idcuenta;
	}

	public void setIdcuenta(Integer idcuenta) {
		this.idcuenta = idcuenta;
	}

	public Integer getIdbanco() {
		return this.idbanco;
	}

	public void setIdbanco(Integer idbanco) {
		this.idbanco = idbanco;
	}

	public String getNcuenta() {
		return this.ncuenta;
	}

	public void setNcuenta(String ncuenta) {
		this.ncuenta = ncuenta;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}