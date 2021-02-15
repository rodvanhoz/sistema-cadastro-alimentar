package com.rvr.sistemacadastroalimentar.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TiposAlimento")
public class TiposAlimento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTipoAlimento; 
	
	private String descricao;
	
	@OneToMany(mappedBy = "tipoAlimento")
	private List<Alimentos> alimentos;
	
	public TiposAlimento() {
		this.idTipoAlimento = null;
		this.descricao = null;
	}

	public TiposAlimento(Integer idTipoAlimento, String descricao) {
		this.idTipoAlimento = idTipoAlimento;
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getIdTipoAlimento() {
		return idTipoAlimento;
	}

	public void setIdTipoAlimento(Integer idTipoAlimento) {
		this.idTipoAlimento = idTipoAlimento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTipoAlimento == null) ? 0 : idTipoAlimento.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TiposAlimento other = (TiposAlimento) obj;
		if (idTipoAlimento == null) {
			if (other.idTipoAlimento != null)
				return false;
		} else if (!idTipoAlimento.equals(other.idTipoAlimento))
			return false;
		return true;
	}

}
