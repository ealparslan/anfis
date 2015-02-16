package phd.anfis.datastructures;

import java.util.List;

import phd.anfis.exceptions.NoPreNodeException;

public class Layer0Node extends AbstractNode {

	public Layer0Node(double val) {
		super(val);
	}
	
	@Override
	public void addPreNode(INode n) throws NoPreNodeException
	{
		throw new NoPreNodeException();
	}
	
	@Override
	public void setPreNodes(List<INode> l) throws NoPreNodeException {
		throw new NoPreNodeException();
	}
	

}
