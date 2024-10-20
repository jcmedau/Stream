package example.hello;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MainClass {

	public static void main(String[] args) {
		try {
			String allData = new String(Files.readAllBytes(Path.of("pessoas.json")));
			Gson gson = new Gson();
			TypeToken<List<Pessoa>> typeToken = new TypeToken<List<Pessoa>>() {};
			List<Pessoa> pessoas = gson.fromJson(allData, typeToken);
			
			PessoaService pessoaService = new PessoaService(pessoas);
			
			System.out.println("Imprimr uma Pessoa");
			Pessoa p1 = pessoaService.getById(11).orElse(null);
			System.out.println(p1);
			
			System.out.println("\nImprime todas as Pessoas com  mais de 30 anos em ordem de nome");
			
			Comparator<Pessoa> c = Comparator.comparing((Pessoa p) -> p.getNome());
			Predicate<Pessoa> predicate = p -> p.getIdade() > 30;
			pessoaService.findBy(predicate,c).forEach(p -> System.out.println(p));
			
			System.out.println("Quantidade de registros: " + pessoaService.countAll());
			pessoaService.deleteById(150);
			System.out.println("Quantidade de registros: " + pessoaService.countAll());
			pessoaService.deleteById(151);
			System.out.println("Quantidade de registros: " + pessoaService.countAll());
			
			Pessoa np = new Pessoa();
			np.setId(151);
			np.setNome("Novo Cadastro");
			np.setEmail("novo@email.com");
			np.setCpf("123.456.789-10");
			
			pessoaService.save(np);
			
			System.out.println("Quantidade de registros: " + pessoaService.countAll());
			
			System.out.println("\nImprime todas as Pessoas em ordem de id");
			c = Comparator.comparing((Pessoa p) -> p.getId());
			pessoaService.findAll(c).forEach(p -> System.out.println(p));
						
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
}
