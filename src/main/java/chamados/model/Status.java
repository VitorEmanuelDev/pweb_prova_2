package chamados.model;

public enum Status {
	
	EM_ABERTO(1, "Em aberto"),
	FECHADO(2, "Fechado"),
	PENDENTE(3, "Pendente"),
	EM_ANDAMENTO(4, "Em andamento");
	
	private int id;
	private String status;

	Status(int id, String status) {
		this.id = id;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	@Override 
	public String toString() {
		return status;
	}
}
