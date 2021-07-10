package tr.edu.iyte.fesg.cases.studentattendancesystem.allproducts;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import com.google.common.collect.Sets;

import java.util.Set;

import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.fesg.fullesggeneration.FullESGGenerator;
import tr.edu.iyte.fesg.model.FeaturedESG;
import tr.edu.iyte.fesg.model.FeaturedESGComposer;
import tr.edu.iyte.fesg.model.featuremodel.Conjunction;
import tr.edu.iyte.fesg.model.featuremodel.Connector;
import tr.edu.iyte.fesg.model.featuremodel.Disjunction;
import tr.edu.iyte.fesg.model.featuremodel.Feature;
import tr.edu.iyte.fesg.model.featuremodel.FeatureModel;
import tr.edu.iyte.fesg.model.featuremodel.FeaturePriority;
import tr.edu.iyte.fesg.model.featuremodel.Implicant;
import tr.edu.iyte.fesg.model.featuremodel.Implication;

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
		Set<Set<Feature>> orFeatureCombinationsSet = getORFeatureCombinationsSet();
		Set<Set<Feature>> xorFeatureCombinationsSet = getXORFeatureCombinationsSet();
		
		eliminateFeatureCombinationsByConstraints(orFeatureCombinationsSet);
		eliminateFeatureCombinationsByConstraints(xorFeatureCombinationsSet);
		
		Set<Set<Feature>> realFeatureCombinationsSet = getRealFeatureCombinations(orFeatureCombinationsSet, xorFeatureCombinationsSet);
		Set<Set<Feature>> sortedRealFeatureCombinationsSet = sortRealFeaturesByConstraints(realFeatureCombinationsSet);
		
		possibleFeatureCombinationsSet.addAll(sortedRealFeatureCombinationsSet);
	}
		
	public void createFeaturedESGSet() {
		createFeatureCombinationsSet();
		String folderName = "files/Cases/StudentAttendanceSystemPL";
		for (Set<Feature> featureCombination : possibleFeatureCombinationsSet) {
			int size = featureCombination.size() + 1;
			String[] esgName = new String[size];
			String[] esgFileName = new String[size];
			String productESGName = "";
			esgName[0] = "core";
			esgFileName[0] = "core_usefull.mxe";
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
			//System.out.println("Current FeaturedESG " + i + " " + featuredESG.getName());
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

	private Set<Set<Feature>> getORFeatureCombinationsSet() {
		Set<Set<Feature>> featureCombinationsSet = new LinkedHashSet<Set<Feature>>();
		Set<Feature> orFeatureSet = orFeatureSet();
		
		Set<Set<Feature>> tempORFeatureCombinationsSet = Sets.powerSet(orFeatureSet);
		Iterator<Set<Feature>> tempORFeatureCombinationsSetIterator = tempORFeatureCombinationsSet.iterator();

//System.out.println("Start size " + tempORFeatureCombinationsSet.size());
		while(tempORFeatureCombinationsSetIterator.hasNext()) {
			Set<Feature> tempOrFeatureCombination = tempORFeatureCombinationsSetIterator.next();
//System.out.print("temp or feature combination ");tempOrFeatureCombination.forEach(e->System.out.print(e.getName() + " "));System.out.println();
			Set<Feature> distinctParentFeatures = new LinkedHashSet<Feature>();
			if(!tempOrFeatureCombination.isEmpty()) {
				for(Feature feature : tempOrFeatureCombination) {
					distinctParentFeatures.add(feature.getParent());
				}
				if(!(distinctParentFeatures.size() < 4)) {
//System.out.println("VALID COMBINATION ");
					featureCombinationsSet.add(tempOrFeatureCombination);
				}
			}
		}
//System.out.println("End size " + featureCombinationsSet.size());		
		
		return featureCombinationsSet;
	}
	
	private Set<Set<Feature>> getRealFeatureCombinations(Set<Set<Feature>> orFeatureCombinationsSet, Set<Set<Feature>> xorFeatureCombinationsSet){
		Set<Set<Feature>> featureCombinationsSet =  new LinkedHashSet<Set<Feature>>();
		for(Set<Feature> orCombination : orFeatureCombinationsSet) {
			for(Set<Feature> xorCombination : xorFeatureCombinationsSet) {
				Set<Feature> featureCombination = Sets.union(orCombination, xorCombination);
				featureCombinationsSet.add(featureCombination);
			}
		}
		return featureCombinationsSet;
	}
	
	private Set<Set<Feature>> getXORFeatureCombinationsSet(){
		Set<Set<Feature>> xorFeatureCombinationsSet = new LinkedHashSet<Set<Feature>>();
		
		Set<Feature> submitAttendanceMethodXORFeatures = null;
		Set<Feature> notificationXORFeatures = null;
		
		int count = 0;
		for(Set<Feature> xorFeatureSet : featureModel.getXORFeatures().values()) {
			if(count == 0) {
				submitAttendanceMethodXORFeatures = xorFeatureSet;
			}else if(count == 1) {
				notificationXORFeatures = xorFeatureSet;
			}
			count++;
		}

		Set<List<Feature>> cartesian = Sets.cartesianProduct(submitAttendanceMethodXORFeatures,notificationXORFeatures);
		Iterator<List<Feature>> cartesianIterator = cartesian.iterator();
		
		while(cartesianIterator.hasNext()) {
			List<Feature> featureList = cartesianIterator.next();
			Set<Feature> featureSet = new LinkedHashSet<Feature>();
			featureSet.addAll(featureList);
			xorFeatureCombinationsSet.add(featureSet);
		}
		
		
		return xorFeatureCombinationsSet;
	}

/*
	private Set<Set<Feature>> addMandatoryFeaturesToFeatureCombinations(Set<Set<Feature>> featureCombinationsSet) {
		Set<Feature> mandatoryFeatureSet = getMandatoryConcreteFeatureSet();
		Iterator<Set<Feature>> featureCombinationsSetIterator = featureCombinationsSet.iterator();

		Set<Set<Feature>> newFeatureCombinationsSet = new LinkedHashSet<Set<Feature>>();

		while (featureCombinationsSetIterator.hasNext()) {
			Set<Feature> featureCombination = featureCombinationsSetIterator.next();
			Set<Feature> realFeatureCombination = Sets.union(mandatoryFeatureSet, featureCombination);
			newFeatureCombinationsSet.add(realFeatureCombination);
		}

		return newFeatureCombinationsSet;
	}
*/

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
//System.out.print("Current ");
//featureCombination.forEach(e-> System.out.print(e.getName() +  " "));System.out.println();
			for (Implication implication : featureModel.getImpConstraints()) {
//System.out.println("Implication " + implication.toString());
				Implicant leftHandSide = implication.getLeftHandSide();
				boolean hasLHS = false;
				if (leftHandSide instanceof Feature) {
					hasLHS = featureCombination.contains((Feature) leftHandSide);
				} else if (leftHandSide instanceof Connector) {
					for(Feature connFeature : ((Connector) leftHandSide).getFeatureSet()){
						hasLHS = hasLHS || featureCombination.contains(connFeature);
					}
				}
				if (hasLHS) {
//System.out.println("Has LHS");
					boolean hasRHS = true;
					Implicant rightHandSide = implication.getRightHandSide();
					if (rightHandSide instanceof Feature) {
						hasRHS = featureCombination.contains((Feature) rightHandSide);
					} else if (rightHandSide instanceof Connector) {
						if(rightHandSide instanceof Conjunction) {
							hasRHS = featureCombination.containsAll(((Connector) rightHandSide).getFeatureSet());
						}else if(rightHandSide instanceof Disjunction) {
							Set<Feature> featureSet = ((Connector) rightHandSide).getFeatureSet();
							Set<Feature> intersection = Sets.intersection(featureCombination, featureSet);
							hasRHS = (intersection.size() >= 1);
						}
					}
					if (hasRHS == false) {
//System.out.println("NO RHS REMOVED");
						featureCombinationIterator.remove();
						break;
					}
				}
			}
		}
	}

	private void prioritizeFeatures() {
		Set<Feature> implyingOptionalFeatureSet = getImplyingOptionalFeatures();
		Set<Feature> impliedOptionalFeatureSet = getImpliedOptionalFeatures();

		for (Feature feature : featureModel.getFeatureSet()) {
			if (!feature.isMandatory()) {
				if (implyingOptionalFeatureSet.contains(feature) && !impliedOptionalFeatureSet.contains(feature)) {
					feature.setPriority(FeaturePriority.LOW);
				} else if (implyingOptionalFeatureSet.contains(feature) && impliedOptionalFeatureSet.contains(feature)) {
					feature.setPriority(FeaturePriority.MODERATE);
				} else if (!implyingOptionalFeatureSet.contains(feature) && impliedOptionalFeatureSet.contains(feature)) {
					feature.setPriority(FeaturePriority.AVERAGE);
				} else {
					feature.setPriority(FeaturePriority.HIGH);
				}
			} else {
				feature.setPriority(FeaturePriority.MAJOR);
			}
		}
	}

	private Set<Feature> getImplyingOptionalFeatures() {
		Set<Feature> implyingOptionalFeatureSet = new LinkedHashSet<Feature>();
		for (Implication implication : featureModel.getImpConstraints()) {
//System.out.println("Implication " + implication.toString());
			Implicant leftHandSide = implication.getLeftHandSide();
			if (leftHandSide instanceof Feature) {
				if (!((Feature) leftHandSide).isMandatory()) {
					implyingOptionalFeatureSet.add(((Feature) leftHandSide));
				}
			} else if (leftHandSide instanceof Connector) {
				for (Feature feature : ((Connector) leftHandSide).getFeatureSet()) {
					if (!feature.isMandatory()) {
						implyingOptionalFeatureSet.add(feature);
					}
				}
			}
		}

		return implyingOptionalFeatureSet;
	}

	private Set<Feature> getImpliedOptionalFeatures() {
		Set<Feature> impliedOptionalFeatureSet = new LinkedHashSet<Feature>();
		for (Implication implication : featureModel.getImpConstraints()) {
//System.out.println("Implication " + implication.toString());
			Implicant rightHandSide = implication.getRightHandSide();
			if (rightHandSide instanceof Feature) {
				if (!((Feature) rightHandSide).isMandatory()) {
					impliedOptionalFeatureSet.add(((Feature) rightHandSide));
				}
			} else if (rightHandSide instanceof Connector) {
				for (Feature feature : ((Connector) rightHandSide).getFeatureSet()) {
					if (!feature.isMandatory()) {
						impliedOptionalFeatureSet.add(feature);
					}
				}
			}
		}
		return impliedOptionalFeatureSet;
	}
/*
	private Set<Feature> getMandatoryConcreteFeatureSet() {
		Set<Feature> mandatoryFeatureSet = new LinkedHashSet<Feature>();
		for (Feature feature : featureModel.getFeatureSet()) {
			if (!feature.equals(featureModel.getRoot()) && !feature.isAbstract() && feature.isMandatory()) {
				mandatoryFeatureSet.add(feature);
			}
		}

		return mandatoryFeatureSet;
	}
*/
	
	private Set<Feature> orFeatureSet() {
		Set<Feature> orFeatureSet = new LinkedHashSet<Feature>();
		for (Feature feature : featureModel.getFeatureSet()) {
			if (!feature.equals(featureModel.getRoot()) && feature.getParent().equals(featureModel.getRoot())  && !feature.isAbstract()) {
				orFeatureSet.add(feature);
			}
		}
		for (Entry<Feature, Set<Feature>> entry : featureModel.getORFeatures().entrySet()) {
			orFeatureSet.addAll(entry.getValue());
		}
//orFeatureSet.forEach(e->System.out.println("OR Feature " + e.getName()));
		return orFeatureSet;
	}
}
