package com.betha.educaweb.integrador.casodeuso;

enum SqlTypes {

	INSERTE {
		@Override
		public String generateSQL(String tableName,String where, String set, String... params) {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO " + tableName + " VALUES ( ");
			for (String param : params) {
				sql.append(param).append(",");
			}
			sql.append(")");
			return sql.toString().replace(",)", " )");
		}
	},
	DELETE {
		@Override
		public String generateSQL(String tableName,String where, String set, String... params) {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM " + tableName);
			if(where != null){
				sql.append(" where ").append(where);
			}
			return sql.toString();
		}
	},
	UPDATE {
		@Override
		public String generateSQL(String tableName,String where,String set,  String... params) {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE " + tableName);
			sql.append(" SET ").append(set);
			sql.append(" WHERE ").append(where);
			return sql.toString();
		}
	},
	SELECT {
		@Override
		public String generateSQL(String tableName,String where,String set,  String... params) {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT * FROM ").append(tableName);
			if(where != null){
				sql.append(" WHERE ").append(where);
			}
			return sql.toString();
		}
		
		@Override
		public boolean temRetorno() {
			return true;
		}
	};

	/**
	 * 
	 * @param value
	 * @return
	 * @throws IllegalArgumentException
	 *             caso o value nao corresponda a nenhuma constante
	 * @throws NullPointerException
	 *             caso o value seja nulo
	 */
	public static SqlTypes parse(String value) throws IllegalArgumentException,
			NullPointerException {
		for (SqlTypes type : SqlTypes.values()) {
			if (value.toUpperCase().equals(type.name())) {
				return type;
			}
		}
		throw new IllegalArgumentException(
				"o value passado nao corresponde a nenhuma constante em SqlTypes");
	}

	public abstract String generateSQL(String tableName, String where, String set,  String... params);
	
	/**
	 * Informa se o tipo do sql quando executado tem algum retorno
	 * @return
	 */
	public boolean temRetorno(){
		return false;
	}

}
