package phd.anfis.controller;

import java.util.ArrayList;
import java.util.List;

import phd.anfis.algorithms.Cartesian;
import phd.anfis.datastructures.INode;
import phd.anfis.datastructures.Layer0Node;
import phd.anfis.datastructures.Layer1Node;
import phd.anfis.datastructures.Layer2Node;
import phd.anfis.datastructures.Layer3Node;
import phd.anfis.datastructures.Layer4Node;
import phd.anfis.datastructures.Layer5Node;
import phd.anfis.datastructures.Parameter;
import phd.anfis.exceptions.NoPostNodeException;
import phd.anfis.exceptions.NoPreNodeException;

public class Machine {
	
	private int MF_NUMBER;
	private int INPUT_NUMBER;
	private int RULE_NUMBER;
	public INode[] layer0, layer1, layer2, layer3, layer4, layer5;
	
	public Machine(int mf_number, int input_number) {
		this.MF_NUMBER = mf_number;
		this.INPUT_NUMBER = input_number;
		this.RULE_NUMBER = (int) Math.pow(MF_NUMBER, INPUT_NUMBER);
		layer0 = new INode[INPUT_NUMBER];
		layer1 = new INode[INPUT_NUMBER * MF_NUMBER];
		layer2 = new INode[RULE_NUMBER];
		layer3 = new INode[RULE_NUMBER];
		layer4 = new INode[RULE_NUMBER];
		layer5 = new INode[1];
		for(int i=0;i<layer0.length;i++) layer0[i]=new Layer0Node(0);
		for(int i=0;i<layer1.length;i++) layer1[i]=new Layer1Node(0);
		for(int i=0;i<layer2.length;i++) layer2[i]=new Layer2Node(0);
		for(int i=0;i<layer3.length;i++) layer3[i]=new Layer3Node(0);
		for(int i=0;i<layer4.length;i++) layer4[i]=new Layer4Node(0,layer0);
		for(int i=0;i<layer5.length;i++) layer5[i]=new Layer5Node(0);
		
		//dummyDataLoader();
		
		try {
			constructFIS();
		} catch (NoPostNodeException | NoPreNodeException e) {
			e.printStackTrace();
		}
	}
	
	public void learn(Double realOutput){
		
		//System.out.println("New Input Learning!");
		
		// VALUE COMPUTATIONS (calculate_output)
		for (INode iNode : layer1)	iNode.compute();
		double layer2total = 0;
		for (INode iNode : layer2) {
			iNode.compute();
			layer2total += iNode.getValue();
		}
		for (INode iNode : layer3) iNode.compute(layer2total);
		for (INode iNode : layer4) iNode.compute();
		for (INode iNode : layer5) iNode.compute();
		
		//ERROR CALCULATIONS (calculate_de_do)
		for (INode iNode : layer5) iNode.calculateError(realOutput);
		for (INode iNode : layer4) iNode.calculateError();
		for (INode iNode : layer3) iNode.calculateError();
		for (INode iNode : layer2) iNode.calculateError(layer2total);
		for (INode iNode : layer1) iNode.calculateError();

		// DE_DP PARAMETER UPDATES (update_de_dp)
		for (INode iNode : layer4) iNode.update_de_dp();
		for (INode iNode : layer1) iNode.update_de_dp();
		
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	public void updateParameters(){
		System.out.println("Parameter updates for this epoch!");
		for (INode iNode : layer1){
			iNode.updateParameters(1000);
			//System.out.println(iNode.toString());
		}
	}
	
	
	private void constructFIS() throws NoPostNodeException, NoPreNodeException{
		
		// postnodes for layer 0
		for (int i=0 ; i<layer0.length ; i++){
			for (int j=0; j < MF_NUMBER ; j++){
				layer0[i].addPostNode(layer1[i*MF_NUMBER+j]);
			}
		}
		// prenodes for layer 2
		Cartesian cart = new Cartesian();
		List<List<INode>> general= new ArrayList<List<INode>>();
		List<INode> tempList = null;
		int mf_order = 0;
		for (int i=0; i<layer1.length ; i++){
			if (mf_order%MF_NUMBER==0){
				tempList = new ArrayList<INode>();
			}
			tempList.add(layer1[i]);
			mf_order++;
			if (mf_order%MF_NUMBER==0){
				general.add(tempList);
			}
		}
		int index=0;
		List<List<INode>> product = cart.cartesianProduct(general);
		for (List<INode> list : product) {
			layer2[index].setPreNodes(list);
			index++;
		}
		definePostNodes(layer2);
		
		// postnodes for layer 2
		for (int i=0; i<layer2.length ; i++) {
			layer2[i].addPostNode(layer3[i]);
		}	
		// postnodes for layer 3
		for (int i=0 ; i<layer3.length ; i++){
			layer3[i].addPostNode(layer4[i]);
		}	
		// postnodes for layer 4
		for (int i=0 ; i<layer4.length ; i++){
			layer4[i].addPostNode(layer5[0]);
		}
		// prenode calculation
		definePreNodes(layer4);
		definePreNodes(layer3);
		definePreNodes(layer2);
		//definePreNodes(layer1); //calculated from postnodes
		definePreNodes(layer0);
		
	}
	
	private void definePreNodes(INode[] layer) throws NoPreNodeException{
		for (int i=0; i<layer.length ; i++){
			List<INode> postnodes = layer[i].getPostNodes();
			for (INode iNode : postnodes) {
				iNode.addPreNode(layer[i]);
			}
		}
	}
	
	private void definePostNodes(INode[] layer) throws NoPostNodeException{
		for (int i=0; i<layer.length ; i++){
			List<INode> prenodes = layer[i].getPreNodes();
			for (INode iNode : prenodes) {
				iNode.addPostNode(layer[i]);
			}
		}
	}
	
	public String layerToString(INode[] layer, int key){
		String output = "";
		/**
		 * 1 -> values
		 * 2 -> values and parameters
		 * 3 -> parameters detailed
		 * 4 -> values and errors
		 */

		switch (key) {
		case 1:
			for (INode iNode : layer) {
				output += iNode.getValue() + "\n";
			}
			break;
		case 2:
			for (INode iNode : layer) {
				output += "\nNode Value: " + iNode.getValue() + "\t";
				for (Parameter param : iNode.getParameters()) {
					output += "Parameter: " + param.getValue() + "\t";
				}
			}
			break;
		case 3:
			for (INode iNode : layer) {
				for (Parameter param : iNode.getParameters()) {
					output += "\nParameter: " + param.getValue() + "\t";
					output += "De_dp: " + param.getDe_dp() + "\t";
				}
			}
			break;
		case 4:
			for (INode iNode : layer) {
				output += "\nNode Value: " + iNode.getValue() + " Error: " + iNode.getError() + "\n";
			}
			break;
		default:
			break;
		}
		/*
		for (INode iNode : layer) {
			output += "\n\nNode value: " + iNode.getValue() + "\n";
			output +=  "Prenodes: ";
			for (INode preNode : iNode.getPreNodes()) {
				output += preNode.getValue() + "\t";
			}
			output +=  "\nPostnodes: ";
			for (INode postNode : iNode.getPostNodes()) {
				output += postNode.getValue() + "\t";
			}
		}
		*/
		return output;
	}
	
	public String parametersToString(INode[] layer){
		String retval="";
		for (INode iNode : layer) {
			retval += "\n";
			for (Parameter param : iNode.getParameters()) {
				retval += param.getDe_dp() + "\t";
			}
		}
		return retval;
	}

}
