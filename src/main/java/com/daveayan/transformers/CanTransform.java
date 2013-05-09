package com.daveayan.transformers;

public interface CanTransform {
	public boolean canTransform(Object from, Class<?> to, Context context);
	public Object transform(Object from, Class<?> to, Context context) ;
}