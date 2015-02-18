package phd.anfis.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements ILogger {
	
	File file;
	FileWriter fw;
	BufferedWriter bw;
	
	public FileLogger() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void printLog(String s) {
		try {
			file = new File("resources/log.txt");
 			//if (!file.exists()) {
			//	file.createNewFile();
			//}
			fw = new FileWriter(file.getAbsoluteFile(),true);
			bw = new BufferedWriter(fw);
			bw.write(s+"\n");
			bw.close();
  
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
