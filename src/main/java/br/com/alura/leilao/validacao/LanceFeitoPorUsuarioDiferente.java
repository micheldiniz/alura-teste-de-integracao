package br.com.alura.leilao.validacao;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;

public class LanceFeitoPorUsuarioDiferente implements ValidarLance {

	@Override
	public Boolean validar(Lance lance) {
		Usuario usuario = ultimoUsuarioAProporLance(lance);		
		if (!usuario.equals(lance.getUsuario())) {
			return Boolean.TRUE;
		}else
			throw new IllegalArgumentException("Não é permitido proposta de lance subsequente de um mesmo usuário!");		
	}

	@Override
	public String showErrorMessage() {
		return "Não é permitido proposta de lance subsequente de um mesmo usuário!";
	}
	
	
	private Usuario ultimoUsuarioAProporLance(Lance lance) {
		Leilao leilao = lance.getLeilao();
		Lance UltimoLance = leilao.getLances().get(leilao.getLances().size()-1);  
		Usuario usuario = UltimoLance.getUsuario();
		return usuario;
	}

}
