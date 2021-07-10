package tr.edu.iyte.fesg.cases.studentattendancesystem.allproducts;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import tr.edu.iyte.esg.coverageanalysis.TestSequenceCoverageAnalyser;

public class ReusePercentageCalculator {

	public static void main(String[] args) throws IOException {
		String PUCFolder = "files/Cases/StudentAttendanceSystemPL/Results_IncrementalTestSequenceComposition/TestSequences";
		String existingProductFolder = "files/Cases/StudentAttendanceSystemPL/Results_IncrementalTestSequenceComposition/ExistingProductTestSequences";
		String singleModelFolder = "files/Cases/StudentAttendanceSystemPL/Results_SingleModel/TestSequences";

		File pucFolder = new File(PUCFolder);
		File epFolder = new File(existingProductFolder);
		File smFolder = new File(singleModelFolder);

		File[] puclistOfFiles = pucFolder.listFiles();
		File[] eplistOfFiles = epFolder.listFiles();
		File[] smlistOfFiles = smFolder.listFiles();

		System.out.println(puclistOfFiles.length);
		System.out.println(eplistOfFiles.length);
		System.out.println(smlistOfFiles.length);
		List<List<String>> rows = new LinkedList<List<String>>();

		for (int i = 0; i < puclistOfFiles.length; i++) {
			String scenarioID = "";
			String pucID = "";
			String epID = "";
			String covlength = "";

			List<String> epTestSequences = null;
			List<String> pucTestSequences = null;
			List<String> smTestSequences = null;
			int pucNumberOfEvents = 0;
			int epNumberOfEvents = 0;

			List<String> row = new LinkedList<String>();

			if (eplistOfFiles[i].isFile()) {
				File epFile = new File(PUCFolder + "/" + eplistOfFiles[i].getName());
				String epCurrentFileName = epFile.getName();
				System.out.println("epCurrentFileName " + epCurrentFileName);

				String prefix1 = "sas-";
				pucID += epCurrentFileName.substring(prefix1.length(), prefix1.length() + 4);
				
				String prefix2 = "sas-0002-";
				scenarioID += epCurrentFileName.substring(prefix2.length(), prefix2.length() + 4);
				
				String prefix3 = "sas-0002-0004-";
				epID += epCurrentFileName.substring(prefix3.length(), prefix3.length() + 4);
				
				String prefix4 = "sas-0002-0004-0001_length";
				covlength += epCurrentFileName.substring(prefix4.length(), prefix4.length() + 1);

				epTestSequences = TestSequenceCoverageAnalyser.testCasesFromFile(existingProductFolder + "/" + eplistOfFiles[i].getName());
				epNumberOfEvents = TestSequenceCoverageAnalyser.numberOfEvents(epTestSequences);
				
				row.add(scenarioID);
				row.add(epID);
				row.add(pucID);
				row.add("inc(" + covlength + ")");
				row.add(Integer.toString(epTestSequences.size()));
				row.add(Integer.toString(epNumberOfEvents));

			}
			if (puclistOfFiles[i].isFile()) {
				File pucFile = new File(PUCFolder + "/" + puclistOfFiles[i].getName());
				String pucCurrentFileName = pucFile.getName();
				System.out.println("pucCurrentFileName " + pucCurrentFileName);

				String prefix1 = "sas-";
				String pid = pucCurrentFileName.substring(prefix1.length(), prefix1.length() + 4);
				System.out.println();

				String prefix2 = "sas-0002-";
				String sid = pucCurrentFileName.substring(prefix2.length(), prefix2.length() + 4);
				
				String prefix3 = "sas-0002-0004-INC_length";
				String length = pucCurrentFileName.substring(prefix3.length(), prefix3.length() + 1);

				
				if (pucID.equals(pid) && scenarioID.equals(sid) && covlength.equals(length)) {
					pucTestSequences = TestSequenceCoverageAnalyser
							.testCasesFromFile(PUCFolder + "/" + puclistOfFiles[i].getName());
					pucNumberOfEvents = TestSequenceCoverageAnalyser.numberOfEvents(pucTestSequences);
					row.add(Integer.toString(pucTestSequences.size()));
					row.add(Integer.toString(pucNumberOfEvents));
				}
			}
			
			int commonSequences = 0;
			List<String> commonSequencesList = new LinkedList<String>();
			for(String epSequence : epTestSequences) {
				
				for(String pucSequence : pucTestSequences) {
					if(epSequence.equalsIgnoreCase(pucSequence)) {
						commonSequences += 1;
						commonSequencesList.add(epSequence);
					}
					
				}
			}
			int numberOfCommonSequences = TestSequenceCoverageAnalyser.numberOfEvents(commonSequencesList);
			row.add(Integer.toString(commonSequences));
			row.add(Integer.toString(numberOfCommonSequences));
			
			double percentage1 = ((double)((double)commonSequences / (double)pucTestSequences.size())) * 100;
			row.add(Double.toString(percentage1));
			
			double percentage2 = ((double)((double)commonSequences / (double)epTestSequences.size())) * 100;
			row.add(Double.toString(percentage2));
			
			int reusedEvents = findReuse(epTestSequences, pucTestSequences);
			double percentage3 = ((double) ((double) reusedEvents / (double) pucNumberOfEvents)) * 100;
			row.add(Double.toString(percentage3));

			double percentage4 = ((double) ((double) reusedEvents / (double) epNumberOfEvents)) * 100;
			row.add(Double.toString(percentage4));
			
			if (smlistOfFiles[i].isFile()) {
				File smFile = new File(smFolder + "/" + smlistOfFiles[i].getName());
				String smCurrentFileName = smFile.getName();

				String prefix1 = "sas-";
				String pid = smCurrentFileName.substring(prefix1.length(), prefix1.length() + 4);
				
				String prefix2 = "sas-0002-";
				String sid = smCurrentFileName.substring(prefix2.length(), prefix2.length() + 4);
				
				String prefix3 = "sas-0002-0004_length";
				String length = smCurrentFileName.substring(prefix3.length(), prefix3.length() + 1);

				
				if (pucID.equals(pid) && scenarioID.equals(sid) && covlength.equals(length)) {
					smTestSequences = TestSequenceCoverageAnalyser
							.testCasesFromFile(smFolder + "/" + smlistOfFiles[i].getName());
					int smNumberOfEvents = TestSequenceCoverageAnalyser.numberOfEvents(smTestSequences);
					row.add(Integer.toString(smTestSequences.size()));
					row.add(Integer.toString(smNumberOfEvents));
				}
			}
			
			
			
			//System.out.println(pucID + "-" + scenarioID + ": " + commonSequences + "/" + pucTestSequences.size() + " " + percentage);
			rows.add(row);

		}

		FileWriter csvWriter = null;
		try {
			csvWriter = new FileWriter("files/Cases/StudentAttendanceSystemPL/reuse" + ".csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		csvWriter.append("Scenario ID");
		csvWriter.append(",");
		csvWriter.append("Existing Product ID");
		csvWriter.append(",");
		csvWriter.append("PUC ID");
		csvWriter.append(",");
		csvWriter.append("Test Set");
		csvWriter.append(",");
		csvWriter.append("Existing Product Number of Sequences");
		csvWriter.append(",");
		csvWriter.append("Existing Product Number of Events");
		csvWriter.append(",");
		csvWriter.append("PUC Number of Sequences");
		csvWriter.append(",");
		csvWriter.append("PUC Number of Events");
		csvWriter.append(",");
		csvWriter.append("Number of Common Sequences");
		csvWriter.append(",");
		csvWriter.append("Number of Common Events");
		csvWriter.append(",");
		csvWriter.append("Test Sequences Reused in Test Set/PUC Number of Sequences");
		csvWriter.append(",");
		csvWriter.append("Test Sequences Reused in Test Set/Existing Product Number of Sequences");
		csvWriter.append(",");
		csvWriter.append("Events Reused in Test Sequences/PUC Number of Events");
		csvWriter.append(",");
		csvWriter.append("Events Reused in Test Sequences/Existing Product Number of Events");
		csvWriter.append(",");
		csvWriter.append("Single Model Number of Sequences");
		csvWriter.append(",");
		csvWriter.append("Single Model Number of Events");
		csvWriter.append(",");
		csvWriter.append("\n");

		for (List<String> rowData : rows) {
			csvWriter.append(String.join(",", rowData));
			csvWriter.append("\n");
		}

		csvWriter.flush();
		csvWriter.close();

	}
	
	private static int findReuse(List<String> epTestSequences, List<String> pucTestSequences) {
		int reuse = 0;
		for (String s : epTestSequences) {
			int length = findMaxPrefixLength(s, pucTestSequences);
			reuse = reuse + length;
			System.out.println("Resue " + reuse);
		}

		return reuse;
	}

	private static int findMaxPrefixLength(String s, List<String> pucTestSequences) {
		int max = 0;
System.out.println("EP s " + s);
		for (String t : pucTestSequences) {
System.out.println("   PUC t " + t);
			StringTokenizer st = new StringTokenizer(s, ":,");
			StringTokenizer tt = new StringTokenizer(t, ":,");
			st.nextToken(); //skip the part before :
			tt.nextToken(); //skip the part before :
			int i = 0;
			while(st.hasMoreTokens() && tt.hasMoreTokens() && (st.nextToken().compareTo(tt.nextToken())==0)) {
				i = i + 1;
			}
System.out.println("   -> " + i);
			if(i > max) {
				max = i;
			}
		}

		return max;
	}
}
