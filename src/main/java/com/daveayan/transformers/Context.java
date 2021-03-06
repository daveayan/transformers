package com.daveayan.transformers;

import java.util.HashMap;
import java.util.Map;

public class Context {
	private Map<String, Object> contextHolder = new HashMap<String, Object>();
	
	public Context put(Transformer transformer) {
		contextHolder.put("__transformer__", transformer);
		return this;
	}
	
	public Context removeTransformer() {
		contextHolder.put("__transformer__", null);
		return this;
	}
	
	public Transformer transformer() {
		return (Transformer) contextHolder.get("__transformer__");
	}
	
	public Context put(String key, Object object) {
		contextHolder.put(key, object);
		return this;
	}
	
	public Context and(String key, Object object) {
		return put(key, object);
	}
	
	public static Context newInstance() {
		return new Context();
	}
	
	public Object get(String key) {
		return contextHolder.get(key);
	}
	
	private Context() {}
}