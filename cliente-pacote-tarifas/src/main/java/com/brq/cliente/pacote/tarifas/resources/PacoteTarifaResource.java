package com.brq.cliente.pacote.tarifas.resources;

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

import com.brq.cliente.pacote.tarifas.entities.PacoteTarifa;
import com.brq.cliente.pacote.tarifas.services.PacoteTarifaService;

@RestController
@RequestMapping(value = "/pacoteTarifas")
public class PacoteTarifaResource {
	
	@Autowired
	private PacoteTarifaService service;
	
	
	
	  @GetMapping 
	  public ResponseEntity<List<PacoteTarifa>> findAll() { 
		  List<PacoteTarifa> list = service.findAll(); 
		  return ResponseEntity.ok(list); 
	  }
	 
	
		@GetMapping(value = "/{id}")
		public ResponseEntity<PacoteTarifa> findById(@PathVariable Long id) {
			PacoteTarifa obj = service.find(id);
			return ResponseEntity.ok(obj);
		}
		
		@PostMapping
		public ResponseEntity<Void> insert(@RequestBody PacoteTarifa obj){
			obj = service.insert(obj);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
			return ResponseEntity.created(uri).build();
		}
		
		@PutMapping(value = "/{id}")
		public ResponseEntity<Void> update(@RequestBody PacoteTarifa obj, @PathVariable Long id){
			obj.setId(id);
			obj = service.update(obj);
			return ResponseEntity.noContent().build();
			
		}
		
		@DeleteMapping(value = "/{id}")
		public ResponseEntity<PacoteTarifa> delete(@PathVariable Long id) {
			service.delete(id);
			return ResponseEntity.noContent().build();
			
		}
		
		  @GetMapping(value = "/pacoteTarifaByClienteId/{clienteId}") 
		  public ResponseEntity <PacoteTarifa> getPacoteTarifaByClienteId(@PathVariable Long clienteId) { 
			  PacoteTarifa pacote = service.getPacoteTarifa(clienteId); 
			  return ResponseEntity.ok(pacote); 
		  }
		
		
		
		
		

}
