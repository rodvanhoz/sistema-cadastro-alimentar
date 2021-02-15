package com.rvr.sistemacadastroalimentar.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "PesoAlimento")
public class PesoAlimento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPesoAlimento;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "idRefeicao")
	private Refeicoes refeicao;
	
	@ManyToOne
	@JoinColumn(name = "idAlimento")
	private Alimentos alimento;
	
	private Double peso;
	private Double kcal;
	
	public PesoAlimento() {
		
	}

	public PesoAlimento(Integer idPesoAlimento, Alimentos alimento, Double peso, Refeicoes refeicao) {
		this.idPesoAlimento = idPesoAlimento;
		this.alimento = alimento;
		this.peso = peso;
		this.refeicao = refeicao;
		
		this.kcal = calculaValorCalorico();
	}

	private Double calculaValorCalorico() {
		return ((this.peso * alimento.getQtdeCarboidrato()) * 4) + ((this.peso * alimento.getQtdeProteinas()) * 4) + ((this.peso * alimento.getQtdeGorduras()) * 9);
	}

	public Integer getIdPesoAlimento() {
		return idPesoAlimento;
	}

	public void setIdPesoAlimento(Integer idPesoAlimento) {
		this.idPesoAlimento = idPesoAlimento;
	}

	public Alimentos getAlimento() {
		return alimento;
	}

	public void setAlimento(Alimentos alimento) {
		this.alimento = alimento;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}
	
	public Refeicoes getRefeicao() {
		return refeicao;
	}

	public void setRefeicao(Refeicoes refeicao) {
		this.refeicao = refeicao;
	}
	
	public Double getKcal() {
		return kcal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPesoAlimento == null) ? 0 : idPesoAlimento.hashCode());
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
		PesoAlimento other = (PesoAlimento) obj;
		if (idPesoAlimento == null) {
			if (other.idPesoAlimento != null)
				return false;
		} else if (!idPesoAlimento.equals(other.idPesoAlimento))
			return false;
		return true;
	}

	
}
