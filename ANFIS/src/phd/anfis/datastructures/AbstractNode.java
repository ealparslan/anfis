package phd.anfis.datastructures;

import java.util.List;

public abstract class AbstractNode implements INode {
	
	private double value;
	private List<INode> preNodes;
	
	public AbstractNode(double val) {
		this.setValue(val);
	}

	@Override
	public void addPreNode(INode n) {
		preNodes.add(n);
	}

	@Override
	public void setPreNodes(List<INode> l) {
		preNodes = l;
	}

	@Override
	public List<INode> getPreNodes() {
		return preNodes;
	}

	@Override
	public void setValue(double value) {
		this.value = value;
	}
	@Override
	public double getValue() {
		return this.value;
	}

}
