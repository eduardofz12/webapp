package application.domain;

import java.io.Serializable;
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
@Table(name = "desafio")
public class Desafio implements Serializable{

	private static final long serialVersionUID = -4787213351298912761L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="desafio_seq")
	@SequenceGenerator(name = "desafio_seq", sequenceName = "desafio_seq", initialValue = 1, allocationSize=1)
	private Long id;

	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "descricao")
	private String descricao;
	
	@JoinColumn(name = "situacao_id", referencedColumnName = "id")
    @ManyToOne
    private Situacao situacao;
	
	@Column(name = "dataInclusao")
	private Date dataInclusao;
	
	@Column(name = "dataFinal")
	private Date dataFinal;
	
	@Column(name = "dataInicial")
	private Date dataInicial;
	
	@JoinColumn(name = "Usuario_id", referencedColumnName = "id")
    @ManyToOne
    private Usuario usuario;
	
	public Desafio() {
	}

	public Desafio(String titulo, String descricao) {
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
		Desafio other = (Desafio) obj;
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

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public Situacao getSituacao() {
		return situacao;
	}

}
