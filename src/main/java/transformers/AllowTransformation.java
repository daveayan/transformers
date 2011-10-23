package transformers;

public interface AllowTransformation {
	public boolean allowTransformation(Object from, Class<?> to, Context context);
}