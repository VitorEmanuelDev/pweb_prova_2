package chamados.service.Impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chamados.dto.PerfilDTO;
import chamados.exception.ResourceNotFoundException;
import chamados.model.Perfil;
import chamados.repository.PerfilRepository;
import chamados.service.PerfilServiceInterface;

@Service
public class PerfilServiceInterfaceImpl implements PerfilServiceInterface{

	@Autowired
	private PerfilRepository perfilRepository;

	@Override
	@Transactional
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
	@Transactional
	public PerfilDTO updatePerfil(Long id, Perfil perfil) throws ResourceNotFoundException {

		Perfil perfilAtual = this.perfilRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado para o ID :: " + id));
		this.perfilRepository.save(perfilAtual);
		PerfilDTO perfilDTO = new PerfilDTO();
		perfilDTO.setNome(perfil.getNome());
		perfilDTO.setEmail(perfil.getEmail());
		perfilDTO.setFoto(perfil.getFoto());

		return perfilDTO;	
	}

	@Override
	@Transactional
	public void deletePerfil(Long id) {
		this.perfilRepository.deleteById(id);		
	}

	@Override
	@Transactional(readOnly = true)
	public List<PerfilDTO> getPerfis() {
		List<PerfilDTO> perfisDTO = new ArrayList<>();		
		this.perfilRepository.findAll().forEach(perfil -> {
			PerfilDTO perfilDTO = new PerfilDTO();
			perfisDTO.add(perfilDTO)	;		
		});
		return perfisDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public PerfilDTO getPerfil(Long id) throws ResourceNotFoundException {

		Perfil perfil = this.perfilRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado para o ID :: " + id));
		PerfilDTO perfilDTO = new PerfilDTO();
		perfilDTO.setNome(perfil.getNome());
		perfilDTO.setEmail(perfil.getEmail());
		perfilDTO.setFoto(perfil.getFoto());

		return perfilDTO;	
	}

}
