package application.services;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.domain.Desafio;

@Service
public class DesafioService {

	public static Logger log = LoggerFactory.getLogger(DesafioService.class);
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	public List<Desafio> getAllDesafiosByParams(Map<String,String> requestParams) {
		
		EntityManager em = entityManagerFactory.createEntityManager();
		Query query = em.createQuery(createQuery(requestParams));
		List<Desafio>  desafios = query.getResultList();
        em.close();
        
		return desafios;
	}
	
	public String createQuery(Map<String,String> requestParams) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT d \n");
		sb.append("FROM Desafio d \n");
		sb.append(" WHERE 1=1 \n");
		
		if(StringUtils.isNotBlank(requestParams.get("titulo"))) {
			sb.append(" and LOWER(d.titulo) like LOWER('").append(requestParams.get("titulo")).append("') \n");
		}
		
		if(StringUtils.isNotBlank(requestParams.get("situacao")) && !"1".equals(requestParams.get("situacao")) && !"null".equals(requestParams.get("situacao"))) {
			sb.append(" and d.situacao = '").append(requestParams.get("situacao")).append("' \n");
		}
		
		if(StringUtils.isNotBlank(requestParams.get("dataInclusaoInicio"))) {
			sb.append(" and (d.dataInclusao between '").append(requestParams.get("dataInclusaoInicio")).append("' and '").append(requestParams.get("dataInclusaoFim")).append("') \n");
		}
		
		if(StringUtils.isNotBlank(requestParams.get("dataFinalInicio"))) {
			sb.append(" and (d.dataFinal between '").append(requestParams.get("dataFinalInicio")).append("' and '").append(requestParams.get("dataFinalFim")).append("') \n");
		}

		sb.append(" order by d.id desc \n");
		
		return sb.toString();
	}
	
}
