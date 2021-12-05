package application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import application.domain.Situacao;

public interface SituacaoRepository extends JpaRepository<Situacao, Long>{

	@Query(value = "SELECT * FROM Situacao WHERE ativa = 'S'", nativeQuery = true)
	List<Situacao> findAllAtivas();
}
