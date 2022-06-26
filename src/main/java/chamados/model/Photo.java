package chamados.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "fotos")
public class Photo {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name")
	private String nome;
	@Lob
	@Column(name = "content", length = 100000)
	private byte[] content;
//	@Column(name = "perfil_id")
//	private Long perfilId;
		
	public Photo() {
		
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
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] image) {
		this.content = image;
	}
//	public Long getPerfilId() {
//		return perfilId;
//	}
//	public void setPerfilId(Long perfilId) {
//		this.perfilId = perfilId;
//	}

}