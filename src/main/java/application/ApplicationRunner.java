package application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import application.domain.Situacao;
import application.repositories.SituacaoRepository;

//@EnableAutoConfiguration(exclude = {HibernateJpaAutoConfiguration.class, DataSourceAutoConfiguration.class})
@SpringBootApplication
public class ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationRunner.class, args);
	}
	
	@Bean
    CommandLineRunner insertSituacoes(SituacaoRepository repo) {
		return args -> {
			List<Situacao> cadastros = repo.findAll();
			if(!cadastros.isEmpty()) {
				return;
			}
            List<Situacao> situacoes = new ArrayList<>();
            situacoes.add(new Situacao(null, "Todas", "S"));
            situacoes.add(new Situacao(null, "Aberto", "S"));
            situacoes.add(new Situacao(null, "Em implementação", "S"));
            situacoes.add(new Situacao(null, "Implementado", "S"));
            situacoes.add(new Situacao(null, "Rejeitado", "S"));
            situacoes.add(new Situacao(null, "Inativo", "S"));
            situacoes.add(new Situacao(null, "Aguardando solução", "N"));
            
            repo.saveAll(situacoes);
        };
    }
}
