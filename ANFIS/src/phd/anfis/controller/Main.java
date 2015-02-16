package phd.anfis.controller;

import java.util.ArrayList;
import java.util.List;

import phd.anfis.dal.FileDataLoader;
import phd.anfis.dal.IDataLoader;


public class Main {

	
	public static List<Double> realOutputList = new ArrayList<Double>();
	public static List<Double[]> inputs = new ArrayList<Double[]>();
	
	public static void main(String[] args) {
		
		Machine anfis = new Machine(3, 2);
		
		//IDataLoader loader = new FileDataLoader("resources/input.txt");
		//loader.LoadData(inputs, realOutputList);
		
		//System.out.println(anfis.layerToString(anfis.layer0));;

	}

}
