package chamados;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

	@Override
	public void run(String... args) throws Exception {
		Cliente cliente = new Cliente("nome cliente", "42398869000186", "endereco 1100");
		cliente = this.clienteRepository.save(cliente);
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(cliente);
		
		System.out.println(json);
		
		Perfil perfil = new Perfil("nome perfil", "teste123@gmail.com", null);
		perfil = this.perfilRepository.save(perfil);
		ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		json = ow.writeValueAsString(perfil);
		
		System.out.println(json);
		
		Chamado chamado = new Chamado(cliente.getId(), "42398869000186", "vitor", "assunto exemplo", "endereco 987");
		chamado = this.chamadoRepository.save(chamado);
		ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		json = ow.writeValueAsString(chamado);
		
		System.out.println(json);
					
	}
}
