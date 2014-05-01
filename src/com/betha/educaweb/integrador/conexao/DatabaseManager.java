package com.betha.educaweb.integrador.conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DatabaseManager {

	private Connection conexao;

	private DatabaseManager(Connection conexao) {
		this.conexao = conexao;
	}

	public static DatabaseManager getInstance(ConnectionFactory factory)
			throws DatabaseRuntimeException {
		Connection conexao = factory.getConnection();
		return new DatabaseManager(conexao);
	}
	
	private boolean queryValida(String sql, Object... params) throws NullPointerException{
		if(sql == null){
			throw new NullPointerException("NullPointerException ao executar query. Sql informado e nulo");
		}
		
		int count = 0;
		for(int i =0 ; i < sql.length(); i++){
			if(sql.charAt(i) == '?'){
				count++;
			}
		}
		
		return count == params.length;
	}

	public List<List<Object>> executarQuery(String sql, Object...params) throws DatabaseRuntimeException {
		
		if(!queryValida(sql, params)){
			throw new DatabaseRuntimeException("numero de parametros não é igual ao numero de binds do sql");
		}
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conexao.prepareStatement(sql);
			int idx = 1;
			for(Object param: params){
				stmt.setObject(idx++, param);
			}
			rs = stmt.executeQuery();
			
			ResultSetMetaData mtdata = rs.getMetaData();
			List<List<Object>> result = new LinkedList<>();
			while(rs.next()){
				List<Object> collumns = new LinkedList<>();
				for(int i=1;i <mtdata.getColumnCount(); i++){
					collumns.add(rs.getObject(i));
				}
				result.add(collumns);
			}
			
			return result;
		} catch (SQLException e) {
			throw new DatabaseRuntimeException(e.getCause());
		} finally {
			try {
				fecharRecursos(stmt, rs);
			} catch (SQLException e) {
				throw new DatabaseRuntimeException(e.getCause());
			}
		}
	}
	
	private void fecharRecursos(PreparedStatement stmt, ResultSet rs) throws SQLException{
		if(rs != null){
			rs.close();
		}
		if(stmt != null){
			stmt.close();
		}
	}

	public void executarSQL() throws DatabaseRuntimeException {
	}

	public void comitar() throws DatabaseRuntimeException {
		try {
			conexao.commit();
		} catch (SQLException e) {
			throw new DatabaseRuntimeException(e.getCause());
		}
	}

	public void fecharConexao() throws DatabaseRuntimeException {
		try {
			conexao.close();
		} catch (SQLException e) {
			throw new DatabaseRuntimeException(e.getCause());
		}
	}

}
