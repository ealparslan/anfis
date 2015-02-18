package phd.anfis.membership;

import phd.anfis.datastructures.Parameter;

public class GeneralizedBell implements IMembershipFunction {

	double a,b,c;
	
	public GeneralizedBell() {
	}
	
	@Override
	public double compute(Double input, Parameter[] params) {
		a = params[0].getValue();
		b = params[1].getValue();
		c = params[2].getValue();
		return 1/(1+Math.pow(Math.abs((input-c)/a), 2*b));
	}

}
