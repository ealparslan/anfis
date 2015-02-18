package phd.anfis.datastructures;

import java.util.ArrayList;
import java.util.List;

import phd.anfis.exceptions.NoPostNodeException;
import phd.anfis.exceptions.NoPreNodeException;

public abstract class AbstractNode implements INode {
	
	private double value;
	private double errorDerivative;
	private List<INode> preNodes;
	private List<INode> postNodes;
	Parameter[] params;
	
	public AbstractNode(double val) {
		this.setValue(val);
		this.preNodes = new ArrayList<INode>();
		this.postNodes = new ArrayList<INode>();
	}
	
	@Override
	public void compute(double... param){
		// no any special computation by default
	}
	
	@Override
	public void calculateError(double... param){
		// no any special computation by default
	}
	
	@Override
	public void update_de_dp(){
		// no any special computation by default
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
	
	@Override
	public void setError(double error) {
		this.errorDerivative = error;
	}
	@Override
	public double getError() {
		return this.errorDerivative;
	}
	
	@Override
	public Parameter[] getParameters() {
		return params;
	}
	
	@Override
	public void updateParameters(double step_size) {
		double lenght = gradient_vector_length();
		for (Parameter parameter : params) {
			parameter.setValue(parameter.getValue() - (step_size * parameter.getDe_dp() / lenght));
		}
	}
	
	private double gradient_vector_length(){
		double total=0;
		for (Parameter parameter : params) {
			total += Math.pow(parameter.getDe_dp(), 2.0);
		}
		return total;
	}
	@Override
	public String toString() {
		String retval="";
		retval += "Node Value: " + this.getValue() + "\t" + " Node Error: " + this.getError() + "\n";
		for (Parameter parameter : params) {
			retval += "Parameter Value: " + parameter.getValue() + "\t" + " Parameter Error: " + parameter.getDe_dp() + "\n";
		}
		return retval;
	}

}
