package com.daveayan.transformers.impl;

import com.daveayan.mirage.ReflectionUtils;
import com.daveayan.transformers.CanTransform;
import com.daveayan.transformers.Context;

public class StringToFloat  implements CanTransform {
	public Float transform(Object from, Class<?> to, String fieldName, Context context) {
		return Float.parseFloat(from.toString());
	}
	public boolean canTransform(Object from, Class<?> to, String fieldName, Context context) {
		return from != null && to != null && ReflectionUtils.objectIsOfType(from, String.class) && ReflectionUtils.classIsOfEitherType(to, Float.class, float.class);
	}
}
