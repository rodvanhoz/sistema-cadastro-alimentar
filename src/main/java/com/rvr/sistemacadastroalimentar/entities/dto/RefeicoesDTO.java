package com.rvr.sistemacadastroalimentar.entities.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rvr.sistemacadastroalimentar.entities.PesoAlimento;
import com.rvr.sistemacadastroalimentar.entities.Refeicoes;

public class RefeicoesDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Double carboidrato;
	private Double proteina;
	private Double gordura;
	private Double peso;
	private Double kcal;

	@JsonIgnore
	private Map<String, Double> macros = new HashMap<>();

	private Integer idRefeicao;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GNT")
	private Instant moment;

	private Set<PesoAlimentoDTO> pesoAlimentos = new HashSet<>();

	public RefeicoesDTO(Refeicoes refeicao) {
		this.idRefeicao = refeicao.getIdRefeicao();
		this.moment = refeicao.getMoment();

	}

	public void calculaMacrosPorRefeicao() {

		carboidrato = 0.0;
		proteina = 0.0;
		gordura = 0.0;
		peso = 0.0;
		kcal = 0.0;

		macros.clear();

		pesoAlimentos.forEach(e -> {

			kcal += e.getKcal();
			carboidrato += (e.getPeso() * e.getAlimento().getQtdeCarboidrato());
			proteina += (e.getPeso() * e.getAlimento().getQtdeProteinas());
			gordura += (e.getPeso() * e.getAlimento().getQtdeGorduras());
			peso += e.getPeso();
		});

		macros.put("carboidratos", carboidrato);
		macros.put("proteinas", proteina);
		macros.put("gorduras", gordura);
		macros.put("kcal", kcal);
		macros.put("peso", peso);
	}

	public Integer getIdRefeicao() {
		return idRefeicao;
	}

	public void setIdRefeicao(Integer idRefeicao) {
		this.idRefeicao = idRefeicao;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public Set<PesoAlimentoDTO> getPesoAlimentos() {
		return pesoAlimentos;
	}

	public void setPesoAlimentosDTO(List<PesoAlimentoDTO> pesoAlimentos) {

		pesoAlimentos.forEach(e -> {
			this.pesoAlimentos.add(e);
		});
	}

	public void setPesoAlimentos(List<PesoAlimento> pesoAlimentos) {

		pesoAlimentos.forEach(e -> {
			this.pesoAlimentos.add(new PesoAlimentoDTO(e));
		});
	}

	public Map<String, Double> getMacros() {
		return macros;
	}

	public Double getCarboidrato() {
		return carboidrato;
	}

	public Double getProteina() {
		return proteina;
	}

	public Double getGordura() {
		return gordura;
	}

	public Double getPeso() {
		return peso;
	}

	public Double getKcal() {
		return kcal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idRefeicao == null) ? 0 : idRefeicao.hashCode());
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
		RefeicoesDTO other = (RefeicoesDTO) obj;
		if (idRefeicao == null) {
			if (other.idRefeicao != null)
				return false;
		} else if (!idRefeicao.equals(other.idRefeicao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RefeicoesDTO [idRefeicao=" + idRefeicao + ", moment=" + moment + ", pesoAlimentos=" + pesoAlimentos
				+ "]";
	}

}
