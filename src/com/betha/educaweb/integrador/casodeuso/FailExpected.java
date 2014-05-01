package com.betha.educaweb.integrador.casodeuso;

import java.util.List;

public class FailExpected {

	private final List<String[]> esperado;
	private final List<List<Object>> resultado;

	public FailExpected(List<String[]> esperado, List<List<Object>> resultado) {
		this.esperado = esperado;
		this.resultado = resultado;
	}

	public List<String[]> getEsperado() {
		return esperado;
	}

	public List<List<Object>> getResultado() {
		return resultado;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Esperado(s):\n");
		
		int count = 1;
		for (String[] e : esperado) {
			count = 1;
			for (String c : e) {
				sb.append(c.trim());
				if (count < e.length) {
					sb.append(" | ");
				}
				count++;
			}
			sb.append("\n");
		}

		sb.append("Resultado(s):\n");
		for (List<Object> e : resultado) {
			count = 1;
			for (Object c : e) {
				sb.append(c.toString().trim());
				if(count < e.size()){
					sb.append(" | ");
				}
				count++;
			}
			sb.append("\n");
		}

		return sb.toString();
	}

}
