package phd.anfis.dal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import phd.anfis.datastructures.INode;
import phd.anfis.datastructures.Layer0Node;

public class FileDataLoader implements IDataLoader {
	
	public String filePath;
	
	public FileDataLoader(String path) {
		this.filePath = path;
	}
	
	@Override
	public void LoadData(List<INode[]> i, List<Double> o) {
		
		BufferedReader br = null;

		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(filePath));
			while ((sCurrentLine = br.readLine()) != null) {
				String[] tmp = sCurrentLine.split(";");
				INode[] tmp0Layer = new INode[tmp.length-1];
				for (int j=0 ; j<tmp.length-1 ; j++){
					tmp0Layer[j] = new Layer0Node(Double.parseDouble(tmp[j]));
				}
				Double tmpOut = Double.parseDouble(tmp[tmp.length-1]);
				i.add(tmp0Layer);
				o.add(tmpOut);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
