package modeloEntidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the categoria database table.
 * 
 */
@Entity
@Table(name="categoria")
@NamedQuery(name="Categoria.findAll", query="SELECT c FROM Categoria c")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer idcategoria;

	@Column(nullable=false, length=50)
	private String categoria;

	public Categoria() {
	}
	
	public Categoria(String categoria) {
		super();
		this.categoria = categoria;
	}



	public Integer getIdcategoria() {
		return this.idcategoria;
	}

	public void setIdcategoria(Integer idcategoria) {
		this.idcategoria = idcategoria;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return  categoria;
	}

}