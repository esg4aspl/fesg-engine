package tr.edu.iyte.fesg.cases.resultrecordingutilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TestSequenceModifier {
	
	public static void listFilesForFolder(File folder) {
	    for (File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory() && fileEntry.getName().startsWith("Results_") && !fileEntry.getName().contains("Full")) {
				String resultsFileName = fileEntry.getName();

	        	for(File resultsEntry : fileEntry.listFiles()) {
	        		if(resultsEntry.isDirectory() && resultsEntry.getName().startsWith("Test")) {
	        			for(File seqEntry : resultsEntry.listFiles()) {

	        				sequenceReader(folder.getName(), resultsFileName, seqEntry);
	        			}
	        		}
	        	}
	        }
	    }
	}
	
	public static void sequenceReader(String folderName, String approach, File sequcenceFile) {
		
		String seqFileName = sequcenceFile.getName();
		String filePath = sequcenceFile.getPath();
		BufferedReader reader;
		 try {
			 reader = new BufferedReader(new FileReader(filePath));
			 String sequence = reader.readLine();
			 while(sequence != null) {
				 String newSequence =  modifySequence(sequence);
				 System.out.println(newSequence);
				 sequenceWriter(folderName, seqFileName, approach, newSequence);
				 sequence = reader.readLine();
			 }
			 reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void sequenceWriter(String folderName, String seqFileName, String approach, String newSequence) {
		String rrgFileName = createRRGFileName(approach, seqFileName);
		String rrgFilePath = "files/MXEFiles/" + folderName + "/1RegFiles/sequences/" + rrgFileName + ".txt";
		BufferedWriter writer;
		
		try {
			File file = new File(rrgFilePath);
			writer = new BufferedWriter(new FileWriter(file, true));
			if(file.length() > 0) {
				writer.write("\n" + newSequence);
				writer.close();
			}else {
				writer.write(newSequence);
				writer.close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static String createRRGFileName(String approach, String seqFileName) {
		String[] resultApproach = approach.split("_");
		String approachTag = null;
		
		String[] nameLength = seqFileName.split("_");
		String lengthTag = nameLength[1].substring(0,7);
		String fileName = "";
		
		if(resultApproach[1].equals("FullTestSequenceComposition")) {
			approachTag = "FULL";
			fileName = nameLength[0];
		}else if(resultApproach[1].equals("IncrementalTestSequenceComposition")) {
			String[] incFileName = nameLength[0].split("-");
			fileName = incFileName[0] + "-" + incFileName[1];
			approachTag = incFileName[2];
		}else if(resultApproach[1].equals("SingleModel")) {
			approachTag = "SM";
			fileName = nameLength[0];
		}
		
		

		String rrgFileName = fileName  + "_rrg-1seq-tb_" + lengthTag + "-" + approachTag + "_sequences";
		return rrgFileName;
	}
	

	public static String modifySequence(String seq) {
		String[] ss = seq.split(":");
		String sequence = ss[1].trim();
		String newSequence = "";
		String[] eventSeq = sequence.split(",");
		for(String event : eventSeq) {
			//System.out.println(event);
			String newEvent = modifyEventName(event.trim());
			newSequence += newEvent + " ";
		}
		newSequence.trim();
		
		return newSequence;
		
	}
	
	public static String modifyEventName(String eventName) {
		
		String[] ee = eventName.split("_");
		String newEventName = "";
		
		for(String e : ee) {
			newEventName += e.trim();
		}
		
		return newEventName;
	}

}
