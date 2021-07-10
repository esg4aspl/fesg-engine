package tr.edu.iyte.fesg.cases.email.allproducts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class TestSimulationFileReader {
		
	public static void main(String[] args) throws IOException {
		String faultsTestExecutions = "files/Cases/EmailPL/1RegFiles/_spl_em_2_faults-testexecutions-20percent-1234567890";		
		File faultsTestExecutionsFolder = new File(faultsTestExecutions);

		File[] faultsTestExecutionsFiles = faultsTestExecutionsFolder.listFiles();
		System.out.println(faultsTestExecutionsFiles.length);

		List<List<String>> rows = new LinkedList<List<String>>();
		
		for (int i = 0; i < faultsTestExecutionsFiles.length; i++) {

			String scenarioID = "";
			String productID = "";
			String testSetNumber = "";
			String testSet = "";
			
			if (faultsTestExecutionsFiles[i].isFile()) {
				File faultsTestExecutionsFile = new File(
						faultsTestExecutions + "/" + faultsTestExecutionsFiles[i].getName());
				String faultsTestExecutionsFileName = faultsTestExecutionsFile.getName();

				if (faultsTestExecutionsFileName.contains("length")) {
					//System.out.println(faultsTestExecutionsFileName);
					List<String> row = new LinkedList<String>();
					String prefix1 = "em-";
					productID += faultsTestExecutionsFileName.substring(prefix1.length(), prefix1.length() + 2);
					row.add(productID);
					String prefix2 = "em-02-";
					scenarioID += faultsTestExecutionsFileName.substring(prefix2.length(), prefix2.length() + 2);
					row.add(scenarioID);
					String prefix3 = "em-02-04_rrg-1seq-tb_length";
					testSetNumber += faultsTestExecutionsFileName.substring(prefix3.length(), prefix3.length() + 1);
					String prefix4 = "em-02-04_rrg-1seq-tb_length2-";
					testSet += faultsTestExecutionsFileName.substring(prefix4.length(), prefix4.length() + 2);

					if (testSet.equalsIgnoreCase("in")) {
						row.add("inc(" + testSetNumber + ")");
					} else if (testSet.equalsIgnoreCase("sm")) {
						row.add("sm(" + testSetNumber + ")");
					}
					
					List<String> lineList = new LinkedList<String>();
					try (BufferedReader br = new BufferedReader(new FileReader(faultsTestExecutionsFile))) {
						String line;
						try {
							while ((line = br.readLine()) != null) {
								if(!line.equals(""))
									lineList.add(line);
							}
						} catch (IOException e) {

							e.printStackTrace();
						}
					} catch (FileNotFoundException e) {

						e.printStackTrace();
					} catch (IOException e1) {

						e1.printStackTrace();
					}
					
					String lastLine = lineList.get(lineList.size() - 1);
					//System.out.println(lastLine);
					
					String eventsExecuted = lastLine.substring(0,lastLine.indexOf("\t"));
					//System.out.println("Events executed " + eventsExecuted);
					row.add(eventsExecuted.trim());
					
					String faultsRevealed = lastLine.substring(lastLine.indexOf("\t"), lastLine.indexOf("\t") + 4);
					//System.out.println("FaultsRevealed " + faultsRevealed.trim());
					row.add(faultsRevealed.trim());
					
					for (String s : row) {
						System.out.print(s + " ");
					}
					System.out.println();

					rows.add(row);
				}
			}
		}
		
	
		FileWriter csvWriter = null;
		try {
			csvWriter = new FileWriter("files/Cases/EmailPL/table6 part2" + ".csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		csvWriter.append("PUC ID");
		csvWriter.append(",");
		csvWriter.append("Scenario ID");
		csvWriter.append(",");
		csvWriter.append("Test Set");
		csvWriter.append(",");
		csvWriter.append("Events Executed");
		csvWriter.append(",");
		csvWriter.append("Faults Revealed");
		csvWriter.append(",");
		csvWriter.append("\n");
		
		for (List<String> rowData : rows) {
		    csvWriter.append(String.join(",", rowData));
		    csvWriter.append("\n");
		}

		csvWriter.flush();
		csvWriter.close();

	}
}
