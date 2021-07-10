package tr.edu.iyte.fesg.cases.bankaccount.allproducts;

import java.util.AbstractMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import tr.edu.iyte.fesg.cases.fileoperations.SPLResultsFilesRenamer;

public class ResultFilesRenamer {

	public static void main(String[] args) {
		String folderPath = "files/Cases/BankAccountPL/Results_SingleModel/TestSequences";

		Map<String, AbstractMap.SimpleEntry<Integer, Integer>> map = getProductNameScenarioIDProductIDMap();
		//SPLResultsFilesRenamer.sequenceFileRenamer(folderPath,"ba", map);
		// SPLResultsFilesRenamer.productmodelFileRenamer(folderPath,"ba", map);
		//SPLResultsFilesRenamer.coverageAnalysisFileRenamer(folderPath, map);
		SPLResultsFilesRenamer.testSequenceFileRenamer(folderPath, "ba", map);

	}

	public static Map<String, AbstractMap.SimpleEntry<Integer, Integer>> getProductNameScenarioIDProductIDMap() {

		Map<String, AbstractMap.SimpleEntry<Integer, Integer>> productNameScenarioIDProductIDMap = new LinkedHashMap<>();

		AbstractMap.SimpleEntry<Integer, Integer> entry1 = new AbstractMap.SimpleEntry<>(2, 6);
		productNameScenarioIDProductIDMap.put("cancelWithdraw", entry1);

		AbstractMap.SimpleEntry<Integer, Integer> entry2 = new AbstractMap.SimpleEntry<>(3, 24);
		productNameScenarioIDProductIDMap.put("credit", entry2);

		AbstractMap.SimpleEntry<Integer, Integer> entry3 = new AbstractMap.SimpleEntry<>(5, 4);
		productNameScenarioIDProductIDMap.put("cancelDeposit_interest", entry3);

		AbstractMap.SimpleEntry<Integer, Integer> entry4 = new AbstractMap.SimpleEntry<>(64, 25);
		productNameScenarioIDProductIDMap.put("credit_interest", entry4);

		AbstractMap.SimpleEntry<Integer, Integer> entry5 = new AbstractMap.SimpleEntry<>(19, 7);
		productNameScenarioIDProductIDMap.put("interest_cancelWithdraw", entry5);

		AbstractMap.SimpleEntry<Integer, Integer> entry6 = new AbstractMap.SimpleEntry<>(66, 30);
		productNameScenarioIDProductIDMap.put("credit_cancelWithdraw", entry6);

		AbstractMap.SimpleEntry<Integer, Integer> entry7 = new AbstractMap.SimpleEntry<>(4, 2);
		productNameScenarioIDProductIDMap.put("interest_interestEstimation", entry7);

		AbstractMap.SimpleEntry<Integer, Integer> entry8 = new AbstractMap.SimpleEntry<>(20, 9);
		productNameScenarioIDProductIDMap.put("cancelWithdraw_dailyLimit", entry8);

		AbstractMap.SimpleEntry<Integer, Integer> entry9 = new AbstractMap.SimpleEntry<>(68, 28);
		productNameScenarioIDProductIDMap.put("cancelDeposit_credit_interest", entry9);

		AbstractMap.SimpleEntry<Integer, Integer> entry10 = new AbstractMap.SimpleEntry<>(41, 13);
		productNameScenarioIDProductIDMap.put("cancelDeposit_interest_cancelWithdraw", entry10);

		AbstractMap.SimpleEntry<Integer, Integer> entry11 = new AbstractMap.SimpleEntry<>(31, 15);
		productNameScenarioIDProductIDMap.put("cancelDeposit_cancelWithdraw_dailyLimit", entry11);

		AbstractMap.SimpleEntry<Integer, Integer> entry12 = new AbstractMap.SimpleEntry<>(67, 26);
		productNameScenarioIDProductIDMap.put("credit_interest_interestEstimation", entry12);

		AbstractMap.SimpleEntry<Integer, Integer> entry13 = new AbstractMap.SimpleEntry<>(43, 36);
		productNameScenarioIDProductIDMap.put("cancelDeposit_credit_cancelWithdraw", entry13);

		AbstractMap.SimpleEntry<Integer, Integer> entry14 = new AbstractMap.SimpleEntry<>(23, 8);
		productNameScenarioIDProductIDMap.put("interest_cancelWithdraw_interestEstimation", entry14);

		AbstractMap.SimpleEntry<Integer, Integer> entry15 = new AbstractMap.SimpleEntry<>(78, 33);
		productNameScenarioIDProductIDMap.put("credit_cancelWithdraw_dailyLimit", entry15);

		AbstractMap.SimpleEntry<Integer, Integer> entry16 = new AbstractMap.SimpleEntry<>(17, 14);
		productNameScenarioIDProductIDMap.put("cancelDeposit_interest_cancelWithdraw_interestEstimation", entry16);

		AbstractMap.SimpleEntry<Integer, Integer> entry17 = new AbstractMap.SimpleEntry<>(50, 21);
		productNameScenarioIDProductIDMap.put("cancelDeposit_cancelWithdraw_dailyLimit_overdraft", entry17);

		AbstractMap.SimpleEntry<Integer, Integer> entry18 = new AbstractMap.SimpleEntry<>(86, 39);
		productNameScenarioIDProductIDMap.put("cancelDeposit_credit_cancelWithdraw_dailyLimit", entry18);

		AbstractMap.SimpleEntry<Integer, Integer> entry19 = new AbstractMap.SimpleEntry<>(71, 32);
		productNameScenarioIDProductIDMap.put("credit_interest_cancelWithdraw_interestEstimation", entry19);

		AbstractMap.SimpleEntry<Integer, Integer> entry20 = new AbstractMap.SimpleEntry<>(85, 34);
		productNameScenarioIDProductIDMap.put("credit_interest_cancelWithdraw_dailyLimit", entry20);

		AbstractMap.SimpleEntry<Integer, Integer> entry21 = new AbstractMap.SimpleEntry<>(49, 16);
		productNameScenarioIDProductIDMap.put("cancelDeposit_interest_cancelWithdraw_dailyLimit", entry21);

		AbstractMap.SimpleEntry<Integer, Integer> entry22 = new AbstractMap.SimpleEntry<>(36, 19);
		productNameScenarioIDProductIDMap.put("interest_cancelWithdraw_dailyLimit_overdraft", entry22);

		AbstractMap.SimpleEntry<Integer, Integer> entry23 = new AbstractMap.SimpleEntry<>(54, 40);
		productNameScenarioIDProductIDMap.put("cancelDeposit_credit_interest_cancelWithdraw_dailyLimit", entry23);

		AbstractMap.SimpleEntry<Integer, Integer> entry24 = new AbstractMap.SimpleEntry<>(62, 22);
		productNameScenarioIDProductIDMap.put("cancelDeposit_interest_cancelWithdraw_dailyLimit_overdraft", entry24);

		AbstractMap.SimpleEntry<Integer, Integer> entry25 = new AbstractMap.SimpleEntry<>(76, 38);
		productNameScenarioIDProductIDMap.put("cancelDeposit_credit_interest_cancelWithdraw_interestEstimation",
				entry25);

		AbstractMap.SimpleEntry<Integer, Integer> entry26 = new AbstractMap.SimpleEntry<>(52, 17);
		productNameScenarioIDProductIDMap.put("cancelDeposit_interest_cancelWithdraw_dailyLimit_interestEstimation",
				entry26);

		AbstractMap.SimpleEntry<Integer, Integer> entry27 = new AbstractMap.SimpleEntry<>(83, 35);
		productNameScenarioIDProductIDMap.put("credit_interest_cancelWithdraw_dailyLimit_interestEstimation", entry27);

		AbstractMap.SimpleEntry<Integer, Integer> entry28 = new AbstractMap.SimpleEntry<>(39, 20);
		productNameScenarioIDProductIDMap.put("interest_cancelWithdraw_dailyLimit_interestEstimation_overdraft",
				entry28);

		AbstractMap.SimpleEntry<Integer, Integer> entry29 = new AbstractMap.SimpleEntry<>(55, 23);
		productNameScenarioIDProductIDMap
				.put("cancelDeposit_interest_cancelWithdraw_dailyLimit_interestEstimation_overdraft", entry29);

		AbstractMap.SimpleEntry<Integer, Integer> entry30 = new AbstractMap.SimpleEntry<>(89, 41);
		productNameScenarioIDProductIDMap
				.put("cancelDeposit_credit_interest_cancelWithdraw_dailyLimit_interestEstimation", entry30);

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
