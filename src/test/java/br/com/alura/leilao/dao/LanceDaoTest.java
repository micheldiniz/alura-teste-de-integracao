package br.com.alura.leilao.dao;


import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;
import br.com.alura.leilao.util.builder.LanceBuilder;
import br.com.alura.leilao.util.builder.LeilaoBuilder;
import br.com.alura.leilao.util.builder.UsuarioBuilder;

class LanceDaoTest {

	private LanceDao dao;
	private EntityManager em;
	
	@BeforeEach
	public void beforeEach() {
		this.em = JPAUtil.getEntityManager();
		this.dao = new LanceDao(em);
		em.getTransaction().begin();
	}
	
	@AfterEach
	public void afterEach() {
		em.getTransaction().rollback();
	}
		
	@Nested
	@DisplayName("Regras de negócio de Lance")
	class PropoeLance{
//		@Test
//		void NaoDeveriaAceitarLanceComValorInvalido() {
//			Usuario usuario = geraNovoUsuario();
//			Usuario usuario2 = geraNovoUsuario();
//			
//			Leilao leilao = geraLeilao(usuario, "50");		
//			
//			Lance lance = geraLance(usuario2, leilao, "51");
//			leilao.propoe(lance);
//			
//			Lance lance2 = geraLance(usuario, leilao, "-51");			
//
//			Assert.assertEquals(false, leilao.propoe(lance2));
//		}
		
		@Test
		void NaoDeveriaAceitarLanceComDataMenorQueADeAbertura() {
			Usuario usuario = geraNovoUsuario();
			Usuario usuario2 = geraNovoUsuario();
			
			Leilao leilao = geraLeilao(usuario, "50", LocalDate.now().plusDays(1));
			
			System.out.println( LocalDate.now().plusDays(1));
			
			Lance lance = geraLance(usuario, leilao, "51");
			leilao.propoe(lance);
			Lance lance2 = geraLance(usuario2, leilao, "56");
			
			Assert.assertEquals(false, leilao.propoe(lance2));
		}
		
		@Test
		void NaoDeveriaAceitarLanceComValorMenorDeUmCentavo() {
			Usuario usuario = geraNovoUsuario();
			Usuario usuario2 = geraNovoUsuario();
			
			Leilao leilao = geraLeilao(usuario, "0.1");		
			
			Lance lance = geraLance(usuario2, leilao, "0.01");
			leilao.propoe(lance);
			
			Lance lance2 = geraLance(usuario, leilao, "0.02");
			leilao.propoe(lance2);

			Assert.assertEquals(false, leilao.propoe(lance));
		}
		
		@Test
		void NaoDeveriaAceitarDoisLancesSeguidosDeUmMesmoUsuario() {
			Usuario usuario = geraNovoUsuario();
			
			Leilao leilao = geraLeilao(usuario, "500");		
			
			Lance lance = geraLance(usuario, leilao, "400");
			leilao.propoe(lance);
			
			Lance lance2 = geraLance(usuario, leilao, "500");			
			
			Assert.assertEquals(false, leilao.propoe(lance2));
		}
		
		@Test
		void NaoDeveriaAceitarNovoLanceSeTotalDeLancesDadosPeloUsuarioForCinco() {
			Usuario usuario = geraNovoUsuario();					
			Usuario usuario2 = geraNovoUsuario();
			
			Leilao leilao = geraLeilao(usuario, "500");		
			
			Lance lance1 = geraLance(usuario, leilao, "400");
			leilao.propoe(lance1);
									
			Lance lance2 = geraLance(usuario2, leilao, "500");
			leilao.propoe(lance2);
			
			Lance lance3 = geraLance(usuario, leilao, "600");
			leilao.propoe(lance3);
			
			Lance lance4 = geraLance(usuario2, leilao, "700");
			leilao.propoe(lance4);
			
			Lance lance5 = geraLance(usuario, leilao, "800");
			leilao.propoe(lance5);
			
			Lance lance6 = geraLance(usuario2, leilao, "500");
			leilao.propoe(lance6);
			
			Lance lance7 = geraLance(usuario, leilao, "600");
			leilao.propoe(lance7);
			
			Lance lance8 = geraLance(usuario2, leilao, "700");
			leilao.propoe(lance8);
			
			Lance lance9 = geraLance(usuario, leilao, "800");
			leilao.propoe(lance9);
			
			Lance lance10 = geraLance(usuario2, leilao, "900");
			leilao.propoe(lance10);
			
			Lance lance11 = geraLance(usuario2, leilao, "900");
			
			
			Assert.assertEquals(false, leilao.propoe(lance11));
		}
		
		@Test
		void NaoDeveriaAceitarLanceComValorMenorQueOUltimo() {
			Usuario usuario = geraNovoUsuario();					
			Usuario usuario2 = geraNovoUsuario();
			
			Leilao leilao = geraLeilao(usuario, "500");		
			
			Lance lance = geraLance(usuario, leilao, "400");
			leilao.propoe(lance);
			
			Lance lance2 = geraLance(usuario2, leilao, "500");
			leilao.propoe(lance2);
			
			Lance lance3 = geraLance(usuario2, leilao, "490");
			leilao.propoe(lance3);
			
			
			Assert.assertEquals(false, leilao.propoe(lance3));		
		}
		
		@Test
		void NaoDeveriaAceitarLanceMenorQueOValorInicialDoLeilao() {
			Usuario usuario = geraNovoUsuario();					
			Usuario usuario2 = geraNovoUsuario();
			
			Leilao leilao = geraLeilao(usuario, "500");		
			
			Lance lance = geraLance(usuario, leilao, "400");
			leilao.propoe(lance);
			
			Lance lance2 = geraLance(usuario2, leilao, "400");
			
			Assert.assertEquals(false, leilao.propoe(lance2));			
		}
		
	}
	
	@Nested
	class consultaLanc{
	
		@Test
		void testDeveriaRetornarMaiorLanceDoLeilao() {
			Usuario usuario = geraNovoUsuario();					
			Leilao leilao = geraLeilao(usuario, "700");			
			Lance lance = geraLance(usuario, leilao, "400");				
			Lance lance2 = geraLance(usuario, leilao,"600");
			
			leilao.propoe(lance);
			leilao.propoe(lance2);
					
			Lance maior = dao.buscarMaiorLanceDoLeilao(leilao);
			
			Assert.assertEquals(maior.getValor(), new BigDecimal("600"));
			
		}

	}
	private Lance geraLance(Usuario usuario, Leilao leilao, String valor) {
		Lance lance = new LanceBuilder()
				.comValor(valor)
				.comUsuario(usuario)
				.comLeilao(leilao)
				.criar();
		em.persist(lance);
		return lance;
	}
	
	public Leilao geraLeilao(Usuario usuario, String valorInicial, LocalDate data) {
		Leilao leilao = new LeilaoBuilder()
				.comNome("Mochila")
				.comValorInicial(valorInicial)
				.comData(data)
				.comUsuario(usuario)
				.criar();		
		em.persist(leilao);
		return leilao;
	}

	private Leilao geraLeilao(Usuario usuario, String valorInicial) {
		Leilao leilao = new LeilaoBuilder()
				.comNome("Mochila")
				.comValorInicial(valorInicial)
				.comData(LocalDate.now())
				.comUsuario(usuario)
				.criar();		
		em.persist(leilao);
		return leilao;
	}
	
	private Usuario geraNovoUsuario() {
		Usuario usuario = new UsuarioBuilder()
				.comNome("Fulano")
				.comEmail("fulano@gmail.com")
				.comSenha("12345678").criar();	
		em.persist(usuario);
		return usuario;
	}
	
	

}
