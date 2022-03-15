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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Pacote de Tarifa")
@RequestMapping(value = "/pacoteTarifas")
public class PacoteTarifaResource {
	
	@Autowired
	private PacoteTarifaService service;
		
	@ApiOperation(value = "Retorna uma lista de pacotes de tarifa")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna uma lista de pacotes de tarifa"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção")
	})
	@GetMapping 
	public ResponseEntity<List<PacoteTarifa>> findAll() { 
	  List<PacoteTarifa> list = service.findAll(); 
	  return ResponseEntity.ok(list); 
	 }
	 
	@ApiOperation(value = "Retorna o pacote de tarifa pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o pacote de tarifa pelo id"),
			@ApiResponse(code = 404, message = "Pacote de tarifa não encontrado!"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção")
	})
	@GetMapping(value = "/{id}")
	public ResponseEntity<PacoteTarifa> findById(@PathVariable Long id) {
		PacoteTarifa obj = service.find(id);
		return ResponseEntity.ok(obj);
	}
	
	
	@ApiOperation(value = "Criação de um pacote de tarifa")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Criação de um pacote de tarifa"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção")
	})
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody PacoteTarifa obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value = "Atualização de um pacote de tarifa pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Atualização de um pacote de tarifa pelo id"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 404, message = "Pacote de tarifa não encontrado!"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção")
	})
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody PacoteTarifa obj, @PathVariable Long id){
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
		
	}
	
	
	@ApiOperation(value = "Exclusão de um pacote de um pacote de tarifa pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Exclusão de um pacote de um pacote de tarifa pelo id"),
			@ApiResponse(code = 400, message = "Não é possivel excluir o pacote de tarifa"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 404, message = "Pacote de tarifa não encontrado!"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção")
	})
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<PacoteTarifa> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	@ApiOperation(value = "Retorna o pacote de tarifa pelo id do cliente")	
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o pacote de tarifa pelo id do cliente"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 404, message = "Cliente não encontrado!"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção")
	})
	@GetMapping(value = "/pacoteTarifaByClienteId/{clienteId}") 
	public ResponseEntity <PacoteTarifa> getPacoteTarifaByClienteId(@PathVariable Long clienteId) { 
		PacoteTarifa pacote = service.getPacoteTarifa(clienteId); 
		return ResponseEntity.ok(pacote); 
	}
		
		
		
		
		

}
