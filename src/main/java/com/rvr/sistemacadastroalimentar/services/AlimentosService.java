package com.rvr.sistemacadastroalimentar.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.rvr.sistemacadastroalimentar.entities.Alimentos;
import com.rvr.sistemacadastroalimentar.repositories.AlimentosRepository;
import com.rvr.sistemacadastroalimentar.services.exceptions.DatabaseException;
import com.rvr.sistemacadastroalimentar.services.exceptions.ResourceNotFoundException;

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
	public Alimentos update(Integer id, Alimentos obj) {
		try {
			Alimentos entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} 
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(Alimentos entity, Alimentos obj) {
		entity.setNome(obj.getNome());
		entity.setPeso(obj.getPeso());
		entity.setQtdeCarboidrato(obj.getQtdeCarboidrato());
		entity.setQtdeGorduras(obj.getQtdeGorduras());
		entity.setQtdeProteinas(obj.getQtdeProteinas());
		entity.setTipoAlimento(obj.getTipoAlimento());
	}

}
