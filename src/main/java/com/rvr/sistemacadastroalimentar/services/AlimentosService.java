package com.rvr.sistemacadastroalimentar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rvr.sistemacadastroalimentar.entities.Alimentos;
import com.rvr.sistemacadastroalimentar.repositories.AlimentosRepository;

@Service
public class AlimentosService implements ServiceWithView<Alimentos> {
	
	@Autowired
	private AlimentosRepository repository;

	@Override
	public List<Alimentos> findAll() {
		return repository.findAll();
	}

	@Override
	public Alimentos findById(Integer i) {
		return repository.findById(i)
				.get();
	}

	public Alimentos insert(Alimentos alimento) {
		return repository.save(alimento);
	}

}
