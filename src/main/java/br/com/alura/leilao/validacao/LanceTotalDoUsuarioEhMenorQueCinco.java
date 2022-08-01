package br.com.alura.leilao.validacao;

import java.util.List;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;

public class LanceTotalDoUsuarioEhMenorQueCinco implements ValidarLance {

	@Override
	public Boolean validar(Lance lance) {
		int total = 0;
		
		List<Lance> lances = lance.getLeilao().getLances(); 
		
		for (Lance l : lances) {
			if (l.getUsuario().equals(lance.getUsuario()))
				++total;
			System.out.println(l.getUsuario().getNome());
		}				
		if (total <= 5) {
			return Boolean.TRUE;
		}
		else 
			throw new IllegalArgumentException("Usuário já realizou o numero máximo (5) de lances no leilão!");
		
	}

	@Override
	public String showErrorMessage() {
		return "Usuário já realizou o numero máximo (5) de lances no leilão!";
	}

}