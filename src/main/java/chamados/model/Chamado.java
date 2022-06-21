package chamados.model;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;


@Entity
@Table(name = "chamados")
public class Chamado {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	//@NotNull
	@Column(name = "cliente_id", insertable = false, updatable = false)
	private Long clienteId;
	@NotNull
	@Pattern(regexp="\\d{14}", message = "Apenas informe 14 digitos.")
	@CNPJ
	@Column(name = "cliente_cnpj")
	private String clienteCnpj;
	@NotNull
	@Size(min=3, max=100)
	@Column(name = "nome_cliente")
	private String nomeCliente;
	@NotNull
	@Column(name = "assunto")
	private String assunto;
	@NotNull
	@Column(name = "status")
	private String status;
	@NotNull
	@Column(name = "endereco")
	private String endereco;
	@NotNull
	@Column(name = "cadastrado_em")
	private Date cadastradoEm;
	@ManyToOne
	private Cliente cliente;
	
	public Chamado(@NotNull Long clienteId,
			@NotNull @Pattern(regexp = "\\d{14}", message = "Apenas informe 14 digitos.") @CNPJ String clienteCnpj,
			@NotNull @Size(min = 3, max = 100) String nomeCliente, @NotNull String assunto,
			@NotNull String endereco) {
		this.clienteId = clienteId;
		this.clienteCnpj = clienteCnpj;
		this.nomeCliente = nomeCliente;
		this.assunto = assunto;
		this.status = Status.EM_ABERTO.toString();
		this.endereco = endereco;
		this.cadastradoEm = setCadastradoEm();
	}

	public Chamado() {

	}

	public long getId() {
		return id;
	}

	public Long getClienteId() {
		return clienteId;
	}
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
	public String getClienteCnpj() {
		return clienteCnpj;
	}
	public void setClienteCnpj(String clienteCnpj) {
		this.clienteCnpj = clienteCnpj;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Date getCadastradoEm() {
		return cadastradoEm;
	}
	public Date setCadastradoEm() {
		return new Date();
	}

}
