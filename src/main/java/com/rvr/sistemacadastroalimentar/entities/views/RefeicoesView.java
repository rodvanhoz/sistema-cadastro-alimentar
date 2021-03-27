package com.rvr.sistemacadastroalimentar.entities.views;

import java.util.ArrayList;
import java.util.List;

import com.rvr.sistemacadastroalimentar.entities.Refeicoes;
import com.rvr.sistemacadastroalimentar.entities.dto.PesoAlimentoDTO;
import com.rvr.sistemacadastroalimentar.entities.dto.RefeicoesDTO;

public class RefeicoesView {

	private Double kcalTotal;
	private Double carboidratos;
	private Double proteinas;
	private Double gorduras;
	private Double pesoTotal;

	private List<Refeicoes> refeicoesModel = new ArrayList<>();
	private RefeicoesDTO dto;

	List<RefeicoesDTO> refeicoes = new ArrayList<>();
	List<PesoAlimentoDTO> pesosAlimentos = new ArrayList<>();

	public RefeicoesView(List<Refeicoes> refeicoes) {

		this.kcalTotal = 0.0;
		this.carboidratos = 0.0;
		this.proteinas = 0.0;
		this.gorduras = 0.0;
		this.pesoTotal = 0.0;

		this.refeicoesModel = refeicoes;

		calculaMacrosDiario(refeicoes);
	}

	public RefeicoesView(Refeicoes refeicao) {

		this.kcalTotal = 0.0;
		this.carboidratos = 0.0;
		this.proteinas = 0.0;
		this.gorduras = 0.0;
		this.pesoTotal = 0.0;
		
		List<Refeicoes> ref = new ArrayList<>();
		ref.add(refeicao);

		this.refeicoesModel.add(refeicao);

		calculaMacrosDiario(ref);
	}
	
	private void calculaMacrosDiario(List<Refeicoes> refeicoes) {

		if (this.refeicoes.size() == 0) {
			this.refeicoes = criaListaDTO(refeicoes);
		}

		this.refeicoes.forEach(e -> {
			e.calculaMacrosPorRefeicao();

			this.kcalTotal += e.getMacros().get("kcal");
			this.carboidratos += e.getMacros().get("carboidratos");
			this.proteinas += e.getMacros().get("proteinas");
			this.gorduras += e.getMacros().get("gorduras");
			this.pesoTotal += e.getMacros().get("peso");
		});

		System.out.println(this.pesoTotal);
	}

	private List<RefeicoesDTO> criaListaDTO(List<Refeicoes> refeicoes) {

		List<RefeicoesDTO> list = new ArrayList<>();

		refeicoes.forEach(e -> {
			dto = new RefeicoesDTO(e);
			list.add(dto);
		});

		return list;
	}

	public void recalcularPesos() {

		calculaMacrosDiario(this.refeicoesModel);
	}

	public Double getKcalTotal() {
		return kcalTotal;
	}

	public Double getCarboidratos() {
		return carboidratos;
	}

	public Double getProteinas() {
		return proteinas;
	}

	public Double getGorduras() {
		return gorduras;
	}

	public Double getPesoTotal() {
		return pesoTotal;
	}

	public List<RefeicoesDTO> getRefeicoes() {
		return refeicoes;
	}

}
