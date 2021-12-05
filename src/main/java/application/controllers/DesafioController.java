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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.domain.Desafio;
import application.exception.ResourceNotFoundException;
import application.repositories.DesafioRepository;
import application.services.DesafioService;

@RestController
@RequestMapping("/api/v1/desafios")
public class DesafioController {

	@Autowired
	private DesafioRepository desafioRepository;
	
	@Autowired
	private DesafioService desafioService;
	
	@GetMapping()
	public List<Desafio> getAllDesafiosByParams(@RequestParam Map<String,String> requestParams) {
		
		return desafioService.getAllDesafiosByParams(requestParams);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Desafio> getDesafioById(@PathVariable(value = "id") Long desafioId) throws ResourceNotFoundException {
		
		Desafio desafio = desafioRepository.findById(desafioId)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum desafio com este id :: " + desafioId));
		
		return ResponseEntity.ok().body(desafio);
	}
	
	@PostMapping()
	public Desafio createDesafio(@RequestBody Desafio desafio) {
		return desafioRepository.save(desafio);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Desafio> updateDesafio(@PathVariable(value = "id") Long desafioId, @Valid @RequestBody Desafio desafioDetails) throws ResourceNotFoundException {
		
		Desafio desafio = desafioRepository.findById(desafioId)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum desafio com este id :: " + desafioId));
		
		desafio.setDescricao(desafioDetails.getDescricao());
		desafio.setTitulo(desafioDetails.getTitulo());
		
		return ResponseEntity.ok(desafioRepository.save(desafio));
	}
	
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteDesafio(@PathVariable(value = "id") Long desafioId) throws ResourceNotFoundException {
		
		Desafio desafio = desafioRepository.findById(desafioId)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum desafio com este id :: " + desafioId));
		
		desafioRepository.delete(desafio);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return response;
	}
}
