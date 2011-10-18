package transformers.impl;

import mirage.ReflectionUtils;
import transformers.CanTransform;
import transformers.Context;

public class StringToInteger implements CanTransform {
	public Integer transform(Object from, Class<?> to, Context context) {
		return Integer.parseInt(from.toString());
	}
	public boolean canTransform(Object from, Class<?> to, Context context) {
		return from != null && to != null && ReflectionUtils.objectIsOfType(from, String.class) && ReflectionUtils.objectIsOfType(to, Integer.class);
	}
}