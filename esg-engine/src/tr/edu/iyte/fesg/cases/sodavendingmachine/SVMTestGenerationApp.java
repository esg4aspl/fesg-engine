package tr.edu.iyte.fesg.cases.sodavendingmachine;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import tr.edu.iyte.esg.conversion.mxe.MXEFiletoESGConverter;
import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.esg.testgeneration.TestSuite;
import tr.edu.iyte.esg.testgeneration.TestSuiteGenerator;
import tr.edu.iyte.fesg.cases.CaseStudyFilePathCreationUtilities;
import tr.edu.iyte.fesg.cases.TestCoverageAnalysingUtilities;

public class SVMTestGenerationApp extends TestCoverageAnalysingUtilities {
		
		private static String filePath = "files/Cases/SodaVendingMachinePL/";
		/**
		 * productID = 1 -> svmProduct_payEUR
		 * productID = 2 -> svmProduct_payEURServeSoda
		 * productID = 3 -> svmProduct_free
		 * productID = 4 -> svmProduct_payUSD
		 * productID = 5 -> svmProduct_payUSDServeTea
		 * 
		 *initialize productID to build different products
		 */
		public static void main(String[] args) {
			productID = 3;
			coverageLenght = 4;
			
			SVMCaseStudyUtilities.buildProductModel();
			
			String fileName = productESGName.toString();
			//String fileName = "serveTea";
			ESG ESG = null;
			
			//String f = filePath + fileName + CaseStudyFilePathCreationUtilities.mxeFileExtension;
			try {
				ESG = MXEFiletoESGConverter.parseMXEFileForESGSimpleCreation(filePath + fileName + CaseStudyFilePathCreationUtilities.mxeFileExtension);
				//ESG = MXEFiletoESGConverter.parseMXEFileForESGSimpleCreation(f);
			} catch (SAXException | IOException | ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			TestSuiteGenerator testSuiteGenerator = new TestSuiteGenerator();
			
			double startTime1 = System.nanoTime();
			
			TestSuite testSuite = testSuiteGenerator.generateTestSuite(numberOfTransformations,ESG);
			
			double stopTime1 = System.nanoTime();
			double timeElapsed1 = stopTime1 - startTime1;
			System.out.println("Execution time of test generation in miliseconds  : "
					+ timeElapsed1 / (double) 1000000);
			
			System.out.println("Number of vertices:" + ESG.getRealVertexList().size());
			System.out.println("Number of edges:" + ESG.getRealEdgeList().size());
			System.out.println(testSuite);
			
			writeTestSequencesToFile(testSuite.getCompleteEventSequences());
			

		}

}
