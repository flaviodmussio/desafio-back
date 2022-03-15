package com.brq.cliente.pacote.tarifas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brq.cliente.pacote.tarifas.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	public boolean existsByCpf(String cpf);

}
