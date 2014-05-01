package com.betha.educaweb.integrador.conexao;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

public class InputConnectionFactoryTest {

	@Test
	public void deveRetornarInstancia() {
		ConnectionFactory factory = InputConnectionFactory.getInstance();
		Assert.assertNotNull(factory);
	}

	@Test
	public void deveRetornarConexao() {
		ConnectionFactory factory = InputConnectionFactory.getInstance();
		Connection conexao = factory.getConnection();
		try {
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
