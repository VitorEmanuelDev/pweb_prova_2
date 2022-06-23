package chamados.service;

import java.util.List;

import chamados.dto.ChamadoDTO;
import chamados.exception.ResourceNotFoundException;
import chamados.model.Chamado;

public interface ChamadoServiceInterface {
	public ChamadoDTO updateChamado(Long id, Chamado chamado) throws ResourceNotFoundException;
	public void deleteChamado(Long id);
	public List<ChamadoDTO> getChamados();
	public ChamadoDTO getChamado(Long id) throws ResourceNotFoundException;
	public ChamadoDTO createChamado(Chamado chamado) throws ResourceNotFoundException;
}
