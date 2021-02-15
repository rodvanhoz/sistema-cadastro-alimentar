package com.rvr.sistemacadastroalimentar.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
}
