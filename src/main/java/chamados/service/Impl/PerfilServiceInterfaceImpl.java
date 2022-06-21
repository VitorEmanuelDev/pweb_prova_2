package chamados.service.Impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import chamados.dto.PerfilDTO;
import chamados.model.Perfil;
import chamados.repository.PerfilRepository;
import chamados.service.PerfilServiceInterface;

@Service
public class PerfilServiceInterfaceImpl implements PerfilServiceInterface{
	
	@Autowired
	private PerfilRepository perfilRepository;

	@Override
	public PerfilDTO createPerfil(Perfil perfil) {
		Perfil perfilAtual = new Perfil(perfil.getNome(), 
				perfil.getEmail(), perfil.getFoto());
		this.perfilRepository.save(perfilAtual);
		PerfilDTO perfilDTO = new PerfilDTO();
		perfilDTO.setNome(perfil.getNome());
		perfilDTO.setEmail(perfil.getEmail());
		perfilDTO.setFoto(perfil.getFoto());
		return perfilDTO;	
	}

	@Override
	public PerfilDTO updatePerfil(Long id, Perfil perfil) {
		Perfil perfilAtual = this.perfilRepository.getReferenceById(id);	
		this.perfilRepository.save(perfilAtual);
		PerfilDTO perfilDTO = new PerfilDTO();
		perfilDTO.setNome(perfil.getNome());
		perfilDTO.setEmail(perfil.getEmail());
		perfilDTO.setFoto(perfil.getFoto());
		return perfilDTO;	
	}

	@Override
	public void deletePerfil(Long id) {
		this.perfilRepository.deleteById(id);		
	}

	@Override
	public List<PerfilDTO> getPerfis() {
		List<PerfilDTO> perfisDTO = new ArrayList<>();		
		this.perfilRepository.findAll().forEach(perfil -> {
			PerfilDTO perfilDTO = new PerfilDTO();
			perfisDTO.add(perfilDTO)	;		
		});
		return perfisDTO;
	}

	@Override
	public PerfilDTO getPerfil(Long id) {
		Perfil perfil = perfilRepository.getReferenceById(id);
		PerfilDTO perfilDTO = new PerfilDTO();
		perfilDTO.setNome(perfil.getNome());
		perfilDTO.setEmail(perfil.getEmail());
		perfilDTO.setFoto(perfil.getFoto());
		return perfilDTO;	
	}

}
