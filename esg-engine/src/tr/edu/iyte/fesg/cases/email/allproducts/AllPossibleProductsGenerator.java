package tr.edu.iyte.fesg.cases.email.allproducts;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;


import com.google.common.collect.Sets;

import java.util.Set;

import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.fesg.fullesggeneration.FullESGGenerator;
import tr.edu.iyte.fesg.model.FeaturedESG;
import tr.edu.iyte.fesg.model.FeaturedESGComposer;
import tr.edu.iyte.fesg.model.featuremodel.Connector;
import tr.edu.iyte.fesg.model.featuremodel.Feature;
import tr.edu.iyte.fesg.model.featuremodel.FeatureModel;
import tr.edu.iyte.fesg.model.featuremodel.FeaturePriority;


public class AllPossibleProductsGenerator {

	private FeatureModel featureModel;
	private Set<Set<Feature>> possibleFeatureCombinationsSet;
	private Set<FeaturedESG> featuredESGSet;
	private Set<ESG> fullESGSet;

	public AllPossibleProductsGenerator(FeatureModel featureModel) {
		this.featureModel = featureModel;
		possibleFeatureCombinationsSet = new LinkedHashSet<Set<Feature>>();
		featuredESGSet = new LinkedHashSet<FeaturedESG>();
		fullESGSet = new LinkedHashSet<ESG>();
	}

	public FeatureModel getFeatureModel() {
		return featureModel;
	}

	public Set<Set<Feature>> getPossibleFeatureCombinationsSet() {
		return possibleFeatureCombinationsSet;
	}

	public Set<FeaturedESG> getFeaturedESGSet() {
		return featuredESGSet;
	}

	public Set<ESG> getFullESGSet() {
		return fullESGSet;
	}
	
	public void createFeatureCombinationsSet() {
		Set<Set<Feature>> featureCombinationsSet = getFeatureCombinationsSet();
		Set<Set<Feature>> eliminatedFeatureCombinationsSet = new LinkedHashSet<Set<Feature>>();
		eliminatedFeatureCombinationsSet.addAll(featureCombinationsSet);
		eliminateFeatureCombinationsByConstraints(eliminatedFeatureCombinationsSet);
		Set<Set<Feature>> sortedRealFeatureCombinationsSet = sortRealFeaturesByConstraints(eliminatedFeatureCombinationsSet);
		//printFeatureCombinations(sortedRealFeatureCombinationsSet);
		possibleFeatureCombinationsSet.addAll(sortedRealFeatureCombinationsSet);
	}
	
	public void createFeaturedESGSet() {
		createFeatureCombinationsSet();
		String folderName = "files/MXEFiles/EmailPL";
		for (Set<Feature> featureCombination : possibleFeatureCombinationsSet) {
			int size = featureCombination.size() + 1;
			String[] esgName = new String[size];
			String[] esgFileName = new String[size];
			String productESGName = "";
			esgName[0] = "core";
			esgFileName[0] = "core.mxe";
			int i = 1;
			for (Feature feature : featureCombination) {
				esgName[i] = feature.getName();
				esgFileName[i] = feature.getName() + ".mxe";
				if (productESGName.equals("")) {
					if (!feature.isMandatory()) {
						productESGName += feature.getName();
					}
				} else {
					if (!feature.isMandatory()) {
						productESGName += "_" + feature.getName();
					}
				}
				i++;
			}
			if (productESGName.equals("")) {
				productESGName += "baseProduct";
			}
			FeaturedESGComposer featuredESGComposer = new FeaturedESGComposer(productESGName, "core");
			FeaturedESG featuredESG = featuredESGComposer.copmposeFeaturedESGFromMXEFile(folderName, esgFileName,
					esgName);
			featuredESGSet.add(featuredESG);
		}
	}

	public void createFullESGSet() {
		createFeaturedESGSet();
		int i = 0;
		for (FeaturedESG featuredESG : featuredESGSet) {
			FullESGGenerator fullESGGenerator = new FullESGGenerator(i, featuredESG);
			i++;
			fullESGGenerator.generateFullESG();
			ESG fullESG = fullESGGenerator.getFullESG();
			fullESGSet.add(fullESG);
		}
	}

