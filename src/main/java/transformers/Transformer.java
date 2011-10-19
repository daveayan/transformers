package transformers;

import java.util.ArrayList;
import java.util.List;

import transformers.impl.BigDecimalToDouble;
import transformers.impl.DoubleToBigDecimal;
import transformers.impl.IntegerToString;
import transformers.impl.PrimitiveIntToString;
import transformers.impl.StringToByteArrayTransformer;
import transformers.impl.StringToInteger;

public class Transformer {
	private List<CanTransform> transformers_a = new ArrayList<CanTransform>();
	private List<CanTransform> transformers_b = new ArrayList<CanTransform>();
	private List<CanTransform> built_in_transformers = new ArrayList<CanTransform>();
	private CanTransform default_transformer = null;

	public static Transformer newInstance() {
		Transformer transformer = new Transformer();
		transformer.setup_built_in_transformers();
		return transformer;
	}
	
	public Transformer setup_built_in_transformers() {
		this.built_in_transformers.add(new StringToByteArrayTransformer());
		this.built_in_transformers.add(new DoubleToBigDecimal());
		this.built_in_transformers.add(new BigDecimalToDouble());
		this.built_in_transformers.add(new StringToInteger());
		this.built_in_transformers.add(new IntegerToString());
		this.built_in_transformers.add(new PrimitiveIntToString());
		return this;
	}

	public Transformer clear() {
		transformers_a = new ArrayList<CanTransform>();
		transformers_b = new ArrayList<CanTransform>();
		default_transformer = null;
		return this;
	}

	public Object transform(Object from, Class<?> to, Context context) {
		if (context == null)
			context = Context.newInstance();
		context.put(this);
		Object returnObject = delegateTransformation(from, to, context);
		context.removeTransformer();
		return returnObject;
	}

	public Object delegateTransformation(Object from, Class<?> to, Context context) {
		for (CanTransform t : transformers_a) {
			if (t.canTransform(from, to, context)) {
				return t.transform(from, to, context);
			}
		}
		for (CanTransform t : transformers_b) {
			if (t.canTransform(from, to, context)) {
				return t.transform(from, to, context);
			}
		}
		for (CanTransform t : built_in_transformers) {
			if (t.canTransform(from, to, context)) {
				return t.transform(from, to, context);
			}
		}
		if (default_transformer != null && default_transformer.canTransform(from, to, context)) {
			return default_transformer.transform(from, to, context);
		}
		return from;
	}

	public Transformer and_b(CanTransform transformer) {
		return with_b(transformer);
	}

	public Transformer with_b(CanTransform transformer) {
		if (transformer != null) {
			transformers_b.add(transformer);
		}
		return this;
	}

	public Transformer and_a(CanTransform transformer) {
		return with_a(transformer);
	}

	public Transformer with_a(CanTransform transformer) {
		if (transformer != null) {
			transformers_a.add(transformer);
		}
		return this;
	}

	public Transformer with_default_transformer(CanTransform transformer) {
		this.default_transformer = transformer;
		return this;
	}

	private Transformer() {
	}
}
