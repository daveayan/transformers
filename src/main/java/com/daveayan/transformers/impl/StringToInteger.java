package com.daveayan.transformers.impl;

import com.daveayan.mirage.ReflectionUtils;
import com.daveayan.transformers.CanTransform;
import com.daveayan.transformers.Context;

public class StringToInteger implements CanTransform {
	public Integer transform(Object from, Class<?> to, Context context) {
		return Integer.parseInt(from.toString());
	}
	public boolean canTransform(Object from, Class<?> to, Context context) {
		return from != null && to != null && ReflectionUtils.objectIsOfType(from, String.class) && ReflectionUtils.objectIsOfType(to, Integer.class);
	}
}