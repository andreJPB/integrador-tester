package com.betha.educaweb.integrador.casodeuso;

import java.util.List;

public class ExpectedValidator {

	public static boolean isValid(List<List<Object>> validar, Query query) {
		List<String[]> valores = query.getExpected();
		// valida as linhas
		if (valores.size() != validar.size()) {
			return false;
		}

		int row = 0;
		for (List<Object> linha : validar) {
			// valida as colunas
			if (linha.size() != valores.get(row).length) {
				return false;
			}
			
			int column = 0;
			for(Object coluna: linha){
				if(!(coluna.toString().trim().equals(valores.get(row)[column].toString().trim()))){
					return false;
				}
				
				column++;
			}
			row++;
		}

		return true;
	}

}
