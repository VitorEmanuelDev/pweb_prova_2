package chamados.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "clientes", uniqueConstraints={@UniqueConstraint(columnNames= "cnpj")})
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	@Size(min=3, max=100)
	private String nome;
	@NotNull
	@Pattern(regexp="\\d{14}", message = "Apenas informe 14 digitos.")
	@CNPJ
	@Column(name = "cnpj")
	private String cnpj;
	@NotNull
	@Size(min=10, max=140)
	@Column(name = "endereco")
	private String endereco;
	@Column(name = "chamados")
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "cliente", orphanRemoval = true)
	private List<Chamado> chamados;
	
	public Cliente() {
		
	}

	public Cliente(@NotNull @Size(min = 3, max = 100) String nome,
			@NotNull @Pattern(regexp = "\\d{14}", message = "Apenas informe 14 digitos.") @CNPJ String cnpj,
			@NotNull @Size(min = 10, max = 140) String endereco) {
		super();
		this.nome = nome;
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.chamados = new ArrayList<>();
	}

	public long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void modificarCliente(String nome, String cnpj) {
		this.nome = nome;
		this.cnpj = cnpj;
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<Chamado> getChamados() {
		return this.chamados;
	}

	public void adicionarChamado(Chamado chamado) {
		this.chamados.add(chamado);
	}

}
