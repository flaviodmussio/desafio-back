package com.brq.cliente.pacote.tarifas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.brq.cliente.pacote.tarifas.entities.Cliente;
import com.brq.cliente.pacote.tarifas.entities.PacoteTarifa;
import com.brq.cliente.pacote.tarifas.repositories.ClienteRepository;
import com.brq.cliente.pacote.tarifas.repositories.PacoteTarifaRepository;
import com.brq.cliente.pacote.tarifas.services.exception.DataIntegrityException;
import com.brq.cliente.pacote.tarifas.services.exception.ObjectNotFoundException;

@Service
public class PacoteTarifaService {
	
	@Autowired
	private PacoteTarifaRepository repositoTarifaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<PacoteTarifa> findAll() {
		return  repositoTarifaRepository.findAll();
	}
	
	public PacoteTarifa insert(PacoteTarifa obj) {
		obj.setId(null);
		obj = repositoTarifaRepository.save(obj);
		return obj;
	}

	public PacoteTarifa update(PacoteTarifa obj) {
		PacoteTarifa newObj = find(obj.getId());
		upDateDate(newObj, obj);
		return repositoTarifaRepository.save(newObj);
	}
	
	public void delete(Long id) {
		find(id);
		try {
			repositoTarifaRepository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir o pacote de tarifa");
			
		}
	}
	
	
	public PacoteTarifa find(Long id) {
		Optional<PacoteTarifa> obj = repositoTarifaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Pacote de Tarifa não encontrado!"));
		
	}
	
	 public PacoteTarifa getPacoteTarifa(Long clienteId) { 
		 Cliente  cliente;
		 try {
			 cliente = clienteRepository.findById(clienteId).get();
		 }catch (Exception e) {
			throw new ObjectNotFoundException("Cliente não encontrado!");
		}
		  PacoteTarifa pacote = repositoTarifaRepository.findById(cliente.getPacoteTarifa().getId()).get(); 
		  return pacote;
	  
	  }
	
	
	private void upDateDate(PacoteTarifa newObj, PacoteTarifa obj) {
		newObj.setNome(obj.getNome());
		newObj.setValorMaximo(obj.getValorMaximo());
		newObj.setValorMinimo(obj.getValorMinimo());
		
	}


}
