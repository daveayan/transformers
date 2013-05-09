package transformers.impl;

import java.math.BigDecimal;

import transformers.CanTransform;
import transformers.Context;

import com.daveayan.mirage.ReflectionUtils;

public class BigDecimalToDouble implements CanTransform {
	public Double transform(Object from, Class<?> to, Context context) {
		return ((BigDecimal)from).doubleValue();
	}
	public boolean canTransform(Object from, Class<?> to, Context context) {
		return from != null && ReflectionUtils.objectIsOfType(from, BigDecimal.class); 
	}
}