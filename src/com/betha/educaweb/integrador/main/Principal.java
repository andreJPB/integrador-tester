package com.betha.educaweb.integrador.main;

import com.betha.educaweb.integrador.casodeuso.CasoDeUsoRunner;



public class Principal {

	public static void main(String[] args) {

//		if (args.length < 2) {
//			throw new IllegalArgumentException(
//					"Numero de argumentos invalidos. Voce deve informar os casos de uso de entrada e saida");
//		}
//		
//		/**
//		 * tempo em executar um step de entrada e o mesmo de saida
//		 */
//		Long tempo = 0l;
//		if(args.length == 3){
//			try {
//				tempo = new Long(args[2]);
//			}catch(NumberFormatException e){
//				throw new IllegalArgumentException("Tempo de execucao entre os steps invalidos");
//			}
//		}
		
		try {
			new CasoDeUsoRunner("default-casos-testes/entrada.txt", "default-casos-testes/entrada.txt", 0l).run();
		
		} catch (InterruptedException e) {
			e.printStackTrace();
		};

	}
}
