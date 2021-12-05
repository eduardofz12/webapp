package application.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "situacao")
public class Situacao implements Serializable{

	private static final long serialVersionUID = 7282720668433329145L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="situacao_Seq")
	@SequenceGenerator(name = "situacao_Seq", sequenceName = "situacao_Seq", initialValue = 1, allocationSize=1)
	private Long id;

	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "ativa")
	private String ativa;

	public Situacao() {
	}
	
	public Situacao(Long id, String descricao, String ativa) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.ativa = ativa;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getAtiva() {
		return ativa;
	}

	public void setAtiva(String ativa) {
		this.ativa = ativa;
	}
	
}
