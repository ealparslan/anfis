package phd.anfis.datastructures;


import java.util.Random;

import phd.anfis.consequent.ConsequenceCalculator;

public class Layer4Node extends AbstractNode {

	ConsequenceCalculator calculator;
	INode[] inputLayer;
	double helperErrorLayer3;

	
	public Layer4Node(double val,INode[] inputs) {
		super(val);
		this.inputLayer = inputs;
		params = new Parameter[inputLayer.length+1];
		Random r = new Random();
		for (int i=0 ; i<params.length ; i++)
			params[i] = new Parameter(1 * r.nextDouble());
		calculator = new ConsequenceCalculator(params);
	}
	
	
	@Override
	public void compute(double... param){
		super.setValue(super.getPreNodes().get(0).getValue() * (calculator.compute(inputLayer)));
	}
	
	@Override
	public void calculateError(double... param){
		double error = 0;
		for (INode postNode : super.getPostNodes()) {
			error += derivative((Layer5Node)postNode) * postNode.getError();
		}
		super.setError(error);
		errorHelperForLayer3();
	}
	
	private double derivative(Layer5Node postNode){
		return 1.0;
	}
	
	private void errorHelperForLayer3(){
		helperErrorLayer3 = calculator.compute(inputLayer);
	}
	
	@Override
	public void update_de_dp(){
		for (int p=0; p<params.length-1 ; p++) {
			params[p].setDe_dp(params[p].getDe_dp() + super.getError() * derivative_o_p(p));
		}
		params[params.length-1].setDe_dp(params[params.length-1].getDe_dp() + super.getError() * super.getPreNodes().get(0).getValue());
	}
	
	private double derivative_o_p(int p){
		return ( super.getPreNodes().get(0).getValue() * inputLayer[p].getValue()); // wn * x
	}
		

}
