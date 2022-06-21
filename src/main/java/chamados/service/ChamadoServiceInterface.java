package chamados.service;

import java.util.List;

import chamados.dto.ChamadoDTO;
import chamados.model.Chamado;

public interface ChamadoServiceInterface {
	public ChamadoDTO createChamado(Chamado chamado);
	public ChamadoDTO updateChamado(Long id, Chamado chamado);
	public void deleteChamado(Long id);
	public List<ChamadoDTO> getChamados();
	public ChamadoDTO getChamado(Long id);
}
