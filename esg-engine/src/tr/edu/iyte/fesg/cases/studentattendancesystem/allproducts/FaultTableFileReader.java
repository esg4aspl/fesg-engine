package tr.edu.iyte.fesg.cases.studentattendancesystem.allproducts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class FaultTableFileReader {
		
	public static void main(String[] args) throws IOException {
		String faultsTestExecutions = "files/Cases/StudentAttendanceSystemPL/1RegFiles/_spl_sas_3_faulttables-averagetestexecutions-20percent_1234567890";		
		File faultsTestExecutionsFolder = new File(faultsTestExecutions);

		File[] faultsTestExecutionsFiles = faultsTestExecutionsFolder.listFiles();
		//System.out.println(faultsTestExecutionsFiles.length);

		List<List<String>> rows = new LinkedList<List<String>>();
		
		for (int i = 0; i < faultsTestExecutionsFiles.length; i++) {

			String scenarioID = "";
			String productID = "";
			
			if (faultsTestExecutionsFiles[i].isFile()) {
				File faultsTestExecutionsFile = new File(
						faultsTestExecutions + "/" + faultsTestExecutionsFiles[i].getName());
				String faultsTestExecutionsFileName = faultsTestExecutionsFile.getName();

				if (!faultsTestExecutionsFileName.contains("avg")) {
					//System.out.println(faultsTestExecutionsFileName);
					List<String> row = new LinkedList<String>();
					String prefix1 = "sas-";
					productID += faultsTestExecutionsFileName.substring(prefix1.length(), prefix1.length() + 4);
					row.add(productID);
					String prefix2 = "sas-0123-";
					scenarioID += faultsTestExecutionsFileName.substring(prefix2.length(), prefix2.length() + 4);
					row.add(scenarioID);
					
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
					
					String totalFaultCountsLine = lineList.get(2);
					String m[] = totalFaultCountsLine.split("\t");
					row.add(m[0]);
					row.add(m[1]);
					row.add(m[2]);
					row.add(m[3]);
					
					//System.out.println(m[0] + " " + m[1] + " " + m[2] + " " + m[3]);
					
					String seededFaultCountsLine = lineList.get(5);
					//System.out.println(seededFaultCountsLine);
					String s[] = seededFaultCountsLine.split("\t");
					//System.out.println(s[4]);
					row.add(s[4]);
					
					/*
					for (String s : row) {
						System.out.print(s + " ");
					}
					System.out.println();
					*/
					rows.add(row);
				}
			}
		}
		
		
		FileWriter csvWriter = null;
		try {
			csvWriter = new FileWriter("files/Cases/StudentAttendanceSystemPL/table4-sas" + ".csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		csvWriter.append("PUC ID");
		csvWriter.append(",");
		csvWriter.append("Scenario ID");
		csvWriter.append(",");
		csvWriter.append("m=2");
		csvWriter.append(",");
		csvWriter.append("m=3");
		csvWriter.append(",");
		csvWriter.append("m=4");
		csvWriter.append(",");
		csvWriter.append("m=5");
		csvWriter.append(",");
		csvWriter.append("Number of Seeded Faults");
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
