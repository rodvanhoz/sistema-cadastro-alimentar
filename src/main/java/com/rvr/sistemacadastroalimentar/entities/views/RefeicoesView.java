package com.rvr.sistemacadastroalimentar.entities.views;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rvr.sistemacadastroalimentar.entities.Refeicoes;

public class RefeicoesView {

	private Double kcalTotal;
	private Double carboidratos;
	private Double proteinas;
	private Double gorduras;
	private Double pesoTotal;
	
	private Double carb;
	private Double prot;
	private Double gord;
	private Double peso;
	private Double kcal;
	
	private Map<String, Double> macros = new HashMap<>();
	
	public RefeicoesView(List<Refeicoes> refeicoes) {
		
		this.kcalTotal = 0.0;
		this.carboidratos = 0.0;
		this.proteinas = 0.0;
		this.gorduras = 0.0;
		this.pesoTotal = 0.0;
		
		calculaMacrosDiario(refeicoes);
	}

	private void calculaMacrosDiario(List<Refeicoes> refeicoes) {
		
		macros.clear();
		
		refeicoes.forEach(e -> {
			calculaMacrosPorRefeicao(e);
			
			this.kcalTotal += macros.get("kcal");
			this.carboidratos += macros.get("carboidratos");
			this.proteinas += macros.get("proteinas");
			this.gorduras += macros.get("gorduras");
			this.pesoTotal += macros.get("peso");
		});
		
		System.out.println(this.pesoTotal);
	}

	private void calculaMacrosPorRefeicao(Refeicoes refeicao) {
		
		carb = 0.0;
		prot = 0.0;
		gord = 0.0;
		peso = 0.0;
		kcal = 0.0;
		
		refeicao.getPesoAlimentos().forEach(e -> {
			kcal += e.getKcal();
			carb += (e.getPeso() * e.getAlimento().getQtdeCarboidrato());
			prot += (e.getPeso() * e.getAlimento().getQtdeProteinas());
			gord += (e.getPeso() * e.getAlimento().getQtdeGorduras());
			peso += e.getPeso();
		});
		
		macros.put("carboidratos", carb);
		macros.put("proteinas", prot);
		macros.put("gorduras", gord);
		macros.put("kcal", kcal);
		macros.put("peso", peso);
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

}
