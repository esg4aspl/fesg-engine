package tr.edu.iyte.fesg.cases.bankaccount;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import tr.edu.iyte.esg.conversion.mxe.MXEFiletoESGConverter;
import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.esg.testgeneration.TestSuite;
import tr.edu.iyte.esg.testgeneration.TestSuiteGenerator;
import tr.edu.iyte.fesg.cases.CaseStudyFilePathCreationUtilities;
import tr.edu.iyte.fesg.cases.TestCoverageAnalysingUtilities;


/**
 * productID = 1 -> bankAccountProduct_baseProduct productID = 2 ->
 * bankAccountProduct_cancellable productID = 3 -> bankAccountProduct_credit
 * productID = 4 -> bankAccountProduct_dailyLimit productID = 5 ->
 * bankAccountProduct_interest productID = 6 -> bankAccountProduct_overdraft
 * productID = 7 -> bankAccountProduct_creditWithDailyLimit productID = 8 ->
 * bankAccountProduct_overdraftWithInterest
 * 
 * initialize productID to build different products
 * 
 * @param args
 */
public class BankAccountTestGenerationApp extends TestCoverageAnalysingUtilities {

	private static String filePath = "files/MXEFiles/BankAccountPL/";

	public static void main(String[] args) {
		productID = 7;
		coverageLenght = 9;
		BankAccountCaseStudyUtilities.buildProductModel();

		String fatureESGFileName = "interestEstimation";

		ESG ESG = null;
		try {
			ESG = MXEFiletoESGConverter.parseMXEFileForESGSimpleCreation(
					filePath + fatureESGFileName + CaseStudyFilePathCreationUtilities.mxeFileExtension);
		} catch (SAXException | IOException | ParserConfigurationException e) {

			e.printStackTrace();
		}
		System.out.println(fatureESGFileName);
		System.out.println("Number of vertices:" + ESG.getVertexList().size());
		System.out.println("Number of edges:" + ESG.getEdgeList().size());

		TestSuiteGenerator testSuiteGenerator = new TestSuiteGenerator();

		double startTime1 = System.nanoTime();
		TestSuite testSuite = testSuiteGenerator.generateTestSuite(numberOfTransformations, ESG);
		double stopTime1 = System.nanoTime();
		double timeElapsed1 = stopTime1 - startTime1;
		System.out.println("Execution time of test generation in miliseconds  : " + timeElapsed1 / (double) 1000000); // System.out.println(testSuite);

		writeTestSequencesToFile(testSuite.getCompleteEventSequences());
	}

}
