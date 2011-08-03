package transformers;

public interface CanTransform <F, T> {
	public String name() ;
	public T transform(F from, Context context) ;
}