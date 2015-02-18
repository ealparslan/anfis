package phd.anfis.datastructures;

import java.util.List;

import phd.anfis.exceptions.NoPostNodeException;
import phd.anfis.exceptions.NoPreNodeException;

public interface INode {
	
	public double getValue();
	public void setValue(double v);
	public void addPreNode(INode n) throws NoPreNodeException;
	public void setPreNodes(List<INode> l) throws NoPreNodeException;
	public List<INode> getPreNodes();
	public void addPostNode(INode n) throws NoPostNodeException;
	public void setPostNodes(List<INode> l) throws NoPostNodeException;
	public List<INode> getPostNodes();
	public void compute(double... param);
	public void calculateError(double... param);
	public void setError(double e);
	public double getError();
	public void update_de_dp();
	public Parameter[] getParameters();
	public void updateParameters(double step_size);
	public String toString();
}
