package chamados.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "perfis")
public class Perfil {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull
	@Size(min=3, max=100)
	@Column(name = "nome")
	private String nome;
	@NotNull
	@Email
	@Pattern(regexp=".+@.+\\..+", message = "Informe um e-mail válido")
	@Column(name="email", unique=true)
	private String email;
	@OneToOne(targetEntity=Photo.class, fetch=FetchType.EAGER)
	@JoinColumn(name="perfil_id")
	private Photo foto;

	public Perfil() {

	}

	public Perfil(@NotNull @Size(min = 3, max = 100) String nome,
			@NotNull @Email @Pattern(regexp = ".+@.+\\..+", message = "Informe um e-mail válido") String email) {
		this.nome = nome;
		this.email = email;
	}
	

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Photo getFoto() {
		return foto;
	}

	public void setFoto(Photo foto) {
		this.foto = foto;
	}

}