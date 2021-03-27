package com.rvr.sistemacadastroalimentar.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Refeicoes")
public class Refeicoes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRefeicao;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GNT")
	private Instant moment;

	@OneToMany(mappedBy = "refeicao")
	private Set<PesoAlimento> pesoAlimentos = new HashSet<>();

	public Refeicoes() {

	}

	public Refeicoes(Integer idRefeicao, Instant moment) {
		this.idRefeicao = idRefeicao;
		this.moment = moment;

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

	public Set<PesoAlimento> getPesoAlimentos() {
		return pesoAlimentos;
	}

	public void setPesoAlimentos(Set<PesoAlimento> pesoAlimentos) {
		this.pesoAlimentos = pesoAlimentos;
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
		Refeicoes other = (Refeicoes) obj;
		if (idRefeicao == null) {
			if (other.idRefeicao != null)
				return false;
		} else if (!idRefeicao.equals(other.idRefeicao))
			return false;
		return true;
	}
}
