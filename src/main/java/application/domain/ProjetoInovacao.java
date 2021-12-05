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
@Table(name = "projetoinovacao")
public class ProjetoInovacao {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="projetoinovacao_seq")
	@SequenceGenerator(name = "projetoinovacao_seq", sequenceName = "projetoinovacao_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "descricao")
	private String descricao;
	
	@JoinColumn(name = "Ideia_id", referencedColumnName = "id")
    @ManyToOne
    private Ideia ideia;
	
	@JoinColumn(name = "Responsavel_id", referencedColumnName = "id")
    @ManyToOne
    private Usuario responsavel;
	
	@Column(name = "data_inicio")
	private Date dataInicio;
	
	@Column(name = "data_termino")
	private Date dataTermino;
	
	@JoinColumn(name = "usuario_inclusao_id", referencedColumnName = "id")
    @ManyToOne
    private Usuario usuarioInclusao;
	
	@Column(name = "data_inclusao")
	private Date dataInclusao;
	
	@JoinColumn(name = "situacao_id", referencedColumnName = "id")
    @ManyToOne
    private Situacao situacao;
	
	public ProjetoInovacao() {
	}

	public ProjetoInovacao(String titulo, String descricao, Date dataCriacao) {
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
		ProjetoInovacao other = (ProjetoInovacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Ideia getIdeia() {
		return ideia;
	}

	public void setIdeia(Ideia ideia) {
		this.ideia = ideia;
	}

	public Usuario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public Usuario getUsuarioInclusao() {
		return usuarioInclusao;
	}

	public void setUsuarioInclusao(Usuario usuarioInclusao) {
		this.usuarioInclusao = usuarioInclusao;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

}
