package tr.edu.iyte.fesg.applications;

import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.automaticbuild.FeatureModelParser;
import tr.edu.iyte.fesg.model.featuremodel.FeatureModel;

public class FeatureModelParserApp {

	public static void main(String[] args) {
		String path = "files/Cases/SodaVendingMachinePL/configs/model.xml";
		FeatureModelParser featureModelParser = new FeatureModelParser();
		FeatureModel f = featureModelParser.parseXMLFileForFeatureModelCreation(path);
		System.out.println(f);

	}

}
