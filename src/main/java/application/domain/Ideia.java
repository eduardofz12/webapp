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
@Table(name = "ideia")
public class Ideia {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ideia_seq")
	@SequenceGenerator(name = "ideia_seq", sequenceName = "ideia_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "dataInclusao")
	private Date dataInclusao;
	
	@JoinColumn(name = "situacao_id", referencedColumnName = "id")
    @ManyToOne
    private Situacao situacao;
	
	@JoinColumn(name = "desafio_id", referencedColumnName = "id", columnDefinition="bigint", nullable = true)
	@ManyToOne()
	private Desafio desafio;
	
	@JoinColumn(name = "autor_id", referencedColumnName = "id", columnDefinition="bigint")
	@ManyToOne()
	private Usuario autor;
	
	public Ideia() {
	}

	public Ideia(String titulo, String descricao, Date dataInclusao) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.dataInclusao = dataInclusao;
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
		Ideia other = (Ideia) obj;
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

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public Desafio getDesafio() {
		return desafio;
	}

	public void setDesafio(Desafio desafio) {
		this.desafio = desafio;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

}
