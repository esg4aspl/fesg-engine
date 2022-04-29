package tr.edu.iyte.fesg.cases.studentattendancesystem;

import tr.edu.iyte.fesg.cases.CaseStudyFilePathCreationUtilities;

public abstract class SASCaseStudyUtilities extends CaseStudyFilePathCreationUtilities {

	public static void buildProductModel() {
		createCaseStudyFilePathObjects();
		switch (productID) {
		case 1: {
			initializeCaseStudyFolderNames();
			sasProduct_fullProduct_AccessCardEmail();
			break;
		}
		case 2: {
			initializeCaseStudyFolderNames();
			sasProduct_fullProduct_AccessCardSMS();
			break;
		}
		case 3: {
			initializeCaseStudyFolderNames();
			sasProduct_fullProduct_BarcodeEmail();
			break;
		}
		case 4: {
			initializeCaseStudyFolderNames();
			sasProduct_fullProduct_BarcodeSMS();
			break;
		}
		case 5: {
			initializeCaseStudyFolderNames();
			sasProduct_fullProduct_FingerprintEmail();
			break;
		}
		case 6: {
			initializeCaseStudyFolderNames();
			sasProduct_fullProduct_FingerprintSMS();
			break;
		}
		case 7: {
			initializeCaseStudyFolderNames();
			sasProduct_fullProduct_QRCodeEmail();
			break;
		}
		case 8: {
			initializeCaseStudyFolderNames();
			sasProduct_fullProduct_QRCodeSMS();
			break;
		}
		case 9: {
			initializeCaseStudyFolderNames();
			sasProduct_baseProduct_AccessCardEmail();
			break;
		}
		case 10: {
			initializeCaseStudyFolderNames();
			sasProduct_baseProduct_AccessCardSMS();
			break;
		}
		}
	}

	public static void initializeCaseStudyFolderNames() {
		esgFolderName.append("files/Cases/StudentAttendanceSystemPL");
		dotFolderName.append("files/DOTFiles/StudentAttendanceSystemPL");
	}
	
	public static void sasProduct_fullProduct_AccessCardEmail() {
		int numberOfFeatures = 17;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("sasProduct-fullProduct_AccessCardEmail");
		
		esgName[0] = "core";
		esgName[1] = "accessCard";
		esgName[2] = "email";
		esgName[3] = "teacherAccess";
		esgName[4] = "studentAccess";
		esgName[5] = "viewSchedule";
		esgName[6] = "viewClass";
		esgName[7] = "addNewClass";
		esgName[8] = "addNewSchedule";
		esgName[9] = "deleteClass";
		esgName[10] = "updateRecord";
		esgName[11] = "viewRecord";
		esgName[12] = "monitorAttendanceStatus";
		esgName[13] = "updateClassDetail";
		esgName[14] = "editSchedule";
		esgName[15] = "traceAttendanceActivity";
		esgName[16] = "assignNewSchedule";

		esgFileName[0] = "core_usefull.mxe";
		esgFileName[1] = esgName[1] + mxeFileExtension;
		esgFileName[2] = esgName[2] + mxeFileExtension;
		esgFileName[3] = esgName[3] + mxeFileExtension;
		esgFileName[4] = esgName[4] + mxeFileExtension;
		esgFileName[5] = esgName[5] + mxeFileExtension;
		esgFileName[6] = esgName[6] + mxeFileExtension;
		esgFileName[7] = esgName[7] + mxeFileExtension;
		esgFileName[8] = esgName[8] + mxeFileExtension;
		esgFileName[9] = esgName[9] + mxeFileExtension;
		esgFileName[10] = esgName[10] + mxeFileExtension;
		esgFileName[11] = esgName[11] + mxeFileExtension;
		esgFileName[12] = esgName[12] + mxeFileExtension;
		esgFileName[13] = esgName[13] + mxeFileExtension;
		esgFileName[14] = esgName[14] + mxeFileExtension;
		esgFileName[15] = esgName[15] + mxeFileExtension;
		esgFileName[16] = esgName[16] + mxeFileExtension;

	}

