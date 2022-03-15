package com.brq.cliente.pacote.tarifas.enums;

public enum Pacote {
	
	CLASSIC(1L, "PACOTE_CLASSIC", 10.0, 20.0);
	
	private Long id;
	private String nome;
	private Double valorMinimo;
	private Double valorMaximo;
	
	
	private Pacote(Long id, String nome, Double valorMinimo, Double valorMaximo) {
		this.id = id;
		this.nome = nome;
		this.valorMinimo = valorMinimo;
		this.valorMaximo = valorMaximo;
	}


	public Long getId() {
		return id;
	}


	public String getNome() {
		return nome;
	}


	public Double getValorMinimo() {
		return valorMinimo;
	}


	public Double getValorMaximo() {
		return valorMaximo;
	}


	
	
	
	
	

}
