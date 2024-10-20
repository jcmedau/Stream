package example.hello;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Repository<T> {
	
	public long count(Predicate<T> p);
	
	public int countAll();
	
	public void delete (T t);
	
	public void deleteById(int id);
	
	public List<T> findAll();
	
	public List<T> findAll(Comparator<T> c);
	
	public List<T> findBy(Predicate<T> p, Comparator<T> c);
	
	public Optional<T> getById(int id);
	
	public T save (T t);
}
