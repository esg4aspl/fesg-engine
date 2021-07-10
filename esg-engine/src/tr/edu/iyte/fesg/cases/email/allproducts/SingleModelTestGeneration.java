package tr.edu.iyte.fesg.cases.email.allproducts;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Map;

import tr.edu.iyte.esg.conversion.json.JSONFileToESGConverter;
import tr.edu.iyte.esg.eventsequence.EventSequenceUtilties;
import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.esg.testgeneration.TestSuite;
import tr.edu.iyte.esg.testgeneration.TestSuiteGenerator;
import tr.edu.iyte.fesg.cases.email.EmailCaseStudyUtilities;
import tr.edu.iyte.fesg.cases.fileoperations.SPLResultsFilesRenamer;

public class SingleModelTestGeneration extends EmailCaseStudyUtilities {

	public static void main(String[] args) {
		createCaseStudyFilePathObjects();
		initializeCaseStudyFolderNames();
		coverageLenght = 2;

		Map<String, AbstractMap.SimpleEntry<Integer, Integer>> map = ResultFilesRenamer
				.getProductNameScenarioIDProductIDMap();
		SPLResultsFilesRenamer.setMaxScenarioIDMaxProductID(map);
		int scenarioIDDigit = SPLResultsFilesRenamer.getScenarioIDDigit();
		int productIDDigit = SPLResultsFilesRenamer.getProductIDDigit();

		for (String productName : map.keySet()) {

			String fileName = esgFolderName + "/allProductsJSONFiles/" + productName + ".json";

			int productID = map.get(productName).getValue();
			String productIDString = SPLResultsFilesRenamer.adjustIDLength(productID, productIDDigit);
			int scenarioID = map.get(productName).getKey();
			String scenarioIDString = SPLResultsFilesRenamer.adjustIDLength(scenarioID, scenarioIDDigit);

			String newName = "em-" + productIDString + "-" + scenarioIDString;

			ESG ESG = JSONFileToESGConverter.parseJSONFileForESGCreation(fileName);
			TestSuiteGenerator testSuiteGenerator = new TestSuiteGenerator();
			TestSuite testSuite = testSuiteGenerator.generateTestSuite(coverageLenght, ESG);

			try {
				EventSequenceUtilties.esgEventSequenceSetFileWriter(testSuite.getCompleteEventSequences(), esgFolderName
						+ "/Results_SingleModel/TestSequences/" + newName + "_length" + coverageLenght + ".txt");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			 * try {
			 * EventSequenceUtilities.esgEventSequenceSetFileWriterForMutationAnalysis(
			 * testSuite.getCompleteEventSequences(), esgFolderName +
			 * "/1RegFiles/sequences/SM/" + newName + "_rrg-1seq-tb_length" + coverageLenght
			 * + "-SM_sequences.txt"); } catch (IOException e) { // TODO Auto-generated
			 * catch block e.printStackTrace(); }
			 */
			// ESGToRRGFileConversion.convertESGToRRGFileWithoutPseudoEvents(ESG,esgFolderName
			// + "/1RegFiles/productmodels/" + newName);
		}

	}

}
