package tr.edu.iyte.fesg.cases.bankaccount.allproducts;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import java.util.Set;

import tr.edu.iyte.esg.conversion.csv.ESGToCSVFileConverter;
import tr.edu.iyte.esg.conversion.dot.ESGToDOTFileConverter;
import tr.edu.iyte.esg.conversion.json.ESGToJSONFileConverter;
import tr.edu.iyte.esg.conversion.json.JSONFileToESGConverter;
import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.fesg.cases.bankaccount.BankAccountCaseStudyUtilities;
import tr.edu.iyte.fesg.cases.bankaccount.allproducts.AllPossibleProductsGenerator;
import tr.edu.iyte.fesg.conversion.FESGToCSVFileConverter;
import tr.edu.iyte.fesg.conversion.TestScenarioToCSVFileConverter;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.automaticbuild.FeatureModelParser;
import tr.edu.iyte.fesg.model.FeaturedESG;
import tr.edu.iyte.fesg.model.featuremodel.FeatureModel;
import tr.edu.iyte.fesg.testscenario.OneIncrementScenarioGenerator;
import tr.edu.iyte.fesg.testscenario.OneIncrementScneraio;

public class AllPossibleProductsGeneratorApp extends BankAccountCaseStudyUtilities {

	private static String bankAccountFolder = "files/Cases/BankAccountPL/";

