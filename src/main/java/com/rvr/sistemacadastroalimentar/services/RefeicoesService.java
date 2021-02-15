package com.rvr.sistemacadastroalimentar.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rvr.sistemacadastroalimentar.entities.Refeicoes;
import com.rvr.sistemacadastroalimentar.entities.views.RefeicoesView;
import com.rvr.sistemacadastroalimentar.repositories.RefeicoesRepository;

@Service
public class RefeicoesService implements ServiceWithView<Refeicoes> {
	
	@Autowired
	private RefeicoesRepository repository;

	@Override
	public List<Refeicoes> findAll() {
		
		List<Refeicoes> lista = new ArrayList<>();
		
		repository.findAll().forEach(f -> {
			f.calculaMacros();
			lista.add(f);
		});
		
		return lista;
	}

	@Override
	public Refeicoes findById(Integer i) {
		return repository.findById(i)
				.get();
	}
	
	public RefeicoesView findTotalRefeicoesDiarias(Integer i) {
		
		RefeicoesView teste = new RefeicoesView(findAll());
		return teste;
	}
	
	public List<Refeicoes> findByDate(Date data) {
		
		List<Refeicoes> lista = findAll();
//		lista.forEach(e -> {
//			System.out.println(Date.from(e.getMoment()) + " --- " + data + " --- " + Date.from(e.getMoment()).compareTo(data));
//		});
//		
		lista.removeIf(f -> Date.from(f.getMoment()).compareTo(data) != 1);
		
		return lista;
	}

}
