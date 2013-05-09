package transformers.impl;

import transformers.CanTransform;
import transformers.Context;

import com.daveayan.mirage.ReflectionUtils;

public class StringToInteger implements CanTransform {
	public Integer transform(Object from, Class<?> to, Context context) {
		return Integer.parseInt(from.toString());
	}
	public boolean canTransform(Object from, Class<?> to, Context context) {
		return from != null && to != null && ReflectionUtils.objectIsOfType(from, String.class) && ReflectionUtils.objectIsOfType(to, Integer.class);
	}
}