	public FeaturedESG findFeaturedESGByProductName(String productName) {
		FeaturedESG found = null;
		for (FeaturedESG featuredESG : featuredESGSet) {
			if (featuredESG.getName().equalsIgnoreCase(productName)) {
				found = featuredESG;
				break;
			}
		}
		return found;
	}

	private Set<Set<Feature>> getFeatureCombinationsSet() {
		Set<Set<Feature>> featureCombinationsSet = new LinkedHashSet<Set<Feature>>();
		Set<Feature> optionalFeatureSet = getOptionalFeatureSet();
		featureCombinationsSet = Sets.powerSet(optionalFeatureSet);
		
		return featureCombinationsSet;
	}

	private Set<Set<Feature>> sortRealFeaturesByConstraints(Set<Set<Feature>> featureCombinationsSet) {
		Set<Set<Feature>> newFeatureCombinationsSet = new LinkedHashSet<Set<Feature>>();
		prioritizeFeatures();
		Iterator<Set<Feature>> featureCombinationsSetIterator = featureCombinationsSet.iterator();
		while (featureCombinationsSetIterator.hasNext()) {
			Set<Feature> featureCombination = featureCombinationsSetIterator.next();
//System.out.print("Feature combination ");featureCombination.forEach(e->System.out.print(e.getName() + " ")); System.out.println();
			List<Feature> featureList = new LinkedList<Feature>();
			featureList.addAll(featureCombination);
			Collections.sort(featureList);
//System.out.print("Sorted list ");featureList.forEach(e->System.out.print(e.getName() + " ")); System.out.println();
			Set<Feature> newFeatureCombination = new LinkedHashSet<Feature>();
			newFeatureCombination.addAll(featureList);
//System.out.print("New Feature combination ");newFeatureCombination.forEach(e->System.out.print(e.getName() + " ")); System.out.println();
			newFeatureCombinationsSet.add(newFeatureCombination);
		}
		return newFeatureCombinationsSet;
	}

	private void eliminateFeatureCombinationsByConstraints(Set<Set<Feature>> featureCombinationsSet) {
		Iterator<Set<Feature>> featureCombinationIterator = featureCombinationsSet.iterator();
		while (featureCombinationIterator.hasNext()) {
			Set<Feature> featureCombination = featureCombinationIterator.next();
//System.out.print("Current ");featureCombination.forEach(e-> System.out.print(e.getName() +  " "));System.out.println();
			for(Connector connector : featureModel.getConnConstraints()) {
				Set<Feature> disjunctionFeatureSet = connector.getFeatureSet();
//System.out.print("Connector ");disjunctionFeatureSet.forEach(e-> System.out.print(e.getName() +  " "));System.out.println();
				if(featureCombination.containsAll(disjunctionFeatureSet)) {
//System.out.print("Removed ");featureCombination.forEach(e-> System.out.print(e.getName() +  " "));System.out.println();
					featureCombinationIterator.remove();
				}
			}
		}
	}

	private void prioritizeFeatures() {
		for (Feature feature : featureModel.getFeatureSet()) {
			if (!feature.isMandatory()) {
				feature.setPriority(FeaturePriority.MODERATE);
			} else {
				feature.setPriority(FeaturePriority.MAJOR);
			}
		}
	}
	

	private Set<Feature> getOptionalFeatureSet() {
		Set<Feature> optionalFeatureSet = new LinkedHashSet<Feature>();
		for (Feature feature : featureModel.getFeatureSet()) {
			if (!feature.equals(featureModel.getRoot()) /*&& !feature.getParent().equals(featureModel.getRoot())*/ && !feature.isAbstract() && !feature.isMandatory()) {
				optionalFeatureSet.add(feature);
			}
		}

		return optionalFeatureSet;
	}
	
	public void printFeatureCombinations(Set<Set<Feature>> featureCombinationsSet) {
		for(Set<Feature> featureSet : featureCombinationsSet) {
			System.out.print("Feature Combination ");
			featureSet.forEach(e-> System.out.print(e.getName() +  " "));System.out.println();
		}
	}

}
