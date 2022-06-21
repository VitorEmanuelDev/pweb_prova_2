package chamados.service;

import java.util.List;

import chamados.dto.ClienteDTO;
import chamados.model.Cliente;

public interface ClienteServiceInterface {
	public ClienteDTO createCliente(Cliente cliente);
	public ClienteDTO updateCliente(Long id, Cliente cliente);
	public void deleteCliente(Long id);
	public List<ClienteDTO> getClientes();
	public ClienteDTO getCliente(Long id);
}
