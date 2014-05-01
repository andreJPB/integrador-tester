package com.betha.educaweb.integrador.casodeuso;

import java.util.LinkedList;
import java.util.List;


public class Query {

	private final SqlTypes sqlType;
	private final String[] params;
	private final String tableName;
	private final String where;
	private final String set;
	private final List<String> expected;

	public Query(String tableName, SqlTypes sqlType, String where, String set, List<String> expected,
			String... params) {
		this.tableName = tableName;
		this.sqlType = sqlType;
		this.params = params != null ? params : new String[0];
		this.where = where;
		this.set = set;
		this.expected = expected;
	}

	public String getSql() {
		return sqlType.generateSQL(tableName, where, set, params);
	}
	
	/**
	 * Retorna se o sql precisa retornar algo ou é somente execução.
	 * @return
	 */
	public boolean temRetorno(){
		return sqlType.temRetorno();
	}
	
	public List<String[]> getExpected() {
		if(expected == null){
			return null;
		}
		
		List<String[]> r = new LinkedList<>();
		for(String e: expected){
			r.add(e.split(","));
		}
		return r;
	}

}
