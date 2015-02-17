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

	@Override
	public void calculateError(double... param){
		double error = 0;
		for (INode postNode : super.getPostNodes()) {
			error += derivative((Layer2Node)postNode) * postNode.getError();
		}
		super.setError(error);
	}
	
	private double derivative(Layer2Node postNode){
		return (postNode.getValue()/super.getValue());
	}
}
