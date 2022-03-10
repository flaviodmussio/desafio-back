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
import com.brq.cliente.pacote.tarifas.services.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ClienteResource.class)
public class ClienteResourceTest {

	@MockBean
	private ClienteRepository clienteRepository;

	@MockBean
	private ClienteService clienteservice;

	@MockBean
	private PacoteTarifaRepository pacoteTarifaRepository;

	@Autowired
	private MockMvc mockMvc;

	private Cliente c1;
	private PacoteTarifa p1;

	@BeforeEach
	public void before() {

		p1 = new PacoteTarifa(1L, "PACOTE_CLASSIC", 20.0, 30.0);
		c1 = new Cliente(1L, "Carlos", "18611816803", new Date(), p1);
	}

	@Test
	public void retornarListaClientesTest() throws Exception {

		List<Cliente> clienteList = List.of(c1);
		when(clienteRepository.findAll()).thenReturn(clienteList);
		when(clienteservice.findAll()).thenReturn(clienteList);
		this.mockMvc.perform(get("/clientes")).andExpect(status().isOk())
				.andExpect(content().string(containsString("Carlos")));
	}

	@Test
	public void retornarClientePorIdTest() throws Exception {

		when(clienteRepository.findById(c1.getId())).thenReturn(Optional.of(c1));
		when(clienteservice.find(c1.getId())).thenReturn(c1);
		this.mockMvc.perform(get("/clientes/" + c1.getId())).andExpect(status().isOk())
				.andExpect(content().string(containsString("Carlos")));
	}

	@Test
	public void criarClienteTest() throws Exception {

		when(clienteservice.insert(c1)).thenReturn(c1);
		when(clienteRepository.save(c1)).thenReturn(c1);

		this.mockMvc.perform(post("/clientes").content(asJsonString(c1)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	}

	@Test
	public void atualizarClienteTest() throws Exception {

		this.mockMvc
				.perform(put("/clientes/" + c1.getId()).content(asJsonString(c1))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

	@Test
	public void deletarClienteTest() throws Exception {

		this.mockMvc
				.perform(delete("/clientes/" + c1.getId()).content(asJsonString(c1))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

	@Test
	public void retornarClienteByIdTest() throws Exception {

		this.mockMvc
				.perform(get("/clientes/" + c1.getId()).content(asJsonString(c1))
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
