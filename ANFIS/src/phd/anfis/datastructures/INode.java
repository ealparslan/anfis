package phd.anfis.datastructures;

import java.util.List;

public interface INode {
	
	public double getValue();
	public void setValue(double v);
	public void addPreNode(INode n);
	public void setPreNodes(List<INode> l);
	public List<INode> getPreNodes();
	
}
