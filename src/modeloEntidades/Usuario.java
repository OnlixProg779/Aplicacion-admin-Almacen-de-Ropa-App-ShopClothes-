package modeloEntidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name="usuario")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer idusuario;

	@Column(nullable=false, length=300)
	private String contrase�a;

	@Column(nullable=false, length=50)
	private String nombre;

	@Column(nullable=false, length=50)
	private String tipousuario;

	public Usuario() {
	}

	public Integer getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public String getContrase�a() {
		return this.contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipousuario() {
		return this.tipousuario;
	}

	public void setTipousuario(String tipousuario) {
		this.tipousuario = tipousuario;
	}

}