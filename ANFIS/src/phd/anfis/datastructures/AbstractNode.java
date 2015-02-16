package phd.anfis.datastructures;

import java.util.ArrayList;
import java.util.List;

import phd.anfis.exceptions.NoPostNodeException;
import phd.anfis.exceptions.NoPreNodeException;

public abstract class AbstractNode implements INode {
	
	private double value;
	private List<INode> preNodes;
	private List<INode> postNodes;
	
	public AbstractNode(double val) {
		this.setValue(val);
		this.preNodes = new ArrayList<INode>();
		this.postNodes = new ArrayList<INode>();
	}

	@Override
	public void addPreNode(INode n) throws NoPreNodeException {
		preNodes.add(n);
	}

	@Override
	public void setPreNodes(List<INode> l) throws NoPreNodeException {
		preNodes = l;
	}

	@Override
	public List<INode> getPreNodes() {
		return preNodes;
	}

	@Override
	public void addPostNode(INode n) throws NoPostNodeException {
		postNodes.add(n);
	}

	@Override
	public void setPostNodes(List<INode> l) throws NoPostNodeException {
		postNodes = l;
	}

	@Override
	public List<INode> getPostNodes() {
		return postNodes;
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
