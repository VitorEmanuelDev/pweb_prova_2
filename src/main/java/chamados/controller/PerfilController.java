package chamados.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import chamados.dto.PerfilDTO;
import chamados.dto.PhotoDTO;
import chamados.exception.ResourceNotFoundException;
import chamados.model.Perfil;
import chamados.service.PerfilServiceInterface;
import chamados.service.PhotoServiceInterface;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/profile")
public class PerfilController {	
	
	@Autowired
	PerfilServiceInterface perfilServiceInterface;
	
	@Autowired
	PhotoServiceInterface photoServiceInterface;
	
//	@GetMapping
//	@ResponseStatus(HttpStatus.OK)
//	public List<PerfilDTO> getAllPerfis(){	
//		return perfilServiceInterface.getPerfis();
//	}
//	
//	@GetMapping
//	@ResponseStatus(HttpStatus.OK)
//	public PerfilDTO getPerfisById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {		
//		PerfilDTO perfilDTO = perfilServiceInterface.getPerfil(id);
//	    return perfilDTO;
//	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PerfilDTO createPerfil(@RequestBody Perfil perfil) {
		return this.perfilServiceInterface.createPerfil(perfil);
	}
	
	@PostMapping("/photo")
	public PhotoDTO uplaodImage(@RequestParam("image") MultipartFile file)
			throws IOException, ResourceNotFoundException {
			return photoServiceInterface.createFoto(file);
	}
	
	@GetMapping
	public ModelAndView showContent(@PathVariable(value = "id") Long id) throws 
	UnsupportedEncodingException, ResourceNotFoundException {	 
		return	photoServiceInterface.getFoto(id);
	}

	
//	@PutMapping
//	@ResponseStatus(HttpStatus.OK)
//    public PerfilDTO updatePerfil(@PathVariable(value = "id") Long id,
//    	@Validated @RequestBody Perfil perfilCaracteristicas) throws ResourceNotFoundException {
//		PerfilDTO perfilDTO  = perfilServiceInterface.updatePerfil(id, perfilCaracteristicas);        
//        return perfilDTO;     
//    }
//	
//	@DeleteMapping
//	@ResponseStatus(HttpStatus.OK)
//	public Map<String, Boolean> deletePerfil(@PathVariable(value = "id") Long id)  {
//		perfilServiceInterface.deletePerfil(id);       
//	    Map<String, Boolean> resposta = new HashMap<>();
//	    resposta.put("cadastro deletado", Boolean.TRUE);
//	    return resposta;
//	}

}
