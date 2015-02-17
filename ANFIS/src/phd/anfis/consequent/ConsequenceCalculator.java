package phd.anfis.consequent;

import java.util.Random;

public class ConsequenceCalculator {

	double[] params;
	
	public ConsequenceCalculator(int paramNumber) {
		params = new double[paramNumber+1];
		Random r = new Random();
		for (int i=0 ; i<params.length ; i++)
			params[i] = 1 * r.nextDouble();
	}
	
	public double compute(double input, double[] inputparams){
		double retval=0;
		for (int i=0 ; i<inputparams.length ; i++) {
			retval += params[i]*inputparams[i];
		}
		retval += params[params.length-1];
		return retval;
	}

	
}
