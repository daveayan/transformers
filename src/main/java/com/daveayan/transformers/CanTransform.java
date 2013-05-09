package com.daveayan.transformers;

/**
 * A common interface
 * @author adave
 *
 */
public interface CanTransform {
	public boolean canTransform(Object from, Class<?> to, Context context);
	public Object transform(Object from, Class<?> to, Context context) ;
}