package tr.edu.iyte.fesg.cases.studentattendancesystem.allproducts;



import tr.edu.iyte.esg.conversion.json.JSONFileToESGConverter;
import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.esg.testgeneration.TestSuite;
import tr.edu.iyte.esg.testgeneration.TestSuiteGenerator;
import tr.edu.iyte.fesg.cases.studentattendancesystem.StudentAttendanceSystemCaseStudyUtilities;


public class SingleModelTestGeneration extends StudentAttendanceSystemCaseStudyUtilities {

	public static void main(String[] args) {
		createCaseStudyFilePathObjects();
		initializeCaseStudyFolderNames();

		coverageLenght = 2;
		String productName = "viewClass_viewSchedule_SMS_teacherAccess_updateRecord_addNewClass_addNewSchedule_barcode";
		String fileName = esgFolderName + "/allProductsJSONFiles/" + productName + ".json";

		ESG ESG = JSONFileToESGConverter.parseJSONFileForESGCreation(fileName);
		TestSuiteGenerator testSuiteGenerator = new TestSuiteGenerator();
		TestSuite testSuite = testSuiteGenerator.generateTestSuite(coverageLenght, ESG);
		System.out.println(testSuite.toString());
	}

}
