package tr.edu.iyte.fesg.cases.email;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import tr.edu.iyte.esg.conversion.mxe.MXEFiletoESGConverter;
import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.esg.testgeneration.TestSuite;
import tr.edu.iyte.esg.testgeneration.TestSuiteGenerator;
import tr.edu.iyte.fesg.cases.CaseStudyFilePathCreationUtilities;
import tr.edu.iyte.fesg.cases.TestCoverageAnalysingUtilities;
import tr.edu.iyte.fesg.cases.resultrecordingutilities.CompositionTimeMeasurementWriter;

public class EmailTestGenerationApp extends TestCoverageAnalysingUtilities {
	
	private static String filePath = "files/MXEFiles/EmailPL/";
	
	/**
	 * productID = 1 -> emailProduct_baseProduct
	 * productID = 2 -> emailProduct_addressbook
	 * productID = 3 -> emailProduct_addressbookAutoresponder
	 * productID = 4 -> emailProduct_addressbookAutoresponderEncrypt
	 * productID = 5 -> emailProduct_addressbookAutoresponderEncryptSign
	 * productID = 6 -> emailProduct_addressbookAutoresponderForward
	 * productID = 7 -> emailProduct_addressbookAutoresponderSign
	 * productID = 8 -> emailProduct_addressbookEncrypt
	 * productID = 9 -> emailProduct_autoresponder
	 * productID = 10 -> emailProduct_autoresponderEncrypt
	 * productID = 11 -> emailProduct_autoresponderForward
	 * productID = 12 -> emailProduct-encrypt
	 * productID = 13 -> emailProduct-forward
	 * 
	 * initialize productID to build different products
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) {
		productID = 4;
		coverageLenght = 8;

		EmailCaseStudyUtilities.buildProductModel();

		String fileName = "sign";
		ESG ESG = null;

		String f = filePath + fileName + CaseStudyFilePathCreationUtilities.mxeFileExtension;
		try {
			// ESG = MXEFiletoESGConverter.parseMXEFileForESGSimpleCreation(filePath +
			// fileName + CaseStudyFilePathCreationUtilities.mxeFileExtension);
			ESG = MXEFiletoESGConverter.parseMXEFileForESGSimpleCreation(f);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(fileName);
		System.out.println("Number of vertices:" + ESG.getVertexList().size());
		System.out.println("Number of edges:" + ESG.getEdgeList().size());

		TestSuiteGenerator testSuiteGenerator = new TestSuiteGenerator();

		double startTime1 = System.nanoTime();

		TestSuite testSuite = testSuiteGenerator.generateTestSuite(numberOfTransformations, ESG);

		double stopTime1 = System.nanoTime();
		double timeElapsed1 = stopTime1 - startTime1;
		System.out.println("Execution time of test generation in miliseconds  : " + timeElapsed1 / (double) 1000000);

		System.out.println(testSuite);

		writeTestSequencesToFile(testSuite.getCompleteEventSequences());
		CompositionTimeMeasurementWriter.writeTimeMeasurement(filePath, fileName, "SM", coverageLenght,
				timeElapsed1 / (double) 1000000);

	}

}
