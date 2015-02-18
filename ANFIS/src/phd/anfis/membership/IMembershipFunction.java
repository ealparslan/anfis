package phd.anfis.membership;

import phd.anfis.datastructures.Parameter;


public interface IMembershipFunction {
	
	public double compute(Double input, Parameter[] params);

}
