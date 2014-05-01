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
		try {
			conexao.setAutoCommit(false);
		
		} catch (SQLException e) {
			try {
				conexao.close();
			} catch (SQLException e1) {
				throw new DatabaseRuntimeException(e.getCause());
			}
			throw new DatabaseRuntimeException(e.getCause());
		}

		return new DatabaseManager(conexao);
	}


	public List<List<Object>> executarQuery(String sql)
			throws DatabaseRuntimeException {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conexao.prepareStatement(sql);
			rs = stmt.executeQuery();

			ResultSetMetaData mtdata = rs.getMetaData();
			List<List<Object>> result = new LinkedList<>();
			while (rs.next()) {
				List<Object> collumns = new LinkedList<>();
				for (int i = 1; i <= mtdata.getColumnCount(); i++) {
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

	private void fecharRecursos(PreparedStatement stmt, ResultSet rs)
			throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (stmt != null) {
			stmt.close();
		}
	}

	public void executarSQL(String sql) throws DatabaseRuntimeException {
		PreparedStatement stmt = null;
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseRuntimeException(e.getCause());
		} finally {
			try {
				fecharRecursos(stmt, null);
			} catch (SQLException e) {
				throw new DatabaseRuntimeException(e.getCause());
			}
		}
	}

	public void comitar() throws DatabaseRuntimeException {
		try {
			conexao.commit();
		} catch (SQLException e) {
			e.printStackTrace();
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
