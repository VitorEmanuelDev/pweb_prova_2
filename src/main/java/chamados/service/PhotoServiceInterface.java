package chamados.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import chamados.dto.PhotoDTO;
import chamados.exception.ResourceNotFoundException;

public interface PhotoServiceInterface {
	public ModelAndView getFoto(Long id) throws ResourceNotFoundException, UnsupportedEncodingException;
	public PhotoDTO createFoto(MultipartFile file) throws ResourceNotFoundException, IOException;
}
