package transformers.impl;

import transformers.CanTransform;
import transformers.Context;

import com.daveayan.mirage.ReflectionUtils;

public class IntegerToString implements CanTransform {
	public String transform(Object from, Class<?> to, Context context) {
		return ((Integer) from).toString();
	}
	public boolean canTransform(Object from, Class<?> to, Context context) {
		return from != null && to != null && ReflectionUtils.objectIsOfType(from, Integer.class) && to.getName().equals(String.class.getName());
	}
}