package modeloEntidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the vista_devolucion_de_cliente database table.
 * 
 */
@Entity
@Table(name="vista_devolucion_de_cliente")
@NamedQuery(name="VistaDevolucionDeCliente.findAll", query="SELECT v FROM VistaDevolucionDeCliente v")
public class VistaDevolucionDeCliente implements Serializable, Comparable<VistaDevolucionDeCliente> {
	private static final long serialVersionUID = 1L;

	private Integer cantidad;

	@Column(length=100)
	private String descripcion;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private Integer idproducto;

	@Column(length=50)
	private String nombre;

	@Column(length=2147483647)
	private String numerofactura;

	public VistaDevolucionDeCliente() {
	}

	public VistaDevolucionDeCliente(Integer cantidad, String descripcion, Date fecha, Integer idproducto, String nombre,
			String numerofactura) {
		super();
		this.cantidad = cantidad;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.idproducto = idproducto;
		this.nombre = nombre;
		this.numerofactura = numerofactura;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
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

	public Integer getIdproducto() {
		return this.idproducto;
	}

	public void setIdproducto(Integer idproducto) {
		this.idproducto = idproducto;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumerofactura() {
		return this.numerofactura;
	}

	public void setNumerofactura(String numerofactura) {
		this.numerofactura = numerofactura;
	}

	@Override
	public int compareTo(VistaDevolucionDeCliente o) {
		// TODO Auto-generated method stub
		return idproducto - o.idproducto;
	}

}