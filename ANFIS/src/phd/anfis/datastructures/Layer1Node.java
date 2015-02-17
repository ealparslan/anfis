package phd.anfis.datastructures;

import phd.anfis.membership.GeneralizedBell;
import phd.anfis.membership.IMembershipFunction;

public class Layer1Node extends AbstractNode {

	IMembershipFunction mf;
	
	public Layer1Node(double val) {
		super(val);
		mf = new GeneralizedBell();
	}
	
	@Override
	public void compute(double... param){
		super.setValue(mf.compute(super.getPreNodes().get(0).getValue()));
	}

}
