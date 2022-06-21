package chamados.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chamados.dto.ChamadoDTO;
import chamados.model.Chamado;
import chamados.repository.ChamadoRepository;
import chamados.service.ChamadoServiceInterface;

@Service
public class ChamadoServiceInterfaceImpl implements ChamadoServiceInterface{

	@Autowired
	private ChamadoRepository chamadoRepository;

	@Override
	public ChamadoDTO createChamado(Chamado chamado) {
		Chamado chamadoAtual = new Chamado(chamado.getClienteId(), chamado.getClienteCnpj(), 
				chamado.getNomeCliente(), chamado.getAssunto(), chamado.getEndereco());
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
	public ChamadoDTO updateChamado(Long id, Chamado chamado) {
		Chamado chamadoAtual = this.chamadoRepository.getReferenceById(id);
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
	public void deleteChamado(Long id) {
		this.chamadoRepository.deleteById(id);
	}

	@Override
	public List<ChamadoDTO> getChamados() {
		List<ChamadoDTO> chamadosDTO = new ArrayList<>();		
		this.chamadoRepository.findAll().forEach(chamado -> {
			ChamadoDTO chamadoDTO = new ChamadoDTO();
			chamadosDTO.add(chamadoDTO)	;		
		});
		return chamadosDTO;
	}

	@Override
	public ChamadoDTO getChamado(Long id) {
		Chamado chamado = chamadoRepository.getReferenceById(id);
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
