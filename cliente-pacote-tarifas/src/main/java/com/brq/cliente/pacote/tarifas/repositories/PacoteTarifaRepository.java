package com.brq.cliente.pacote.tarifas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.brq.cliente.pacote.tarifas.entities.PacoteTarifa;


@Repository
public interface PacoteTarifaRepository extends JpaRepository<PacoteTarifa, Long> {
	
	@Transactional(readOnly=true)
	List<PacoteTarifa> findByClienteId(Long idCliente);

}
