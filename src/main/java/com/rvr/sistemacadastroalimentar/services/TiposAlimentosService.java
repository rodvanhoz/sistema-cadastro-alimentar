package com.rvr.sistemacadastroalimentar.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.rvr.sistemacadastroalimentar.entities.TiposAlimento;
import com.rvr.sistemacadastroalimentar.repositories.TiposAlimentosRepository;
import com.rvr.sistemacadastroalimentar.services.exceptions.DatabaseException;
import com.rvr.sistemacadastroalimentar.services.exceptions.ResourceNotFoundException;

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
	public TiposAlimento update(Integer id, TiposAlimento obj) {
		try {
			TiposAlimento entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} 
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(TiposAlimento entity, TiposAlimento obj) {
		entity.setDescricao(obj.getDescricao());
	}

}