	public static void sasProduct_fullProduct_AccessCardSMS() {
		int numberOfFeatures = 17;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("sasProduct-fullProduct_AccessCardSMS");
		
		esgName[0] = "core";
		esgName[1] = "accessCard";
		esgName[2] = "SMS";
		esgName[3] = "teacherAccess";
		esgName[4] = "studentAccess";
		esgName[5] = "viewSchedule";
		esgName[6] = "viewClass";
		esgName[7] = "addNewClass";
		esgName[8] = "addNewSchedule";
		esgName[9] = "deleteClass";
		esgName[10] = "updateRecord";
		esgName[11] = "viewRecord";
		esgName[12] = "monitorAttendanceStatus";
		esgName[13] = "updateClassDetail";
		esgName[14] = "editSchedule";
		esgName[15] = "traceAttendanceActivity";
		esgName[16] = "assignNewSchedule";

		esgFileName[0] = "core_usefull.mxe";
		esgFileName[1] = esgName[1] + mxeFileExtension;
		esgFileName[2] = esgName[2] + mxeFileExtension;
		esgFileName[3] = esgName[3] + mxeFileExtension;
		esgFileName[4] = esgName[4] + mxeFileExtension;
		esgFileName[5] = esgName[5] + mxeFileExtension;
		esgFileName[6] = esgName[6] + mxeFileExtension;
		esgFileName[7] = esgName[7] + mxeFileExtension;
		esgFileName[8] = esgName[8] + mxeFileExtension;
		esgFileName[9] = esgName[9] + mxeFileExtension;
		esgFileName[10] = esgName[10] + mxeFileExtension;
		esgFileName[11] = esgName[11] + mxeFileExtension;
		esgFileName[12] = esgName[12] + mxeFileExtension;
		esgFileName[13] = esgName[13] + mxeFileExtension;
		esgFileName[14] = esgName[14] + mxeFileExtension;
		esgFileName[15] = esgName[15] + mxeFileExtension;
		esgFileName[16] = esgName[16] + mxeFileExtension;
	}

	public static void sasProduct_fullProduct_BarcodeEmail() {
		int numberOfFeatures = 17;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("sasProduct-fullProduct_BarcodeEmail");
		
		esgName[0] = "core";
		esgName[1] = "barcode";
		esgName[2] = "email";
		esgName[3] = "teacherAccess";
		esgName[4] = "studentAccess";
		esgName[5] = "viewSchedule";
		esgName[6] = "viewClass";
		esgName[7] = "addNewClass";
		esgName[8] = "addNewSchedule";
		esgName[9] = "deleteClass";
		esgName[10] = "updateRecord";
		esgName[11] = "viewRecord";
		esgName[12] = "monitorAttendanceStatus";
		esgName[13] = "updateClassDetail";
		esgName[14] = "editSchedule";
		esgName[15] = "traceAttendanceActivity";
		esgName[16] = "assignNewSchedule";

		esgFileName[0] = "core_usefull.mxe";
		esgFileName[1] = esgName[1] + mxeFileExtension;
		esgFileName[2] = esgName[2] + mxeFileExtension;
		esgFileName[3] = esgName[3] + mxeFileExtension;
		esgFileName[4] = esgName[4] + mxeFileExtension;
		esgFileName[5] = esgName[5] + mxeFileExtension;
		esgFileName[6] = esgName[6] + mxeFileExtension;
		esgFileName[7] = esgName[7] + mxeFileExtension;
		esgFileName[8] = esgName[8] + mxeFileExtension;
		esgFileName[9] = esgName[9] + mxeFileExtension;
		esgFileName[10] = esgName[10] + mxeFileExtension;
		esgFileName[11] = esgName[11] + mxeFileExtension;
		esgFileName[12] = esgName[12] + mxeFileExtension;
		esgFileName[13] = esgName[13] + mxeFileExtension;
		esgFileName[14] = esgName[14] + mxeFileExtension;
		esgFileName[15] = esgName[15] + mxeFileExtension;
		esgFileName[16] = esgName[16] + mxeFileExtension;
	}

