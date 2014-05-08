package com.daveayan.transformers.impl;

import com.daveayan.mirage.ReflectionUtils;
import com.daveayan.transformers.CanTransform;
import com.daveayan.transformers.Context;

public class StringToDouble implements CanTransform {
	public Double transform(Object from, Class<?> to, Context context) {
		return Double.parseDouble(from.toString());
	}
	public boolean canTransform(Object from, Class<?> to, Context context) {
		return from != null && to != null && ReflectionUtils.objectIsOfType(from, String.class) && ReflectionUtils.objectIsOfType(to, Double.class);
	}
}
