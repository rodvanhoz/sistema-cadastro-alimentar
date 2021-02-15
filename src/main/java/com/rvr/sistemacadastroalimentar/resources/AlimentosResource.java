package com.rvr.sistemacadastroalimentar.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rvr.sistemacadastroalimentar.entities.Alimentos;
import com.rvr.sistemacadastroalimentar.services.AlimentosService;

@RestController
@RequestMapping("api/alimentos")
public class AlimentosResource {
	
	@Autowired
	private AlimentosService service;
	
	@GetMapping
	//@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Alimentos>> getAlimentos() {
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Alimentos> getAlimento(@PathVariable int id) {
		return ResponseEntity.ok().body(service.findById(id));
	}
	

	@PostMapping
	public ResponseEntity<Alimentos> insert(@RequestBody Alimentos alimento) {
		alimento = service.insert(alimento);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(alimento.getIdAlimento()).toUri();
		return ResponseEntity.created(uri).body(alimento);
	}
}
