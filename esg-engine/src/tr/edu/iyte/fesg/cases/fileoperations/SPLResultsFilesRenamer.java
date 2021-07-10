package tr.edu.iyte.fesg.cases.fileoperations;

import java.io.File;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Map.Entry;

public class SPLResultsFilesRenamer {
	
	private static int scenarioIDDigit;
	private static int productIDDigit;
	
	
	public static int getScenarioIDDigit() {
		return scenarioIDDigit;
	}
	
	public static int getProductIDDigit() {
		return productIDDigit;
	}
	
	public static void testSequenceFileRenamer(String folderPath, String splIdentifier,
			Map<String, AbstractMap.SimpleEntry<Integer, Integer>> map) {
		setMaxScenarioIDMaxProductID(map);
		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();
		System.out.println(listOfFiles.length);
		int counter = 0;
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				File file = new File(folderPath + "/" + listOfFiles[i].getName());
				String currentFileName = file.getName();
				System.out.println(currentFileName);
				String eof = "_length2.txt";
				String productName = currentFileName.substring(0, currentFileName.length()- eof.length());
				String endOfFileName = currentFileName.substring(currentFileName.length()- eof.length(),
						currentFileName.length());
				System.out.println(productName);
				System.out.println(endOfFileName);

				int scenarioID = 0;
				int productID = 0;

				for (String underScorePUCName : map.keySet()) {
					if (productName.equals(underScorePUCName)) {
						AbstractMap.SimpleEntry<Integer, Integer> pair = map.get(underScorePUCName);
						scenarioID = pair.getKey();
						productID = pair.getValue();
						counter++;
						String newScenarioIDString = adjustIDLength(scenarioID, scenarioIDDigit);
						String newProductIDString = adjustIDLength(productID, productIDDigit);
						String newName = splIdentifier + "-" + newProductIDString + "-" + newScenarioIDString + endOfFileName;
						System.out.println(newName);
						System.out.println(counter);
						file.renameTo(new File(folderPath + "/" + newName));
					}
				}				
			}
		}
	}
	
	public static void coverageAnalysisFileRenamer(String folderPath, Map<String, AbstractMap.SimpleEntry<Integer, Integer>> map) {
		setMaxScenarioIDMaxProductID(map);
		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();
		System.out.println(listOfFiles.length);
		int counter = 0;
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				File file = new File(folderPath + "/" + listOfFiles[i].getName());
				String currentFileName = file.getName();
				System.out.println(currentFileName);
				String productName = currentFileName.substring(currentFileName.indexOf("_") + 1, currentFileName.indexOf("-"));
				String endOfFileName = currentFileName.substring(currentFileName.length()-16,
						currentFileName.length());
				System.out.println(productName);
				System.out.println(endOfFileName);

				int scenarioID = 0;
				int productID = 0;

				for (String underScorePUCName : map.keySet()) {
					if (productName.equals(underScorePUCName)) {
						AbstractMap.SimpleEntry<Integer, Integer> pair = map.get(underScorePUCName);
						scenarioID = pair.getKey();
						productID = pair.getValue();
						counter++;
						String newScenarioIDString = adjustIDLength(scenarioID, scenarioIDDigit);
						String newProductIDString = adjustIDLength(productID, productIDDigit);
						String newName = "coverageAnalysis" + "_" + newProductIDString + "-" + newScenarioIDString + endOfFileName;
						System.out.println(newName);
						System.out.println(counter);
						file.renameTo(new File(folderPath + "/" + newName));
					}
				}				
			}
		}
	}
	
	

	public static void sequenceFileRenamer(String folderPath, String splIdentifier,
			Map<String, AbstractMap.SimpleEntry<Integer, Integer>> map) {
		setMaxScenarioIDMaxProductID(map);
		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();
		System.out.println(listOfFiles.length);
		int counter = 0;
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				File file = new File(folderPath + "/" + listOfFiles[i].getName());
				String currentFileName = file.getName();
				System.out.println(currentFileName);
				String endOfFile = "-INC_length2.txt";
				String productName = currentFileName.substring(0, currentFileName.length() - endOfFile.length());

				int scenarioID = 0;
				int productID = 0;

				for (String underScorePUCName : map.keySet()) {
					if (productName.equals(underScorePUCName)) {
						System.out.println(productName);
						AbstractMap.SimpleEntry<Integer, Integer> pair = map.get(underScorePUCName);
						scenarioID = pair.getKey();
						productID = pair.getValue();
						counter++;
					}

				}
				String endOfFileName = currentFileName.substring(currentFileName.length() - endOfFile.length(),currentFileName.length());
				String newScenarioIDString = adjustIDLength(scenarioID, scenarioIDDigit);
				String newProductIDString = adjustIDLength(productID, productIDDigit);
				
				String newName = splIdentifier + "-" + newProductIDString + "-" + newScenarioIDString + endOfFileName;
				System.out.println(newName);
				System.out.println(counter);
				file.renameTo(new File(folderPath + "/" + newName));
				
			}
		}
	}
	
	public static void productmodelFileRenamer(String folderPath, String splIdentifier,
			Map<String, AbstractMap.SimpleEntry<Integer, Integer>> map) {
		setMaxScenarioIDMaxProductID(map);
		
		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();
		System.out.println(listOfFiles.length);
		int counter = 0;
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				File file = new File(folderPath + "/" + listOfFiles[i].getName());
				String currentFileName = file.getName();
				System.out.println(currentFileName);
				String productName = currentFileName.substring(currentFileName.indexOf("-") + 1, currentFileName.indexOf("_"));
				System.out.println(productName);
				String key = "";
				int productID = 0;
				int scenarioID = 0;

				for (String underScorePUCName : map.keySet()) {
					String[] underScorePUCNameArray = underScorePUCName.split("_");
					key = underScorePUCNameArray[0];
					for (int j = 1; j < underScorePUCNameArray.length; j++) {
						String featureName = underScorePUCNameArray[j];
						String newFeatureName = "";
						newFeatureName += featureName.substring(0, 1).toUpperCase();
						newFeatureName += featureName.substring(1, featureName.length());
						key += newFeatureName;
					}
					if (productName.equals(key)) {
						AbstractMap.SimpleEntry<Integer, Integer> pair = map.get(underScorePUCName);
						scenarioID = pair.getKey();
						productID = pair.getValue();
						counter++;
					}

				}
				String endOfFileName = currentFileName.substring(currentFileName.indexOf("_"),
						currentFileName.length());

				String newScenarioIDString = adjustIDLength(scenarioID, scenarioIDDigit);
				String newProductIDString = adjustIDLength(productID, productIDDigit);
				
				String newName = splIdentifier + "-" + newProductIDString + "-" + newScenarioIDString + endOfFileName;
				System.out.println(newName);
				System.out.println(counter);
				//file.renameTo(new File(folderPath + "/" + newName));
				
			}
		}
	}
	
	
	public static void setMaxScenarioIDMaxProductID(Map<String, AbstractMap.SimpleEntry<Integer, Integer>> map) {
		int maxScenarioID = 0;
		int maxProductID = 0;
		
		for(Entry<String,AbstractMap.SimpleEntry<Integer, Integer>> entry: map.entrySet()) {
			AbstractMap.SimpleEntry<Integer, Integer> pair = entry.getValue();
			int scenarioID = pair.getKey();
			int productID = pair.getValue();
			if(scenarioID > maxScenarioID) {
				maxScenarioID = scenarioID;
			}
			if(productID > maxProductID) {
				maxProductID = productID;
			}
		}
		
		scenarioIDDigit = String.valueOf(maxScenarioID).length();
		productIDDigit = String.valueOf(maxProductID).length();

	}

	
	public static String adjustIDLength(int ID, int length) {
		
		String scenarioIDString = String.valueOf(ID);
		String newScenarioIDString = "";
		if(scenarioIDString.length() < length) {
			int difference = length - scenarioIDString.length();
			for(int k = 0; k < difference; k++) {
				newScenarioIDString += "0";
			}
		}
		newScenarioIDString += scenarioIDString;
		
		return newScenarioIDString;
		
	}

}
