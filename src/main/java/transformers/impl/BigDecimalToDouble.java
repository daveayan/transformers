package transformers.impl;

import java.math.BigDecimal;

import mirage.ReflectionUtils;
import transformers.CanTransform;

public class BigDecimalToDouble implements CanTransform<BigDecimal, Double> {
	public String name() {
		return BigDecimal.class.getName() + "-" + Double.class.getName();
	}
	public boolean canTransform(BigDecimal from, Class<?> to) {
		return ReflectionUtils.objectIsOfType(from, BigDecimal.class) & ReflectionUtils.objectIsOfType(to, Double.class);
	}
	public Double transform(BigDecimal from) {
		return from.doubleValue();
	}
}