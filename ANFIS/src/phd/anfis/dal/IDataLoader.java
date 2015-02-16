package phd.anfis.dal;

import java.util.List;

import phd.anfis.datastructures.INode;

public interface IDataLoader {
	
	public void LoadData(List<Double[]> i,List<Double> o);

}
