package application.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.domain.Ideia;
import application.exception.ResourceNotFoundException;
import application.repositories.IdeiaRepository;

@RestController
@RequestMapping("/api/v1/ideias")
public class IdeiaController {

	@Autowired
	private IdeiaRepository ideiaRepository;
	
	@GetMapping()
	public List<Ideia> getAllIdeias() {
		return (List<Ideia>) ideiaRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Ideia> getIdeiaById(@PathVariable(value = "id") Long ideiaId) throws ResourceNotFoundException {
		
		Ideia ideia = ideiaRepository.findById(ideiaId)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhuma ideia com este id :: " + ideiaId));
		
		return ResponseEntity.ok().body(ideia);
	}
	
	@PostMapping()
	public Ideia createIdeia(@RequestBody Ideia ideia) {
		return ideiaRepository.save(ideia);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Ideia> updateIdeia(@PathVariable(value = "id") Long ideiaId, @Valid @RequestBody Ideia ideiaDetails) throws ResourceNotFoundException {
		
		Ideia ideia = ideiaRepository.findById(ideiaId)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhuma ideia com este id :: " + ideiaId));
		
		ideia.setDescricao(ideiaDetails.getDescricao());
		ideia.setTitulo(ideiaDetails.getTitulo());
		
		return ResponseEntity.ok(ideiaRepository.save(ideia));
	}
	
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteIdeia(@PathVariable(value = "id") Long ideiaId) throws ResourceNotFoundException {
		
		Ideia ideia = ideiaRepository.findById(ideiaId)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhuma ideia com este id :: " + ideiaId));
		
		ideiaRepository.delete(ideia);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return response;
	}
}
