package transformers.impl;

import java.math.BigDecimal;

import mirage.ReflectionUtils;

import transformers.CanTransform;
import transformers.Context;

public class DoubleToBigDecimal implements CanTransform {
	public BigDecimal transform(Object from, Class<?> to, Context context) {
		return new BigDecimal((Double)from);
	}
	public boolean canTransform(Object from, Class<?> to, Context context) {
		return from != null && to != null && ReflectionUtils.objectIsOfType(from, Double.class) && ReflectionUtils.objectIsOfType(to, BigDecimal.class);
	}
}