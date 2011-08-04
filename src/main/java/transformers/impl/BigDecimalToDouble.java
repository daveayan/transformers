package transformers.impl;

import java.math.BigDecimal;

import mirage.ReflectionUtils;
import transformers.CanTransform;
import transformers.Context;

public class BigDecimalToDouble implements CanTransform {
	private Class<?> fromClass = BigDecimal.class;
	private Class<?> toClass = Double.class;
	public Double transform(Object from, Class<?> to, Context context) {
		return ((BigDecimal)from).doubleValue();
	}
	public boolean canTransform(Object from, Class<?> to, Context context) {
		return from != null && ReflectionUtils.objectIsOfType(from, fromClass); 
	}
}