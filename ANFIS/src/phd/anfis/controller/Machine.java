package phd.anfis.controller;

import java.util.ArrayList;
import java.util.List;

import phd.anfis.datastructures.INode;

public class Machine {
	
	private int MF_NUMBER;
	private int INPUT_NUMBER;
	
	public List<INode[]> layer0 = new ArrayList<INode[]>();
	public List<INode[]> layer1 = new ArrayList<INode[]>();
	public List<INode[]> layer2 = new ArrayList<INode[]>();
	public List<INode[]> layer3 = new ArrayList<INode[]>();
	public List<INode[]> layer4 = new ArrayList<INode[]>();
	public List<INode[]> layer5 = new ArrayList<INode[]>();
	
	public Machine(int mf_number, int input_number) {
		this.MF_NUMBER = mf_number;
		this.INPUT_NUMBER = input_number;
	}
	
	private void constructFIS(){
		
	}
	
	
	public String layerToString(List<INode[]> layer){
		String output = "";
		
		for (INode[] iNodes : layer) {
			output += "\n" + "NEW LAYER VECTOR \n";
			for (INode iNode : iNodes) {
				output += iNode.getValue() + "\t";
			}
		}	
		return output;
	}
	


}
