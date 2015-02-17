package phd.anfis.datastructures;

public class Layer2Node extends AbstractNode {

	double layer2total = 0;
	public Layer2Node(double val) {
		super(val);
	}
	
	@Override
	public void compute(double... param){
		double product=1;
		for (INode iNode : super.getPreNodes())
			product *= iNode.getValue();
		super.setValue(product);
	}
	
	@Override
	public void calculateError(double... param){
		layer2total = param[0];
		double error = 0;
		for (INode postNode : super.getPostNodes()) {
			error += derivative((Layer3Node)postNode) * postNode.getError();
		}
		super.setError(error);
	}
	
	private double derivative(Layer3Node postNode){
		return ((layer2total - super.getValue())/layer2total*layer2total);
	}

}
