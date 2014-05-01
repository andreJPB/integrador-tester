package com.betha.educaweb.integrador.casodeuso;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class CasoDeUsoReaderTest {
	
	@Test
	public void deveLerCasoDeUso(){
		CasoDeUso casoDeUso = CasoDeUsoReader.read("default-casos-testes/entrada.txt");
		
		Assert.assertNotNull(casoDeUso);
		
		List<Step> stepes = casoDeUso.getPassos();
		
		for(Step step: stepes){
			for(Query query: step.getQuerys()){
				for(Object o: query.getParams()){
					System.out.println(o);
				}
			}
		}
	}

}
