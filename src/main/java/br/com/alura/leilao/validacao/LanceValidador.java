package br.com.alura.leilao.validacao;


import java.util.ArrayList;
import java.util.List;

import br.com.alura.leilao.model.Lance;

public class LanceValidador implements ValidarLance{
	
	private List<ValidarLance> validadores = new ArrayList<ValidarLance>();
	
	private List<String> mensagensDeErro = new ArrayList<String>();
		
	public LanceValidador() {
		validadores.add(new LanceEhMaiorQueOAnterior());
		validadores.add(new LancePodeSerRealizadoPoisLeilaoEstaAberto());
		validadores.add(new LanceFeitoPorUsuarioDiferente());
		validadores.add(new LanceNaoEhMenorQueOValorInicialDoLeilao());
		validadores.add(new LanceNaoPossuiDataMenorQueADeDoLeilao());
		validadores.add(new LanceTotalDoUsuarioEhMenorQueCinco());
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