	public static void sasProduct_fullProduct_BarcodeSMS() {
		int numberOfFeatures = 17;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("sasProduct-fullProduct_BarcodeSMS");
		
		esgName[0] = "core";
		esgName[1] = "barcode";
		esgName[2] = "SMS";
		esgName[3] = "teacherAccess";
		esgName[4] = "studentAccess";
		esgName[5] = "viewSchedule";
		esgName[6] = "viewClass";
		esgName[7] = "addNewClass";
		esgName[8] = "addNewSchedule";
		esgName[9] = "deleteClass";
		esgName[10] = "updateRecord";
		esgName[11] = "viewRecord";
		esgName[12] = "monitorAttendanceStatus";
		esgName[13] = "updateClassDetail";
		esgName[14] = "editSchedule";
		esgName[15] = "traceAttendanceActivity";
		esgName[16] = "assignNewSchedule";

		esgFileName[0] = "core_usefull.mxe";
		esgFileName[1] = esgName[1] + mxeFileExtension;
		esgFileName[2] = esgName[2] + mxeFileExtension;
		esgFileName[3] = esgName[3] + mxeFileExtension;
		esgFileName[4] = esgName[4] + mxeFileExtension;
		esgFileName[5] = esgName[5] + mxeFileExtension;
		esgFileName[6] = esgName[6] + mxeFileExtension;
		esgFileName[7] = esgName[7] + mxeFileExtension;
		esgFileName[8] = esgName[8] + mxeFileExtension;
		esgFileName[9] = esgName[9] + mxeFileExtension;
		esgFileName[10] = esgName[10] + mxeFileExtension;
		esgFileName[11] = esgName[11] + mxeFileExtension;
		esgFileName[12] = esgName[12] + mxeFileExtension;
		esgFileName[13] = esgName[13] + mxeFileExtension;
		esgFileName[14] = esgName[14] + mxeFileExtension;
		esgFileName[15] = esgName[15] + mxeFileExtension;
		esgFileName[16] = esgName[16] + mxeFileExtension;
	}

	public static void sasProduct_fullProduct_FingerprintEmail() {
		int numberOfFeatures = 17;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("sasProduct-fullProduct_FingerprintEmail");
		
		esgName[0] = "core";
		esgName[1] = "fingerprint";
		esgName[2] = "email";
		esgName[3] = "teacherAccess";
		esgName[4] = "studentAccess";
		esgName[5] = "viewSchedule";
		esgName[6] = "viewClass";
		esgName[7] = "addNewClass";
		esgName[8] = "addNewSchedule";
		esgName[9] = "deleteClass";
		esgName[10] = "updateRecord";
		esgName[11] = "viewRecord";
		esgName[12] = "monitorAttendanceStatus";
		esgName[13] = "updateClassDetail";
		esgName[14] = "editSchedule";
		esgName[15] = "traceAttendanceActivity";
		esgName[16] = "assignNewSchedule";

		esgFileName[0] = "core_usefull.mxe";
		esgFileName[1] = esgName[1] + mxeFileExtension;
		esgFileName[2] = esgName[2] + mxeFileExtension;
		esgFileName[3] = esgName[3] + mxeFileExtension;
		esgFileName[4] = esgName[4] + mxeFileExtension;
		esgFileName[5] = esgName[5] + mxeFileExtension;
		esgFileName[6] = esgName[6] + mxeFileExtension;
		esgFileName[7] = esgName[7] + mxeFileExtension;
		esgFileName[8] = esgName[8] + mxeFileExtension;
		esgFileName[9] = esgName[9] + mxeFileExtension;
		esgFileName[10] = esgName[10] + mxeFileExtension;
		esgFileName[11] = esgName[11] + mxeFileExtension;
		esgFileName[12] = esgName[12] + mxeFileExtension;
		esgFileName[13] = esgName[13] + mxeFileExtension;
		esgFileName[14] = esgName[14] + mxeFileExtension;
		esgFileName[15] = esgName[15] + mxeFileExtension;
		esgFileName[16] = esgName[16] + mxeFileExtension;
	}

