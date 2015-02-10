package phd.anfis.controller;

import java.util.ArrayList;
import java.util.List;

import phd.anfis.dal.FileDataLoader;
import phd.anfis.dal.IDataLoader;
import phd.anfis.datastructures.INode;
import phd.anfis.datastructures.Layer0Node;

public class Main {

	public static List<INode> layer0 = new ArrayList<INode>();
	public static List<INode> layer1 = new ArrayList<INode>();
	public static List<INode> layer2 = new ArrayList<INode>();
	public static List<INode> layer3 = new ArrayList<INode>();
	public static List<INode> layer4 = new ArrayList<INode>();
	public static List<INode> layer5 = new ArrayList<INode>();
	public static List<Double> realOutputList = new ArrayList<Double>();
	
	public static void main(String[] args) {
		
		IDataLoader loader = new FileDataLoader("../../../resources/input.txt");
		loader.LoadData(layer0, realOutputList);

	}

}
