package phd.anfis.membership;

import java.util.Random;

public class GeneralizedBell implements IMembershipFunction {

	double a,b,c;
	
	public GeneralizedBell() {
		Random r = new Random();
		a = 1 * r.nextDouble();
		b = 1 * r.nextDouble();
		c = 1 * r.nextDouble();
	}
	
	@Override
	public double compute(Double input) {
		return 1/(1+Math.pow(Math.abs((input-c)/a), 2*b));
	}

}
