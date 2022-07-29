package br.com.alura.leilao.validacao;

import java.util.List;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;

public class LanceEhMaiorQueOAnterior implements ValidarLance {

	@Override
	public Boolean validar(Lance lance) {
		Leilao leilao = lance.getLeilao();
		List<Lance> lances = leilao.getLances();
		Lance ultimoLance = leilao.getLances().get(lances.size() -1);
		if (lance.getValor().compareTo(ultimoLance.getValor()) == 1)
			return Boolean.TRUE;
		else
			throw new IllegalArgumentException("Valor do Lance é menor que o anterior!");
	}

	@Override
	public String showErrorMessage() {
		return "Valor do Lance é menor que o anterior!";
	}
	
}
