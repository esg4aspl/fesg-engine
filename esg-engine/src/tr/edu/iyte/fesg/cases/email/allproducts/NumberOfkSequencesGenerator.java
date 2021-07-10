package tr.edu.iyte.fesg.cases.email.allproducts;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.AbstractMap;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import tr.edu.iyte.esg.conversion.json.JSONFileToESGConverter;
import tr.edu.iyte.esg.esgtransforming.TransformedESGGenerator;
import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.fesg.cases.email.EmailCaseStudyUtilities;


public class NumberOfkSequencesGenerator extends EmailCaseStudyUtilities {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		createCaseStudyFilePathObjects();
		initializeCaseStudyFolderNames();
		
		Map<String, AbstractMap.SimpleEntry<Integer, Integer>> map = ResultFilesRenamer
				.getProductNameScenarioIDProductIDMap();
		List<List<String>> rows = new LinkedList<List<String>>();
		TransformedESGGenerator transformedESGGenerator = new TransformedESGGenerator();
		
		for (String PUCName : map.keySet()) {
			List<String> row = new LinkedList<String>();
			ESG ESG = null;
			try {
				ESG = JSONFileToESGConverter.parseJSONFileForESGSimpleCreation(esgFolderName + "/allProductsJSONFiles/" + PUCName + ".json");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(ESG.toString());
			ESG twoESG = transformedESGGenerator.generateTransformedESG(3, ESG);
			ESG threeESG = transformedESGGenerator.generateTransformedESG(4, ESG);
			System.out.println(twoESG.toString());
			System.out.println(threeESG.toString());
			row.add(Integer.toString(map.get(PUCName).getValue()));
			row.add(PUCName);
			row.add(Integer.toString(ESG.getRealVertexList().size()));
			row.add(Integer.toString(ESG.getRealEdgeList().size()));
			row.add(Integer.toString(twoESG.getRealEdgeList().size()));
			row.add(Integer.toString(threeESG.getRealEdgeList().size()));
			rows.add(row);
		}
		
		try {
			writePUCNumberOfSequences(rows, esgFolderName + "/em-table3");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void writePUCNumberOfSequences(List<List<String>> rows, String filePathAndName) throws IOException {
		FileWriter csvWriter = null;
		try {
			csvWriter = new FileWriter(filePathAndName + ".csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		csvWriter.append("Product Under Consideration ID");
		csvWriter.append(",");
		csvWriter.append("PUC");
		csvWriter.append(",");
		csvWriter.append("k = 1");
		csvWriter.append(",");
		csvWriter.append(" k = 2");
		csvWriter.append(",");
		csvWriter.append("k = 3");
		csvWriter.append(",");
		csvWriter.append("k = 4");
		csvWriter.append(",");
		csvWriter.append("\n");

		for (List<String> rowData : rows) {
			csvWriter.append(String.join(",", rowData));
			csvWriter.append("\n");
		}

		csvWriter.flush();
		csvWriter.close();
	}

}
