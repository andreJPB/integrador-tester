package com.betha.educaweb.integrador.casodeuso;

import java.util.LinkedList;
import java.util.List;

import com.betha.educaweb.integrador.conexao.DatabaseManager;
import com.betha.educaweb.integrador.conexao.DatabaseRuntimeException;
import com.betha.educaweb.integrador.conexao.InputConnectionFactory;
import com.betha.educaweb.integrador.conexao.OutputConnectionFactory;

public class CasoDeUsoRunner {
	private final Long tempo;
	private final String in;
	private final String out;

	private DatabaseManager managerInput;
	private DatabaseManager managerOutput;

	private List<FailExpected> expectedFails = new LinkedList<>();

	public CasoDeUsoRunner(String in, String out, Long tempo) {
		this.in = in;
		this.out = out;
		this.tempo = tempo;
	}

	public void run() throws InterruptedException {
		CasoDeUso input = CasoDeUsoReader.read(in);
		CasoDeUso saida = CasoDeUsoReader.read(out);

		managerInput = DatabaseManager.getInstance(InputConnectionFactory
				.getInstance());
		managerOutput = DatabaseManager.getInstance(OutputConnectionFactory
				.getInstance());

		executarPassos(input, saida);
		managerInput.fecharConexao();
		managerOutput.fecharConexao();
		
		imprimirErrors();
	}

	public void executarPassos(CasoDeUso input, CasoDeUso saida)
			throws InterruptedException {
		for (Step passoEntrada : input.getPassos()) {
			executarSqlsEntrada(passoEntrada.getQuerys());

			Step passoSaida = saida.getPasso(passoEntrada.getNumero());
			if (passoSaida == null) {
				throw new IllegalArgumentException("O step: "
						+ passoEntrada.getNumero()
						+ " nao passui um step no caso de saida");
			}

			Thread.sleep(tempo);
			executarSqlsSaida(passoSaida.getQuerys());

		}
	}

	private void executarSqlsEntrada(List<Query> querys)
			throws DatabaseRuntimeException {
		for (Query query : querys) {
			if (query.temRetorno()) {
				List<List<Object>> result = managerInput.executarQuery(query
						.getSql());
				if (!ExpectedValidator.isValid(result, query)) {
					expectedFails.add(new FailExpected(query.getExpected(),
							result));
				}
				
			} else {
				managerInput.executarSQL(query.getSql());
			}

			managerInput.comitar();
		}

	}

	private void executarSqlsSaida(List<Query> querys)
			throws DatabaseRuntimeException {
		for (Query query : querys) {
			if (query.temRetorno()) {
				List<List<Object>> result = managerOutput.executarQuery(query
						.getSql());
				
				if (!ExpectedValidator.isValid(result, query)) {
					expectedFails.add(new FailExpected(query.getExpected(),
							result));

				}
			} else {
				managerOutput.executarSQL(query.getSql());
			}

			managerOutput.comitar();
		}
	}

	private void imprimirErrors() {
		for(FailExpected fail: expectedFails){
			System.out.println(fail);
		}
	}

}
