package br.com.alura.leilao.validacao;

import java.math.BigDecimal;

import br.com.alura.leilao.model.Lance;

public class LanceNaoEhMenorQueOValorInicialDoLeilao implements ValidarLance {

	@Override
	public Boolean validar(Lance lance) {
		BigDecimal valorInicialLeilao = lance.getLeilao().getValorInicial();
		if (lance.getValor().compareTo(valorInicialLeilao) > 0) {
			return Boolean.TRUE;
		}else
			throw new IllegalArgumentException("Lance com valor menor que o valor inicial do leilão!");
	}

	@Override
	public String showErrorMessage() {
		return "Lance com valor menor que o valor inicial do leilão!";
	}

}
