package com.rvr.sistemacadastroalimentar.entities.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rvr.sistemacadastroalimentar.entities.Alimentos;
import com.rvr.sistemacadastroalimentar.entities.PesoAlimento;
import com.rvr.sistemacadastroalimentar.entities.Refeicoes;

public class PesoAlimentoDTO implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	private Integer idPesoAlimento;
	
	@JsonIgnore
	private Refeicoes refeicao;
	
	private Alimentos alimento;
	private Double peso;
	private Double kcal;
	
	public PesoAlimentoDTO(PesoAlimento pesoAlimento) {
		this.idPesoAlimento = pesoAlimento.getIdPesoAlimento();
		this.refeicao = pesoAlimento.getRefeicao();
		this.alimento = pesoAlimento.getAlimento();
		this.peso = pesoAlimento.getPeso();
		
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

	public Refeicoes getRefeicao() {
		return refeicao;
	}

	public void setRefeicao(Refeicoes refeicao) {
		this.refeicao = refeicao;
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

	public Double getKcal() {
		return kcal;
	}

	@Override
	public String toString() {
		return "PesoAlimentoDTO [idPesoAlimento=" + idPesoAlimento + ", refeicao=" + refeicao + ", alimento=" + alimento
				+ ", peso=" + peso + ", kcal=" + kcal + "]";
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
		PesoAlimentoDTO other = (PesoAlimentoDTO) obj;
		if (idPesoAlimento == null) {
			if (other.idPesoAlimento != null)
				return false;
		} else if (!idPesoAlimento.equals(other.idPesoAlimento))
			return false;
		return true;
	}

}
