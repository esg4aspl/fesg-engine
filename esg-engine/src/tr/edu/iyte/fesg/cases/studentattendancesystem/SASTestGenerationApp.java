package tr.edu.iyte.fesg.cases.studentattendancesystem;

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


public class SASTestGenerationApp extends TestCoverageAnalysingUtilities  {
	
	private static String filePath = "files/MXEFiles/StudentAttendanceSystemPL/";
	/**
	 * productID = 1 -> sasProduct-studentUserBarcodeSMS
	 * productID = 2 -> sasProduct-teacherUserAccessCardEmail
	 * productID = 3 -> sasProduct-limitedStudentUserBarcodeSMS
	 * productID = 4 -> sasProduct-limitedteacherUserAccessCardEmail
	 * productID = 5 -> sasProduct-limitedteacherUserFingerprintEmail
	 * productID = 6 -> sasProduct-limitedTeacherUserQRCodeSMS
	 * productID = 7 -> sasProduct-bothUsersAccessCardEmail
	 * productID = 8 -> sasProduct-bothUsersBarcodeSMS
	 * productID = 9 -> sasProduct-bothUsersFingerprintEmail
	 * productID = 10 -> sasProduct-bothUsersQRCodeSMS
	 * productID = 11 -> sasProduct-substituteTeacherUserAccessCardEmail
	 * productID = 12 -> sasProduct-deputyHeadTeacherUserAccessCardEmail
	 * productID = 13 -> sasProduct-seniorSubstituteTeacherUserAccessCardEmail
	 * 
	 * 
	 * initialize productID to build different products
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		productID =7;
		coverageLenght = 8;
		StudentAttendanceSystemCaseStudyUtilities.buildProductModel();
		
		String fileName = productESGName.toString();
		//System.out.println(productESGName);
		//String fileName = "assignNewSchedule";
		
		ESG ESG = null;
		
		//String f = filePath + fileName + CaseStudyFilePathCreationUtilities.mxeFileExtension;
		try {
			ESG = MXEFiletoESGConverter.parseMXEFileForESGSimpleCreation(filePath + fileName + CaseStudyFilePathCreationUtilities.mxeFileExtension);
			//ESG = MXEFiletoESGConverter.parseMXEFileForESGSimpleCreation(f);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(fileName);
		System.out.println("Number of vertices:" + ESG.getVertexList().size());
		System.out.println("Number of edges:" + ESG.getEdgeList().size());
		
		TestSuiteGenerator testSuiteGenerator = new TestSuiteGenerator();

		double startTime1 = System.nanoTime();
		
		TestSuite testSuite = testSuiteGenerator.generateTestSuite(numberOfTransformations,ESG);
		
		double stopTime1 = System.nanoTime();
		double timeElapsed1 = stopTime1 - startTime1;
		System.out.println("Execution time of test generation in miliseconds  : "
				+ timeElapsed1 / (double) 1000000);
		

//		System.out.println(testSuite);
		
		writeTestSequencesToFile(testSuite.getCompleteEventSequences());
		CompositionTimeMeasurementWriter.writeTimeMeasurement(filePath, fileName, "SM",coverageLenght ,timeElapsed1 / (double) 1000000);

	}

}
