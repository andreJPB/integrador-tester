package com.betha.educaweb.integrador.casodeuso;

import java.util.List;

public class Step {
	
	private final List<Query> querys;
	private final int numero;
	
	public Step(int numero, List<Query> querys) {
		this.numero = numero;
		this.querys = querys;
	}
	
	public List<Query> getQuerys() {
		return querys;
	}
	
	public int getNumero() {
		return numero;
	}

}
