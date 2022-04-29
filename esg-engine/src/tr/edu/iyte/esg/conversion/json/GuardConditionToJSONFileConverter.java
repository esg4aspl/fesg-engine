package tr.edu.iyte.esg.conversion.json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import tr.edu.iyte.esg.model.guardcondition.GuardCondition;

public class GuardConditionToJSONFileConverter {
	
	public static void convertGuardConditionListToJSONFile(List<GuardCondition> guardConditionList, String fileName) throws FileNotFoundException {
		
		Writer fileWriter = null;
		try {
			fileWriter = new FileWriter(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.println("{");
		printWriter.println("\t\t" + "\"guardConditions\"" + "\t:" +  "[");
		
		for(int i = 0; i < guardConditionList.size() - 1; i++) {
			
			GuardCondition guardCondition = guardConditionList.get(i);
			printGuardCondition(printWriter, guardCondition);
			printWriter.println(",");
		}
		GuardCondition guardCondition = guardConditionList.get(guardConditionList.size() - 1);
		printGuardCondition(printWriter, guardCondition);
		printWriter.println("\n\t\t" + "]");
		printWriter.println("}");
		
		printWriter.close();
	
	}
	

	private static void printGuardCondition(PrintWriter printWriter, GuardCondition guardCondition) {
		printWriter.println("\t\t" + "{");
		printWriter.println("\t\t\t" + "\"ID\"" + "\t:" + guardCondition.getID() + ",");
		printWriter.println("\t\t\t" + "\"conditionName\"" + "\t:" + guardCondition.getConditionName() + ",");
		printWriter.println("\t\t\t" + "\"result\"" + "\t:" + guardCondition.isResult() + ",");
		printWriter.println("\t\t\t" + "\"edgesToBeRemoved\"" + "\t:" +  "[");
		
		Map<String,List<String>> edgesToBeRemoved = guardCondition.getEdgesToBeRemoved();
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
