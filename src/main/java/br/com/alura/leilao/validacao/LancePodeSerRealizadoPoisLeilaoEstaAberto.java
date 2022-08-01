package br.com.alura.leilao.validacao;

import br.com.alura.leilao.model.Lance;

public class LancePodeSerRealizadoPoisLeilaoEstaAberto implements ValidarLance {

	@Override
	public Boolean validar(Lance lance) {	
		if(lance.getLeilao().isAberto()) {
			return Boolean.TRUE;
		}else {
			throw new IllegalArgumentException("Leilão está fechado e não está recebendo novas propostas!");
		}		
	}

	@Override
	public String showErrorMessage() {
		return "Leião está fechado e não está recebendo novas propostas!";
	}

}