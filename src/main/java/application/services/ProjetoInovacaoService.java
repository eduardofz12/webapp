package application.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import application.domain.Desafio;
import application.domain.Ideia;
import application.domain.ProjetoInovacao;
import application.domain.Situacao;
import application.repositories.DesafioRepository;
import application.repositories.IdeiaRepository;
import application.repositories.ProjetoInovacaoRepository;

@Service
public class ProjetoInovacaoService {

	@Autowired
	private ProjetoInovacaoRepository projetoRepository;

	@Autowired
	private IdeiaRepository ideiaRepository;
	
	@Autowired
	private DesafioRepository desafioRepository;
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	public ProjetoInovacao createProjetoInovacao(ProjetoInovacao projetoInovacao) throws ResourceNotFoundException{
		if(projetoInovacao.getId() == null) {
			
			Optional<Ideia> ideia = ideiaRepository.findById(projetoInovacao.getIdeia().getId());
			if(ideia.isEmpty()) {
				throw new ResourceNotFoundException("Não foi possível encontrar a ideia selecionada no banco de dados.");
			}
			
			if(ideia.get().getDesafio() != null) {
				if(3 != ideia.get().getDesafio().getSituacao().getId()) {
					Optional<Desafio> desafio = desafioRepository.findById(ideia.get().getDesafio().getId());
					desafio.get().setSituacao(new Situacao(Long.valueOf(3), null, null));
					desafioRepository.save(desafio.get());
				}
			}
			
			if(3 != ideia.get().getSituacao().getId()) {
				
				ideia.get().setSituacao(new Situacao(Long.valueOf(3), null, null));
				ideiaRepository.save(ideia.get());
			}
			
		}
		
		return projetoRepository.save(projetoInovacao);
	}

	public List<ProjetoInovacao> getAllProjetosByParams(Map<String, String> requestParams) {
		EntityManager em = entityManagerFactory.createEntityManager();
		Query query = em.createQuery(createQuery(requestParams));
		List<ProjetoInovacao> projetos = query.getResultList();
		em.close();

		return projetos;
	}

	public String createQuery(Map<String, String> requestParams) {

		StringBuilder sb = new StringBuilder();

		sb.append("SELECT d \n");
		sb.append("FROM ProjetoInovacao d \n");
		sb.append(" WHERE 1=1 \n");

		if (StringUtils.isNotBlank(requestParams.get("titulo"))) {
			sb.append(" and LOWER(d.titulo) like LOWER('%").append(requestParams.get("titulo")).append("%') \n");
		}
		
		if (StringUtils.isNotBlank(requestParams.get("responsavel"))) {
			sb.append(" and d.responsavel = '").append(requestParams.get("responsavel")).append("' \n");
		}

		if (StringUtils.isNotBlank(requestParams.get("situacao")) && !"1".equals(requestParams.get("situacao"))
				&& !"null".equals(requestParams.get("situacao"))) {
			sb.append(" and d.situacao = '").append(requestParams.get("situacao")).append("' \n");
		}

		if (StringUtils.isNotBlank(requestParams.get("dataInclusaoInicio"))) {
			sb.append(" and (d.dataInclusao between '").append(requestParams.get("dataInclusaoInicio"))
					.append("' and '").append(requestParams.get("dataInclusaoFim")).append("') \n");
		}

		if (StringUtils.isNotBlank(requestParams.get("dataFinalInicio"))) {
			sb.append(" and (d.dataFinal between '").append(requestParams.get("dataFinalInicio")).append("' and '")
					.append(requestParams.get("dataFinalFim")).append("') \n");
		}

		sb.append(" order by d.id desc \n");

		return sb.toString();
	}

}
