package application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.domain.Situacao;
import application.repositories.SituacaoRepository;

@RestController
@RequestMapping("/api/v1/situacoes")
public class SituacaoController {

	@Autowired
	private SituacaoRepository situacaoRepository;
	
	@GetMapping()
	public List<Situacao> getAllSituacoesAtivas() {
		return (List<Situacao>) situacaoRepository.findAllAtivas();
	}
}
