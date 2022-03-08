package com.brq.cliente.pacote.tarifas.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.brq.cliente.pacote.tarifas.entities.Cliente;
import com.brq.cliente.pacote.tarifas.entities.PacoteTarifa;
import com.brq.cliente.pacote.tarifas.repositories.PacoteTarifaRepository;
import com.brq.cliente.pacote.tarifas.services.exception.DataIntegrityException;
import com.brq.cliente.pacote.tarifas.services.exception.ObjectNotFoundException;

@Service
public class PacoteTarifaService {
	
	@Autowired
	private PacoteTarifaRepository repository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	
	@Value("${cliente.host}")
	private String clienteHost;
	
	

	public List<PacoteTarifa> findAll() {
		return  repository.findAll();
	}
	
	public PacoteTarifa insert(PacoteTarifa obj) {
		obj.setId(null);
		obj = repository.save(obj);
		return obj;
	}

	public PacoteTarifa update(PacoteTarifa obj) {
		PacoteTarifa newObj = find(obj.getId());
		upDateDate(newObj, obj);
		return repository.save(newObj);
	}
	
	public void delete(Long id) {
		find(id);
		try {
			repository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir o pacote de tarifa");
			
		}
	}
	
	public List<PacoteTarifa> getPacoteTarifa(Long clienteId) {
		Map<String,String> uriVariables = new HashMap<>();
		uriVariables.put("id", ""+clienteId);
	     Cliente cliente =  restTemplate.getForObject(clienteHost+"/clientes/{id}",Cliente.class, uriVariables);
	     List<PacoteTarifa> list = repository.findByClienteId(cliente.getId());
	     return list;
		
	}
	
	
	
	public PacoteTarifa find(Long id) {
		Optional<PacoteTarifa> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+id+" Tipo: "+PacoteTarifa.class.getName()));
		
	}
	
	
	private void upDateDate(PacoteTarifa newObj, PacoteTarifa obj) {
		newObj.setNome(obj.getNome());
		
	}


}
