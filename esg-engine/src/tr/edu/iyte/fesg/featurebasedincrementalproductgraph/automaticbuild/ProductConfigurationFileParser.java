package tr.edu.iyte.fesg.featurebasedincrementalproductgraph.automaticbuild;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import tr.edu.iyte.fesg.model.featuremodel.Feature;
import tr.edu.iyte.fesg.model.featuremodel.FeatureModel;

public class ProductConfigurationFileParser {

	public String[] parseConfigurationFileForFeatureNameArrayCreation(String filePath, FeatureModel featureModel,String coreESGName) {
		int arraySize = 0;
		Set<String>  featureConfig = null;
		try {
			File XMLFile = new File(filePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(XMLFile);
			doc.getDocumentElement().normalize();

			NodeList feature = doc.getElementsByTagName("feature");
			featureConfig = parseConcreteFeaturesOfConfiguration(feature,featureModel);
			arraySize = featureConfig.size() + 1;

		} catch (

		ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
		
		String[] featureNameArray = new String[arraySize];
		featureNameArray[0] = coreESGName;
		Iterator<String> featureConfigIterator = featureConfig.iterator();
		int counter = 1;
		while(featureConfigIterator.hasNext()) {
			String featureName = featureConfigIterator.next();
			featureNameArray[counter] = featureName;
			counter++;
			
		}
		
		return featureNameArray;

	}

	public Set<String> parseConcreteFeaturesOfConfiguration(NodeList nodeList, FeatureModel featureModel) {

		Set<String> featureConfig = new LinkedHashSet<String>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				String automatic = element.getAttribute("automatic");
				String manual = element.getAttribute("manual");
				String name = element.getAttribute("name");
				boolean isSelected = automatic.equals("selected") || manual.equals("selected");
				if (isSelected) {
					Feature feature = featureModel.findFeatureByName(name);
					if (!feature.equals(null)) {
						if (!feature.isAbstract()) {
							featureConfig.add(name);
						}
					}
				}
			}
		}
		return featureConfig;
	}
}
