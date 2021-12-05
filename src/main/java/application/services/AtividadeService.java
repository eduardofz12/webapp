package application.services;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.domain.Atividade;
import application.exception.ResourceNotFoundException;

@Service
public class AtividadeService {

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	public List<Atividade> getAllAtividadesByParams(Map<String, String> requestParams) throws ResourceNotFoundException {
		if(!isParamsValid(requestParams)) {
			throw new ResourceNotFoundException("Não é possível realizar a busca com os parâmetros informados.");
		}
		EntityManager em = entityManagerFactory.createEntityManager();
		Query query = em.createQuery(createQuery(requestParams));
		List<Atividade> atividades = query.getResultList();
		em.close();

		return atividades;
	}

	public String createQuery(Map<String, String> requestParams) {

		StringBuilder sb = new StringBuilder();

		sb.append("SELECT d \n");
		sb.append("FROM Atividade d \n");
		sb.append(" WHERE 1=1 \n");

		if (StringUtils.isNotBlank(requestParams.get("projetoId"))) {
			sb.append(" and d.projetoInovacao = ").append(requestParams.get("projetoId")).append(" \n");
		}
		
		if (StringUtils.isNotBlank(requestParams.get("status"))) {
			sb.append(" and d.status = ").append(requestParams.get("status")).append(" \n");
		}

		sb.append(" order by d.id desc \n");

		return sb.toString();
	}
	
	public Boolean isParamsValid(Map<String, String> requestParams) {
		if(StringUtils.isBlank(requestParams.get("projetoId"))) {
			return false;
		}
		return true;
	}
}
