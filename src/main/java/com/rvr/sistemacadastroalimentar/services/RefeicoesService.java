package com.rvr.sistemacadastroalimentar.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.rvr.sistemacadastroalimentar.entities.Refeicoes;
import com.rvr.sistemacadastroalimentar.entities.views.RefeicoesView;
import com.rvr.sistemacadastroalimentar.repositories.RefeicoesRepository;
import com.rvr.sistemacadastroalimentar.services.exceptions.DatabaseException;
import com.rvr.sistemacadastroalimentar.services.exceptions.ResourceNotFoundException;

@Service
public class RefeicoesService {

	@Autowired
	private RefeicoesRepository repository;

	@Autowired
	private PesoAlimentoService pesoAlimentoService;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	public RefeicoesView findAll() {
		return recalculaPesos(new RefeicoesView(repository.findAll()));
	}

	public RefeicoesView findById(Integer i) {

		return recalculaPesos(new RefeicoesView(repository.findById(i).get()));

	}

	public RefeicoesView findByDate(Date data) {

		List<Refeicoes> lista = repository.findAll();
		Date d = new Date();
		
		lista.forEach(e -> {

			System.out.println(d);
			System.out.println(data);
			System.out.println(" ######### - " + Date.from(e.getMoment()).compareTo(data));
		});
		
		lista.removeIf(f -> Date.from(f.getMoment()).compareTo(data) != 1);

		return recalculaPesos(new RefeicoesView(lista));
	}

	public void delete(Integer id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e1) {
			throw new DatabaseException(e1.getMessage());
		}
	}

	public Refeicoes update(Integer id, Refeicoes obj) {
		try {
			Refeicoes entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	public Refeicoes insert(Refeicoes obj) {
		return repository.save(obj);
	}

	private void updateData(Refeicoes entity, Refeicoes obj) {
		entity.setMoment(obj.getMoment());
		entity.setPesoAlimentos(obj.getPesoAlimentos());
	}

	private RefeicoesView recalculaPesos(RefeicoesView view) {

		view.getRefeicoes().forEach(e -> {
			e.setPesoAlimentos(pesoAlimentoService.findByIdRefeicao(e.getIdRefeicao()));
		});

		view.recalcularPesos();

		return view;
	}
}
