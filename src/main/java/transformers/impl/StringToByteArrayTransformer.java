package transformers.impl;

import transformers.CanTransform;
import transformers.Context;

public class StringToByteArrayTransformer implements CanTransform {
	public boolean canTransform(Object from, Class<?> to, Context context) {
		if(from == null) { return false; }
		return from instanceof String && "byte[]".equals(to.getCanonicalName());
	}

	public Object transform(Object from, Class<?> to, Context context) {
		return ((String) from).getBytes();
	}
}