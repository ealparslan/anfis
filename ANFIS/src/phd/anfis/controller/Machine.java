package phd.anfis.controller;

import java.util.ArrayList;
import java.util.List;
import phd.anfis.datastructures.INode;
import phd.anfis.datastructures.Layer0Node;
import phd.anfis.datastructures.Layer1Node;
import phd.anfis.datastructures.Layer2Node;
import phd.anfis.datastructures.Layer3Node;
import phd.anfis.datastructures.Layer4Node;
import phd.anfis.datastructures.Layer5Node;
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
		for(int i=0;i<layer4.length;i++) layer4[i]=new Layer4Node(0);
		for(int i=0;i<layer5.length;i++) layer5[i]=new Layer5Node(0);
		dummyDataLoader();
		try {
			constructFIS();
		} catch (NoPostNodeException | NoPreNodeException e) {
			e.printStackTrace();
		}
	}
	
	
	private void constructFIS() throws NoPostNodeException, NoPreNodeException{
		
		// postnodes for layer 0
		for (int i=0 ; i<layer0.length ; i++){
			for (int j=0; j < MF_NUMBER ; j++){
				layer0[i].addPostNode(layer1[i*MF_NUMBER+j]);
			}
		}
		
		// postnodes for layer 1
		for (int i=0 ; i<layer1.length ; i++){
			int inp_order = ((int) i / MF_NUMBER);
			int float_amount = (int) Math.pow(MF_NUMBER, inp_order);
			int mf_order = i % MF_NUMBER;
			int index= mf_order*MF_NUMBER;
			do {
				
				for (int n=0; n<float_amount ; n++){
					layer1[i].addPostNode(layer2[index]);
					index += float_amount;
				}
				for (int n=0; n<float_amount ; n++){
					index++;
				}	
			} while (index<layer2.length);			
		}
		
		
		// postnodes for layer 2
		for (INode iNode : layer2) {
			for (INode iNode2 : layer3) {
				iNode.addPostNode(iNode2);
			}
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
		definePreNodes(layer1);
		definePreNodes(layer0);
		
		//System.out.println(layerToString(layer1));
		/*
		for (INode iNode : layer1) {
			System.out.println("\nLayer 1 Node:" + iNode.getValue());
			System.out.println("Post nodes: ");
			for (INode pNode : iNode.getPostNodes()) {
				System.out.print(pNode.getValue() + "\t");
			}
			System.out.println("");
		}
		*/
	}
	
	private void definePreNodes(INode[] layer) throws NoPreNodeException{
		for (int i=0; i<layer.length ; i++){
			List<INode> postnodes = layer[i].getPostNodes();
			for (INode iNode : postnodes) {
				iNode.addPreNode(layer[i]);
			}
		}
	}
	
	private void dummyDataLoader(){
		
		int i=1;
		for (INode iNode : layer0) {
			iNode.setValue(i);
			i++;
		}
		for (INode iNode : layer1) {
			iNode.setValue(i);
			i++;
		}
		for (INode iNode : layer2) {
			iNode.setValue(i);
			i++;
		}
		for (INode iNode : layer3) {
			iNode.setValue(i);
			i++;
		}
		for (INode iNode : layer4) {
			iNode.setValue(i);
			i++;
		}
		for (INode iNode : layer5) {
			iNode.setValue(i);
			i++;
		}
	}
	
	
	
	public String layerToString(INode[] layer){
		String output = "";
		
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
		
		return output;
	}
	


}
