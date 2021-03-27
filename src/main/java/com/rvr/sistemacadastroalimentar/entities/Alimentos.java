package com.rvr.sistemacadastroalimentar.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Alimentos")
public class Alimentos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAlimento; 
	
	private String nome;
	private Double peso;
	private Double qtdeCarboidrato;
	private Double qtdeProteinas;
	private Double qtdeGorduras;
	
	@ManyToOne
	@JoinColumn(name = "idTipoAlimento")
	private TiposAlimento tipoAlimento;
	
	@OneToMany(mappedBy = "alimento")
	private List<PesoAlimento> pesoAlimento;
	
	public Alimentos() {
		
		this.nome = null;
		this.peso = null;
		this.qtdeCarboidrato = null;
		this.qtdeProteinas = null;
		this.qtdeGorduras = null;
		this.tipoAlimento = null;
	}


	public Alimentos(Integer idAlimento, String nome, Double peso, Double qtdeCarboidrato, Double qtdeProteinas,
			Double qtdeGorduras, TiposAlimento tipoAlimento) {
		this.idAlimento = idAlimento;
		this.nome = nome;
		this.peso = peso;
		this.qtdeCarboidrato = qtdeCarboidrato;
		this.qtdeProteinas = qtdeProteinas;
		this.qtdeGorduras = qtdeGorduras;
		this.tipoAlimento = tipoAlimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getQtdeCarboidrato() {
		return qtdeCarboidrato;
	}


	public void setQtdeCarboidrato(Double qtdeCarboidrato) {
		this.qtdeCarboidrato = qtdeCarboidrato;
	}


	public Double getQtdeProteinas() {
		return qtdeProteinas;
	}


	public void setQtdeProteinas(Double qtdeProteinas) {
		this.qtdeProteinas = qtdeProteinas;
	}


	public Double getQtdeGorduras() {
		return qtdeGorduras;
	}


	public void setQtdeGorduras(Double qtdeGorduras) {
		this.qtdeGorduras = qtdeGorduras;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public TiposAlimento getTipoAlimento() {
		return tipoAlimento;
	}

	public void setTipoAlimento(TiposAlimento tipoAlimento) {
		this.tipoAlimento = tipoAlimento;
	}


	public Integer getIdAlimento() {
		return idAlimento;
	}


	public void setIdAlimento(Integer idAlimento) {
		this.idAlimento = idAlimento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAlimento == null) ? 0 : idAlimento.hashCode());
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
		Alimentos other = (Alimentos) obj;
		if (idAlimento == null) {
			if (other.idAlimento != null)
				return false;
		} else if (!idAlimento.equals(other.idAlimento))
			return false;
		return true;
	}
	
}
