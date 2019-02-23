package modeloEntidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the devolucion_de_cliente database table.
 * 
 */
@Entity
@Table(name="devolucion_de_cliente")
@NamedQuery(name="DevolucionDeCliente.findAll", query="SELECT d FROM DevolucionDeCliente d")
public class DevolucionDeCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(nullable=false)
	private Integer cantidad;

	@Column(nullable=false, length=100)
	private String descripcion;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date fecha;

	@Column(nullable=false)
	private Integer idproducto;

	@Column(nullable=false, length=2147483647)
	private String numerofactura;

	public DevolucionDeCliente() {
	}

	public DevolucionDeCliente(Integer cantidad, String descripcion, Date fecha, Integer idproducto,
			String numerofactura) {
		super();
		this.cantidad = cantidad;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.idproducto = idproducto;
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

	public String getNumerofactura() {
		return this.numerofactura;
	}

	public void setNumerofactura(String numerofactura) {
		this.numerofactura = numerofactura;
	}

}