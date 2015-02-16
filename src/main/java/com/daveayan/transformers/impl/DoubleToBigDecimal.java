package com.daveayan.transformers.impl;

import java.math.BigDecimal;

import com.daveayan.mirage.ReflectionUtils;
import com.daveayan.transformers.CanTransform;
import com.daveayan.transformers.Context;

public class DoubleToBigDecimal implements CanTransform {
	public BigDecimal transform(Object from, Class<?> to, String fieldName, Context context) {
		return new BigDecimal((Double)from);
	}
	public boolean canTransform(Object from, Class<?> to, String fieldName, Context context) {
		return from != null && to != null && ReflectionUtils.objectIsOfType(from, Double.class) && ReflectionUtils.objectIsOfType(to, BigDecimal.class);
	}
}