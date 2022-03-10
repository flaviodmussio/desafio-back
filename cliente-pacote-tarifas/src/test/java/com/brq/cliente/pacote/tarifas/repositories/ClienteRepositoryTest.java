package com.brq.cliente.pacote.tarifas.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.brq.cliente.pacote.tarifas.entities.Cliente;
import com.brq.cliente.pacote.tarifas.entities.PacoteTarifa;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ClienteRepositoryTest {

	@Autowired
	private ClienteRepository clienteRepository;

	private Cliente c1;
	private Cliente c2;
	private Cliente c3;
	private PacoteTarifa p1;
	private PacoteTarifa p2;
	
	@BeforeEach 
	public void before() {
		p1 = new PacoteTarifa(null, "PACOTE_CLASSIC", 20.0, 30.0);
		p2 = new PacoteTarifa(null, "PACOTE_TOP", 40.0, 50.);

		c1 = new Cliente(1L, "Carlos", "18611816803", new Date(), p1);
		c2 = new Cliente(2L, "Maria", "16522498712", new Date(), p2);
		c3 = new Cliente(3L, "Joao", "15633214505", new Date(), p2);

		p1.getClientes().addAll(Arrays.asList(c1));
		p2.getClientes().addAll(Arrays.asList(c2, c3));
		
	}
	
	@Test
	public void informarQuantideDeClientesTest() {

		Integer countCliente = clienteRepository.findAll().size();
		assertEquals(3, countCliente);
	}

	
	@Test
	public void deletarUmClienteTest() {

		clienteRepository.delete(c1);
		Optional<Cliente> cliente = clienteRepository.findById(c1.getId());
		assertThat(cliente).isEmpty();
	}
	
	@Test
	public void retornarUmClienteByIdTest() {

		Optional<Cliente> cliente = clienteRepository.findById(c1.getId());
		assertNotNull(cliente);
		System.out.println("Nome: "+cliente.get().getNome());
	}
	
	@Test
	public void atualizarUmClienteByIdTest() {
		
		Cliente newObj = clienteRepository.findById(c1.getId()).get();
		upDateDate(newObj, c1);
		Cliente cliente = clienteRepository.save(newObj);
		assertNotNull(cliente);
	}
	
	private void upDateDate(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setCpf(obj.getCpf());
		newObj.setDataNascimento(obj.getDataNascimento());
		
	}

}
