package com.betha.educaweb.integrador.conexao;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * Essa classe representa fábrica que vai prover conexão para os dados de saída dos testes
 * @author fernando moraes
 *
 */
public class ResultConnectionFactory implements ConnectionFactory{
	
	

	@Override
	public Connection getConnection() throws DatabaseRuntimeException {
		return null;
	}

}
