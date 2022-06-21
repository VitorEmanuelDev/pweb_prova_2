package chamados.service;

import java.util.List;

import chamados.dto.PerfilDTO;
import chamados.model.Perfil;

public interface PerfilServiceInterface {
	public PerfilDTO createPerfil(Perfil perfil);
	public PerfilDTO updatePerfil(Long id, Perfil perfil);
	public void deletePerfil(Long id);
	public List<PerfilDTO> getPerfis();
	public PerfilDTO getPerfil(Long id);
}
