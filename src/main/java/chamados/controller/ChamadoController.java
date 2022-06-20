package chamados.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import chamados.model.Cliente;
import chamados.repository.ChamadoRepository;
import chamados.service.Impl.ChamadoServiceInterfaceImpl;

@RestController
@RequestMapping("/dashboard")
public class ChamadoController {	
	
	@Autowired
	ChamadoServiceInterfaceImpl chamadoServiceInterfaceImpl;
	
	@GetMapping("/listar")
	@ResponseStatus(HttpStatus.OK)
	public List<ChamadoDTO> getAllChamados(){	
		return chamadoServiceInterfaceImpl.getChamados();
	}
	
	@GetMapping("/listar/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ChamadoDTO> getChamadooById(@PathVariable(value = "cliente") Long id)
	    throws ResourceNotFoundException {		
	    Chamado chamado = chamadoRepository.findById(id)
	      .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada para o ID :: " + id));
	    return ResponseEntity.ok().body(chamado);
	}
	
	@PostMapping("/cadastrar")
	@ResponseStatus(HttpStatus.CREATED)
	public Chamado createChamado(@RequestBody Chamado chamado) {
		return this.chamadoRepository.save(chamado);
	}
	
	@PutMapping("/atualizar/{id}")
	@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ChamadoDTO> updateChamado(@PathVariable(value = "id") Long id,
    	@Validated @RequestBody Chamado cadastroCaracteristicas) throws ResourceNotFoundException {
        Chamado chamado = chamadoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada para o ID :: " + id));
        
        chamado.setCpf(cadastroCaracteristicas.getCpf());
        chamado.setEmail(cadastroCaracteristicas.getEmail());
        chamado.setNome(cadastroCaracteristicas.getNome());
        chamado.setDataNascimento(cadastroCaracteristicas.getDataNascimento());*/
        
        return ResponseEntity.ok(this.chamadoRepository.save(chamado));
        
    }
	

	@DeleteMapping("/deletar/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Boolean> deleteChamado(@PathVariable(value = "id") Long id) 
			throws ResourceNotFoundException {
	    Chamado chamado = chamadoRepository.findById(id)
	   .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada para o ID :: " + id));
	
	    this.chamadoRepository.delete(chamado);
	    Map<String, Boolean> resposta = new HashMap<>();
	    resposta.put("cadastro deletado", Boolean.TRUE);
	    return resposta;
	}

}
