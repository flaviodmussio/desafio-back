package com.brq.cliente.pacote.tarifas.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.brq.cliente.pacote.tarifas.entities.PacoteTarifa;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PacoteTafiraRepositoryTest {

	@Autowired
	private PacoteTarifaRepository pacoteTarifaRepository;
	
	private PacoteTarifa p1;
	private PacoteTarifa p2;
	private PacoteTarifa p3;
	private PacoteTarifa p4;
	
	@BeforeEach 
	public void before() {
		p1 = new PacoteTarifa(null, "PACOTE_CLASSIC", 20.0, 30.0);
		p2 = new PacoteTarifa(null, "PACOTE_TOP", 40.0, 50.);
		p3 = new PacoteTarifa(null, "PACOTE_PREMIUM", 50.0, 60.0);
		
		pacoteTarifaRepository.saveAll(Arrays.asList(p1, p2, p3));
		
	}

	
	@Test 
	public void inserirUmPacoteTarifaTest() {
		p4 = new PacoteTarifa(null, "PACOTE_MAXIMO", 60.0, 40.0);
		pacoteTarifaRepository.save(p4);
		
		Integer countCount = pacoteTarifaRepository.findAll().size(); 
		assertEquals(7,countCount); 
	}
	 
	@Test
	public void deletarUmClienteTest() {

		pacoteTarifaRepository.delete(p1);
		Optional<PacoteTarifa> pacoteTarifa = pacoteTarifaRepository.findById(p1.getId());
		assertThat(pacoteTarifa).isEmpty();
	}
	
	@Test
	public void retornarUmClienteByIdTest() {

		Optional<PacoteTarifa> pacoteTarifa = pacoteTarifaRepository.findById(p2.getId());
		assertNotNull(pacoteTarifa);
		System.out.println("Nome: "+pacoteTarifa.get().getNome());
	}
	
	@Test
	public void atualizarUmClienteByIdTest() {
		
		PacoteTarifa newObj = pacoteTarifaRepository.findById(p3.getId()).get();
		upDateDate(newObj, p3);
		pacoteTarifaRepository.save(newObj);

	}
	
	private void upDateDate(PacoteTarifa newObj, PacoteTarifa obj) {
		newObj.setNome(obj.getNome());
		newObj.setValorMaximo(obj.getValorMaximo());
		newObj.setValorMinimo(obj.getValorMinimo());
		
	}

}
