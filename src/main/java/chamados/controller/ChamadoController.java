package chamados.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import chamados.dto.ChamadoDTO;
import chamados.exception.ResourceNotFoundException;
import chamados.model.Chamado;
import chamados.service.ChamadoServiceInterface;

@RestController
@RequestMapping("/dashboard")
public class ChamadoController {	
	
	@Autowired
	ChamadoServiceInterface chamadoServiceInterface;
	
	@GetMapping("/list")
	@ResponseStatus(HttpStatus.OK)
	public List<ChamadoDTO> getAllChamados(){	
		return chamadoServiceInterface.getChamados();
	}
	
	@GetMapping("/list/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ChamadoDTO> getChamadoById(@PathVariable(value = "cliente") Long id) throws ResourceNotFoundException {		
		ChamadoDTO chamadoDTO = chamadoServiceInterface.getChamado(id);
	    return ResponseEntity.ok().body(chamadoDTO);
	}
	
	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public ChamadoDTO createChamado(@RequestBody Chamado chamado) throws ResourceNotFoundException {
		return this.chamadoServiceInterface.createChamado(chamado);
	}
	
	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ChamadoDTO> updateChamado(@PathVariable(value = "id") Long id,
    	@Validated @RequestBody Chamado chamadoCaracteristicas) throws ResourceNotFoundException {
		ChamadoDTO chamadoDTO  = chamadoServiceInterface.updateChamado(id, chamadoCaracteristicas);        
        return ResponseEntity.ok(chamadoDTO);     
    }
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Boolean> deleteChamado(@PathVariable(value = "id") Long id)  {
		chamadoServiceInterface.deleteChamado(id);       
	    Map<String, Boolean> resposta = new HashMap<>();
	    resposta.put("chamado deletado", Boolean.TRUE);
	    return resposta;
	}

}
