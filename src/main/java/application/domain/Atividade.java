package application.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "atividade")
public class Atividade {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projetoinovacaoatividade_seq")
	@SequenceGenerator(name = "projetoinovacaoatividade_seq", sequenceName = "projetoinovacaoatividade_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	@Column(name = "titulo")
	private String titulo;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "data_inclusao")
	private Date dataInclusao;

	@Column(name = "data_inicio")
	private Date dataInicio;

	@Column(name = "data_termino")
	private Date dataTermino;

	@Column(name = "data_ultimaalteracao")
	private Date dataUltimaAlteracao;

	@Column(name = "status")
	private Integer status;

	@JoinColumn(name = "usuario_ultimaalteracao", referencedColumnName = "id")
	@ManyToOne
	private Usuario ultimaAlteracaoUsuario;

	@JoinColumn(name = "projeto_id", referencedColumnName = "id")
	@ManyToOne
	private ProjetoInovacao projetoInovacao;

	@JoinColumn(name = "responsavel_id", referencedColumnName = "id", columnDefinition="bigint", nullable = true)
	@ManyToOne()
	private Usuario responsavel;

	public Atividade() {
	}

	public Atividade(String titulo, String descricao, Date dataCriacao) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atividade other = (Atividade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}

	public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Usuario getUltimaAlteracaoUsuario() {
		return ultimaAlteracaoUsuario;
	}

	public void setUltimaAlteracaoUsuario(Usuario ultimaAlteracaoUsuario) {
		this.ultimaAlteracaoUsuario = ultimaAlteracaoUsuario;
	}

	public ProjetoInovacao getProjetoInovacao() {
		return projetoInovacao;
	}

	public void setProjetoInovacao(ProjetoInovacao projetoInovacao) {
		this.projetoInovacao = projetoInovacao;
	}

	public Usuario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

}
