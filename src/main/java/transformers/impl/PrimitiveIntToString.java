package transformers.impl;

import transformers.CanTransform;
import transformers.Context;

import com.daveayan.mirage.ReflectionUtils;

public class PrimitiveIntToString implements CanTransform {
	public String transform(Object from, Class<?> to, Context context) {
		return "" + from;
	}
	public boolean canTransform(Object from, Class<?> to, Context context) {
		return from != null && to != null && from.getClass().getName().equals("int") && ReflectionUtils.objectIsOfType(to, String.class);
	}
}