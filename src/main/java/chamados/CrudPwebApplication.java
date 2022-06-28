package chamados;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper; 
import com.fasterxml.jackson.databind.ObjectWriter;

import chamados.model.Chamado;
import chamados.model.Cliente;
import chamados.model.Perfil;
import chamados.repository.ChamadoRepository;
import chamados.repository.ClienteRepository;
import chamados.repository.PerfilRepository;

@SpringBootApplication
public class CrudPwebApplication implements CommandLineRunner{

	public static void main(String[] args) throws JsonProcessingException {
		SpringApplication.run(CrudPwebApplication.class, args);
	}
	
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Bean
	public ModelMapper modelMapper() {
	 return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		Cliente cliente = new Cliente("Selecione um cliente", "16380481000148", "          ");
		cliente = this.clienteRepository.save(cliente);
//		Cliente cliente1 = new Cliente("Teste", "56514857000184", "endereco da pessoa");
//		cliente1 = this.clienteRepository.save(cliente1);	
//		Perfil perfil = new Perfil("nome perfil", "teste123@gmail.com");
//		perfil = this.perfilRepository.save(perfil);
//		Chamado chamado = new Chamado(cliente1.getId(), "Tobias", "assunto exemplo", "endereco 987", "Aberto");
//		chamado = this.chamadoRepository.save(chamado);				
	}
}
