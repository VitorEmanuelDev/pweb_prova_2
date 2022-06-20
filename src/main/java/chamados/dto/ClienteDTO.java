package chamados.dto;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class ClienteDTO {

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
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "clienteDTO", orphanRemoval = true)
	private List<ChamadoDTO> chamadoDTOs;

	public ClienteDTO() {

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<ChamadoDTO> getChamado() {
		return chamadoDTOs;
	}

	public void setChamado(List<ChamadoDTO> chamadoDTOs) {
		this.chamadoDTOs = chamadoDTOs;
	}

}
