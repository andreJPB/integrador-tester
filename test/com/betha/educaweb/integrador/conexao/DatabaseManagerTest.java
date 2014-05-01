package com.betha.educaweb.integrador.conexao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;


public class DatabaseManagerTest {
	
	@Test
	public void deveExecutarQuery(){
		DatabaseManager manager = DatabaseManager.getInstance(InputConnectionFactory.getInstance());
		String sql = "SELECT * FROM Clientes WHERE codigo_cliente = ?";
		List<List<Object>> rs = manager.executarQuery(sql, 2);
		
		for(List<Object> r: rs){
			for(Object o: r){
				System.out.println(o);
			}
		}
		
		manager.fecharConexao();
		
		Assert.assertNotNull(rs);
	}

}
