package phd.anfis.datastructures;

import java.util.List;

import phd.anfis.exceptions.NoPostNodeException;

public class Layer5Node extends AbstractNode {

	public Layer5Node(double val) {
		super(val);
	}
	
	@Override
	public void addPostNode(INode n) throws NoPostNodeException
	{
		throw new NoPostNodeException();
	}
	
	@Override
	public void setPostNodes(List<INode> l) throws NoPostNodeException {
		throw new NoPostNodeException();
	}

}
