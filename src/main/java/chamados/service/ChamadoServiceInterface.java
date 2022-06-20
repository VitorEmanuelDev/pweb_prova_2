package chamados.service;

import java.util.List;

import chamados.dto.ChamadoDTO;
import chamados.model.Chamado;

public interface ChamadoServiceInterface {
	public abstract void createChamado(Chamado chamado);
	public abstract ChamadoDTO updateChamado(Long id, Chamado chamado);
	public abstract void deleteChamado(Long id);
	public abstract List<ChamadoDTO> getChamados();
}
