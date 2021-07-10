package tr.edu.iyte.fesg.cases.email.allproducts;

import java.util.AbstractMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import tr.edu.iyte.fesg.cases.fileoperations.SPLResultsFilesRenamer;

public class ResultFilesRenamer {

	public static void main(String[] args) {
		String folderPath = "files/Cases/EmailPL/1RegFiles/sequences/INC";

		Map<String, AbstractMap.SimpleEntry<Integer, Integer>> map = getProductNameScenarioIDProductIDMap();
		SPLResultsFilesRenamer.sequenceFileRenamer(folderPath,"em", map);
		//SPLResultsFilesRenamer.productmodelFileRenamer(folderPath,"em", map);

	}

	public static Map<String, AbstractMap.SimpleEntry<Integer, Integer>> getProductNameScenarioIDProductIDMap() {
		
		Map<String, AbstractMap.SimpleEntry<Integer, Integer>> productNameScenarioIDProductIDMap = new LinkedHashMap<>();
		
		AbstractMap.SimpleEntry<Integer, Integer> entry1 = new AbstractMap.SimpleEntry<>(4,12);
		productNameScenarioIDProductIDMap.put(
				"sign",
				entry1);
		
		AbstractMap.SimpleEntry<Integer, Integer> entry2 = new AbstractMap.SimpleEntry<>(1,2);
		productNameScenarioIDProductIDMap.put(
				"autoresponder", entry2);
		
		AbstractMap.SimpleEntry<Integer, Integer> entry3 = new AbstractMap.SimpleEntry<>(3,8);
		productNameScenarioIDProductIDMap.put(
				"encrypt",
				entry3);

		AbstractMap.SimpleEntry<Integer, Integer> entry4 = new AbstractMap.SimpleEntry<>(2,4);
		productNameScenarioIDProductIDMap.put(
				"forward",
				entry4);
		
		AbstractMap.SimpleEntry<Integer, Integer> entry5 = new AbstractMap.SimpleEntry<>(35,20);
		productNameScenarioIDProductIDMap.put(
				"encrypt_sign",
				entry5);

		AbstractMap.SimpleEntry<Integer, Integer> entry6 = new AbstractMap.SimpleEntry<>(26,20);
		productNameScenarioIDProductIDMap.put(
				"encrypt_sign",
				entry6);

		AbstractMap.SimpleEntry<Integer, Integer> entry7 = new AbstractMap.SimpleEntry<>(5,3);
		productNameScenarioIDProductIDMap.put(
				"addressbook_autoresponder",
				entry7);
		         
		AbstractMap.SimpleEntry<Integer, Integer> entry8 = new AbstractMap.SimpleEntry<>(33,14);
		productNameScenarioIDProductIDMap.put(
				"autoresponder_sign",
				entry8);

		AbstractMap.SimpleEntry<Integer, Integer> entry9 = new AbstractMap.SimpleEntry<>(9,3);
		productNameScenarioIDProductIDMap.put(
				"addressbook_autoresponder",
				entry9);

		AbstractMap.SimpleEntry<Integer, Integer> entry10 = new AbstractMap.SimpleEntry<>(18,16);
		productNameScenarioIDProductIDMap.put(
				"forward_sign",
				
				entry10);
		
		AbstractMap.SimpleEntry<Integer, Integer> entry11 = new AbstractMap.SimpleEntry<>(34,16);
		productNameScenarioIDProductIDMap.put(
				"forward_sign",
				entry11);
		
		AbstractMap.SimpleEntry<Integer, Integer> entry12 = new AbstractMap.SimpleEntry<>(6,5);
		productNameScenarioIDProductIDMap.put(
				"addressbook_forward",
				entry12);

		AbstractMap.SimpleEntry<Integer, Integer> entry13 = new AbstractMap.SimpleEntry<>(8,13);
		productNameScenarioIDProductIDMap.put(
				"addressbook_sign",
				entry13);

		AbstractMap.SimpleEntry<Integer, Integer> entry14 = new AbstractMap.SimpleEntry<>(7,9);
		productNameScenarioIDProductIDMap.put(
				"addressbook_encrypt",
				entry14);
		
		AbstractMap.SimpleEntry<Integer, Integer> entry15 = new AbstractMap.SimpleEntry<>(30,22);
		productNameScenarioIDProductIDMap.put(
				"autoresponder_encrypt_sign",
				entry15);

		AbstractMap.SimpleEntry<Integer, Integer> entry16 = new AbstractMap.SimpleEntry<>(45,18);
		productNameScenarioIDProductIDMap.put(
				"autoresponder_forward_sign",
				entry16);
		
		AbstractMap.SimpleEntry<Integer, Integer> entry17 = new AbstractMap.SimpleEntry<>(39,15);
		productNameScenarioIDProductIDMap.put(
				"addressbook_autoresponder_sign",
				entry17);

		AbstractMap.SimpleEntry<Integer, Integer> entry18 = new AbstractMap.SimpleEntry<>(36,15);
		productNameScenarioIDProductIDMap.put(
				"addressbook_autoresponder_sign",
				entry18);

		AbstractMap.SimpleEntry<Integer, Integer> entry19 = new AbstractMap.SimpleEntry<>(27,11);
		productNameScenarioIDProductIDMap.put(
				"addressbook_autoresponder_encrypt",
				entry19);
		
		AbstractMap.SimpleEntry<Integer, Integer> entry20 = new AbstractMap.SimpleEntry<>(14,11);
		productNameScenarioIDProductIDMap.put(
				"addressbook_autoresponder_encrypt",
				entry20);
		
		AbstractMap.SimpleEntry<Integer, Integer> entry21 = new AbstractMap.SimpleEntry<>(40,18);
		productNameScenarioIDProductIDMap.put(
				"autoresponder_forward_sign",
				entry21);

		AbstractMap.SimpleEntry<Integer, Integer> entry22 = new AbstractMap.SimpleEntry<>(19,7);
		productNameScenarioIDProductIDMap.put(
				"addressbook_autoresponder_forward",
				entry22);
		
		AbstractMap.SimpleEntry<Integer, Integer> entry23 = new AbstractMap.SimpleEntry<>(49,22);
		productNameScenarioIDProductIDMap.put(
				"autoresponder_encrypt_sign",
				entry23);
		         
		AbstractMap.SimpleEntry<Integer, Integer> entry24 = new AbstractMap.SimpleEntry<>(21,7);
		productNameScenarioIDProductIDMap.put(
				"addressbook_autoresponder_forward",
				entry24);

		AbstractMap.SimpleEntry<Integer, Integer> entry25 = new AbstractMap.SimpleEntry<>(28,21);
		productNameScenarioIDProductIDMap.put(
				"addressbook_encrypt_sign",
				entry25);

		AbstractMap.SimpleEntry<Integer, Integer> entry26 = new AbstractMap.SimpleEntry<>(48,21);
		productNameScenarioIDProductIDMap.put(
				"addressbook_encrypt_sign",
				entry26);

		AbstractMap.SimpleEntry<Integer, Integer> entry27 = new AbstractMap.SimpleEntry<>(47,19);
		productNameScenarioIDProductIDMap.put(
				"addressbook_autoresponder_forward_sign",
				entry27);

		AbstractMap.SimpleEntry<Integer, Integer> entry28 = new AbstractMap.SimpleEntry<>(31,23);
		productNameScenarioIDProductIDMap.put(
				"addressbook_autoresponder_encrypt_sign",
				entry28);
		
		AbstractMap.SimpleEntry<Integer, Integer> entry29 = new AbstractMap.SimpleEntry<>(23,19);
		productNameScenarioIDProductIDMap.put(
				"addressbook_autoresponder_forward_sign",
				entry29);
		AbstractMap.SimpleEntry<Integer, Integer> entry30 = new AbstractMap.SimpleEntry<>(51,23);
		productNameScenarioIDProductIDMap.put(
				"addressbook_autoresponder_encrypt_sign",
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
