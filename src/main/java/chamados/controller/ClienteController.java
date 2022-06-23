package chamados.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import chamados.dto.ClienteDTO;
import chamados.exception.ResourceNotFoundException;
import chamados.model.Cliente;
import chamados.service.ClienteServiceInterface;

@RestController
@RequestMapping("/costumers")
public class ClienteController {	
	
	@Autowired
	ClienteServiceInterface clienteServiceInterface;
	
	@GetMapping("/list")
	@ResponseStatus(HttpStatus.OK)
	public List<ClienteDTO> getAllClientes(){	
		return clienteServiceInterface.getClientes();
	}
	
	@GetMapping("/list/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ClienteDTO> getClienteById(@PathVariable(value = "cliente") Long id) throws ResourceNotFoundException {		
		ClienteDTO clienteDTO = clienteServiceInterface.getCliente(id);
	    return ResponseEntity.ok().body(clienteDTO);
	}
	
	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteDTO createCliente(@RequestBody Cliente cliente) {
		return this.clienteServiceInterface.createCliente(cliente);
	}
	
	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable(value = "id") Long id,
    	@Validated @RequestBody Cliente clienteCaracteristicas) throws ResourceNotFoundException {
		ClienteDTO clienteDTO  = clienteServiceInterface.updateCliente(id, clienteCaracteristicas);        
        return ResponseEntity.ok(clienteDTO);     
    }
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Boolean> deleteCliente(@PathVariable(value = "id") Long id)  {
		clienteServiceInterface.deleteCliente(id);       
	    Map<String, Boolean> resposta = new HashMap<>();
	    resposta.put("cliente deletado", Boolean.TRUE);
	    return resposta;
	}
}
