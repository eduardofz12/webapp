package application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import application.domain.Ideia;

public interface IdeiaRepository extends JpaRepository<Ideia, Long> {

}
