package tr.edu.iyte.fesg.cases.elevator;

import java.io.IOException;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import tr.edu.iyte.esg.conversion.dot.ESGToDOTFileConverter;
import tr.edu.iyte.esg.conversion.mxe.MXEFiletoESGConverter;
import tr.edu.iyte.esg.eventsequence.EventSequence;
import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.esg.testgeneration.TestSuiteGenerator;
import tr.edu.iyte.fesg.cases.TestCoverageAnalysingUtilities;

public class ElevatorProductESGMXEFileToDotFile extends TestCoverageAnalysingUtilities {
	
	/**
	 * productID = 1 -> elevatorProduct_baseProduct
	 * productID = 2 -> elevatorProduct_weight
	 * productID = 3 -> elevatorProduct_weightExecutiveFloor
	 * productID = 4 -> elevatorProduct_weightOverloaded
	 * productID = 5 -> elevatorProduct_fullProduct 
	 * initialize productID to build different products
	 * 
	 * @param args
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	
	private static String files = "files/";
	private static String subFolderPL = "ElevatorPL/";
	private static String subFolderCases = "Cases/";
	
	private static String subFolderDOT = "DOTFiles/";
	private static String dotFileExtension = ".dot";
	
	//private static String subFolderTestCases = "ProductESGTestCases/";
	
	
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		String esgFileName = "elevatorProduct_fullProduct";
		int esgID = 5;

		String mxefilePath = files + subFolderCases + subFolderPL + esgFileName +  mxeFileExtension;
		String dotfilePath = files + subFolderDOT + subFolderPL + esgFileName +  dotFileExtension;
		
		ESG ESG = MXEFiletoESGConverter.parseMXEFileForESGSimpleCreation(mxefilePath, esgFileName, esgID);
		
		ESGToDOTFileConverter.buildDOTFileFromESG(ESG, dotfilePath);
		
		TestSuiteGenerator testSuiteGenerator = new TestSuiteGenerator();
		Set<EventSequence> cesSet = testSuiteGenerator.generateTestSuite(ESG).getCompleteEventSequences();
		createCaseStudyFilePathObjects();
		//String testCasePath = files + subFolderTestCases + subFolderPL + esgFileName +  ".txt";
		writeTestSequencesToFile(cesSet);

	}

}
