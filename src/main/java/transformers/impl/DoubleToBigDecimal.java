package transformers.impl;

import java.math.BigDecimal;

import transformers.CanTransform;
import transformers.Context;

public class DoubleToBigDecimal implements CanTransform<Double, BigDecimal> {
	public String name() {
		return Double.class.getName() + "-" + BigDecimal.class.getName();
	}
	public BigDecimal transform(Double from, Context context) {
		return new BigDecimal(from);
	}
}