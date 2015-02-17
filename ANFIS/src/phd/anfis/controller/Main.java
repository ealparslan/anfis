package phd.anfis.controller;

import java.util.ArrayList;
import java.util.List;

import phd.anfis.dal.FileDataLoader;
import phd.anfis.dal.IDataLoader;


public class Main {

	
	public static List<Double> realOutputList = new ArrayList<Double>();
	public static List<Double[]> inputsList = new ArrayList<Double[]>();
	public static int EPOCH_SIZE = 1;
	public static int MEMBERSHIP_FUNCTION_COUNT = 2;
	public static int INPUT_COUNT = 4;
	
	public static void main(String[] args) {
		
		Machine anfis = new Machine(MEMBERSHIP_FUNCTION_COUNT, INPUT_COUNT);
		
		IDataLoader loader = new FileDataLoader("resources/input.txt");
		loader.LoadData(inputsList, realOutputList);
		
		for (int e=0; e<EPOCH_SIZE ; e++){ //foreach epoch run
			int inputRow=0;
			for (Double[] inputs : inputsList) { // foreach input row
				int inputElement=0;
				for (Double input : inputs){ // this is for setting input values to layer0 nodes
					anfis.layer0[inputElement].setValue(input);
					inputElement++;
				}
				System.out.println("=====================================NEW INPUT=========================");
				System.out.println("=====================================NEW INPUT=========================");
				System.out.println("=====================================NEW INPUT=========================");
				System.out.println("=====================================NEW INPUT=========================");

				anfis.learn(realOutputList.get(inputRow)); // we have a structured machine with filled layer 0, train!! 
				
				// something todo about learning results
				inputRow++;
			}
			anfis.updateParameters();
		}
		
	}

}
