package tr.edu.iyte.fesg.conversion;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import tr.edu.iyte.esg.conversion.json.JSONFileToESGConverter;
import tr.edu.iyte.esg.conversion.mxe.MXEFiletoESGConverter;
import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.fesg.testscenario.OneIncrementScneraio;



public class TestScenarioToCSVFileConverter {
	
	private static String filePath;
	
	public static String getFilePath() {
		return filePath;
	}

	public static void setFilePath(String filePath) {
		TestScenarioToCSVFileConverter.filePath = filePath;
	}
	
	
	public static void writeOneIncrementTestScenariosToCSVFile(List<OneIncrementScneraio> oneIncrementScneraioList, String filePath, String fileName) throws IOException {
		setFilePath(filePath);
		List<List<String>> rows = fillRowsForOneIncrementScneraioList(oneIncrementScneraioList);
		writeFeaturedESGSetToCSVFile(rows, filePath + fileName);
	}

	public static void writeFeaturedESGSetToCSVFile(List<List<String>> rows, String filePathAndName) throws IOException {
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
		csvWriter.append("EP Edge Size");
		csvWriter.append(",");
		csvWriter.append("Product Under Consideration");
		csvWriter.append(",");
		csvWriter.append("PUC # of Features");
		csvWriter.append(",");
		csvWriter.append("PUC Edge Size");
		csvWriter.append(",");
		csvWriter.append("Increment");
		csvWriter.append(",");
		csvWriter.append("Increment Edge Size");
		csvWriter.append(",");
		csvWriter.append("\n");
		
		for (List<String> rowData : rows) {
		    csvWriter.append(String.join(",", rowData));
		    csvWriter.append("\n");
		}

		csvWriter.flush();
		csvWriter.close();
	}
	
	/**
	 * number of features includes the core feature
	 * @param featuredESGSet
	 * @return
	 */
	public static List<List<String>> fillRowsForOneIncrementScneraioList(List<OneIncrementScneraio> oneIncrementScneraioList) {
		List<List<String>> rows = new LinkedList<List<String>>();
		/*
		List<String> titleRow = new LinkedList<String>();
		titleRow.add("Existing Product");
		titleRow.add("EP Edge Size");
		titleRow.add("Product Under Consideration");
		titleRow.add("PUC Edge Size");
		titleRow.add("Increment");
		titleRow.add("Increment Edge Size");
		rows.add(titleRow);
		*/
		for(OneIncrementScneraio oneIncrementScneraio: oneIncrementScneraioList) {
			List<String> row = new LinkedList<String>();
			
			int scenarioID = oneIncrementScneraio.getId();
			String existingProductName = oneIncrementScneraio.getExistingProduct().getName();
			int existingProductNumberOfFeatures = oneIncrementScneraio.getExistingProduct().getFeaturedESGSet().size();
			int existingProductEdgeSize = numberOfEdgesFromJSONFile(filePath + "allProductsJSONFiles/" + existingProductName + ".json");
			
			String pucName = oneIncrementScneraio.getProductUnderConsideration().getName();
			int pucNumberOfFeatures = oneIncrementScneraio.getProductUnderConsideration().getFeaturedESGSet().size();
			int pucEdgeSize = numberOfEdgesFromJSONFile(filePath + "allProductsJSONFiles/" + pucName + ".json");
			
			String incrementName = oneIncrementScneraio.getIncrementFeature().getName();
			int incrementEdgeSize = numberOfEdgesFromMXEFile(filePath + incrementName + ".mxe");
			
			String scenarioIdStr = Integer.toString(scenarioID);
			row.add(scenarioIdStr);
			
			row.add(existingProductName);
			String existingProductNumberOfFeaturesStr = Integer.toString(existingProductNumberOfFeatures);
			row.add(existingProductNumberOfFeaturesStr);
			String existingProductEdgeSizeStr = Integer.toString(existingProductEdgeSize);
			row.add(existingProductEdgeSizeStr);
			
			row.add(pucName);
			String pucNumberOfFeaturesStr = Integer.toString(pucNumberOfFeatures);
			row.add(pucNumberOfFeaturesStr);
			String pucEdgeSizeStr = Integer.toString(pucEdgeSize);
			row.add(pucEdgeSizeStr);
			
			row.add(incrementName);
			String incrementEdgeSizeStr = Integer.toString(incrementEdgeSize);
			row.add(incrementEdgeSizeStr);
			
			rows.add(row);
		}
		
		
		return rows;
	}
	
	private static int numberOfEdgesFromJSONFile(String filePath) {
		
		ESG ESG = JSONFileToESGConverter.parseJSONFileForESGCreation(filePath);
		return ESG.getEdgeList().size();
	}
	
	private static int numberOfEdgesFromMXEFile(String filePath) {
		ESG ESG = null;
		try {
			ESG = MXEFiletoESGConverter.parseMXEFileForESGSimpleCreation(filePath);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ESG.getEdgeList().size();
	}



}
