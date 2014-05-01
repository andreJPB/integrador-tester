package com.betha.educaweb.integrador.conexao;

public class DatabaseRuntimeException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public DatabaseRuntimeException(String msg){
		super(msg);
	}
	
	public DatabaseRuntimeException(Throwable cause){
		super(cause);
	}
	
}
