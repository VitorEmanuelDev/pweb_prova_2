package chamados.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;

import chamados.dto.ChamadoDTO;
import chamados.exception.ResourceNotFoundException;
import chamados.model.Chamado;
import chamados.service.ChamadoServiceInterface;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping
public class ChamadoController {	
	
	@Autowired
	ChamadoServiceInterface chamadoServiceInterface;
	
	@GetMapping("/dashboard")
	@ResponseStatus(HttpStatus.OK)
	public List<ChamadoDTO> getAllChamados(){	
		return chamadoServiceInterface.getChamados();
	}
	
	@GetMapping("dashboard/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ChamadoDTO getChamadoById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {		
		ChamadoDTO chamadoDTO = chamadoServiceInterface.getChamado(id);
	    return chamadoDTO;
	}
	
	@PostMapping("/new")
	@ResponseStatus(HttpStatus.CREATED)
	public ChamadoDTO createChamado(@RequestBody Chamado chamado) throws ResourceNotFoundException {
		return this.chamadoServiceInterface.createChamado(chamado);
	}
	
	@PutMapping("/dashboard/{id}")
	@ResponseStatus(HttpStatus.OK)
    public ChamadoDTO updateChamado(@PathVariable(value = "id") Long id,
    	@Validated @RequestBody Chamado chamado) throws ResourceNotFoundException {
		ChamadoDTO chamadoDTO  = chamadoServiceInterface.updateChamado(id, chamado);        
        return chamadoDTO;     
    }
	
	@DeleteMapping("/dashboard/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Boolean> deleteChamado(@PathVariable(value = "id") Long id)  {
		chamadoServiceInterface.deleteChamado(id);       
	    Map<String, Boolean> resposta = new HashMap<>();
	    resposta.put("chamado deletado", Boolean.TRUE);
	    return resposta;
	}

}
