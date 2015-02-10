package phd.anfis.controller;

import java.util.ArrayList;
import java.util.List;
import phd.anfis.dal.FileDataLoader;
import phd.anfis.dal.IDataLoader;


public class Main {

	
	public static List<Double> realOutputList = new ArrayList<Double>();
	
	public static void main(String[] args) {
		
		Machine anfis = new Machine(3, 3);
		
		IDataLoader loader = new FileDataLoader("resources/input.txt");
		loader.LoadData(anfis.layer0, realOutputList);
		
		System.out.println(anfis.layerToString(anfis.layer0));;

	}

}
