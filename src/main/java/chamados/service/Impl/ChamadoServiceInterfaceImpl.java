package chamados.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chamados.dto.ChamadoDTO;
import chamados.exception.ResourceNotFoundException;
import chamados.model.Chamado;
import chamados.model.Cliente;
import chamados.repository.ChamadoRepository;
import chamados.repository.ClienteRepository;
import chamados.service.ChamadoServiceInterface;

@Service

public class ChamadoServiceInterfaceImpl implements ChamadoServiceInterface{

	@Autowired
	private ChamadoRepository chamadoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	@Transactional
	public ChamadoDTO createChamado(Chamado chamado) throws ResourceNotFoundException{
		Cliente cliente = this.clienteRepository.findById(chamado.getClienteId())
				.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado para o ID :: " + chamado.getClienteId()));
		Chamado chamadoAtual = new Chamado(cliente.getId(), chamado.getClienteCnpj(), 
				chamado.getNomeCliente(), chamado.getAssunto(), chamado.getEndereco());
		chamadoAtual = this.chamadoRepository.save(chamadoAtual);
		ChamadoDTO chamadoDTO = new ChamadoDTO();
		chamadoDTO.setAssunto(chamadoAtual.getAssunto());
		chamadoDTO.setCadastradoEm(chamadoAtual.getCadastradoEm());
		chamadoDTO.setClienteCnpj(chamadoAtual.getClienteCnpj());
		chamadoDTO.setClienteId(chamadoAtual.getClienteId());
		chamadoDTO.setEndereco(chamadoAtual.getEndereco());
		chamadoDTO.setNomeCliente(chamadoAtual.getNomeCliente());
		chamadoDTO.setStatus(chamadoAtual.getStatus());
		return chamadoDTO;	
	}

	@Override
	@Transactional
	public ChamadoDTO updateChamado(Long id, Chamado chamado) throws ResourceNotFoundException {
		Chamado chamadoAtual = this.chamadoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Chamado não encontrado para o ID :: " + id));
		chamadoAtual.setAssunto(chamado.getAssunto());
		chamadoAtual.setCadastradoEm();
		chamadoAtual.setClienteCnpj(chamado.getClienteCnpj());
		chamadoAtual.setClienteId(chamado.getClienteId());
		chamadoAtual.setEndereco(chamado.getEndereco());
		chamadoAtual.setNomeCliente(chamado.getNomeCliente());
		chamadoAtual.setStatus(chamado.getStatus());
		this.chamadoRepository.save(chamadoAtual);
		ChamadoDTO chamadoDTO = new ChamadoDTO();
		chamadoDTO.setAssunto(chamadoAtual.getAssunto());
		chamadoDTO.setCadastradoEm(chamadoAtual.getCadastradoEm());
		chamadoDTO.setClienteCnpj(chamadoAtual.getClienteCnpj());
		chamadoDTO.setClienteId(chamadoAtual.getClienteId());
		chamadoDTO.setEndereco(chamadoAtual.getEndereco());
		chamadoDTO.setNomeCliente(chamadoAtual.getNomeCliente());
		chamadoDTO.setStatus(chamadoAtual.getStatus());
		return chamadoDTO;	
	}

	@Override
	@Transactional
	public void deleteChamado(Long id) {
		this.chamadoRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ChamadoDTO> getChamados() {
		List<ChamadoDTO> chamadosDTO = new ArrayList<>();		
		this.chamadoRepository.findAll().forEach(chamado -> {
			ChamadoDTO chamadoDTO = new ChamadoDTO();
			chamadosDTO.add(chamadoDTO)	;		
		});
		return chamadosDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public ChamadoDTO getChamado(Long id) throws ResourceNotFoundException {
		Chamado chamado = chamadoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Chamado não encontrado para o ID :: " + id));
		ChamadoDTO chamadoDTO = new ChamadoDTO();
		chamadoDTO.setAssunto(chamado.getAssunto());
		chamadoDTO.setCadastradoEm(chamado.getCadastradoEm());
		chamadoDTO.setClienteCnpj(chamado.getClienteCnpj());
		chamadoDTO.setClienteId(chamado.getClienteId());
		chamadoDTO.setEndereco(chamado.getEndereco());
		chamadoDTO.setNomeCliente(chamado.getNomeCliente());
		chamadoDTO.setStatus(chamado.getStatus());
		return chamadoDTO;	
	}

}
