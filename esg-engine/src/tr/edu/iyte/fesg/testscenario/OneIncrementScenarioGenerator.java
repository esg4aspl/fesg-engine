package tr.edu.iyte.fesg.testscenario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.fesg.model.FeaturedESG;


public class OneIncrementScenarioGenerator {

	private Set<FeaturedESG> productSet;
	private Map<Integer, Set<FeaturedESG>> numberOfFeaturesFeaturedESGMap;
	private List<OneIncrementScneraio> scenarioList;

	public OneIncrementScenarioGenerator(Set<FeaturedESG> productSet) {
		setProductSet(productSet);
		numberOfFeaturesFeaturedESGMap = new LinkedHashMap<Integer, Set<FeaturedESG>>();
		setNumberOfFeaturesFeaturedESGMap();
		scenarioList = new ArrayList<OneIncrementScneraio>();
	}

	public void generateOneIncrementScnearioList() {
		Iterator<FeaturedESG> productSetIterator = productSet.iterator();
		int id = -1;
		int maxNumberOfFeatures = maxNumberOfFeatures();
		while (productSetIterator.hasNext()) {
			FeaturedESG featuredESG = productSetIterator.next();
//System.out.println("Existing product " + featuredESG.getName());
			int numberOfFeatures = featuredESG.getFeaturedESGSet().size();
			Integer targetNumberOfFeatures = numberOfFeatures + 1;
			if (targetNumberOfFeatures <= maxNumberOfFeatures) {
				Set<FeaturedESG> targetNumberOfFeaturedESGSet = numberOfFeaturesFeaturedESGMap
						.get(targetNumberOfFeatures);
				Set<ESG> featuredESGSet = featuredESG.getFeaturedESGSet();
				Set<String> nameOfExistingFeaturesSet = nameOfFeaturesSet(featuredESGSet);
//System.out.print("Existing features ");featuredESGSet.forEach(e->System.out.print(e.getName() + " "));System.out.println();
				Iterator<FeaturedESG> targetNumberOfFeaturedESGSetIterator = targetNumberOfFeaturedESGSet.iterator();

				while (targetNumberOfFeaturedESGSetIterator.hasNext()) {
					FeaturedESG targetFeaturedESG = targetNumberOfFeaturedESGSetIterator.next();
//System.out.println("Target product " + targetFeaturedESG.getName());
					Set<String> nameOfTargetFeaturesSet = nameOfFeaturesSet(targetFeaturedESG.getFeaturedESGSet());
//System.out.print("Target features ");targetFeaturedESGSet.forEach(e->System.out.print(e.getName() + " "));System.out.println();					
					if (nameOfTargetFeaturesSet.containsAll(nameOfExistingFeaturesSet)) {
						nameOfTargetFeaturesSet.removeAll(nameOfExistingFeaturesSet);
						for (String incrementName : nameOfTargetFeaturesSet) {
							ESG incrementFeature = targetFeaturedESG.getFeatureESGByName(incrementName);
							id++;
//System.out.println("increment " + incrementFeature.getName());
							OneIncrementScneraio scenario = new OneIncrementScneraio(id, featuredESG, incrementFeature,
									targetFeaturedESG);
							scenarioList.add(scenario);
						}
					}
				}
			}
		}
		
		Collections.sort(scenarioList);
	}

	

	public Set<String> nameOfFeaturesSet(Set<ESG> featuredESGSet) {
		Set<String> nameOfFeaturesSet = new LinkedHashSet<String>();
		for (ESG featureESG : featuredESGSet) {
			nameOfFeaturesSet.add(featureESG.getName());
		}
		return nameOfFeaturesSet;
	}

	/**
	 * 
	 * numberOfFeatures(including core feature)
	 * 
	 */
	public void setNumberOfFeaturesFeaturedESGMap() {
		Iterator<FeaturedESG> productSetIterator = productSet.iterator();
		while (productSetIterator.hasNext()) {
			FeaturedESG featuredESG = productSetIterator.next();
			Integer numberOfFeatures = featuredESG.getFeaturedESGSet().size();
			if (numberOfFeaturesFeaturedESGMap.keySet().contains(numberOfFeatures)) {
				numberOfFeaturesFeaturedESGMap.get(numberOfFeatures).add(featuredESG);
			} else {
				Set<FeaturedESG> featuredESGSet = new LinkedHashSet<FeaturedESG>();
				featuredESGSet.add(featuredESG);
				numberOfFeaturesFeaturedESGMap.put(numberOfFeatures, featuredESGSet);
			}
		}
		/*
		 for(Integer i : numberOfFeaturesFeaturedESGMap.keySet()) {
		 System.out.println(i + " features"); for(FeaturedESG fesg :
		 numberOfFeaturesFeaturedESGMap.get(i)) { System.out.println("\t"+
		 fesg.getName()); } }
		 */
	}

	/**
	 * 
	 * @param numberOfFeatures(including core feature)
	 * @return
	 */
	public Set<FeaturedESG> getProductsHavingCertainNumberOfFeatures(int numberOfFeatures) {
		Iterator<FeaturedESG> productSetIterator = productSet.iterator();
		Set<FeaturedESG> foundProductSet = new LinkedHashSet<FeaturedESG>();
		while (productSetIterator.hasNext()) {
			FeaturedESG featuredESG = productSetIterator.next();
			if (featuredESG.getFeaturedESGSet().size() == numberOfFeatures) {
				foundProductSet.add(featuredESG);
			}
		}
		return foundProductSet;
	}

	/**
	 * 
	 * numberOfFeatures(including core feature)
	 * 
	 */
	public int maxNumberOfFeatures() {
		int maxNumberOfFeatures = 0;
		Iterator<FeaturedESG> productSetIterator = productSet.iterator();
		while (productSetIterator.hasNext()) {
			FeaturedESG featuredESG = productSetIterator.next();
			if (featuredESG.getFeaturedESGSet().size() > maxNumberOfFeatures)
				maxNumberOfFeatures = featuredESG.getFeaturedESGSet().size();
		}
		return maxNumberOfFeatures;
	}

	/**
	 * 
	 * numberOfFeatures(including core feature)
	 * 
	 */
	public int minNumberOfFeatures() {
		int minNumberOfFeatures = Integer.MAX_VALUE;
		Iterator<FeaturedESG> productSetIterator = productSet.iterator();
		while (productSetIterator.hasNext()) {
			FeaturedESG featuredESG = productSetIterator.next();
			if (featuredESG.getFeaturedESGSet().size() < minNumberOfFeatures)
				minNumberOfFeatures = featuredESG.getFeaturedESGSet().size();
		}
		return minNumberOfFeatures;
	}

	public Set<FeaturedESG> getProductSet() {
		return productSet;
	}

	public void setProductSet(Set<FeaturedESG> productSet) {
		this.productSet = productSet;
	}

	public Map<Integer, Set<FeaturedESG>> getNumberOfFeaturesFeaturedESGMap() {
		return numberOfFeaturesFeaturedESGMap;
	}

	public List<OneIncrementScneraio> getScenarioList() {
		return scenarioList;
	}

}
