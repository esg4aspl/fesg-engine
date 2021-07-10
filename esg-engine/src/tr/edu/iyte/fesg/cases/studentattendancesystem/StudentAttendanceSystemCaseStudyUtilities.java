package tr.edu.iyte.fesg.cases.studentattendancesystem;

import tr.edu.iyte.fesg.cases.CaseStudyFilePathCreationUtilities;

public class StudentAttendanceSystemCaseStudyUtilities extends CaseStudyFilePathCreationUtilities {
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
	 * initialize productID to build different products
	 * 
	 * @param args
	 */
	public static void buildProductModel() {
		createCaseStudyFilePathObjects();
		switch (productID) {
		case 1: {
			initializeCaseStudyFolderNames();
			studentUserBarcodeSMS();
			break;
		}
		case 2: {
			initializeCaseStudyFolderNames();
			teacherUserAccessCardEmail();
			break;
		}
		case 3: {
			initializeCaseStudyFolderNames();
			limitedStudentUserBarcodeSMS();
			break;
		}
		case 4: {
			initializeCaseStudyFolderNames();
			limitedTeacherUserAccessCardEmail();
			break;
		}
		case 5: {
			initializeCaseStudyFolderNames();
			limitedteacherUserFingerprintEmail();
			break;
		}
		case 6: {
			initializeCaseStudyFolderNames();
			limitedTeacherUserQRCodeSMS();
			break;
		}
		case 7: {
			initializeCaseStudyFolderNames();
			bothUsersAccessCardEmail();
			break;
		}
		case 8: {
			initializeCaseStudyFolderNames();
			bothUsersBarcodeSMS();
			break;
		}
		case 9: {
			initializeCaseStudyFolderNames();
			bothUsersFingerprintEmail();
			break;
		}
		case 10: {
			initializeCaseStudyFolderNames();
			bothUsersQRCodeSMS();
			break;
		}
		case 11: {
			initializeCaseStudyFolderNames();
			substituteTeacherUserAccessCardEmail();
			break;
		}
		case 12: {
			initializeCaseStudyFolderNames();
			deputyHeadTeacherUserAccessCardEmail();
			break;
		}case 13: {
			initializeCaseStudyFolderNames();
			seniorSubstituteTeacherUserAccessCardEmail();
			break;
		}
		}
	}
	
	public static void initializeCaseStudyFolderNames() {
		esgFolderName.append("files/Cases/StudentAttendanceSystemPL");
		dotFolderName.append("files/DOTFiles/StudentAttendanceSystemPL");
	}
	
	public static void studentUserBarcodeSMS() {
		int numberOfFeatures = 8;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("sasProduct-studentUserBarcodeSMS");

		esgFileName[0] = "core_usefull.mxe";
		esgFileName[1] = "barcode.mxe";
		esgFileName[2] = "SMS.mxe";
		esgFileName[3] = "studentAccess.mxe";
		esgFileName[4] = "viewRecord.mxe";
		esgFileName[5] = "monitorAttendanceStatus.mxe";
		esgFileName[6] = "viewClass.mxe";
		esgFileName[7] = "viewSchedule.mxe";
		

		
		esgName[0] = "core";
		esgName[1] = "barcode";
		esgName[2] = "SMS";
		esgName[3] = "studentAccess";
		esgName[4] = "viewRecord";
		esgName[5] = "monitorAttendanceStatus";
		esgName[6] = "viewClass";
		esgName[7] = "viewSchedule";
		
	}
	
