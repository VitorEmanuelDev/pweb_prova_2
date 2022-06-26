package chamados.dto;


public class PhotoDTO {

	private Long id;
	private String nome;
	private byte[] image;
	private Long perfilId;
	
	public PhotoDTO() {
		
	}
	
//	public Long getId() {
//		return id;
//	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
//	public Long getPerfilId() {
//		return perfilId;
//	}
//	public void setPerfilId(Long perfilId) {
//		this.perfilId = perfilId;
//	}
}