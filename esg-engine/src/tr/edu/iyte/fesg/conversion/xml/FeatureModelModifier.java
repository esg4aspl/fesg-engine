package tr.edu.iyte.fesg.conversion.xml;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import tr.edu.iyte.fesg.model.FeaturedESG;
import tr.edu.iyte.fesg.model.featuremodel.Feature;
import tr.edu.iyte.fesg.model.featuremodel.FeatureModel;

public class FeatureModelModifier {
	
	public static void modifyFeatureModel(FeaturedESG FESG, FeatureModel featureModel) {
		
		Iterator<Feature> featureIterator = featureModel.getFeatureSet().iterator();
		Set<String> nameSet = FESG.getFeatureNames();
		Set<Feature> featuresToBeRemoved = new LinkedHashSet<Feature>();
		while(featureIterator.hasNext()) {
			Feature feature = featureIterator.next();
			
			if(!feature.isAbstract() && !nameSet.contains(feature.getName())) {
				featuresToBeRemoved.add(feature);
			}
		}
		
		for(Feature feature : featuresToBeRemoved) {
			System.out.println(feature.getName() + " to be removed");
			featureModel.removeFeature(feature);
		}
		
		System.out.println(featureModel);
	}

}