	public static void teacherUserAccessCardEmail() {
		int numberOfFeatures = 14;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("sasProduct-teacherUserAccessCardEmail");

		esgFileName[0] = "core_usefull.mxe";
		esgFileName[1] = "accessCard.mxe";
		esgFileName[2] = "email.mxe";
		esgFileName[3] = "teacherAccess.mxe";
		esgFileName[4] = "updateRecord.mxe";
		esgFileName[5] = "traceAttendanceActivity.mxe";
		esgFileName[6] = "viewClass.mxe";
		esgFileName[7] = "addNewClass.mxe";
		esgFileName[8] = "updateClassDetail.mxe";
		esgFileName[9] = "deleteClass.mxe";
		esgFileName[10] = "viewSchedule.mxe";
		esgFileName[11] = "addNewSchedule.mxe";
		esgFileName[12] = "editSchedule.mxe";
		esgFileName[13] = "assignNewSchedule.mxe";

		
		esgName[0] = "core";
		esgName[1] = "accessCard";
		esgName[2] = "email";
		esgName[3] = "teacherAccess";
		esgName[4] = "updateRecord";
		esgName[5] = "traceAttendanceActivity";
		esgName[6] = "viewClass";
		esgName[7] = "addNewClass";
		esgName[8] = "updateClassDetail";
		esgName[9] = "deleteClass";
		esgName[10] = "viewSchedule";
		esgName[11] = "addNewSchedule";
		esgName[12] = "editSchedule";
		esgName[13] = "assignNewSchedule";

	}
	
	public static void limitedStudentUserBarcodeSMS() {
		int numberOfFeatures = 7;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("sasProduct-limitedStudentUserBarcodeSMS");

		esgFileName[0] = "core_usefull.mxe";
		esgFileName[1] = "barcode.mxe";
		esgFileName[2] = "SMS.mxe";
		esgFileName[3] = "studentAccess.mxe";
		esgFileName[4] = "viewRecord.mxe";
		esgFileName[5] = "viewClass.mxe";
		esgFileName[6] = "viewSchedule.mxe";
		
		esgName[0] = "core";
		esgName[1] = "barcode";
		esgName[2] = "SMS";
		esgName[3] = "studentAccess";
		esgName[4] = "viewRecord";
		esgName[5] = "viewClass";
		esgName[6] = "viewSchedule";
		
	}
	
	public static void limitedTeacherUserAccessCardEmail() {
		int numberOfFeatures = 11;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("sasProduct-limitedteacherUserAccessCardEmail");

		esgFileName[0] = "core_usefull.mxe";
		esgFileName[1] = "accessCard.mxe";
		esgFileName[2] = "email.mxe";
		esgFileName[3] = "teacherAccess.mxe";
		esgFileName[4] = "updateRecord.mxe";
		esgFileName[5] = "traceAttendanceActivity.mxe";
		esgFileName[6] = "viewClass.mxe";
		esgFileName[7] = "addNewClass.mxe";
		esgFileName[8] = "updateClassDetail.mxe";
		esgFileName[9] = "deleteClass.mxe";
		esgFileName[10] = "viewSchedule.mxe";
		
		esgName[0] = "core";
		esgName[1] = "accessCard";
		esgName[2] = "email";
		esgName[3] = "teacherAccess";
		esgName[4] = "updateRecord";
		esgName[5] = "traceAttendanceActivity";
		esgName[6] = "viewClass";
		esgName[7] = "addNewClass";
		esgName[8] = "updateClassDetail";
		esgName[9] = "deleteClass";
		esgName[10] = "viewSchedule";

	}
	
	public static void limitedteacherUserFingerprintEmail() {
		int numberOfFeatures = 11;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("sasProduct-limitedteacherUserFingerprintEmail");

		esgFileName[0] = "core_usefull.mxe";
		esgFileName[1] = "fingerprint.mxe";
		esgFileName[2] = "email.mxe";
		esgFileName[3] = "teacherAccess.mxe";
		esgFileName[4] = "updateRecord.mxe";
		esgFileName[5] = "traceAttendanceActivity.mxe";
		esgFileName[6] = "viewClass.mxe";
		esgFileName[7] = "addNewClass.mxe";
		esgFileName[8] = "updateClassDetail.mxe";
		esgFileName[9] = "deleteClass.mxe";
		esgFileName[10] = "viewSchedule.mxe";
		
		esgName[0] = "core";
		esgName[1] = "fingerprint";
		esgName[2] = "email";
		esgName[3] = "teacherAccess";
		esgName[4] = "updateRecord";
		esgName[5] = "traceAttendanceActivity";
		esgName[6] = "viewClass";
		esgName[7] = "addNewClass";
		esgName[8] = "updateClassDetail";
		esgName[9] = "deleteClass";
		esgName[10] = "viewSchedule";

	}
	
