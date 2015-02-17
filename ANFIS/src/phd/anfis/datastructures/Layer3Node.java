package phd.anfis.datastructures;

public class Layer3Node extends AbstractNode {

	
	public Layer3Node(double val) {
		super(val);
	}
	
	@Override
	public void compute(double... param){
		super.setValue(super.getPreNodes().get(0).getValue()/param[0]);;
	}
	
	@Override
	public void calculateError(double... param){
		double error = 0;
		for (INode postNode : super.getPostNodes()) {
			error += derivative((Layer4Node)postNode) * postNode.getError();
		}
		super.setError(error);
	}
	
	private double derivative(Layer4Node postNode){
		return postNode.helperErrorLayer3;
	}

}
