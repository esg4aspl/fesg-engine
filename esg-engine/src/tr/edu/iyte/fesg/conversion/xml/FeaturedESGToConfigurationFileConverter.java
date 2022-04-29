package tr.edu.iyte.fesg.conversion.xml;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import tr.edu.iyte.fesg.model.FeaturedESG;
import tr.edu.iyte.fesg.model.featuremodel.Feature;
import tr.edu.iyte.fesg.model.featuremodel.FeatureModel;

public class FeaturedESGToConfigurationFileConverter {

	public static void convertFeaturedESGToConfigurationFile(String filePath, FeaturedESG featuredESG,
			FeatureModel featureModel) {

		Set<String> nameSet = featuredESG.getFeatureNames();
		Iterator<Feature> featureSetIterator = featureModel.getFeatureSet().iterator();
/*
		Writer fileWriter = null;
		try {
			fileWriter = new FileWriter(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter printWriter = new PrintWriter(fileWriter);
*/
		System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\r\n" + "<configuration>");
		while (featureSetIterator.hasNext()) {
			Feature feature = featureSetIterator.next();

			if (feature.equals(featureModel.getRoot()) || feature.isMandatory()) {
				System.out.println("<feature automatic=\"selected\" name=\"" + feature.getName() + "\"/>");
			}

			if (feature.isAbstract()) {
				if (!feature.isMandatory() && (isAtLeastOneChildFeatureOfOptionalAbstractORFeatureSelected(featureModel, feature, nameSet)
						|| isAtLeastOneChildFeatureOfOptionalAbstractXORFeatureSelected(featureModel, feature,
								nameSet))) {
					System.out.println("<feature automatic=\"selected\" name=\"" + feature.getName() + "\"/>");
				}else {
					System.out.println("<feature name=\"" + feature.getName() + "\"/>");
				}
				if (isAbstractFeatureGropingOR(featureModel, feature)) {
					Set<Feature> ORFeatureSet = featureModel.getORFeatures().get(feature);
					Iterator<Feature> ORFeatureSetIterator = ORFeatureSet.iterator();
					while (ORFeatureSetIterator.hasNext()) {
						Feature ORFeature = ORFeatureSetIterator.next();
						if (nameSet.contains(ORFeature.getName().trim().toLowerCase())) {
							System.out.println("<feature automatic=\"selected\" manual=\"selected\" name=\""
									+ ORFeature.getName() + "\"/>");
						} else {
							System.out.println("<feature name=\"" + ORFeature.getName() + "\"/>");
						}
					}
				}
				if (isAbstractFeatureGropingXOR(featureModel, feature)) {
					Set<Feature> XORFeatureSet = featureModel.getXORFeatures().get(feature);
					Iterator<Feature> XORFeatureSetIterator = XORFeatureSet.iterator();
					while (XORFeatureSetIterator.hasNext()) {
						Feature XORFeature = XORFeatureSetIterator.next();
						if (nameSet.contains(XORFeature.getName().trim().toLowerCase())) {
							System.out.println("<feature manual=\"selected\" name=\"" + XORFeature.getName() + "\"/>");
						} else {
							System.out
									.println("<feature automatic=\"unselected\" name=\"" + XORFeature.getName() + "\"/>");
						}
					}
				}
			}
			Map<Feature, Set<Feature>> orFeatures = featureModel.getORFeatures();
			Map<Feature, Set<Feature>> xorFeatures = featureModel.getXORFeatures();
			
			if(!(isFeatureGrouped(orFeatures, feature) || isFeatureGrouped(xorFeatures, feature))) {
				if (nameSet.contains(feature.getName().trim().toLowerCase())) {
					System.out.println("<feature manual=\"selected\" name=\"" + feature.getName() + "\"/>");
				} else {
					System.out.println("<feature name=\"" + feature.getName() + "\"/>");
				}	
			}
		
		}
		
	//printWriter.close();
	}

	private static  boolean isAbstractFeatureGropingXOR(FeatureModel featureModel, Feature feature) {
		return featureModel.getXORFeatures().keySet().contains(feature);
	}

	private static  boolean isAbstractFeatureGropingOR(FeatureModel featureModel, Feature feature) {
		return featureModel.getORFeatures().keySet().contains(feature);
	}

	private static  boolean isAtLeastOneChildFeatureOfOptionalAbstractXORFeatureSelected(FeatureModel featureModel,
			Feature feature, Set<String> nameSet) {
		if (featureModel.getXORFeatures().keySet().contains(feature)) {
			Set<Feature> XORFeatureSet = featureModel.getXORFeatures().get(feature);
			Iterator<Feature> XORFeatureSetIterator = XORFeatureSet.iterator();
			while (XORFeatureSetIterator.hasNext()) {
				Feature XORFeature = XORFeatureSetIterator.next();
				if (nameSet.contains(XORFeature.getName().trim().toLowerCase())) {
					return true;
				}
			}
		}
		return false;
	}

	private static  boolean isAtLeastOneChildFeatureOfOptionalAbstractORFeatureSelected(FeatureModel featureModel,
			Feature feature, Set<String> nameSet) {
		if (featureModel.getORFeatures().keySet().contains(feature)) {
			Set<Feature> ORFeatureSet = featureModel.getORFeatures().get(feature);
			Iterator<Feature> ORFeatureSetIterator = ORFeatureSet.iterator();
			while (ORFeatureSetIterator.hasNext()) {
				Feature ORFeature = ORFeatureSetIterator.next();
				if (nameSet.contains(ORFeature.getName().trim().toLowerCase())) {
					return true;
				}
			}
		}
		return false;
	}
	
	private static  boolean isFeatureGrouped(Map<Feature, Set<Feature>> groupedFeatures, Feature feature) {
	
		Iterator<Entry<Feature, Set<Feature>>> entrySetIterator = groupedFeatures.entrySet().iterator();
		boolean isFeatureGrouped = false;
		
		while(entrySetIterator.hasNext()) {
			Entry<Feature, Set<Feature>> entry = entrySetIterator.next();
			if(entry.getValue().contains(feature)) {
				isFeatureGrouped = isFeatureGrouped || true;
			}
		}
		return isFeatureGrouped;
	}
	
	

}
