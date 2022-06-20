package chamados.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chamados.dto.ChamadoDTO;
import chamados.dto.ClienteDTO;
import chamados.model.Chamado;
import chamados.repository.ChamadoRepository;
import chamados.service.ChamadoServiceInterface;

@Service
public class ChamadoServiceInterfaceImpl implements ChamadoServiceInterface{

	@Autowired
	ChamadoRepository chamadoRepository;
	
	@Override
	public void createChamado(Chamado chamado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ChamadoDTO updateChamado(Long id, Chamado chamado) {
		Chamado chamadoAtual = this.chamadoRepository.getReferenceById(id);
		chamadoAtual.setAssunto(chamado.getAssunto());
		chamadoAtual.setCadastradoEm();
		chamadoAtual.setCliente(chamado.getCliente());
		chamadoAtual.setClienteCnpj(chamado.getClienteCnpj());
		chamadoAtual.setClienteId(chamado.getClienteId());
		chamadoAtual.setEndereco(chamado.getEndereco());
		chamadoAtual.setNomeCliente(chamado.getNomeCliente());
		chamadoAtual.setStatus(chamado.getStatus());
		
		this.chamadoRepository.save(chamadoAtual);
		
		ChamadoDTO chamadoDTO = new ChamadoDTO();
		chamadoDTO.setAssunto(chamadoAtual.getAssunto());
		chamadoDTO.setCadastradoEm(chamadoAtual.getCadastradoEm());
		chamadoDTO.setClienteDTO(chamadoAtual.getCliente().);
		
		ClienteDTO clienteDTO = new ClienteDTO();
		//clienteDTO.set
		
		chamadoDTO.setClienteCnpj(chamadoAtual.getClienteCnpj());
		chamadoDTO.setClienteId(chamadoAtual.getClienteId());
		chamadoDTO.setEndereco(chamadoAtual.getEndereco());
		chamadoDTO.setNomeCliente(chamadoAtual.getNomeCliente());
		chamadoDTO.setStatus(chamadoAtual.getStatus());
		
		return null;	
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

}
