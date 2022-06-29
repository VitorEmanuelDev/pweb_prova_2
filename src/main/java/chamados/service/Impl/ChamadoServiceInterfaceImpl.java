package chamados.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
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


	@Autowired
	private ModelMapper mapper;

	@Override
	@Transactional
	public ChamadoDTO createChamado(Chamado chamado) throws ResourceNotFoundException{
		this.clienteRepository.findById(chamado.getClienteId())
				.orElseThrow(() -> new ResourceNotFoundException("Chamado não encontrado para o ID :: " + chamado.getClienteId()));
		Chamado chamadoAtual = new Chamado(chamado.getClienteId(), chamado.getNomeCliente(), chamado.getAssunto(), chamado.getComplemento(), chamado.getStatus());
		chamadoAtual = this.chamadoRepository.save(chamadoAtual);
		return mapper.map(chamadoAtual, ChamadoDTO.class);
	}

	@Override
	@Transactional
	public ChamadoDTO updateChamado(Long id, Chamado chamado) throws ResourceNotFoundException {
		Chamado chamadoAtual = this.chamadoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Chamado não encontrado para o ID :: " + id));
			chamadoAtual.setAssunto(chamado.getAssunto());
			chamadoAtual.setComplemento(chamado.getComplemento());
			chamadoAtual.setStatus(chamado.getStatus());
		chamadoAtual = this.chamadoRepository.save(chamadoAtual);	
		return mapper.map(chamadoAtual, ChamadoDTO.class);
	}

	@Override
	@Transactional
	public void deleteChamado(Long id) {
		this.chamadoRepository.deleteById(id);
	}

	@Override
	@Transactional
	public List<ChamadoDTO> getChamados() {		
		List<ChamadoDTO> chamadosDTO = new ArrayList<>();
		this.chamadoRepository.findAll().forEach(chamado -> {
			chamadosDTO.add(mapper.map(chamado, ChamadoDTO.class));
		});;
		return chamadosDTO;
	}

	@Override
	public ChamadoDTO getChamado(Long id) throws ResourceNotFoundException {
		Chamado chamado = this.chamadoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Chamado não encontrado para o ID :: " + id));
		return mapper.map(chamado, ChamadoDTO.class);
	}

}
