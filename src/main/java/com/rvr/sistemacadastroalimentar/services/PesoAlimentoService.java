package com.rvr.sistemacadastroalimentar.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.rvr.sistemacadastroalimentar.entities.PesoAlimento;
import com.rvr.sistemacadastroalimentar.repositories.PesoAlimentoRepository;
import com.rvr.sistemacadastroalimentar.services.exceptions.DatabaseException;
import com.rvr.sistemacadastroalimentar.services.exceptions.ResourceNotFoundException;

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
	
	public List<PesoAlimento> findByIdRefeicao(Integer i) {
		return repository.findAll()
				.stream()
				.filter(e -> e.getRefeicao().getIdRefeicao() == i)
				.collect(Collectors.toList());
	}
	
	public PesoAlimento insert(PesoAlimento pesoAlimento) {
		return repository.save(pesoAlimento);
	}
	
	@Override
	public void delete(Integer id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
		catch (DataIntegrityViolationException e1) {
			throw new DatabaseException(e1.getMessage());
		}
		
	}

	@Override
	public PesoAlimento update(Integer id, PesoAlimento obj) {
		try {
			PesoAlimento entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} 
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(PesoAlimento entity, PesoAlimento obj) {
		entity.setAlimento(obj.getAlimento());
		entity.setPeso(obj.getPeso());
		entity.setRefeicao(obj.getRefeicao());
	}

}
