package com.brq.cliente.pacote.tarifas.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

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

import com.brq.cliente.pacote.tarifas.entities.Cliente;
import com.brq.cliente.pacote.tarifas.entities.PacoteTarifa;
import com.brq.cliente.pacote.tarifas.enums.Pacote;
import com.brq.cliente.pacote.tarifas.services.ClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Cliente")
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;
	
	@ApiOperation(value = "Retorna uma lista de clientes")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna uma lista de clientes"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção")
	})
	@GetMapping
	public ResponseEntity<List<Cliente>> findAll() {
		List<Cliente> list = service.findAll();
		return ResponseEntity.ok(list);
	}
	
	@ApiOperation(value = "Retorna o cliente pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o cliente pelo id"),
			@ApiResponse(code = 404, message = "Cliente não encontrado!"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção")
	})
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Long id) {
		Cliente obj = service.find(id);
		return ResponseEntity.ok(obj);
	}
	
	@ApiOperation(value = "Criação de um cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Criação de um cliente"),
			@ApiResponse(code = 404, message = "O tamanho de caracteres do cpf deve ser de 11"),
			@ApiResponse(code = 404, message = "Preenchimento obrigatorio"),
			@ApiResponse(code = 404, message = "CPF já existe"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção")
	})
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody Cliente obj) {
		PacoteTarifa p1 = new PacoteTarifa(Pacote.CLASSIC);
		obj.setPacoteTarifa(p1);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value = "Atualização do cliente pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Atualização do cliente pelo id"),
			@ApiResponse(code = 400, message = "Preenchimento obrigatorio"),
			@ApiResponse(code = 400, message = "O tamanho de caracteres do cpf deve ser de 11"),
			@ApiResponse(code = 400, message = "CPF já existe"),
			@ApiResponse(code = 404, message = "Cliente não encontrado!"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção")
	})
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody Cliente obj, @PathVariable Long id) {
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();

	}
	
	
	@ApiOperation(value = "Exclusão de um cliente pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Exclusão de um cliente pelo id"),
			@ApiResponse(code = 404, message = "Cliente não encontrado!"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção")
	})
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Cliente> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();

	}

}
