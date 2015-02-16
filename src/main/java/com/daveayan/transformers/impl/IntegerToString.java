package com.daveayan.transformers.impl;

import com.daveayan.mirage.ReflectionUtils;
import com.daveayan.transformers.CanTransform;
import com.daveayan.transformers.Context;

public class IntegerToString implements CanTransform {
	public String transform(Object from, Class<?> to, String fieldName, Context context) {
		return ((Integer) from).toString();
	}
	public boolean canTransform(Object from, Class<?> to, String fieldName, Context context) {
		return from != null && to != null && ReflectionUtils.objectIsOfType(from, Integer.class) && to.getName().equals(String.class.getName());
	}
}