	public static void limitedTeacherUserQRCodeSMS() {
		int numberOfFeatures = 11;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("sasProduct-limitedTeacherUserQRCodeSMS");

		esgFileName[0] = "core_usefull.mxe";
		esgFileName[1] = "QRCode.mxe";
		esgFileName[2] = "SMS.mxe";
		esgFileName[3] = "teacherAccess.mxe";
		esgFileName[4] = "updateRecord.mxe";
		esgFileName[5] = "traceAttendanceActivity.mxe";
		esgFileName[6] = "viewClass.mxe";
		esgFileName[7] = "addNewClass.mxe";
		esgFileName[8] = "updateClassDetail.mxe";
		esgFileName[9] = "deleteClass.mxe";
		esgFileName[10] = "viewSchedule.mxe";
		
		esgName[0] = "core";
		esgName[1] = "QRCode";
		esgName[2] = "SMS";
		esgName[3] = "teacherAccess";
		esgName[4] = "updateRecord";
		esgName[5] = "traceAttendanceActivity";
		esgName[6] = "viewClass";
		esgName[7] = "addNewClass";
		esgName[8] = "updateClassDetail";
		esgName[9] = "deleteClass";
		esgName[10] = "viewSchedule";

	}
	
	public static void bothUsersAccessCardEmail() {
		int numberOfFeatures = 17;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("sasProduct-bothUsersAccessCardEmail");

		esgFileName[0] = "core_usefull.mxe";
		esgFileName[1] = "accessCard.mxe";
		esgFileName[2] = "email.mxe";
		esgFileName[3] = "studentAccess.mxe";
		esgFileName[4] = "teacherAccess.mxe";
		esgFileName[5] = "viewRecord.mxe";
		esgFileName[6] = "updateRecord.mxe";
		esgFileName[7] = "monitorAttendanceStatus.mxe";
		esgFileName[8] = "traceAttendanceActivity.mxe";
		esgFileName[9] = "viewClass.mxe";
		esgFileName[10] = "addNewClass.mxe";
		esgFileName[11] = "updateClassDetail.mxe";
		esgFileName[12] = "deleteClass.mxe";
		esgFileName[13] = "viewSchedule.mxe";
		esgFileName[14] = "addNewSchedule.mxe";
		esgFileName[15] = "editSchedule.mxe";
		esgFileName[16] = "assignNewSchedule.mxe";

		esgName[0] = "core";
		esgName[1] = "accessCard";
		esgName[2] = "email";
		esgName[3] = "studentAccess";
		esgName[4] = "teacherAccess";
		esgName[5] = "viewRecord";
		esgName[6] = "updateRecord";
		esgName[7] = "monitorAttendanceStatus";
		esgName[8] = "traceAttendanceActivity";
		esgName[9] = "viewClass";
		esgName[10] = "addNewClass";
		esgName[11] = "updateClassDetail";
		esgName[12] = "deleteClass";
		esgName[13] = "viewSchedule";
		esgName[14] = "addNewSchedule";
		esgName[15] = "editSchedule";
		esgName[16] = "assignNewSchedule";

	}
	
	public static void bothUsersBarcodeSMS() {
		int numberOfFeatures = 17;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("sasProduct-bothUsersBarcodeSMS");

		esgFileName[0] = "core_usefull.mxe";
		esgFileName[1] = "barcode.mxe";
		esgFileName[2] = "SMS.mxe";
		esgFileName[3] = "studentAccess.mxe";
		esgFileName[4] = "teacherAccess.mxe";
		esgFileName[5] = "viewRecord.mxe";
		esgFileName[6] = "updateRecord.mxe";
		esgFileName[7] = "monitorAttendanceStatus.mxe";
		esgFileName[8] = "traceAttendanceActivity.mxe";
		esgFileName[9] = "viewClass.mxe";
		esgFileName[10] = "addNewClass.mxe";
		esgFileName[11] = "updateClassDetail.mxe";
		esgFileName[12] = "deleteClass.mxe";
		esgFileName[13] = "viewSchedule.mxe";
		esgFileName[14] = "addNewSchedule.mxe";
		esgFileName[15] = "editSchedule.mxe";
		esgFileName[16] = "assignNewSchedule.mxe";
		
		esgName[0] = "core";
		esgName[1] = "barcode";
		esgName[2] = "SMS";
		esgName[3] = "studentAccess";
		esgName[4] = "teacherAccess";
		esgName[5] = "viewRecord";
		esgName[6] = "updateRecord";
		esgName[7] = "monitorAttendanceStatus";
		esgName[8] = "traceAttendanceActivity";
		esgName[9] = "viewClass";
		esgName[10] = "addNewClass";
		esgName[11] = "updateClassDetail";
		esgName[12] = "deleteClass";
		esgName[13] = "viewSchedule";
		esgName[14] = "addNewSchedule";
		esgName[15] = "editSchedule";
		esgName[16] = "assignNewSchedule";

	}
	
