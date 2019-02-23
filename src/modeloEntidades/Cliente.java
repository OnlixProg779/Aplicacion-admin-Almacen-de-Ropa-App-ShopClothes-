package modeloEntidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cliente database table.
 * 
 */
@Entity
@Table(name="cliente")
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable,Comparable<Cliente> {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=13)
	private String cedula;

	@Column(nullable=false, length=50)
	private String apellido;

	@Column(length=100)
	private String direccion;

	@Column(nullable=false, length=50)
	private String nombre;

	@Column(length=50)
	private String telefono;

	public Cliente() {
	}

	public Cliente(String cedula, String nombre, String apellido , String direccion, String telefono) {
		super();
		this.cedula = cedula;
		this.apellido = apellido;
		this.direccion = direccion;
		this.nombre = nombre;
		this.telefono = telefono;
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public int compareTo(Cliente o) {
		// TODO Auto-generated method stub
		return cedula.compareTo(o.cedula);
	}

}