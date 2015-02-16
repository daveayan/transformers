package com.daveayan.transformers.impl;

import java.util.Calendar;
import java.util.Date;

import com.daveayan.mirage.ReflectionUtils;
import com.daveayan.transformers.CanTransform;
import com.daveayan.transformers.Context;

public class StringMillisToDate implements CanTransform {
	public Date transform(Object from, Class<?> to, String fieldName, Context context) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(Long.parseLong(from.toString()));
		return calendar.getTime();
	}
	public boolean canTransform(Object from, Class<?> to, String fieldName, Context context) {
		if(from != null && to != null && ReflectionUtils.objectIsOfType(from, String.class) && ReflectionUtils.classIsOfEitherType(to, Date.class)) {
			try {
				Long.parseLong(from.toString());
			} catch (NumberFormatException nfe) {
				return false;
			}
			return true;
		}
		return false;
	}
}
