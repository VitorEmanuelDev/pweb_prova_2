package chamados;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper; 
import com.fasterxml.jackson.databind.ObjectWriter;

import chamados.model.Chamado;
import chamados.model.Cliente;
import chamados.model.Perfil;

@SpringBootApplication
public class CrudPwebApplication {

	public static void main(String[] args) throws JsonProcessingException {
		SpringApplication.run(CrudPwebApplication.class, args);

		Cliente cliente = new Cliente("nome teste", "42398869000186", "endereco 1100");

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(cliente);
		
		System.out.println(json);
		
		Perfil perfil = new Perfil("nome peefil", "teste123@gmail.com", null);

		ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		json = ow.writeValueAsString(perfil);
		
		System.out.println(json);
		
		Chamado chamado = new Chamado(1L, "42398869000186", "vitor", "assunto exemplo", "endereco 987");
	
		ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		json = ow.writeValueAsString(chamado);
		
		

		System.out.println(json);

	}

}
