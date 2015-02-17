package phd.anfis.consequent;

import phd.anfis.datastructures.INode;
import phd.anfis.datastructures.Parameter;

public class ConsequenceCalculator {

	Parameter[] params;
	
	public ConsequenceCalculator(Parameter[] params) {
		this.params = params;
	}
	
	public double compute(INode[] inputparams){
		double retval=0;
		for (int i=0 ; i<inputparams.length ; i++) {
			retval += params[i].getValue() * inputparams[i].getValue();
		}
		retval += params[params.length-1].getValue();
		return retval;
	}

	
}
