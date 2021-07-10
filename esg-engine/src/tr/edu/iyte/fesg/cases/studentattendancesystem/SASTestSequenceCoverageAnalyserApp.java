package tr.edu.iyte.fesg.cases.studentattendancesystem;

import java.util.Set;

import tr.edu.iyte.esg.eventsequence.EventSequence;
import tr.edu.iyte.fesg.cases.TestCoverageAnalysingUtilities;
import tr.edu.iyte.fesg.model.FeaturedESG;

public class SASTestSequenceCoverageAnalyserApp extends TestCoverageAnalysingUtilities {
	
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
	 * 
	 * 
	 * initialize productID to build different products
	 * 
	 * @param args
	 */
	
	public static void main(String[] args){
		productID = 11;
		coverageLenght = 2;
	
		
		StudentAttendanceSystemCaseStudyUtilities.buildProductModel();
		FeaturedESG featuredESG = buildFeaturedESG();
		Set<EventSequence> cesSet = composeSequencesFullyWithTimeMeasurement(featuredESG);
		
		writeFullTestCompositionResultsToFile(cesSet);
		analyseFullTestCompositionResultsCoverage(cesSet);
		
	}

}
