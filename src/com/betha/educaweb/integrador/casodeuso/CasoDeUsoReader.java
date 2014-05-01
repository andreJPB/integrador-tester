package com.betha.educaweb.integrador.casodeuso;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CasoDeUsoReader {

	private CasoDeUsoReader() {
	}

	@SuppressWarnings("resource")
	public static CasoDeUso read(String file) {

		try {
			FileInputStream input = new FileInputStream(file);
			byte[] data = new byte[input.available()];

			input.read(data);
			String document = new String(data);
			String[] configs = document.split("\\$");

			String comentario = null;
			Long id = null;
			String upperCaseConfig = null;
			List<Step> steps = new LinkedList<>();
			
			for (String config : configs) {
				if (config == null) {
					continue;
				}
				upperCaseConfig = config.toUpperCase();

				if (upperCaseConfig.startsWith("ID")) {
					id = new Long(config.replace("ID: ", "").trim());
				}

				if (upperCaseConfig.startsWith("COMENTARIO")) {
					comentario = config.replace("COMENTARIO: ", "").trim();
				}
				
				if (upperCaseConfig.startsWith("STEP")) {
					Step step = null;
//					int numero = Integer.parseInt(config.replace("STEP:", "").trim());
					int numero =1 ;
					List<Query> querysObject = new LinkedList<>();
					
					String[] querys = upperCaseConfig.split("\\#");
					for (String query : querys) {
						if (query.trim().isEmpty() || query.startsWith("STEP")) {
							continue;
						}

						Query queryObject = null;
						String tableName = null;
						SqlTypes sqlType = null;
						String where = null;
						Object[] params = null;

						String[] attrs = query.split("\\n");
						for (String attr : attrs) {
							if(attr.startsWith("QUERY")){
								continue;
							}
							String[] values = attr.split(":");
							boolean achouSQLOperation = false;
							boolean achouValores = false;
							for(String value: values){
								
								if(achouSQLOperation){
									achouSQLOperation = false;
									tableName = value.trim();
									continue;
								}
								
								if(isSqlOperation(value.trim())){
									achouSQLOperation = true;
									sqlType = SqlTypes.parse(value.trim());
									continue;
								}
								
								if(value.toUpperCase().startsWith("VALORES")){
									achouValores = true;
									continue;
								}
								
								if(achouValores){
									String[] valores = value.split(",");
									params = new Object[valores.length];
									int count = 0;
									for(String valor: valores){
										params[count++] = valor.trim();
									}
									achouValores =false;
									continue;
								}
							}
						}
						
						queryObject = new Query(tableName, sqlType, where, params);
						querysObject.add(queryObject);
					}
					
					step = new Step(numero, querysObject);
					steps.add(step);
				}

			}

			CasoDeUso caso = new CasoDeUso(id, comentario, steps);
			return caso;

		} catch (IOException e) {
			throw new IllegalArgumentException(e.getCause());
		}
	}

	private static boolean isSqlOperation(String str) {
		try {
			
			SqlTypes.parse(str);
			return true;
		} catch (IllegalArgumentException | NullPointerException e) {

			return false;
		}
	}
}
