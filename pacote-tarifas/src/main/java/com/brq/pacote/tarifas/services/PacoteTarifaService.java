package com.brq.pacote.tarifas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.brq.pacote.tarifas.entities.PacoteTarifa;
import com.brq.pacote.tarifas.repositories.PacoteTarifaRepository;
import com.brq.pacote.tarifas.service.exception.DataIntegrityException;
import com.brq.pacote.tarifas.service.exception.ObjectNotFoundException;

@Service
public class PacoteTarifaService {
	
	@Autowired
	private PacoteTarifaRepository repository;
	
	

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
	
	public PacoteTarifa find(Long id) {
		Optional<PacoteTarifa> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+id+" Tipo: "+PacoteTarifa.class.getName()));
		
	}
	
	
	private void upDateDate(PacoteTarifa newObj, PacoteTarifa obj) {
		newObj.setNome(obj.getNome());
		
	}


}
