package chamados.service;

import java.util.List;

import chamados.dto.PerfilDTO;
import chamados.exception.ResourceNotFoundException;
import chamados.model.Perfil;

public interface PerfilServiceInterface {
	public PerfilDTO createPerfil(Perfil perfil);
	public PerfilDTO updatePerfil(Long id, Perfil perfil) throws ResourceNotFoundException;
	public void deletePerfil(Long id);
	public List<PerfilDTO> getPerfis();
	public PerfilDTO getPerfil(Long id) throws ResourceNotFoundException;
}
