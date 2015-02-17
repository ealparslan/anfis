package phd.anfis.datastructures;


import phd.anfis.consequent.ConsequenceCalculator;

public class Layer4Node extends AbstractNode {

	ConsequenceCalculator calculator;
	
	public Layer4Node(double val) {
		super(val);
		calculator = new ConsequenceCalculator(4);// !!!!!!!!!!!KONFIGURASYONDAN OKUSUN
	}
	
	
	@Override
	public void compute(double... param){
		super.setValue(calculator.compute(super.getPreNodes().get(0).getValue(),param));
	}

}
