package com.rvr.sistemacadastroalimentar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rvr.sistemacadastroalimentar.entities.PesoAlimento;
import com.rvr.sistemacadastroalimentar.repositories.PesoAlimentoRepository;

@Service
public class PesoAlimentoService implements ServiceWithView<PesoAlimento> {
	
	@Autowired
	private PesoAlimentoRepository repository;

	@Override
	public List<PesoAlimento> findAll() {
		return repository.findAll();
	}

	@Override
	public PesoAlimento findById(Integer i) {
		return repository.findById(i)
				.get();
	}
	
	public PesoAlimento insert(PesoAlimento pesoAlimento) {
		return repository.save(pesoAlimento);
	}

}
