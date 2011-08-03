package transformers;

import java.util.HashMap;
import java.util.Map;

import transformers.impl.BigDecimalToDouble;
import transformers.impl.DoubleToBigDecimal;

public class Transformer {
	private Map<String, CanTransform<?, ?>> transformers = new HashMap<String, CanTransform<?, ?>>();
	
	public static Transformer newInstance() {
		Transformer transformer = new Transformer();
		transformer.initialize();
		return transformer;
	}
	
	@SuppressWarnings("unchecked")
	public Object transform(Object from, Class<?> targetClass, Context context) {
		if(from != null && targetClass != null) {
			CanTransform transformer = transformers.get(from.getClass().getName() + "-" + targetClass.getName());
			if(transformer != null) {
				return transformer.transform(from, context);
			}
		}
		return from;
	}
	
	public Transformer and(CanTransform<?, ?> transformer) {
		return with(transformer);
	}
	
	public Transformer with(CanTransform<?, ?> transformer) {
		if(transformers.containsKey(transformer.name())) 
			transformers.remove(transformer.name());
		transformers.put(transformer.name(), transformer);
		return this;
	}
	
	private void initialize() {
		transformers.put("java.math.BigDecimal-java.lang.Double", new BigDecimalToDouble());
		transformers.put("java.lang.Double-java.math.BigDecimal", new DoubleToBigDecimal());
	}
	private Transformer() {}
}
