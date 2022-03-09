package com.brq.cliente.pacote.tarifas.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.brq.cliente.pacote.tarifas.enums.Pacote;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class PacoteTarifa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Double valorMinimo;
	private Double valorMaximo;
	
	@JsonIgnore
	@OneToMany(mappedBy="pacoteTarifa")
	private List<Cliente> clientes = new ArrayList<>();
	
	public PacoteTarifa() {
		
	}
	
	public PacoteTarifa(Pacote classic) {
		addPacote(classic);
	}
	
	public void addPacote(Pacote pacote) {
		this.id = pacote.getId();
		this.nome = pacote.getNome();
		this.valorMinimo = pacote.getValorMinimo();
		this.valorMaximo = pacote.getValorMaximo();
	}

	

	public PacoteTarifa(Long id, String nome, Double valorMinimo, Double valorMaximo) {
		super();
		this.id = id;
		this.nome = nome;
		this.valorMinimo = valorMinimo;
		this.valorMaximo = valorMaximo;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValorMinimo() {
		return valorMinimo;
	}

	public void setValorMinimo(Double valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	public Double getValorMaximo() {
		return valorMaximo;
	}

	public void setValorMaximo(Double valorMaximo) {
		this.valorMaximo = valorMaximo;
	}

	

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PacoteTarifa other = (PacoteTarifa) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	

}
