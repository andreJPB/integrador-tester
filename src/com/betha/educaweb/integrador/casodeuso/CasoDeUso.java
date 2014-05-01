package com.betha.educaweb.integrador.casodeuso;

import java.util.List;

public class CasoDeUso {
	
	private final Long id;
	private final String comentario;
	private final List<Step> passos;
	
	public CasoDeUso(Long id, String comentario, List<Step> passos) {
		this.id = id;
		this.comentario = comentario;
		this.passos = passos;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getComentario() {
		return comentario;
	}
	
	public List<Step> getPassos() {
		return passos;
	}
}
