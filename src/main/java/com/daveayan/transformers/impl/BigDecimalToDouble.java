package com.daveayan.transformers.impl;

import java.math.BigDecimal;

import com.daveayan.mirage.ReflectionUtils;
import com.daveayan.transformers.CanTransform;
import com.daveayan.transformers.Context;

public class BigDecimalToDouble implements CanTransform {
	public Double transform(Object from, Class<?> to, Context context) {
		return ((BigDecimal)from).doubleValue();
	}
	public boolean canTransform(Object from, Class<?> to, Context context) {
		return from != null && ReflectionUtils.objectIsOfType(from, BigDecimal.class); 
	}
}