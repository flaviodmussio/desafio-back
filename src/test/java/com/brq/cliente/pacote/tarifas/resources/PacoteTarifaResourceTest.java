package com.brq.cliente.pacote.tarifas.resources;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.brq.cliente.pacote.tarifas.entities.Cliente;
import com.brq.cliente.pacote.tarifas.entities.PacoteTarifa;
import com.brq.cliente.pacote.tarifas.repositories.ClienteRepository;
import com.brq.cliente.pacote.tarifas.repositories.PacoteTarifaRepository;
import com.brq.cliente.pacote.tarifas.services.PacoteTarifaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PacoteTarifaResource.class)
public class PacoteTarifaResourceTest {

	@MockBean
	private PacoteTarifaService  pacoteTarifaService;

	@MockBean
	private PacoteTarifaRepository pacoteTarifaRepository;
	
	@MockBean
	private ClienteRepository clienteRepository;

	@Autowired
	private MockMvc mockMvc;

	private Cliente c1;
	private PacoteTarifa p1;

	@BeforeEach
	public void before() {

		p1 = new PacoteTarifa(1L, "PACOTE_CLASSIC", 20.0, 30.0);
		c1 = new Cliente(1L, "Mary", "18611816803", new Date(), p1);
	}

	@Test
	public void retornarListaPacoteTarifasTest() throws Exception {

		List<PacoteTarifa> pacoteList = List.of(p1);
		when(pacoteTarifaRepository.findAll()).thenReturn(pacoteList);
		when(pacoteTarifaService.findAll()).thenReturn(pacoteList);
		this.mockMvc.perform(get("/pacoteTarifas")).andExpect(status().isOk())
				.andExpect(content().string(containsString("PACOTE_CLASSIC")));
	}

	@Test
	public void retornarPacoteTarifaPorIdTest() throws Exception {

		when(pacoteTarifaRepository.findById(p1.getId())).thenReturn(Optional.of(p1));
		when(pacoteTarifaService.find(p1.getId())).thenReturn(p1);
		this.mockMvc.perform(get("/pacoteTarifas/" + c1.getId())).andExpect(status().isOk())
				.andExpect(content().string(containsString("PACOTE_CLASSIC")));
	}
	
	@Test
	public void criarPacoteTarifaTest() throws Exception {

		when(pacoteTarifaService.insert(p1)).thenReturn(p1);
		when(pacoteTarifaRepository.save(p1)).thenReturn(p1);

		this.mockMvc.perform(post("/pacoteTarifas").content(asJsonString(p1)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	}

	@Test
	public void atualizarPacoteTarifaTest() throws Exception {

		this.mockMvc
				.perform(put("/pacoteTarifas/" + p1.getId()).content(asJsonString(p1))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

	@Test
	public void deletarPacoteTarifaTest() throws Exception {

		this.mockMvc
				.perform(delete("/pacoteTarifas/" + p1.getId()).content(asJsonString(p1))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

	@Test
	public void retornarPacoteTarifaByIdTest() throws Exception {

		this.mockMvc
				.perform(get("/pacoteTarifas/" + p1.getId()).content(asJsonString(p1))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
