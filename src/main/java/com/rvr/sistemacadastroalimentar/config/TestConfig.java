package com.rvr.sistemacadastroalimentar.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.rvr.sistemacadastroalimentar.entities.Alimentos;
import com.rvr.sistemacadastroalimentar.entities.PesoAlimento;
import com.rvr.sistemacadastroalimentar.entities.Refeicoes;
import com.rvr.sistemacadastroalimentar.entities.TiposAlimento;
import com.rvr.sistemacadastroalimentar.repositories.AlimentosRepository;
import com.rvr.sistemacadastroalimentar.repositories.PesoAlimentoRepository;
import com.rvr.sistemacadastroalimentar.repositories.RefeicoesRepository;
import com.rvr.sistemacadastroalimentar.repositories.TiposAlimentosRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private AlimentosRepository alimentosRepository;
	
	@Autowired
	private TiposAlimentosRepository tiposAlimentosRepository;
	
	@Autowired
	private RefeicoesRepository refeicoesRepository;
	
	@Autowired
	private PesoAlimentoRepository pesoAlimentoRepositoty;
	
	@Override
	public void run(String... args) throws Exception {

		TiposAlimento ta1 = new TiposAlimento(null, "Grão/Cereal");
		TiposAlimento ta2 = new TiposAlimento(null, "Carne Bovina");
		TiposAlimento ta3 = new TiposAlimento(null, "Carne de Frango");
		TiposAlimento ta4 = new TiposAlimento(null, "Ovo");
		TiposAlimento ta5 = new TiposAlimento(null, "Bolacha");
		TiposAlimento ta6 = new TiposAlimento(null, "Queijo");
		TiposAlimento ta7 = new TiposAlimento(null, "Oleonígeno");
		TiposAlimento ta8 = new TiposAlimento(null, "Pão");
		tiposAlimentosRepository.saveAll(Arrays.asList(ta1, ta2, ta3, ta4, ta5, ta6, ta7, ta8));
		
		Alimentos a1 = new Alimentos(null, "Arroz Branco Cozido", 1.0, 0.281, 0.025, 0.002, ta1);
		Alimentos a2 = new Alimentos(null, "Feijão Carioca Cozido", 1.0, 0.136, 0.048, 0.005, ta1);
		Alimentos a3 = new Alimentos(null, "Frango Refogado", 1.0, 0.0, 0.315, 0.022, ta3);
		Alimentos a4 = new Alimentos(null, "Queijo Minas Frescal", 1.0, 0.033, 0.174, 0.173, ta6);
		Alimentos a5 = new Alimentos(null, "Ovo de Galinha", 1.0, 0.02, 0.134, 0.07, ta4);
		Alimentos a6 = new Alimentos(null, "Bolacha de Nata", 1.0, 0.63, 0.434, 0.1167, ta5);
		Alimentos a7 = new Alimentos(null, "Creme de Avelã", 1.0, 0.48, 0.05, 0.42, ta7);
		Alimentos a8 = new Alimentos(null, "Pão de Forma Integral 100%", 1.0, 0.34, 0.138, 0.042, ta8);
		alimentosRepository.saveAll(Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8));

		Refeicoes r1 = new Refeicoes(null, Instant.parse("2020-07-24T12:53:07Z"));
		Refeicoes r2 = new Refeicoes(null, Instant.parse("2020-07-24T15:53:07Z"));

		PesoAlimento pa1 = new PesoAlimento(null, a1, 400.0, r1);
		PesoAlimento pa2 = new PesoAlimento(null, a2, 300.0, r1);
		PesoAlimento pa3 = new PesoAlimento(null, a3, 200.0, r1);
		PesoAlimento pa4 = new PesoAlimento(null, a4, 43.0, r1);
		PesoAlimento pa5 = new PesoAlimento(null, a5, 170.0, r1);
		PesoAlimento pa6 = new PesoAlimento(null, a6, 50.0, r1);
		PesoAlimento pa7 = new PesoAlimento(null, a7, 10.0, r2);
		PesoAlimento pa8 = new PesoAlimento(null, a8, 36.0, r2);
		PesoAlimento pa9 = new PesoAlimento(null, a4, 51.0, r2);
		refeicoesRepository.saveAll(Arrays.asList(r1, r2));
		pesoAlimentoRepositoty.saveAll(Arrays.asList(pa1, pa2, pa3, pa4, pa5, pa6, pa7, pa8, pa9));

		
	}

	
}
