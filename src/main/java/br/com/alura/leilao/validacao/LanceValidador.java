package br.com.alura.leilao.validacao;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.alura.leilao.model.Lance;

public class LanceValidador implements ValidarLance{
	
	private List<ValidarLance> validadores;
	
	private List<String> mensagensDeErro = new ArrayList<String>();
		
	public LanceValidador() {
		validadores = Arrays.asList(
				new LanceEhMaiorQueOAnterior(),
				new LancePodeSerRealizadoPoisLeilaoEstaAberto(),
				new LanceFeitoPorUsuarioDiferente(),
				new LanceNaoEhMenorQueOValorInicialDoLeilao(),
				new LanceNaoPossuiDataMenorQueADeDoLeilao(),
				new LanceTotalDoUsuarioEhMenorQueCinco());
	}
	
	@Override
	public Boolean validar(Lance lance) {		 
		validadores.forEach(v -> {
			if(!v.validar(lance))
				mensagensDeErro.add(v.showErrorMessage());
			});
		mensagensDeErro.forEach(System.out::println);
		return mensagensDeErro.isEmpty();
	}

	@Override
	public String showErrorMessage() {
		return "não é possivel realizar o teste";
	}
}