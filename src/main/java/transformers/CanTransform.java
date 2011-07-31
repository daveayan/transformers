package transformers;

public interface CanTransform <F, T> {
	public String name() ;
	public boolean canTransform(F from, Class<?> to) ;
	public T transform(F from) ;
}