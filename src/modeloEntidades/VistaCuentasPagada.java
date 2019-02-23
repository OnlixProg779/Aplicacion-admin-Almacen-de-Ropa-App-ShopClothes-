package modeloEntidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the vista_cuentas_pagadas database table.
 * 
 */
@Entity
@Table(name="vista_cuentas_pagadas")
@NamedQuery(name="VistaCuentasPagada.findAll", query="SELECT v FROM VistaCuentasPagada v")
public class VistaCuentasPagada implements Serializable, Comparable<VistaCuentasPagada> {
	private static final long serialVersionUID = 1L;

	@Column(length=100)
	private String descripcion;

	@Column(length=50)
	private String estado;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private Integer idcuentaxpagar;

	private double valor;

	public VistaCuentasPagada() {
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

	public Integer getIdcuentaxpagar() {
		return this.idcuentaxpagar;
	}

	public void setIdcuentaxpagar(Integer idcuentaxpagar) {
		this.idcuentaxpagar = idcuentaxpagar;
	}

	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public int compareTo(VistaCuentasPagada o) {
		// TODO Auto-generated method stub
		return idcuentaxpagar - o.idcuentaxpagar;
	}

}