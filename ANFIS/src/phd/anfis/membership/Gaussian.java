package phd.anfis.membership;

import java.util.Random;

public class Gaussian implements IMembershipFunction {
	
	double c,s;
	
	public Gaussian() {
		Random r = new Random();
		c = 1 * r.nextDouble();
		s = 1 * r.nextDouble();
	}


	@Override
	public double compute(Double input) {
		double parantezici=(input-c)/s;
		double kare = Math.pow(parantezici, 2);
		double sonuc = Math.exp(-kare/2);
		return sonuc;
	}

}
