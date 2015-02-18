package phd.anfis.controller;

import java.util.ArrayList;
import java.util.List;

import phd.anfis.dal.FileDataLoader;
import phd.anfis.dal.IDataLoader;
import phd.anfis.logger.FileLogger;
import phd.anfis.logger.ILogger;


public class Main {

	
	public static List<Double> realOutputList = new ArrayList<Double>();
	public static List<Double[]> inputsList = new ArrayList<Double[]>();
	public static int EPOCH_SIZE = 10;
	public static int MEMBERSHIP_FUNCTION_COUNT = 2;
	public static int INPUT_COUNT = 4;
	
	public static ILogger logger = new FileLogger();
	
	public static void main(String[] args) {
		
		Machine anfis = new Machine(MEMBERSHIP_FUNCTION_COUNT, INPUT_COUNT);
		
		IDataLoader loader = new FileDataLoader("resources/input.txt");
		loader.LoadData(inputsList, realOutputList);
		
		for (int e=0; e<EPOCH_SIZE ; e++){ //foreach epoch run
			int inputRow=0;
			double totalError5thLayer=0;
			for (Double[] inputs : inputsList) { // foreach input row
				int inputElement=0;
				for (Double input : inputs){ // this is for setting input values to layer0 nodes
					anfis.layer0[inputElement].setValue(input);
					inputElement++;
				}

				anfis.learn(realOutputList.get(inputRow)); // we have a structured machine with filled layer 0, train!! 
				
				// something todo about learning results
				inputRow++;
				//logger.printLog("\nLayer 5: " + anfis.layerToString(anfis.layer5, 4));
				totalError5thLayer += anfis.layer5[0].getError();

			}
			logger.printLog("Average Error of Layer 5 for all Inputs: " + totalError5thLayer/inputsList.size());
			logger.printLog("==\n==\n==\n==\n==\n==\n");
			//System.out.println("Layer 1: " + anfis.layerToString(anfis.layer1, 4));
			//System.out.println("Layer 2: " + anfis.layerToString(anfis.layer2, 4));
			//System.out.println("Layer 3: " + anfis.layerToString(anfis.layer3, 4));
			//System.out.println("Layer 4: " + anfis.layerToString(anfis.layer4, 4));
			anfis.updateParameters();
		}
		
	}

}
