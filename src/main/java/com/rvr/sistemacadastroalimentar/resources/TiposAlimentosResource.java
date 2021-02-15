package com.rvr.sistemacadastroalimentar.resources;

import java.net.URI;
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

import com.rvr.sistemacadastroalimentar.entities.TiposAlimento;
import com.rvr.sistemacadastroalimentar.services.TiposAlimentosService;

@RestController
@RequestMapping("api/tiposalimentos")
public class TiposAlimentosResource {
	
	@Autowired
	private TiposAlimentosService service;
	
	@GetMapping
	//@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<TiposAlimento>> getAlimentos() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@PostMapping
	public ResponseEntity<TiposAlimento> insert(@RequestBody TiposAlimento tipoAlimento) {
		tipoAlimento = service.insert(tipoAlimento);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tipoAlimento.getIdTipoAlimento()).toUri();
		return ResponseEntity.created(uri).body(tipoAlimento);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<TiposAlimento> insert(@PathVariable Integer id, @RequestBody TiposAlimento tipoAlimento) {
		tipoAlimento = service.update(id, tipoAlimento);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tipoAlimento.getIdTipoAlimento()).toUri();
		return ResponseEntity.created(uri).body(tipoAlimento);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}	
}
