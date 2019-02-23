package modeloEntidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the vista_cuenta database table.
 * 
 */
@Entity
@Table(name="vista_cuenta")
@NamedQuery(name="VistaCuenta.findAll", query="SELECT v FROM VistaCuenta v")
public class VistaCuenta implements Serializable, Comparable<VistaCuenta> {
	private static final long serialVersionUID = 1L;

	@Column(length=50)
	private String banco;

	private Integer idbanco;

	private Integer idcuenta;

	@Column(length=50)
	private String nombre;

	@Column(name="numero_cuenta", length=25)
	private String numeroCuenta;

	@Column(length=20)
	private String tipo;

	public VistaCuenta(String numeroCuenta, String tipo) {
		super();
		this.numeroCuenta = numeroCuenta;
		this.tipo = tipo;
	}

	public VistaCuenta() {
	}

	public String getBanco() {
		return this.banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public Integer getIdbanco() {
		return this.idbanco;
	}

	public void setIdbanco(Integer idbanco) {
		this.idbanco = idbanco;
	}

	public Integer getIdcuenta() {
		return this.idcuenta;
	}

	public void setIdcuenta(Integer idcuenta) {
		this.idcuenta = idcuenta;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumeroCuenta() {
		return this.numeroCuenta;
	}

	@Override
	public String toString() {
		return numeroCuenta + " - " + tipo;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public int compareTo(VistaCuenta o) {
		// TODO Auto-generated method stub
		return idcuenta - o.idcuenta;
	}

}