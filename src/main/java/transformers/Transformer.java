package transformers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import transformers.impl.BigDecimalToDouble;
import transformers.impl.DoubleToBigDecimal;
import transformers.impl.IntegerToString;
import transformers.impl.PrimitiveIntToString;
import transformers.impl.StringToByteArrayTransformer;
import transformers.impl.StringToInteger;

public class Transformer {
	private static Log log = LogFactory.getLog(Transformer.class);
	private AllowTransformation allowTransformation = null;
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
		allowTransformation = null;
		transformers_a = new ArrayList<CanTransform>();
		transformers_b = new ArrayList<CanTransform>();
		default_transformer = null;
		return this;
	}

	public Object transform(Object from, Class<?> to, Context context) {
		if (context == null)
			context = Context.newInstance();
		context.put(this);
		log.info("Transforming " + from + " to " + to);
		Object returnObject = delegateTransformation(from, to, context);
		context.removeTransformer();
		log.info("Transformation Complete. Return Value = " + returnObject);
		return returnObject;
	}

	public Object delegateTransformation(Object from, Class<?> to, Context context) {
		if(allowTransformation != null) {
			if(! allowTransformation.allowTransformation(from, to, context))
				return null;
		}
		for (CanTransform t : transformers_a) {
			if (t.canTransform(from, to, context)) {
				log("a", t, from, to, context);
				Object returnValue = t.transform(from, to, context);
				log.info("Converted to " + returnValue);
				return returnValue;
			}
		}
		for (CanTransform t : transformers_b) {
			if (t.canTransform(from, to, context)) {
				log("b", t, from, to, context);
				Object returnValue = t.transform(from, to, context);
				log.info("Converted to " + returnValue);
				return returnValue;
			}
		}
		for (CanTransform t : built_in_transformers) {
			if (t.canTransform(from, to, context)) {
				log("built_in", t, from, to, context);
				Object returnValue = t.transform(from, to, context);
				log.info("Converted to " + returnValue);
				return returnValue;
			}
		}
		if (default_transformer != null && default_transformer.canTransform(from, to, context)) {
			log("default", default_transformer, from, to, context);
			Object returnValue = default_transformer.transform(from, to, context);
			log.info("Converted to " + returnValue);
			return returnValue;
		}
		return from;
	}
	
	private void log(String listname, CanTransform t, Object from, Class<?> to, Context context) {
		StringBuffer message = new StringBuffer("Using " + listname + "-");
		if(t != null) message.append(t.getClass().getName()); else	message.append("null");
		message.append(" to convert ");
		if(from != null) message.append(from.getClass().getName()); else message.append("null");
		message.append("(" + from + ") to ");
		message.append(to);
		message.append(" in context ");
		message.append(context);
		log.info(message);
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
	
	public Transformer allow_transformation_checker(AllowTransformation at) {
		this.allowTransformation = at;
		return this;
	}

	private Transformer() {
	}
}
