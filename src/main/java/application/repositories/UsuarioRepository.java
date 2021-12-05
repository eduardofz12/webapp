package application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import application.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Usuario findByEmail(String email);
	
	public Usuario findByEmailAndPassword(String email, String password);
	
	@Query("SELECT u FROM Usuario u WHERE LOWER(u.username) = LOWER(:username)")
	public Usuario findByUsernameCaseInsensitive(@Param("username") String username);
}
