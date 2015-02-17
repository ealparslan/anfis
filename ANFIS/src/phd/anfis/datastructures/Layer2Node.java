package phd.anfis.datastructures;

public class Layer2Node extends AbstractNode {

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

}
