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

import com.rvr.sistemacadastroalimentar.entities.PesoAlimento;
import com.rvr.sistemacadastroalimentar.services.PesoAlimentoService;

@RestController
@RequestMapping("api/peso_alimentos")
public class PesoAlimentosResource {
	
	@Autowired
	private PesoAlimentoService service;
	
	@GetMapping
	//@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<PesoAlimento>> getAlimentos() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@PostMapping
	public ResponseEntity<PesoAlimento> insert(@RequestBody PesoAlimento pesoAlimento) {
		System.out.println("@@@@@@@" + pesoAlimento.toString());
		
		if (!validator(pesoAlimento)) {
			System.out.println("Há atributos nulos");
			return ResponseEntity.badRequest().build();
		}
		
		pesoAlimento = service.insert(pesoAlimento);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pesoAlimento.getIdPesoAlimento()).toUri();
		return ResponseEntity.created(uri).body(pesoAlimento);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<PesoAlimento> insert(@PathVariable Integer id, @RequestBody PesoAlimento pesoAlimento) {
		pesoAlimento = service.update(id, pesoAlimento);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pesoAlimento.getIdPesoAlimento()).toUri();
		return ResponseEntity.created(uri).body(pesoAlimento);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	private boolean validator(PesoAlimento pesoAlimento) {
		
		if (pesoAlimento.getRefeicao() == null || (pesoAlimento.getAlimento() == null)) {
			return false;
		}
		else {
			return true;
		}
	}
}
