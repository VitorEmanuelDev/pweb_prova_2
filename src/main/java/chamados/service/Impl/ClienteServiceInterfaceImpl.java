package chamados.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chamados.dto.ClienteDTO;
import chamados.dto.PerfilDTO;
import chamados.exception.ResourceNotFoundException;
import chamados.model.Cliente;
import chamados.model.Perfil;
import chamados.repository.ClienteRepository;
import chamados.service.ClienteServiceInterface;

@Service
public class ClienteServiceInterfaceImpl implements ClienteServiceInterface{

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	@Transactional
	public ClienteDTO createCliente(Cliente cliente) {
		Cliente clienteAtual = new Cliente(cliente.getNome(), 
				cliente.getCnpj(), cliente.getEndereco());
		clienteAtual = this.clienteRepository.save(clienteAtual);	
		return mapper.map(clienteAtual, ClienteDTO.class);
	}

	@Override
	@Transactional
	public List<ClienteDTO> getClientes() {
		List<ClienteDTO> clientesDTO = new ArrayList<>();
		this.clienteRepository.findAll().forEach(cliente -> {
			clientesDTO.add(mapper.map(cliente, ClienteDTO.class));
		});;
		return clientesDTO;
	}

	@Override
	@Transactional
	public ClienteDTO getCliente(Long id) throws ResourceNotFoundException {	
		Cliente cliente = this.clienteRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado para o ID :: " + id));
		return mapper.map(cliente, ClienteDTO.class);
	}

	@Override
	public ClienteDTO updateCliente(Long id, Cliente cliente) throws ResourceNotFoundException {
		Cliente clienteAtual = this.clienteRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado para o ID :: " + id));
		if(cliente.getNome() != null && !cliente.getNome().isBlank() && !cliente.getNome().isEmpty())
			clienteAtual.setNome(cliente.getNome());
		if(cliente.getCnpj() != null && !cliente.getCnpj().isBlank() && !cliente.getCnpj().isEmpty())
			clienteAtual.setCnpj(cliente.getCnpj());
		if(cliente.getEndereco() != null && !cliente.getEndereco().isBlank() && !cliente.getEndereco().isEmpty())
			clienteAtual.setEndereco(cliente.getEndereco());
		return mapper.map(clienteAtual, ClienteDTO.class);
	}

	@Override
	public void deleteCliente(Long id) {
		this.clienteRepository.deleteById(id);	
	}

}
