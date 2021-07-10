package tr.edu.iyte.fesg.applications;

import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.automaticbuild.FeatureModelParser;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.automaticbuild.ProductConfigurationFileParser;
import tr.edu.iyte.fesg.model.featuremodel.FeatureModel;

public class ProductConfigurationBuilderApp {

	public static void main(String[] args) {
		String path = "files/MXEFiles/ElevatorPL/configs/model.xml";
		FeatureModelParser featureModelParser = new FeatureModelParser();
		FeatureModel f = featureModelParser.parseXMLFileForFeatureModelCreation(path);

		String path2 = "files/MXEFiles/ElevatorPL/configs/elevatorProduct_fullProduct.xml";
		ProductConfigurationFileParser productConfigurationFileParser = new ProductConfigurationFileParser();
		String[] featureNameArray = productConfigurationFileParser
				.parseConfigurationFileForFeatureNameArrayCreation(path2, f, "core");
		
		for (String s : featureNameArray) {
			System.out.println(s);
		}

	}

}
