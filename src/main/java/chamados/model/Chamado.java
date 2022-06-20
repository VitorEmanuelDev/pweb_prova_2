package chamados.model;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;

import com.fasterxml.jackson.annotation.JsonBackReference;

import chamados.dto.ClienteDTO;

@Entity
@Table(name = "chamados")
public class Chamado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	@Column(name = "cliente_id")
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
	private LocalDateTime cadastradoEm;
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.PERSIST, targetEntity = ClienteDTO.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id", referencedColumnName = "id")
	private Cliente cliente;
	
	public Chamado() {
		
	}
	
	public Chamado(@NotNull Long clienteId,
			@NotNull @Pattern(regexp = "\\d{14}", message = "Apenas informe 14 digitos.") @CNPJ String clienteCnpj,
			@NotNull @Size(min = 3, max = 100) String nomeCliente, @NotNull String assunto,
			@NotNull String endereco, Cliente cliente) {
		super();
		this.clienteId = clienteId;
		this.clienteCnpj = clienteCnpj;
		this.nomeCliente = nomeCliente;
		this.assunto = assunto;
		this.status = Status.EM_ABERTO.toString();
		this.endereco = endereco;
		this.cadastradoEm = setCadastradoEm();
		this.cliente = cliente;
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
	public LocalDateTime getCadastradoEm() {
		return cadastradoEm;
	}
	public LocalDateTime setCadastradoEm() {
		TimeZone timezone = TimeZone.getTimeZone("Americana/Sao_Paulo");
		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		formatter.setTimeZone(timezone);
		gc.setTimeZone(timezone);
		gc.setTime(new Date());
		LocalDateTime localDateTime = LocalDateTime.parse(formatter.format(gc.getTime()));
		this.cadastradoEm = localDateTime;
		return cadastradoEm;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	

}
