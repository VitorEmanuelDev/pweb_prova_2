package chamados.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chamados.dto.ClienteDTO;
import chamados.model.Cliente;
import chamados.repository.ClienteRepository;
import chamados.service.ClienteServiceInterface;

@Service
public class ClienteServiceInterfaceImpl implements ClienteServiceInterface{
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public ClienteDTO createCliente(Cliente cliente) {
		Cliente clienteAtual = new Cliente(cliente.getNome(), 
				cliente.getCnpj(), cliente.getEndereco());
		this.clienteRepository.save(clienteAtual);
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setNome(cliente.getNome());
		clienteDTO.setCnpj(cliente.getCnpj());
		clienteDTO.setEndereco(cliente.getEndereco());
		return clienteDTO;	
	}

	@Override
	public ClienteDTO updateCliente(Long id, Cliente cliente) {
		Cliente clienteAtual = this.clienteRepository.getReferenceById(id);
		clienteAtual.setEndereco(cliente.getEndereco());
		clienteAtual.setNome(cliente.getNome());
		clienteAtual.setCnpj(cliente.getCnpj());
		clienteAtual.setChamados(cliente.getChamados());

		this.clienteRepository.save(clienteAtual);

		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setEndereco(clienteAtual.getEndereco());
		clienteDTO.setNome(clienteAtual.getNome());
		clienteDTO.setCnpj(clienteAtual.getCnpj());
		return clienteDTO;	
	}

	@Override
	public void deleteCliente(Long id) {
		this.clienteRepository.deleteById(id);
	}

	@Override
	public List<ClienteDTO> getClientes() {
		List<ClienteDTO> clientesDTO = new ArrayList<>();		
		this.clienteRepository.findAll().forEach(cliente -> {
			ClienteDTO clienteDTO = new ClienteDTO();
			clientesDTO.add(clienteDTO)	;		
		});
		return clientesDTO;
	}

	@Override
	public ClienteDTO getCliente(Long id) {
		Cliente cliente = clienteRepository.getReferenceById(id);
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setNome(cliente.getNome());
		clienteDTO.setEndereco(cliente.getEndereco());
		clienteDTO.setCnpj(cliente.getCnpj());
		return clienteDTO;	
	}

}
