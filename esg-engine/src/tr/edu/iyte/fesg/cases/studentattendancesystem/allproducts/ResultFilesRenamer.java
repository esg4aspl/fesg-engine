package tr.edu.iyte.fesg.cases.studentattendancesystem.allproducts;

import java.util.AbstractMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import tr.edu.iyte.fesg.cases.fileoperations.SPLResultsFilesRenamer;

public class ResultFilesRenamer {

	public static void main(String[] args) {
		String folderPath = "files/Cases/StudentAttendanceSystemPL/Results_IncrementalTestSequenceComposition/CoverageAnalysis";

		Map<String, AbstractMap.SimpleEntry<Integer, Integer>> map = getProductNameScenarioIDProductIDMap();
		//SPLResultsFilesRenamer.sequenceFileRenamer(folderPath,"sas", map);
		// SPLResultsFilesRenamer.productmodelFileRenamer(folderPath,"sas", map);
		SPLResultsFilesRenamer.coverageAnalysisFileRenamer(folderPath, map);
		//SPLResultsFilesRenamer.testSequenceFileRenamer(folderPath, "sas", map);

	}

	static Map<String, AbstractMap.SimpleEntry<Integer, Integer>> getProductNameScenarioIDProductIDMap() {
		
		Map<String, AbstractMap.SimpleEntry<Integer, Integer>> productNameScenarioIDProductIDMap = new LinkedHashMap<>();
		
		AbstractMap.SimpleEntry<Integer, Integer> entry1 = new AbstractMap.SimpleEntry<>(4852,1116);
		productNameScenarioIDProductIDMap.put(
				"viewClass_viewSchedule_email_studentAccess_teacherAccess_updateRecord_addNewClass_updateClassDetail_addNewSchedule_viewRecord_accessCard",
				entry1);
		
		AbstractMap.SimpleEntry<Integer, Integer> entry2 = new AbstractMap.SimpleEntry<>(962,210);
		productNameScenarioIDProductIDMap.put(
				"viewSchedule_SMS_studentAccess_teacherAccess_updateRecord_addNewClass_updateClassDetail_viewRecord_traceAttendanceActivity_fingerprint", entry2);
		
		AbstractMap.SimpleEntry<Integer, Integer> entry3 = new AbstractMap.SimpleEntry<>(9432, 2413);
		productNameScenarioIDProductIDMap.put(
				"viewClass_SMS_studentAccess_teacherAccess_updateRecord_addNewClass_updateClassDetail_addNewSchedule_editSchedule_deleteClass_assignNewSchedule_barcode",
				entry3);

		AbstractMap.SimpleEntry<Integer, Integer> entry4 = new AbstractMap.SimpleEntry<>(9436, 2390);
		productNameScenarioIDProductIDMap.put(
				"viewClass_SMS_studentAccess_teacherAccess_updateRecord_addNewClass_updateClassDetail_addNewSchedule_editSchedule_traceAttendanceActivity_assignNewSchedule_fingerprint",
				entry4);
		
		AbstractMap.SimpleEntry<Integer, Integer> entry5 = new AbstractMap.SimpleEntry<>(2511,1493);
		productNameScenarioIDProductIDMap.put(
				"viewClass_SMS_studentAccess_teacherAccess_updateRecord_addNewSchedule_editSchedule_monitorAttendanceStatus_barcode",
				entry5);

		AbstractMap.SimpleEntry<Integer, Integer> entry6 = new AbstractMap.SimpleEntry<>(7500,1803);
		productNameScenarioIDProductIDMap.put(
				"viewClass_viewSchedule_email_studentAccess_teacherAccess_addNewSchedule_editSchedule_viewRecord_monitorAttendanceStatus_QRCode",
				entry6);

		AbstractMap.SimpleEntry<Integer, Integer> entry7 = new AbstractMap.SimpleEntry<>(3942,1231);
		productNameScenarioIDProductIDMap.put(
				"viewClass_viewSchedule_email_studentAccess_teacherAccess_addNewSchedule_viewRecord_monitorAttendanceStatus_deleteClass_QRCode",
				entry7);
		         
		AbstractMap.SimpleEntry<Integer, Integer> entry8 = new AbstractMap.SimpleEntry<>(3445,816);
		productNameScenarioIDProductIDMap.put(
				"viewClass_email_studentAccess_teacherAccess_updateRecord_addNewClass_addNewSchedule_monitorAttendanceStatus_deleteClass_accessCard",
				entry8);

		AbstractMap.SimpleEntry<Integer, Integer> entry9 = new AbstractMap.SimpleEntry<>(9379,2655);
		productNameScenarioIDProductIDMap.put(
				"viewClass_viewSchedule_email_studentAccess_teacherAccess_updateRecord_addNewClass_updateClassDetail_addNewSchedule_editSchedule_viewRecord_traceAttendanceActivity_deleteClass_assignNewSchedule_QRCode",
				entry9);

		AbstractMap.SimpleEntry<Integer, Integer> entry10 = new AbstractMap.SimpleEntry<>(4971,2027);
		productNameScenarioIDProductIDMap.put(
				"viewClass_viewSchedule_email_studentAccess_teacherAccess_updateRecord_addNewClass_updateClassDetail_addNewSchedule_editSchedule_traceAttendanceActivity_QRCode",
				entry10);
		
		AbstractMap.SimpleEntry<Integer, Integer> entry11 = new AbstractMap.SimpleEntry<>(7119,2211);
		productNameScenarioIDProductIDMap.put(
				"viewClass_viewSchedule_email_studentAccess_teacherAccess_updateRecord_addNewClass_addNewSchedule_editSchedule_viewRecord_deleteClass_QRCode",
				entry11);
		
		AbstractMap.SimpleEntry<Integer, Integer> entry12 = new AbstractMap.SimpleEntry<>(6051,2307);
		productNameScenarioIDProductIDMap.put(
				"viewClass_viewSchedule_email_teacherAccess_updateRecord_addNewClass_updateClassDetail_addNewSchedule_editSchedule_deleteClass_QRCode",
				entry12);

		AbstractMap.SimpleEntry<Integer, Integer> entry13 = new AbstractMap.SimpleEntry<>(8577,2079);
		productNameScenarioIDProductIDMap.put(
				"viewSchedule_email_studentAccess_teacherAccess_updateRecord_addNewSchedule_editSchedule_traceAttendanceActivity_deleteClass_QRCode",
				entry13);

		AbstractMap.SimpleEntry<Integer, Integer> entry14 = new AbstractMap.SimpleEntry<>(4365,1107);
		productNameScenarioIDProductIDMap.put(
				"viewClass_viewSchedule_email_studentAccess_teacherAccess_addNewClass_updateClassDetail_addNewSchedule_viewRecord_QRCode",
				entry14);
		
		AbstractMap.SimpleEntry<Integer, Integer> entry15 = new AbstractMap.SimpleEntry<>(2217,1412);
		productNameScenarioIDProductIDMap.put(
				"viewSchedule_email_studentAccess_teacherAccess_updateRecord_addNewClass_updateClassDetail_addNewSchedule_viewRecord_monitorAttendanceStatus_traceAttendanceActivity_deleteClass_accessCard",
				entry15);

		AbstractMap.SimpleEntry<Integer, Integer> entry16 = new AbstractMap.SimpleEntry<>(3493,813);
		productNameScenarioIDProductIDMap.put(
				"viewClass_SMS_studentAccess_teacherAccess_addNewClass_addNewSchedule_viewRecord_monitorAttendanceStatus_deleteClass_barcode",
				entry16);
		
		AbstractMap.SimpleEntry<Integer, Integer> entry17 = new AbstractMap.SimpleEntry<>(1341,352);
		productNameScenarioIDProductIDMap.put(
				"viewClass_viewSchedule_email_studentAccess_teacherAccess_updateRecord_viewRecord_monitorAttendanceStatus_deleteClass_accessCard",
				entry17);

		AbstractMap.SimpleEntry<Integer, Integer> entry18 = new AbstractMap.SimpleEntry<>(5914,1432);
		productNameScenarioIDProductIDMap.put(
				"viewClass_viewSchedule_email_studentAccess_teacherAccess_addNewClass_updateClassDetail_addNewSchedule_monitorAttendanceStatus_deleteClass_accessCard",
				entry18);

		AbstractMap.SimpleEntry<Integer, Integer> entry19 = new AbstractMap.SimpleEntry<>(6361,1562);
		productNameScenarioIDProductIDMap.put(
				"viewClass_SMS_studentAccess_teacherAccess_updateRecord_addNewClass_addNewSchedule_editSchedule_viewRecord_traceAttendanceActivity_fingerprint",
				entry19);
		
		AbstractMap.SimpleEntry<Integer, Integer> entry20 = new AbstractMap.SimpleEntry<>(45,123);
		productNameScenarioIDProductIDMap.put(
				"viewClass_viewSchedule_email_teacherAccess_updateRecord_addNewClass_QRCode",
				entry20);
		
		AbstractMap.SimpleEntry<Integer, Integer> entry21 = new AbstractMap.SimpleEntry<>(6050,1451);
		productNameScenarioIDProductIDMap.put(
				"viewClass_viewSchedule_email_teacherAccess_updateRecord_addNewClass_updateClassDetail_addNewSchedule_traceAttendanceActivity_deleteClass_QRCode",
				entry21);

		AbstractMap.SimpleEntry<Integer, Integer> entry22 = new AbstractMap.SimpleEntry<>(5283,1234);
		productNameScenarioIDProductIDMap.put(
				"viewClass_viewSchedule_SMS_studentAccess_teacherAccess_updateRecord_addNewSchedule_monitorAttendanceStatus_deleteClass_fingerprint",
				entry22);
		
		AbstractMap.SimpleEntry<Integer, Integer> entry23 = new AbstractMap.SimpleEntry<>(7349,2328);
		productNameScenarioIDProductIDMap.put(
				"viewClass_viewSchedule_email_studentAccess_teacherAccess_updateRecord_addNewClass_updateClassDetail_addNewSchedule_editSchedule_viewRecord_monitorAttendanceStatus_deleteClass_accessCard",
				entry23);
		         
		AbstractMap.SimpleEntry<Integer, Integer> entry24 = new AbstractMap.SimpleEntry<>(1706,444);
		productNameScenarioIDProductIDMap.put(
				"viewClass_viewSchedule_email_studentAccess_teacherAccess_addNewClass_monitorAttendanceStatus_deleteClass_accessCard",
				entry24);

		AbstractMap.SimpleEntry<Integer, Integer> entry25 = new AbstractMap.SimpleEntry<>(5615,2183);
		productNameScenarioIDProductIDMap.put(
				"viewSchedule_email_studentAccess_teacherAccess_updateRecord_addNewClass_addNewSchedule_editSchedule_traceAttendanceActivity_deleteClass_QRCode",
				entry25);

		AbstractMap.SimpleEntry<Integer, Integer> entry26 = new AbstractMap.SimpleEntry<>(1658,487);
		productNameScenarioIDProductIDMap.put(
				"viewSchedule_email_teacherAccess_updateRecord_addNewClass_updateClassDetail_deleteClass_QRCode",
				entry26);

		AbstractMap.SimpleEntry<Integer, Integer> entry27 = new AbstractMap.SimpleEntry<>(8178,2279);
		productNameScenarioIDProductIDMap.put(
				"viewSchedule_email_studentAccess_teacherAccess_updateRecord_addNewClass_updateClassDetail_addNewSchedule_editSchedule_viewRecord_monitorAttendanceStatus_deleteClass_QRCode",
				entry27);

		AbstractMap.SimpleEntry<Integer, Integer> entry28 = new AbstractMap.SimpleEntry<>(3225,1634);
		productNameScenarioIDProductIDMap.put(
				"viewClass_SMS_studentAccess_teacherAccess_updateRecord_addNewSchedule_editSchedule_deleteClass_fingerprint",
				entry28);
		
		AbstractMap.SimpleEntry<Integer, Integer> entry29 = new AbstractMap.SimpleEntry<>(5188,1246);
		productNameScenarioIDProductIDMap.put(
				"viewClass_viewSchedule_SMS_studentAccess_teacherAccess_updateRecord_addNewSchedule_traceAttendanceActivity_deleteClass_fingerprint",
				entry29);
		AbstractMap.SimpleEntry<Integer, Integer> entry30 = new AbstractMap.SimpleEntry<>(573,1005);
		productNameScenarioIDProductIDMap.put(
				"viewClass_viewSchedule_SMS_teacherAccess_updateRecord_addNewClass_addNewSchedule_barcode",
				entry30);

		System.out.println("Number of Product Names " + productNameScenarioIDProductIDMap.keySet().size());
		Set<Integer> scenarioIDSet = new LinkedHashSet<Integer>();
		Set<Integer> productIDSet = new LinkedHashSet<Integer>();
		for (AbstractMap.SimpleEntry<Integer, Integer> pair : productNameScenarioIDProductIDMap.values()) {
			scenarioIDSet.add(pair.getKey());
			productIDSet.add(pair.getValue());
		}
		System.out.println("Number of Scenario IDs " + productNameScenarioIDProductIDMap.keySet().size());
		System.out.println("Number of Product IDs " + productNameScenarioIDProductIDMap.keySet().size());

		return productNameScenarioIDProductIDMap;
	}

}
