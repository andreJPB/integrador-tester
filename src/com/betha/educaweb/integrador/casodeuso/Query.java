package com.betha.educaweb.integrador.casodeuso;

public class Query {
	
	private final SqlTypes sqlType;
	private final Object[] params;
	private final String tableName;
	private final String where;
		
	public Query(String tableName, SqlTypes sqlType, String where, Object... params) {
		this.tableName = tableName;
		this.sqlType = sqlType;
		this.params = params;
		this.where = where;
	}
	
	
	public String getSql(){
		return sqlType.getValue()+ " " + tableName + " " + ( where != null ? where: "" ) ;
	}
	
	public Object[] getParams(){
		return params;
	}

}
