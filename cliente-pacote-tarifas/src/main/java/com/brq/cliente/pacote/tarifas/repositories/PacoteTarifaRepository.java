package com.brq.cliente.pacote.tarifas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brq.cliente.pacote.tarifas.entities.PacoteTarifa;


@Repository
public interface PacoteTarifaRepository extends JpaRepository<PacoteTarifa, Long> {


	
	

}
