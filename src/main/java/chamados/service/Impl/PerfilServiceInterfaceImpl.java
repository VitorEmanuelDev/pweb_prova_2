package chamados.service.Impl;


import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chamados.dto.ChamadoDTO;
import chamados.dto.ClienteDTO;
import chamados.dto.PerfilDTO;
import chamados.exception.ResourceNotFoundException;
import chamados.model.Chamado;
import chamados.model.Cliente;
import chamados.model.Perfil;
import chamados.repository.PerfilRepository;
import chamados.service.PerfilServiceInterface;

@Service
public class PerfilServiceInterfaceImpl implements PerfilServiceInterface{

	@Autowired
	private PerfilRepository perfilRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	@Transactional
	public PerfilDTO createPerfil(Perfil perfil) {
		Perfil perfilAtual = new Perfil(perfil.getNome(), 
				perfil.getEmail());
		perfilAtual = this.perfilRepository.save(perfilAtual);		
		return mapper.map(perfilAtual, PerfilDTO.class);
	}

	@Override
	public List<PerfilDTO> getPerfis() {
		List<PerfilDTO> perfisDTO = new ArrayList<>();
		this.perfilRepository.findAll().forEach(perfil -> {
			perfisDTO.add(mapper.map(perfil, PerfilDTO.class));
		});;
		return perfisDTO;
	}

	@Override
	public PerfilDTO getPerfil(Long id) throws ResourceNotFoundException {
		Perfil perfilAtual = this.perfilRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Perfil não encontrado para o ID :: " + id));
		return mapper.map(perfilAtual, PerfilDTO.class);
	}

	@Override
	public PerfilDTO updatePerfil(Long id, Perfil perfil) throws ResourceNotFoundException {
		Perfil perfilAtual = this.perfilRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Perfil não encontrado para o ID :: " + id));
		if(perfil.getNome() != null && !perfil.getNome().isBlank() && !perfil.getNome().isEmpty())
			perfilAtual.setNome(perfil.getNome());
		if(perfil.getEmail() != null && !perfil.getEmail().isBlank() && !perfil.getEmail().isEmpty())
			perfilAtual.setEmail(perfil.getEmail());
		if(perfil.getFoto() != null)
			perfilAtual.setFoto(perfil.getFoto());	
		return mapper.map(perfilAtual, PerfilDTO.class);
	}

	@Override
	public void deletePerfil(Long id) {
		this.perfilRepository.deleteById(id);
	}

}
