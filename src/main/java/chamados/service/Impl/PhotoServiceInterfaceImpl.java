package chamados.service.Impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import chamados.dto.PhotoDTO;
import chamados.exception.ResourceNotFoundException;
import chamados.model.Perfil;
import chamados.model.Photo;
import chamados.repository.PerfilRepository;
import chamados.repository.PhotoRepository;
import chamados.service.PhotoServiceInterface;

@Service
public class PhotoServiceInterfaceImpl implements PhotoServiceInterface{

	@Autowired
	private PhotoRepository photoRepository;
	@Autowired
	private PerfilRepository perfilRepository;
	
	@Autowired
	private ModelMapper mapper;


	@Override
	public PhotoDTO createFoto(MultipartFile file) throws ResourceNotFoundException, IOException {
		Photo fotoAtual = new Photo();
//		Perfil perfil = this.perfilRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Perfil não encontrado para o ID :: " + id));	
//		fotoAtual.setPerfilId(perfil.getId());
		fotoAtual.setContent(file.getBytes());
		fotoAtual.setNome(file.getOriginalFilename());
		fotoAtual.setContent(file.getBytes());
		System.out.println("TESTE " + file.getBytes());
		fotoAtual = this.photoRepository.save(fotoAtual);		
//		perfil.setFoto(fotoAtual);
		return mapper.map(fotoAtual, PhotoDTO.class);
	}


	@Override
	public ModelAndView getFoto(Long id) throws ResourceNotFoundException, UnsupportedEncodingException {
		Perfil perfil = this.perfilRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Foto não encontrado para o ID :: " + id));	
		perfil.getFoto().getId();
		byte[] encodeBase64 = Base64.getEncoder().encode(perfil.getFoto().getContent());
		String base64Encoded = new String(encodeBase64, "UTF-8");
		ModelAndView modelAndView = new ModelAndView("view");
		modelAndView.addObject("contentImage",base64Encoded );
		return modelAndView;
	}

}