	public static void bothUsersFingerprintEmail() {
		int numberOfFeatures = 17;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("sasProduct-bothUsersFingerprintEmail");

		esgFileName[0] = "core_usefull.mxe";
		esgFileName[1] = "fingerprint.mxe";
		esgFileName[2] = "email.mxe";
		esgFileName[3] = "studentAccess.mxe";
		esgFileName[4] = "teacherAccess.mxe";
		esgFileName[5] = "viewRecord.mxe";
		esgFileName[6] = "updateRecord.mxe";
		esgFileName[7] = "monitorAttendanceStatus.mxe";
		esgFileName[8] = "traceAttendanceActivity.mxe";
		esgFileName[9] = "viewClass.mxe";
		esgFileName[10] = "addNewClass.mxe";
		esgFileName[11] = "updateClassDetail.mxe";
		esgFileName[12] = "deleteClass.mxe";
		esgFileName[13] = "viewSchedule.mxe";
		esgFileName[14] = "addNewSchedule.mxe";
		esgFileName[15] = "editSchedule.mxe";
		esgFileName[16] = "assignNewSchedule.mxe";
		
		esgName[0] = "core";
		esgName[1] = "fingerprint";
		esgName[2] = "email";
		esgName[3] = "studentAccess";
		esgName[4] = "teacherAccess";
		esgName[5] = "viewRecord";
		esgName[6] = "updateRecord";
		esgName[7] = "monitorAttendanceStatus";
		esgName[8] = "traceAttendanceActivity";
		esgName[9] = "viewClass";
		esgName[10] = "addNewClass";
		esgName[11] = "updateClassDetail";
		esgName[12] = "deleteClass";
		esgName[13] = "viewSchedule";
		esgName[14] = "addNewSchedule";
		esgName[15] = "editSchedule";
		esgName[16] = "assignNewSchedule";

	}
	
	public static void bothUsersQRCodeSMS() {
		int numberOfFeatures = 17;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("sasProduct-bothUsersQRCodeSMS");

		esgFileName[0] = "core_usefull.mxe";
		esgFileName[1] = "QRCode.mxe";
		esgFileName[2] = "SMS.mxe";
		esgFileName[3] = "studentAccess.mxe";
		esgFileName[4] = "teacherAccess.mxe";
		esgFileName[5] = "viewRecord.mxe";
		esgFileName[6] = "updateRecord.mxe";
		esgFileName[7] = "monitorAttendanceStatus.mxe";
		esgFileName[8] = "traceAttendanceActivity.mxe";
		esgFileName[9] = "viewClass.mxe";
		esgFileName[10] = "addNewClass.mxe";
		esgFileName[11] = "updateClassDetail.mxe";
		esgFileName[12] = "deleteClass.mxe";
		esgFileName[13] = "viewSchedule.mxe";
		esgFileName[14] = "addNewSchedule.mxe";
		esgFileName[15] = "editSchedule.mxe";
		esgFileName[16] = "assignNewSchedule.mxe";
		
		esgName[0] = "core";
		esgName[1] = "QRCode";
		esgName[2] = "SMS";
		esgName[3] = "studentAccess";
		esgName[4] = "teacherAccess";
		esgName[5] = "viewRecord";
		esgName[6] = "updateRecord";
		esgName[7] = "monitorAttendanceStatus";
		esgName[8] = "traceAttendanceActivity";
		esgName[9] = "viewClass";
		esgName[10] = "addNewClass";
		esgName[11] = "updateClassDetail";
		esgName[12] = "deleteClass";
		esgName[13] = "viewSchedule";
		esgName[14] = "addNewSchedule";
		esgName[15] = "editSchedule";
		esgName[16] = "assignNewSchedule";

	}
	
