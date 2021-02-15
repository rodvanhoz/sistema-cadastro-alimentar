package com.rvr.sistemacadastroalimentar.resources;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rvr.sistemacadastroalimentar.entities.Refeicoes;
import com.rvr.sistemacadastroalimentar.entities.TiposAlimento;
import com.rvr.sistemacadastroalimentar.services.RefeicoesService;

@RestController
@RequestMapping("api/refeicoes")
public class RefeicoesResource {
	
	@Autowired
	private RefeicoesService service;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	@GetMapping
	//@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Refeicoes>> getRefeicoes() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Refeicoes> getRefeicoesDiarias(@PathVariable Integer id) {
		
		return ResponseEntity.ok().body(service.findById(id));
	}
	
	@GetMapping(value = "/hoje/{data}")
	public ResponseEntity<List<Refeicoes>> getRefeicoesDiarias(@PathVariable String data) throws ParseException {
		
		return ResponseEntity.ok().body(service.findByDate(sdf.parse(data)));
	}
	
	@PostMapping
	public ResponseEntity<Refeicoes> insert(@RequestBody Refeicoes refeicoes) {
		refeicoes = service.insert(refeicoes);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(refeicoes.getIdRefeicao()).toUri();
		return ResponseEntity.created(uri).body(refeicoes);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Refeicoes> insert(@PathVariable Integer id, @RequestBody Refeicoes refeicoes) {
		refeicoes = service.update(id, refeicoes);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(refeicoes.getIdRefeicao()).toUri();
		return ResponseEntity.created(uri).body(refeicoes);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}	
}
