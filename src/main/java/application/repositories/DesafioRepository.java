package application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import application.domain.Desafio;

public interface DesafioRepository extends JpaRepository<Desafio, Long> {

}
