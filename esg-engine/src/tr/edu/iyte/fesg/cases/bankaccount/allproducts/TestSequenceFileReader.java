package tr.edu.iyte.fesg.cases.bankaccount.allproducts;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import tr.edu.iyte.esg.coverageanalysis.TestSequenceCoverageAnalyser;

public class TestSequenceFileReader {

	public static void main(String[] args) throws IOException {
		String incFolderPath = "files/Cases/BankAccountPL/Results_IncrementalTestSequenceComposition/TestSequences";
		String smFolderPath = "files/Cases/BankAccountPL/Results_SingleModel/TestSequences";

		File incfolder = new File(incFolderPath);
		File smFolder = new File(smFolderPath);

		File[] inclistOfFiles = incfolder.listFiles();
		File[] smlistOfFiles = smFolder.listFiles();
		System.out.println(inclistOfFiles.length);
		System.out.println(smlistOfFiles.length);
		List<List<String>> rows = new LinkedList<List<String>>();

		for (int i = 0; i < inclistOfFiles.length; i++) {
			String testSetNumber = "";
			String scenarioID = "";
			if (inclistOfFiles[i].isFile()) {
				File incFile = new File(incFolderPath + "/" + inclistOfFiles[i].getName());
				String incCurrentFileName = incFile.getName();
				List<String> incRow = new LinkedList<String>();
				String postfix1 = "-INC_length2.txt";
				scenarioID += incCurrentFileName.substring(incCurrentFileName.length() - postfix1.length() - 2,
						incCurrentFileName.length() - postfix1.length());
				incRow.add(scenarioID);
				String postfix2 = ".txt";
				testSetNumber += incCurrentFileName.substring(incCurrentFileName.length() - postfix2.length() - 1,
						incCurrentFileName.length() - postfix2.length());
				incRow.add("inc(" + testSetNumber + ")");
				List<String> incTestSequences = TestSequenceCoverageAnalyser
						.testCasesFromFile(incFolderPath + "/" + inclistOfFiles[i].getName());
				incRow.add(Integer.toString(incTestSequences.size()));
				int numberOfEvents = TestSequenceCoverageAnalyser.numberOfEvents(incTestSequences);
				incRow.add(Integer.toString(numberOfEvents));
				System.out.println("INC " + scenarioID + " length " + testSetNumber);
				rows.add(incRow);

			}

			if (smlistOfFiles[i].isFile()) {
				List<String> smRow = new LinkedList<String>();
				smRow.add(scenarioID);
				smRow.add("sm(" + testSetNumber + ")");
				List<String> smTestSequences = TestSequenceCoverageAnalyser
						.testCasesFromFile(smFolderPath + "/" + smlistOfFiles[i].getName());
				smRow.add(Integer.toString(smTestSequences.size()));
				int smnumberOfEvents = TestSequenceCoverageAnalyser.numberOfEvents(smTestSequences);
				smRow.add(Integer.toString(smnumberOfEvents));
				rows.add(smRow);
			}
		}

		FileWriter csvWriter = null;
		try {
			csvWriter = new FileWriter("files/Cases/BankAccountPL/table5 part1" + ".csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		csvWriter.append("Scenario ID");
		csvWriter.append(",");
		csvWriter.append("Test Set");
		csvWriter.append(",");
		csvWriter.append("Sequence Number");
		csvWriter.append(",");
		csvWriter.append("Total Length");
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
