package transformers.impl;

import java.math.BigDecimal;

import transformers.CanTransform;
import transformers.Context;

public class BigDecimalToDouble implements CanTransform<BigDecimal, Double> {
	public String name() {
		return BigDecimal.class.getName() + "-" + Double.class.getName();
	}
	public Double transform(BigDecimal from, Context context) {
		return from.doubleValue();
	}
}