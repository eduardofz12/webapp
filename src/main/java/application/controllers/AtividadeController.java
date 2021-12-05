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

import application.domain.Atividade;
import application.exception.ResourceNotFoundException;
import application.repositories.AtividadeRepository;
import application.services.AtividadeService;

@RestController
@RequestMapping("/api/v1/atividades")
public class AtividadeController {

	@Autowired
	private AtividadeRepository projetoInovacaoAtividadeRepository;
	
	@Autowired
	private AtividadeService atividadeService;
	
	@GetMapping()
	public List<Atividade> getAllProjetoInovacaoAtividades(@RequestParam Map<String,String> requestParams) throws ResourceNotFoundException {
		return atividadeService.getAllAtividadesByParams(requestParams);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Atividade> getProjetoInovacaoAtividadeById(@PathVariable(value = "id") Long projetoInovacaoAtividadeId) throws ResourceNotFoundException {
		
		Atividade projetoInovacaoAtividade = projetoInovacaoAtividadeRepository.findById(projetoInovacaoAtividadeId)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhuma projetoInovacaoAtividade com este id :: " + projetoInovacaoAtividadeId));
		
		return ResponseEntity.ok().body(projetoInovacaoAtividade);
	}
	
	@PostMapping()
	public Atividade createProjetoInovacaoAtividade(@RequestBody Atividade projetoInovacaoAtividade) {
		return projetoInovacaoAtividadeRepository.save(projetoInovacaoAtividade);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Atividade> updateAtividade(@PathVariable(value = "id") Long projetoInovacaoAtividadeId, @Valid @RequestBody Atividade projetoInovacaoAtividadeDetails) throws ResourceNotFoundException {
		
		Atividade projetoInovacaoAtividade = projetoInovacaoAtividadeRepository.findById(projetoInovacaoAtividadeId)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhuma projetoInovacaoAtividade com este id :: " + projetoInovacaoAtividadeId));
		
		projetoInovacaoAtividade.setDescricao(projetoInovacaoAtividadeDetails.getDescricao());
		projetoInovacaoAtividade.setTitulo(projetoInovacaoAtividadeDetails.getTitulo());
		
		return ResponseEntity.ok(projetoInovacaoAtividadeRepository.save(projetoInovacaoAtividade));
	}
	
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteProjetoInovacaoAtividade(@PathVariable(value = "id") Long projetoInovacaoAtividadeId) throws ResourceNotFoundException {
		
		Atividade projetoInovacaoAtividade = projetoInovacaoAtividadeRepository.findById(projetoInovacaoAtividadeId)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhuma projetoInovacaoAtividade com este id :: " + projetoInovacaoAtividadeId));
		
		projetoInovacaoAtividadeRepository.delete(projetoInovacaoAtividade);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return response;
	}
}
