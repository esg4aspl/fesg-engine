package tr.edu.iyte.fesg.fullesgtoproductesg;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.esg.model.Edge;
import tr.edu.iyte.esg.model.guardcondition.GuardCondition;
import tr.edu.iyte.esg.model.validation.ESGValidator;
import tr.edu.iyte.fesg.fullesggeneration.FullESGGenerator;
import tr.edu.iyte.fesg.model.FeaturedESG;
import tr.edu.iyte.fesg.model.featuredependencytree.FeatureDependencyTreeBuilder;
import tr.edu.iyte.fesg.testsequencecomposition.TestSequenceCompositionUtilities;

public class BaseProductToCandidateProductESGSetBuilder {
	
	private FeaturedESG baseProductFESG;
	private FeaturedESG fullProductFESG;
	private FeatureDependencyTreeBuilder featureDependencyTreeBuilder; 
	
	public BaseProductToCandidateProductESGSetBuilder(FeaturedESG baseProductFESG, FeaturedESG fullProductFESG, FeatureDependencyTreeBuilder featureDependencyTreeBuilder) {
		this.baseProductFESG = baseProductFESG;
		this.fullProductFESG = fullProductFESG;
		this.featureDependencyTreeBuilder = featureDependencyTreeBuilder;
	}
	
	public FeaturedESG getBaseProductFESG() {
		return baseProductFESG;
	}
	
	public FeaturedESG getFullProductFESG() {
		return fullProductFESG;
	}
	
	public FeatureDependencyTreeBuilder getFeatureDependencyTreeBuilder() {
		return featureDependencyTreeBuilder;
	}

	public List<ESG> getCandidateFeatureESGList() {

		List<ESG> candidateFeatureESGList = new LinkedList<ESG>();
		Iterator<ESG> featuredESGSetIterator = fullProductFESG.getFeaturedESGSet().iterator();

		while (featuredESGSetIterator.hasNext()) {
			ESG candidateFeatureESG = featuredESGSetIterator.next();
			String candidateFeatureESGName = candidateFeatureESG.getName();

			ESG controlESG = baseProductFESG.getFeatureESGByName(candidateFeatureESGName);

			if (controlESG == null) {
				candidateFeatureESGList.add(candidateFeatureESG);
			}
		}
		System.out.println("Candidate Feature ESG list");
		candidateFeatureESGList.forEach(e -> System.out.print(e.getName() + " "));
		System.out.println();

		return candidateFeatureESGList;
	}
	
	public List<List<ESG>> getListOfFeatureESGCombinations(List<ESG> candidateFeatureESGList, int r) {
		 
		int n = candidateFeatureESGList.size();

		ESG candidateFeatureESGArr[] = new ESG[n];
		for(int i = 0; i < n; i++) {
			candidateFeatureESGArr[i] = candidateFeatureESGList.get(i);
		}
		ESG featureESGCombinationArr[] = new ESG[r];
		
		FeatureCombinationUtility featureCombinationUtility = new FeatureCombinationUtility();
		featureCombinationUtility.combinationUtil(candidateFeatureESGArr, n, r, 0, featureESGCombinationArr, 0);
		
		List<List<ESG>> featureCombinationList = featureCombinationUtility.getFeatureCombinationList();
		
		System.out.println(featureCombinationUtility.toString());
		return featureCombinationList;
		
	}
	
	public Set<FeaturedESG> getSetOfProperFeaturedESG(FeaturedESG baseProductFESG, List<List<ESG>> featureCombinationList){
		Set<FeaturedESG> properFeaturedESGSet = new LinkedHashSet<>();
		Iterator<List<ESG>> featureCombinationListIterator = featureCombinationList.iterator();
		int index = 0;
		while(featureCombinationListIterator.hasNext()) {
			List<ESG> featureCombination = featureCombinationListIterator.next();
			FeaturedESG newProduct = new FeaturedESG(baseProductFESG);
			
			Iterator<ESG> featureCombinationIterator = featureCombination.iterator();
			String newProductName = "";
			while(featureCombinationIterator.hasNext()) {
				ESG featureESG = featureCombinationIterator.next();
				newProduct.addFeatureESG(featureESG);
				newProductName += featureESG.getName() + "_";
			}
			
			FullESGGenerator fullESGGenerator = new FullESGGenerator(index, newProduct);
			fullESGGenerator.generateFullESG();
			ESG fullESG = fullESGGenerator.getFullESG();
			index++;
			ESGValidator esgValidator = new ESGValidator();
			if(esgValidator.isValid(fullESG)) {
				properFeaturedESGSet.add(newProduct);
				newProduct.setName(newProductName);
			}
			
		}
		
		System.out.println(properFeaturedESGSet.size());
		for(FeaturedESG FESG: properFeaturedESGSet) {
			System.out.println(FESG.getName());
		}
		
		
		return properFeaturedESGSet;
	}
	
