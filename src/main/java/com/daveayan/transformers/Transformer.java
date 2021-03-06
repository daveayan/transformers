package com.daveayan.transformers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.daveayan.transformers.impl.BigDecimalToDouble;
import com.daveayan.transformers.impl.DoubleToBigDecimal;
import com.daveayan.transformers.impl.IntegerToString;
import com.daveayan.transformers.impl.PrimitiveIntToString;
import com.daveayan.transformers.impl.StringMillisToDate;
import com.daveayan.transformers.impl.StringToByteArrayTransformer;
import com.daveayan.transformers.impl.StringToDouble;
import com.daveayan.transformers.impl.StringToInteger;

public class Transformer {
	private static Log log = LogFactory.getLog(Transformer.class);
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

	public Object transform(Object from, Class<?> to, String fieldName, Context context) {
		if (context == null)
			context = Context.newInstance();
		context.put(this);
		log.debug("Transforming " + from + " to " + to);
		Object returnObject = delegateTransformation(from, to, fieldName, context);
		context.removeTransformer();
		log.debug("Transformation Complete. Return Value = " + returnObject);
		return returnObject;
	}

	public Object delegateTransformation(Object from, Class<?> to, String fieldName, Context context) {
		for (CanTransform t : transformers_a) {
			if (t.canTransform(from, to, fieldName, context)) {
				Object returnValue = t.transform(from, to, fieldName, context);
				log.debug("Using " + t + " converted [" + from + "] to ["+ as_string(returnValue) + "]");
				return returnValue;
			}
		}
		for (CanTransform t : transformers_b) {
			if (t.canTransform(from, to, fieldName, context)) {
				Object returnValue = t.transform(from, to, fieldName, context);
				log.debug("Using " + t + " converted [" + from + "] to ["+ as_string(returnValue) + "]");
				return returnValue;
			}
		}
		for (CanTransform t : built_in_transformers) {
			if (t.canTransform(from, to, fieldName, context)) {
				Object returnValue = t.transform(from, to, fieldName, context);
				log.debug("Using " + t + " converted [" + from + "] to ["+ as_string(returnValue) + "]");
				return returnValue;
			}
		}
		if (default_transformer != null && default_transformer.canTransform(from, to, fieldName, context)) {
			Object returnValue = default_transformer.transform(from, to, fieldName, context);
			log.debug("Using " + default_transformer + " converted [" + from + "] to ["+ as_string(returnValue) + "]");
			return returnValue;
		}
		return from;
	}

	private String as_string(Object object) {
		if(object == null) {
			return null;
		}
		return object.toString();
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
