package com.daveayan.transformers.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.daveayan.mirage.ReflectionUtils;
import com.daveayan.transformers.CanTransform;
import com.daveayan.transformers.Context;

public class StringMMDDYYYYDateToDate  implements CanTransform {
	public Date transform(Object from, Class<?> to, Context context) {
		return toUtilDate(from.toString(), "MM/dd/yyyy");
	}
	public boolean canTransform(Object from, Class<?> to, Context context) {
		if(from != null && to != null && ReflectionUtils.objectIsOfType(from, String.class) && ReflectionUtils.classIsOfEitherType(to, Date.class)) {
			return (toUtilDate(from.toString(), "MM/dd/yyyy") != null);
		}
		return false;
	}
	
	private Date toUtilDate(String dateToValidate, String dateFromat){
		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);
		try {
			return sdf.parse(dateToValidate);
		} catch (ParseException e) {
			return null;
		}
	}
}
