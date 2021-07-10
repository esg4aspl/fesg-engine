package tr.edu.iyte.fesg.conversion;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.fesg.model.FeaturedESG;

public class FESGToCSVFileConverter {
	
	public static void writeFeaturedESGSetToCSVFile(List<List<String>> rows, String filePathAndName) throws IOException {
		FileWriter csvWriter = null;
		try {
			csvWriter = new FileWriter(filePathAndName + ".csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		csvWriter.append("name");
		csvWriter.append(",");
		csvWriter.append("#OfFeatures");
		csvWriter.append(",");
		csvWriter.append("Features");
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
	public static List<List<String>> fillRowsForFeaturedESGSet(Set<FeaturedESG> featuredESGSet) {
		List<List<String>> rows = new LinkedList<List<String>>();
		
		for(FeaturedESG featuredESG: featuredESGSet) {
			List<String> row = new LinkedList<String>();
			String name = featuredESG.getName();
			String numberOfFeatures = Integer.toString(featuredESG.getFeaturedESGSet().size());
			String features = "";
			int counter = 0;
			for(ESG featureESG : featuredESG.getFeatureESGSet()) {
				counter++;
				if(counter < featuredESG.getFeatureESGSet().size()) {
					features += featureESG.getName() + "; ";
				}else {
					features += featureESG.getName();
				}
			}
						
			row.add(name);
			row.add(numberOfFeatures);
			row.add(features);
			
			rows.add(row);
		}
		
		
		return rows;
	}

}
