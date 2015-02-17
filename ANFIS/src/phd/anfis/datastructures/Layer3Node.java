package phd.anfis.datastructures;

public class Layer3Node extends AbstractNode {

	
	public Layer3Node(double val) {
		super(val);
	}
	
	@Override
	public void compute(double... param){
		super.setValue(super.getPreNodes().get(0).getValue()/param[0]);;
	}

}