	public static void main(String[] args) {

		AllPossibleProductsGenerator allPossibleProductsGenerator = getAllPossibleProductsGenerator();

		Set<FeaturedESG> productSet = getFeaturedESGSet(allPossibleProductsGenerator);
		OneIncrementScenarioGenerator oneIncrementScenarioGenerator = new OneIncrementScenarioGenerator(productSet);
		oneIncrementScenarioGenerator.generateOneIncrementScnearioList();
		List<OneIncrementScneraio> oneIncrementScneraioList = oneIncrementScenarioGenerator.getScenarioList();
		Map<String, AbstractMap.SimpleEntry<Integer, Integer>> map = ResultFilesRenamer
				.getProductNameScenarioIDProductIDMap();
		List<List<String>> rows = new LinkedList<List<String>>();

		for (String PUCName : map.keySet()) {
			for (OneIncrementScneraio scenario : oneIncrementScneraioList) {
				if (scenario.getProductUnderConsideration().getName().equals(PUCName)) {
					int scenarioID = map.get(PUCName).getKey();
					if (scenario.getId() == scenarioID) {
						List<String> row = new LinkedList<String>();
						int productID = map.get(PUCName).getValue();
						System.out.print(scenarioID + " ");
						System.out.print(scenario.getExistingProduct().getName() + " ");
						System.out.print(scenario.getExistingProduct().getFeaturedESGSet().size() + " ");
						String existingNumberOfFeatures = Integer
								.toString(scenario.getExistingProduct().getFeaturedESGSet().size());
						String existingFeatures = "";
						int counter = 0;
						for (ESG featureESG : scenario.getExistingProduct().getFeatureESGSet()) {
							counter++;
							if (counter < scenario.getExistingProduct().getFeatureESGSet().size()) {
								existingFeatures += featureESG.getName() + "; ";
							} else {
								existingFeatures += featureESG.getName();
							}
						}
						System.out.print(existingFeatures + " ");
						System.out.print(productID + " ");
						System.out.print(scenario.getProductUnderConsideration().getName() + " ");
						System.out.print(scenario.getProductUnderConsideration().getFeaturedESGSet().size() + " ");
						String PUCFeatures = "";
						counter = 0;
						for (ESG featureESG : scenario.getProductUnderConsideration().getFeatureESGSet()) {
							counter++;
							if (counter < scenario.getProductUnderConsideration().getFeatureESGSet().size()) {
								PUCFeatures += featureESG.getName() + "; ";
							} else {
								PUCFeatures += featureESG.getName();
							}
						}
						System.out.print(PUCFeatures);
						System.out.print(scenario.getIncrementFeature().getName());
						System.out.println();

						row.add(Integer.toString(scenarioID));
						row.add(scenario.getExistingProduct().getName());
						row.add(existingNumberOfFeatures);
						row.add(existingFeatures);
						row.add(Integer.toString(productID));
						row.add(scenario.getProductUnderConsideration().getName());
						row.add(Integer.toString(scenario.getProductUnderConsideration().getFeaturedESGSet().size()));
						row.add(PUCFeatures);
						row.add(scenario.getIncrementFeature().getName());
						rows.add(row);
					}

				}
			}
		}

		try {
			writeScenario(rows, bankAccountFolder + "ba-table2");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void writeScenario(List<List<String>> rows, String filePathAndName) throws IOException {
		FileWriter csvWriter = null;
		try {
			csvWriter = new FileWriter(filePathAndName + ".csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		csvWriter.append("Scenario ID");
		csvWriter.append(",");
		csvWriter.append("Existing Product");
		csvWriter.append(",");
		csvWriter.append("EP # of Features");
		csvWriter.append(",");
		csvWriter.append("EP Features");
		csvWriter.append(",");
		csvWriter.append("Product Under Consideration ID");
		csvWriter.append(",");
		csvWriter.append("PUC Name");
		csvWriter.append(",");
		csvWriter.append("PUC # Features");
		csvWriter.append(",");
		csvWriter.append("PUC Features");
		csvWriter.append(",");
		csvWriter.append("Increment");
		csvWriter.append(",");
		csvWriter.append("\n");

		for (List<String> rowData : rows) {
			csvWriter.append(String.join(",", rowData));
			csvWriter.append("\n");
		}

		csvWriter.flush();
		csvWriter.close();
	}

	public static AllPossibleProductsGenerator getAllPossibleProductsGenerator() {
		String path = bankAccountFolder + "configs/model.xml";
		FeatureModelParser featureModelParser = new FeatureModelParser();
		FeatureModel featureModel = featureModelParser.parseXMLFileForFeatureModelCreation(path);
		AllPossibleProductsGenerator allPossibleProductsGenerator = new AllPossibleProductsGenerator(featureModel);

		return allPossibleProductsGenerator;
	}

	public static Set<FeaturedESG> getFeaturedESGSet(AllPossibleProductsGenerator allPossibleProductsGenerator) {

		allPossibleProductsGenerator.createFeaturedESGSet();
		return allPossibleProductsGenerator.getFeaturedESGSet();
	}

	public static FeaturedESG findFeaturedESGByProductName(AllPossibleProductsGenerator allPossibleProductsGenerator,
			String productName) {
		getFeaturedESGSet(allPossibleProductsGenerator);
		FeaturedESG featuredESG = allPossibleProductsGenerator.findFeaturedESGByProductName(productName);

		return featuredESG;
	}

	public static void writeESGSetToCSVFile(AllPossibleProductsGenerator allPossibleProductsGenerator) {
		String folderPath = bankAccountFolder + "allProductsJSONFiles/";
		allPossibleProductsGenerator.createFullESGSet();
		List<List<String>> rows = ESGToCSVFileConverter.fillRowsForESGSet(allPossibleProductsGenerator.getFullESGSet());
		try {
			ESGToCSVFileConverter.writeESGSetToCSVFile(rows, folderPath + "allFullESGs");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void writeFeaturedESGSetToCSVFile(AllPossibleProductsGenerator allPossibleProductsGenerator) {
		String folderPath = bankAccountFolder + "allProductsJSONFiles/";
		allPossibleProductsGenerator.createFeaturedESGSet();
		List<List<String>> rows = FESGToCSVFileConverter.fillRowsForFeaturedESGSet(allPossibleProductsGenerator.getFeaturedESGSet());
		try {
			FESGToCSVFileConverter.writeFeaturedESGSetToCSVFile(rows, folderPath + "allFeaturedESGs");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void writeToJSONFile(AllPossibleProductsGenerator allPossibleProductsGenerator) {

		String folderPath = bankAccountFolder + "allProductsJSONFiles/";
		allPossibleProductsGenerator.createFullESGSet();
		for (ESG fullESG : allPossibleProductsGenerator.getFullESGSet()) {
			String filePath = folderPath + fullESG.getName() + ".json";
			ESGToJSONFileConverter.writeESGToJSONFile(fullESG, filePath);
		}
	}

	public static void writeToDOTFile(AllPossibleProductsGenerator allPossibleProductsGenerator) {

		String folderPath = bankAccountFolder + "allProductsDOTFiles/";
		allPossibleProductsGenerator.createFullESGSet();
		for (ESG fullESG : allPossibleProductsGenerator.getFullESGSet()) {
			String filePath = folderPath + fullESG.getName() + ".dot";
			ESGToDOTFileConverter.buildDOTFileFromESG(fullESG, filePath);
		}
	}

	public static void writeNumberOfEgdes(String productName) {
		String folderPath = bankAccountFolder + "allProductsJSONFiles/";
		try {
			ESG ESG = JSONFileToESGConverter.parseJSONFileForESGSimpleCreation(folderPath + productName);
			System.out.println("Number of edges: " + ESG.getEdgeList().size());
			System.out.println("Number of real edges: " + ESG.getRealEdgeList().size());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<OneIncrementScneraio> genereateOneIncrementScneraioList(
			AllPossibleProductsGenerator allPossibleProductsGenerator) {
		Set<FeaturedESG> productSet = getFeaturedESGSet(allPossibleProductsGenerator);
		OneIncrementScenarioGenerator oneIncrementScenarioGenerator = new OneIncrementScenarioGenerator(productSet);
		oneIncrementScenarioGenerator.generateOneIncrementScnearioList();
		List<OneIncrementScneraio> oneIncrementScneraioList = oneIncrementScenarioGenerator.getScenarioList();

		return oneIncrementScneraioList;
	}

	public static void writeOneIncrementScneraioListToCSVFile(List<OneIncrementScneraio> oneIncrementScneraioList) {

		TestScenarioToCSVFileConverter.setFilePath(bankAccountFolder);
		List<List<String>> rows = TestScenarioToCSVFileConverter
				.fillRowsForOneIncrementScneraioList(oneIncrementScneraioList);
		try {
			TestScenarioToCSVFileConverter.writeFeaturedESGSetToCSVFile(rows,
					bankAccountFolder + "allProductsJSONFiles/" + "bankAccountTestScenarios");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
