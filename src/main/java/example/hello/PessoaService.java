package example.hello;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class PessoaService implements Repository<Pessoa> {
	
	private List<Pessoa> database;
	
	public PessoaService(List<Pessoa> allPessoas) {
		this.database = allPessoas;
	}

	@Override
	public long count(Predicate<Pessoa> p) {
		return database.stream().filter(p).count();
	}
	
	@Override
	public int countAll() {
		return database.size();
	}	
	
	@Override
	public void delete(Pessoa p) {
		database.remove(p);		
	}

	@Override
	public void deleteById(int id) {
		getById(id).ifPresent(p -> delete(p));
	}

	@Override
	public List<Pessoa> findAll() {
		return database.stream().toList();
	}

	@Override
	public List<Pessoa> findAll(Comparator<Pessoa> c) {
		return database.stream().sorted(c).toList();
	}

	@Override
	public List<Pessoa> findBy(Predicate<Pessoa> p, Comparator<Pessoa> c) {
		return database.stream().filter(p).sorted(c).toList();
	}

	@Override
	public Optional<Pessoa> getById(int id) {
		return database.stream().filter(p -> p.getId() == id).findFirst();
	}

	@Override
	public Pessoa save(Pessoa p) {
		database.add(p);
		return p;
	}
}
