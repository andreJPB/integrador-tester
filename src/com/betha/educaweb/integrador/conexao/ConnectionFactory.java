package com.betha.educaweb.integrador.conexao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionFactory {

	public Connection getConnection() throws DatabaseRuntimeException;
}
