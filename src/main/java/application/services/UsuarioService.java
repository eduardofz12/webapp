package application.services;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.domain.Usuario;
import application.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario salvaUsuario(Usuario usuario) throws Exception {

		if (StringUtils.isNotBlank(usuario.getEmail())) {
			if (fetchUserByEmail(usuario.getEmail())) {
				throw new Exception("email ja existe");
			}
			
			return usuarioRepository.save(usuario);
		} 
		
		throw new Exception("email invalido");
	}

	public Boolean fetchUserByEmail(String email) {
		return usuarioRepository.findByEmail(email) != null;
	}
	
	public Usuario fetchUserByCredentials(String email, String password) {
		return usuarioRepository.findByEmailAndPassword(email, password);
	}

	public Usuario loginUser(Usuario user) throws Exception {
		
		if (StringUtils.isNotBlank(user.getEmail()) && StringUtils.isNotBlank(user.getPassword())) {
			return fetchUserByCredentials(user.getEmail(), user.getPassword());
		}
		
		return null;
	}

	public List<Usuario> getAllUsuarios() {
		return usuarioRepository.findAll();
	}
}
