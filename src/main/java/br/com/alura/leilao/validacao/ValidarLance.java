package br.com.alura.leilao.validacao;

import br.com.alura.leilao.model.Lance;

public interface ValidarLance {
	
	public Boolean validar(Lance lance);

	public String showErrorMessage();	

}
