package com.betha.educaweb.integrador.casodeuso;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class CasoDeUsoReaderTest {
	
	@Test
	public void deveLerCasoDeUso(){
		CasoDeUso casoDeUso = CasoDeUsoReader.read("default-casos-testes/entrada.txt");
		
		Assert.assertNotNull(casoDeUso);
	}

}
