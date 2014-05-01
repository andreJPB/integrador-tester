package com.betha.educaweb.integrador.conexao;

import java.util.Properties;

public class DatabaseProperties {
	
	private Properties properties;
	
	public DatabaseProperties(Properties properties){
		this.properties = properties;
	}
	
	public String getAddress(){
		return properties.getProperty("dbaddress");
	}
	
	public String getPassword(){
		return properties.getProperty("dbpassword");
	}
	
	public String getUser(){
		return properties.getProperty("dbuser");
	}
	
	public String getJdbcClass(){
		return properties.getProperty("jdbcclass");
	}

}