	public static void sasProduct_fullProduct_FingerprintSMS() {
		int numberOfFeatures = 17;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("sasProduct-fullProduct_FingerprintSMS");
		
		esgName[0] = "core";
		esgName[1] = "fingerprint";
		esgName[2] = "SMS";
		esgName[3] = "teacherAccess";
		esgName[4] = "studentAccess";
		esgName[5] = "viewSchedule";
		esgName[6] = "viewClass";
		esgName[7] = "addNewClass";
		esgName[8] = "addNewSchedule";
		esgName[9] = "deleteClass";
		esgName[10] = "updateRecord";
		esgName[11] = "viewRecord";
		esgName[12] = "monitorAttendanceStatus";
		esgName[13] = "updateClassDetail";
		esgName[14] = "editSchedule";
		esgName[15] = "traceAttendanceActivity";
		esgName[16] = "assignNewSchedule";

		esgFileName[0] = "core_usefull.mxe";
		esgFileName[1] = esgName[1] + mxeFileExtension;
		esgFileName[2] = esgName[2] + mxeFileExtension;
		esgFileName[3] = esgName[3] + mxeFileExtension;
		esgFileName[4] = esgName[4] + mxeFileExtension;
		esgFileName[5] = esgName[5] + mxeFileExtension;
		esgFileName[6] = esgName[6] + mxeFileExtension;
		esgFileName[7] = esgName[7] + mxeFileExtension;
		esgFileName[8] = esgName[8] + mxeFileExtension;
		esgFileName[9] = esgName[9] + mxeFileExtension;
		esgFileName[10] = esgName[10] + mxeFileExtension;
		esgFileName[11] = esgName[11] + mxeFileExtension;
		esgFileName[12] = esgName[12] + mxeFileExtension;
		esgFileName[13] = esgName[13] + mxeFileExtension;
		esgFileName[14] = esgName[14] + mxeFileExtension;
		esgFileName[15] = esgName[15] + mxeFileExtension;
		esgFileName[16] = esgName[16] + mxeFileExtension;
	}
	
	public static void sasProduct_fullProduct_QRCodeEmail() {
		int numberOfFeatures = 17;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("sasProduct-fullProduct_QRCodeEmail");
		
		esgName[0] = "core";
		esgName[1] = "QRCode";
		esgName[2] = "email";
		esgName[3] = "teacherAccess";
		esgName[4] = "studentAccess";
		esgName[5] = "viewSchedule";
		esgName[6] = "viewClass";
		esgName[7] = "addNewClass";
		esgName[8] = "addNewSchedule";
		esgName[9] = "deleteClass";
		esgName[10] = "updateRecord";
		esgName[11] = "viewRecord";
		esgName[12] = "monitorAttendanceStatus";
		esgName[13] = "updateClassDetail";
		esgName[14] = "editSchedule";
		esgName[15] = "traceAttendanceActivity";
		esgName[16] = "assignNewSchedule";

		esgFileName[0] = "core_usefull.mxe";
		esgFileName[1] = esgName[1] + mxeFileExtension;
		esgFileName[2] = esgName[2] + mxeFileExtension;
		esgFileName[3] = esgName[3] + mxeFileExtension;
		esgFileName[4] = esgName[4] + mxeFileExtension;
		esgFileName[5] = esgName[5] + mxeFileExtension;
		esgFileName[6] = esgName[6] + mxeFileExtension;
		esgFileName[7] = esgName[7] + mxeFileExtension;
		esgFileName[8] = esgName[8] + mxeFileExtension;
		esgFileName[9] = esgName[9] + mxeFileExtension;
		esgFileName[10] = esgName[10] + mxeFileExtension;
		esgFileName[11] = esgName[11] + mxeFileExtension;
		esgFileName[12] = esgName[12] + mxeFileExtension;
		esgFileName[13] = esgName[13] + mxeFileExtension;
		esgFileName[14] = esgName[14] + mxeFileExtension;
		esgFileName[15] = esgName[15] + mxeFileExtension;
		esgFileName[16] = esgName[16] + mxeFileExtension;
	}
	
