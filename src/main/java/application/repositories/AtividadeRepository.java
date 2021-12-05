package application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import application.domain.Atividade;

public interface AtividadeRepository extends JpaRepository<Atividade, Long>{
}
