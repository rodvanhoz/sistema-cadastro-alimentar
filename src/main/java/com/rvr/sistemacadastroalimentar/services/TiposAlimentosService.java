package com.rvr.sistemacadastroalimentar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rvr.sistemacadastroalimentar.entities.TiposAlimento;
import com.rvr.sistemacadastroalimentar.repositories.TiposAlimentosRepository;

@Service
public class TiposAlimentosService implements ServiceWithView<TiposAlimento> {

	@Autowired
	private TiposAlimentosRepository repository;
	
	@Override
	public List<TiposAlimento> findAll() {
		return repository.findAll(); 
	}

	@Override
	public TiposAlimento findById(Integer i) {
		return repository.findById(i)
				.get();
	}
	
	public TiposAlimento insert(TiposAlimento tipoAlimento) {
		return repository.save(tipoAlimento);
	}

}