	public Set<Set<String>> getToBeRemovedFeatureNamesSet(FeaturedESG fullProductFESG, Set<FeaturedESG> properFeaturedESGSet){
		Set<Set<String>> toBeRemovedFeaturesSet = new LinkedHashSet<Set<String>>();
		
		Iterator<FeaturedESG> properFeaturedESGSetIterator = properFeaturedESGSet.iterator();
		
		while(properFeaturedESGSetIterator.hasNext()) {
			FeaturedESG properFESG = properFeaturedESGSetIterator.next();
			System.out.print(properFESG.getName() + "--->");
			Set<String> featureNameSet = new LinkedHashSet<>();
			for(ESG featureESG : fullProductFESG.getFeatureESGSet()) {
				String featureESGName = featureESG.getName();
				ESG foundFeatureESG = properFESG.getFeatureESGByName(featureESGName);
				
				if(foundFeatureESG == null) {
					featureNameSet.add(featureESGName);
					System.out.print(featureESGName + ", ");
					
				}
			}
			System.out.println();
			toBeRemovedFeaturesSet.add(featureNameSet);	
		}
		
		return toBeRemovedFeaturesSet;
	}
	
	public Set<List<GuardCondition>> getGuardConditionListSet(FeaturedESG fullProductFESG, Set<Set<String>> toBeRemovedFeaturesSet){
		Set<List<GuardCondition>> guardConditionListSet = new LinkedHashSet<>();
		
		for(Set<String> featureNameSet : toBeRemovedFeaturesSet) {
			List<GuardCondition> guardConditionList = new LinkedList<GuardCondition>();
			int ID = 0;
			for(String featureName : featureNameSet) {
				GuardCondition guardCondition = new GuardCondition(ID,featureName, false);
				ESG featureESG = fullProductFESG.getFeatureESGByName(featureName);
				addEdgesToBeRemoved(guardCondition, featureESG);
				guardConditionList.add(guardCondition);
				ID++;
			}
			guardConditionListSet.add(guardConditionList);
			guardConditionList.forEach(e->System.out.print(e.getID() + " "+ e.getConditionName() + " ----"));
			System.out.println();
		}
		System.out.println(guardConditionListSet.size());
		return guardConditionListSet;
	}
	
	public String getFileName(List<GuardCondition> guardConditionList) {
		String s = "";
		
		for(int i = 0; i < guardConditionList.size() - 1 ; i++) {
			GuardCondition guardCondition = guardConditionList.get(i);
			s+= guardCondition.getConditionName() + "_";
		}
		GuardCondition guardCondition = guardConditionList.get(guardConditionList.size() - 1);
		s+= guardCondition.getConditionName() ;
		return s;
	}
	
	private void  addEdgesToBeRemoved(GuardCondition guardCondition, ESG featureESG) {

		for(Edge edge : featureESG.getEdgeList()) {
			String sourceEvetName = edge.getSource().getEvent().getName();
			String targetEventName = edge.getTarget().getEvent().getName();
			
			if(TestSequenceCompositionUtilities.isConnectionPoint(sourceEvetName)) {
				sourceEvetName = TestSequenceCompositionUtilities.getESGAndEventNameInConnectionPoint(sourceEvetName)[1];
			}
			
			if(TestSequenceCompositionUtilities.isConnectionPoint(targetEventName)) {
				targetEventName = TestSequenceCompositionUtilities.getESGAndEventNameInConnectionPoint(targetEventName)[1];
			}
			
			if(!sourceEvetName.equals("]") && !targetEventName.equals("["))	
				guardCondition.addEdgeToBeRemoved(sourceEvetName, targetEventName);

		}

	}
	
	

	



}