	public static void sasProduct_fullProduct_QRCodeSMS() {
		int numberOfFeatures = 17;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("sasProduct-fullProduct_QRCodeSMS");
		
		esgName[0] = "core";
		esgName[1] = "QRCode";
		esgName[2] = "SMS";
		esgName[3] = "teacherAccess";
		esgName[4] = "studentAccess";
		esgName[5] = "viewSchedule";
		esgName[6] = "viewClass";
		esgName[7] = "addNewClass";
		esgName[8] = "addNewSchedule";
		esgName[9] = "deleteClass";
		esgName[10] = "updateRecord";
		esgName[11] = "viewRecord";
		esgName[12] = "monitorAttendanceStatus";
		esgName[13] = "updateClassDetail";
		esgName[14] = "editSchedule";
		esgName[15] = "traceAttendanceActivity";
		esgName[16] = "assignNewSchedule";

		esgFileName[0] = "core_usefull.mxe";
		esgFileName[1] = esgName[1] + mxeFileExtension;
		esgFileName[2] = esgName[2] + mxeFileExtension;
		esgFileName[3] = esgName[3] + mxeFileExtension;
		esgFileName[4] = esgName[4] + mxeFileExtension;
		esgFileName[5] = esgName[5] + mxeFileExtension;
		esgFileName[6] = esgName[6] + mxeFileExtension;
		esgFileName[7] = esgName[7] + mxeFileExtension;
		esgFileName[8] = esgName[8] + mxeFileExtension;
		esgFileName[9] = esgName[9] + mxeFileExtension;
		esgFileName[10] = esgName[10] + mxeFileExtension;
		esgFileName[11] = esgName[11] + mxeFileExtension;
		esgFileName[12] = esgName[12] + mxeFileExtension;
		esgFileName[13] = esgName[13] + mxeFileExtension;
		esgFileName[14] = esgName[14] + mxeFileExtension;
		esgFileName[15] = esgName[15] + mxeFileExtension;
		esgFileName[16] = esgName[16] + mxeFileExtension;
	}
	
	public static void sasProduct_baseProduct_AccessCardEmail() {
		int numberOfFeatures = 8;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("sasProduct-baseProduct_AccessCardEmail");
		
		esgName[0] = "core";
		esgName[1] = "accessCard";
		esgName[2] = "email";
		esgName[3] = "teacherAccess";
		esgName[4] = "studentAccess";
		esgName[5] = "viewRecord";
		esgName[6] = "viewClass";
		esgName[7] = "viewSchedule";

		esgFileName[0] = "core_usefull.mxe";
		esgFileName[1] = esgName[1] + mxeFileExtension;
		esgFileName[2] = esgName[2] + mxeFileExtension;
		esgFileName[3] = esgName[3] + mxeFileExtension;
		esgFileName[4] = esgName[4] + mxeFileExtension;
		esgFileName[5] = esgName[5] + mxeFileExtension;
		esgFileName[6] = esgName[6] + mxeFileExtension;
		esgFileName[7] = esgName[7] + mxeFileExtension;

	}
	
	public static void sasProduct_baseProduct_AccessCardSMS() {
		int numberOfFeatures = 8;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("sasProduct-baseProduct_AccessCardEmail");
		
		esgName[0] = "core";
		esgName[1] = "accessCard";
		esgName[2] = "SMS";
		esgName[3] = "teacherAccess";
		esgName[4] = "studentAccess";
		esgName[5] = "viewRecord";
		esgName[6] = "viewClass";
		esgName[7] = "viewSchedule";

		esgFileName[0] = "core_usefull.mxe";
		esgFileName[1] = esgName[1] + mxeFileExtension;
		esgFileName[2] = esgName[2] + mxeFileExtension;
		esgFileName[3] = esgName[3] + mxeFileExtension;
		esgFileName[4] = esgName[4] + mxeFileExtension;
		esgFileName[5] = esgName[5] + mxeFileExtension;
		esgFileName[6] = esgName[6] + mxeFileExtension;
		esgFileName[7] = esgName[7] + mxeFileExtension;

	}
}
