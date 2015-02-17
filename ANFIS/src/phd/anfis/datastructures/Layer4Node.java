package phd.anfis.datastructures;


import phd.anfis.consequent.ConsequenceCalculator;

public class Layer4Node extends AbstractNode {

	ConsequenceCalculator calculator;
	INode[] inputLayer;
	double helperErrorLayer3;
	
	public Layer4Node(double val,INode[] inputs) {
		super(val);
		this.inputLayer = inputs;
		calculator = new ConsequenceCalculator(inputLayer.length);
	}
	
	
	@Override
	public void compute(double... param){
		super.setValue(super.getPreNodes().get(0).getValue() * (calculator.compute(inputLayer)));
	}
	
	@Override
	public void calculateError(double... param){
		double error = 0;
		for (INode postNode : super.getPostNodes()) {
			error += derivative() * postNode.getError();
		}
		super.setError(error);
		errorHelperForLayer3();
	}
	
	private double derivative(){
		return 1.0;
	}
	
	private void errorHelperForLayer3(){
		helperErrorLayer3 = calculator.compute(inputLayer);
	}

}
