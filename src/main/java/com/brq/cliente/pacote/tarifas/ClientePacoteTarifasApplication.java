package com.brq.cliente.pacote.tarifas;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.brq.cliente.pacote.tarifas.entities.Cliente;
import com.brq.cliente.pacote.tarifas.entities.PacoteTarifa;
import com.brq.cliente.pacote.tarifas.repositories.ClienteRepository;
import com.brq.cliente.pacote.tarifas.repositories.PacoteTarifaRepository;

@SpringBootApplication
public class ClientePacoteTarifasApplication implements CommandLineRunner {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	@Autowired
	private PacoteTarifaRepository pacoteTarifaRepository;

	public static void main(String[] args) {
		SpringApplication.run(ClientePacoteTarifasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		
		  PacoteTarifa p1 = new PacoteTarifa(null, "PACOTE_CLASSIC", 20.0, 30.0);
		  PacoteTarifa p2 = new PacoteTarifa(null, "PACOTE_TOP", 40.0, 50.);
		  PacoteTarifa p3 = new PacoteTarifa(null, "PACOTE_PREMIUM", 50.0, 60.0);
		  
		  Cliente c1 = new Cliente(null, "Joao", "18611816803", new Date(), p1);
		  Cliente c2 = new Cliente(null, "Ana", "16522498712", new Date(), p2);
		  Cliente c3 = new Cliente(null, "Jose", "15633214505", new Date(), p2);
		  
		  
		  p1.getClientes().addAll(Arrays.asList(c1));
		  p2.getClientes().addAll(Arrays.asList(c2,c3));
		  
		  pacoteTarifaRepository.saveAll(Arrays.asList(p1,p2,p3));
		  clienteRepository.saveAll(Arrays.asList(c1,c2,c3));
		 
		  
		 
		
	}

}
