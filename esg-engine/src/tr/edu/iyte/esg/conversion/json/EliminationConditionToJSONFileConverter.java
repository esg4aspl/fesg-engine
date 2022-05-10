package tr.edu.iyte.esg.conversion.json;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import tr.edu.iyte.esg.model.eliminationcondition.EliminationCondition;

import java.util.Set;

public class EliminationConditionToJSONFileConverter {
	
	public static void convertEliminationConditionListToJSONFile(List<EliminationCondition> eliminationConditionList, String fileName) throws FileNotFoundException {
		
		Writer fileWriter = null;
		try {
			fileWriter = new FileWriter(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.println("{");
		printWriter.println("\t\t" + "\"eliminationConditions\"" + "\t:" +  "[");
		
		for(int i = 0; i < eliminationConditionList.size() - 1; i++) {
			
			EliminationCondition eliminationCondition = eliminationConditionList.get(i);
			printEliminationCondition(printWriter, eliminationCondition);
			printWriter.println(",");
		}
		EliminationCondition eliminationCondition = eliminationConditionList.get(eliminationConditionList.size() - 1);
		printEliminationCondition(printWriter, eliminationCondition);
		printWriter.println("\n\t\t" + "]");
		printWriter.println("}");
		
		printWriter.close();
	
	}
	

	private static void printEliminationCondition(PrintWriter printWriter, EliminationCondition eliminationCondition) {
		printWriter.println("\t\t" + "{");
		printWriter.println("\t\t\t" + "\"ID\"" + "\t:" + eliminationCondition.getID() + ",");
		printWriter.println("\t\t\t" + "\"conditionName\"" + "\t:\"" + eliminationCondition.getConditionName() + "\",");
		printWriter.println("\t\t\t" + "\"result\"" + "\t:" + eliminationCondition.isResult() + ",");
		printWriter.println("\t\t\t" + "\"edgesToBeRemoved\"" + "\t:" +  "[");
		
		Map<String,List<String>> edgesToBeRemoved = eliminationCondition.getEdgesToBeRemoved();
		Set<Entry<String,List<String>>> entrySet = edgesToBeRemoved.entrySet();
		Iterator<Entry<String,List<String>>> entrySetIterator = entrySet.iterator();
		int index = 0;
		int size = entrySet.size();
		while(entrySetIterator.hasNext()) {
			Entry<String,List<String>> entry = entrySetIterator.next();
			String source = entry.getKey();
			List<String> targetList = entry.getValue();
		
			for(int i = 0; i < targetList.size() - 1; i++) {
				String target = targetList.get(i);
				String toBeWritten = "\t\t\t\t\t" +"\"" + source + "->" + target + "\"";
				printWriter.print(toBeWritten);
				printWriter.println(",");
				
			}
			String lastTarget = targetList.get(targetList.size() - 1);
			String toBeWritten = "\t\t\t\t\t" +"\"" + source + "->" + lastTarget + "\"";
			
			if(index < size - 1) {
				printWriter.print(toBeWritten);
				printWriter.println(",");
			}else
				printWriter.println(toBeWritten);
			
			index++;
			
		}
		printWriter.println("\t\t\t" +  "]");
		printWriter.print("\t\t" + "}");
		
	}
	


}