	public static void substituteTeacherUserAccessCardEmail() {
		int numberOfFeatures = 9;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("sasProduct-substituteTeacherUserAccessCardEmail");

		esgFileName[0] = "core_usefull.mxe";
		esgFileName[1] = "accessCard.mxe";
		esgFileName[2] = "email.mxe";
		esgFileName[3] = "teacherAccess.mxe";
		esgFileName[4] = "updateRecord.mxe";
		esgFileName[5] = "traceAttendanceActivity.mxe";
		esgFileName[6] = "viewClass.mxe";
		esgFileName[7] = "viewSchedule.mxe";
		esgFileName[8] = "addNewClass.mxe";
		
		esgName[0] = "core";
		esgName[1] = "accessCard";
		esgName[2] = "email";
		esgName[3] = "teacherAccess";
		esgName[4] = "updateRecord";
		esgName[5] = "traceAttendanceActivity";
		esgName[6] = "viewClass";
		esgName[7] = "viewSchedule";
		esgName[8] = "addNewClass";

		
	}
	
	
	public static void deputyHeadTeacherUserAccessCardEmail() {
		int numberOfFeatures = 12;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("sasProduct-deputyHeadTeacherUserAccessCardEmail");

		esgFileName[0] = "core_usefull.mxe";
		esgFileName[1] = "accessCard.mxe";
		esgFileName[2] = "email.mxe";
		esgFileName[3] = "teacherAccess.mxe";
		esgFileName[4] = "updateRecord.mxe";
		esgFileName[5] = "traceAttendanceActivity.mxe";
		esgFileName[6] = "viewClass.mxe";
		esgFileName[7] = "addNewClass.mxe";
		esgFileName[8] = "updateClassDetail.mxe";
		esgFileName[9] = "viewSchedule.mxe";
		esgFileName[10] = "addNewSchedule.mxe";
		esgFileName[11] = "editSchedule.mxe";
		
		esgName[0] = "core";
		esgName[1] = "accessCard";
		esgName[2] = "email";
		esgName[3] = "teacherAccess";
		esgName[4] = "updateRecord";
		esgName[5] = "traceAttendanceActivity";
		esgName[6] = "viewClass";
		esgName[7] = "addNewClass";
		esgName[8] = "updateClassDetail";
		esgName[9] = "viewSchedule";
		esgName[10] = "addNewSchedule";
		esgName[11] = "editSchedule";

	}
	
	public static void seniorSubstituteTeacherUserAccessCardEmail() {
		int numberOfFeatures = 10;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("sasProduct-seniorSubstituteTeacherUserAccessCardEmail");

		esgFileName[0] = "core_usefull.mxe";
		esgFileName[1] = "accessCard.mxe";
		esgFileName[2] = "email.mxe";
		esgFileName[3] = "teacherAccess.mxe";
		esgFileName[4] = "updateRecord.mxe";
		esgFileName[5] = "traceAttendanceActivity.mxe";
		esgFileName[6] = "viewClass.mxe";
		esgFileName[7] = "viewSchedule.mxe";
		esgFileName[8] = "addNewClass.mxe";
		esgFileName[9] = "updateClassDetail.mxe";
		
		esgName[0] = "core";
		esgName[1] = "accessCard";
		esgName[2] = "email";
		esgName[3] = "teacherAccess";
		esgName[4] = "updateRecord";
		esgName[5] = "traceAttendanceActivity";
		esgName[6] = "viewClass";
		esgName[7] = "viewSchedule";
		esgName[8] = "addNewClass";
		esgName[9] = "updateClassDetail";

	}
	

}
