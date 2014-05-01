package com.betha.educaweb.integrador.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.betha.educaweb.integrador.util.PropertiesReader;

/**
 * Essa classe representa fábrica que vai prover conexão para os dados de
 * saída dos testes
 * 
 * @author fernando moraes
 * 
 */
public class OutputConnectionFactory implements ConnectionFactory {

	private final static String FILE_NAME = "config/result.properties";
	private final DatabaseProperties dbProperties;

	private OutputConnectionFactory(DatabaseProperties dbProperties) {
		this.dbProperties = dbProperties;
	}

	public static OutputConnectionFactory getInstance() {

		try {
			DatabaseProperties dbProperties = new DatabaseProperties(
					PropertiesReader.read(FILE_NAME));

			Class.forName(dbProperties.getJdbcClass());
			return new OutputConnectionFactory(dbProperties);

		} catch (final ClassNotFoundException | IllegalArgumentException e) {
			throw new IllegalStateException(e.getCause());
		}

	}

	@Override
	public Connection getConnection() throws DatabaseRuntimeException {
		try{
			return DriverManager.getConnection(dbProperties.getAddress(),
				dbProperties.getUser(), dbProperties.getPassword());
		}catch(SQLException e){
			throw new DatabaseRuntimeException(e.getCause());
		}
	}

}
