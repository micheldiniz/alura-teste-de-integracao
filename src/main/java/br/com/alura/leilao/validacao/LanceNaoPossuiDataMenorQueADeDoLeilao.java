package br.com.alura.leilao.validacao;

import java.time.LocalDate;

import br.com.alura.leilao.model.Lance;

public class LanceNaoPossuiDataMenorQueADeDoLeilao implements ValidarLance {

	@Override
	public Boolean validar(Lance lance) {		
		LocalDate dataAberturaLeilao = lance.getLeilao().getDataAbertura();
		if (!lance.getData().isBefore(dataAberturaLeilao)) {
			return Boolean.TRUE;
		}
		else {
			throw new IllegalArgumentException("Data do lance é menor que a Data de abertura do Leilao");			
		}
	}

	@Override
	public String showErrorMessage() {
		return "Data do lance menor que a Data de abertura do Leilao";
	}

}
