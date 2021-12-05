package application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.domain.Usuario;
import application.exception.ResourceNotFoundException;
import application.repositories.UsuarioRepository;
import application.services.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping()
	public Usuario cadastraUsuario(@RequestBody Usuario usuario) throws Exception {
		return usuarioService.salvaUsuario(usuario);
	}
	
	@PostMapping("/login")
	public Usuario loginUser(@RequestBody Usuario user) throws Exception {
		return usuarioService.loginUser(user);
	}
	
	@GetMapping()
	public List<Usuario> getAllUsuarios() {
		return (List<Usuario>) usuarioService.getAllUsuarios();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable(value = "id") Long usuarioId) throws ResourceNotFoundException {
		
		Usuario usuario = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum usuário com este id :: " + usuarioId));
		
		return ResponseEntity.ok().body(usuario);
	}
}
