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

import application.domain.ProjetoInovacao;
import application.exception.ResourceNotFoundException;
import application.repositories.ProjetoInovacaoRepository;
import application.services.ProjetoInovacaoService;

@RestController
@RequestMapping("/api/v1/projetos")
public class ProjetoInovacaoController {

	@Autowired
	private ProjetoInovacaoRepository projetoInovacaoRepository;
	
	@Autowired
	private ProjetoInovacaoService projetoInovacaoService;
	
	@GetMapping()
	public List<ProjetoInovacao> getAllProjetoInovacaos(@RequestParam Map<String,String> requestParams) {
		return projetoInovacaoService.getAllProjetosByParams(requestParams);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProjetoInovacao> getProjetoInovacaoById(@PathVariable(value = "id") Long projetoInovacaoId) throws ResourceNotFoundException {
		
		ProjetoInovacao projetoInovacao = projetoInovacaoRepository.findById(projetoInovacaoId)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum projetoInovacao com este id :: " + projetoInovacaoId));
		
		return ResponseEntity.ok().body(projetoInovacao);
	}
	
	@PostMapping()
	public ProjetoInovacao createProjetoInovacao(@RequestBody ProjetoInovacao projetoInovacao) throws ResourceNotFoundException {
		
		return projetoInovacaoService.createProjetoInovacao(projetoInovacao);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProjetoInovacao> updateProjetoInovacao(@PathVariable(value = "id") Long projetoInovacaoId, @Valid @RequestBody ProjetoInovacao projetoInovacaoDetails) throws ResourceNotFoundException {
		
		ProjetoInovacao projetoInovacao = projetoInovacaoRepository.findById(projetoInovacaoId)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum projetoInovacao com este id :: " + projetoInovacaoId));
		
		projetoInovacao.setDescricao(projetoInovacaoDetails.getDescricao());
		projetoInovacao.setTitulo(projetoInovacaoDetails.getTitulo());
		
		return ResponseEntity.ok(projetoInovacaoRepository.save(projetoInovacao));
	}
	
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteProjetoInovacao(@PathVariable(value = "id") Long projetoInovacaoId) throws ResourceNotFoundException {
		
		ProjetoInovacao projetoInovacao = projetoInovacaoRepository.findById(projetoInovacaoId)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum projetoInovacao com este id :: " + projetoInovacaoId));
		
		projetoInovacaoRepository.delete(projetoInovacao);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return response;
	}
}
