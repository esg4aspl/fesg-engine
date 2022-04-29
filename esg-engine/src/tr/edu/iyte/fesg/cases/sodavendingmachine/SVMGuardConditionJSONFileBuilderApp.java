package tr.edu.iyte.fesg.cases.sodavendingmachine;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import tr.edu.iyte.esg.conversion.json.GuardConditionToJSONFileConverter;
import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.esg.model.guardcondition.GuardCondition;
import tr.edu.iyte.fesg.cases.TestCoverageAnalysingUtilities;
import tr.edu.iyte.fesg.fullesgtoproductesg.BaseProductToCandidateProductESGSetBuilder;
import tr.edu.iyte.fesg.fullesgtoproductesg.GuardConditionApplier;
import tr.edu.iyte.fesg.model.FeaturedESG;
import tr.edu.iyte.fesg.model.featuredependencytree.FeatureDependencyTreeBuilder;

public class SVMGuardConditionJSONFileBuilderApp extends TestCoverageAnalysingUtilities {

	public static void main(String[] args) {
		
		String SPLFolderName = "files/Cases/SodaVendingMachinePL/";

		/*
		 * baseProduct
		 */
		productID = 7;
		SVMCaseStudyUtilities.buildProductModel();
		FeaturedESG baseProductFESG = buildFeaturedESG();

		productID = 6;
		SVMCaseStudyUtilities.buildProductModel();
		FeaturedESG fullProductFESG = buildFeaturedESG();
		
		GuardConditionApplier guardConditionApplier = new GuardConditionApplier();
		FeatureDependencyTreeBuilder featureDependencyTreeBuilder = guardConditionApplier.getFeatureDependencyTreeBuilder(fullProductFESG);


		BaseProductToCandidateProductESGSetBuilder baseProductToCandidateProductESGSetBuilder = new BaseProductToCandidateProductESGSetBuilder(
				baseProductFESG, fullProductFESG, featureDependencyTreeBuilder);

		List<ESG> candidateFeatureESGList = baseProductToCandidateProductESGSetBuilder.getCandidateFeatureESGList();
		List<List<ESG>> featureCombinationList = baseProductToCandidateProductESGSetBuilder.getListOfFeatureESGCombinations(candidateFeatureESGList,3);
		Set<FeaturedESG> properFeaturedESGSet = baseProductToCandidateProductESGSetBuilder.getSetOfProperFeaturedESG(baseProductFESG, featureCombinationList);
		
		Set<Set<String>> toBeRemovedFeaturesSet = baseProductToCandidateProductESGSetBuilder.getToBeRemovedFeatureNamesSet(fullProductFESG, properFeaturedESGSet);
		System.out.println(toBeRemovedFeaturesSet.size());
		

		Set<List<GuardCondition>> guardConditionListSet = baseProductToCandidateProductESGSetBuilder.getGuardConditionListSet(fullProductFESG, toBeRemovedFeaturesSet);
		
	/*	
		for(List<GuardCondition> guardConditionList : guardConditionListSet) {
			String guardConditionName = baseProductToCandidateProductESGSetBuilder.getFileName(guardConditionList);
			String guardConditionFileName = SPLFolderName + "JSONFiles/svmProduct-guardCondition_"
					+ guardConditionName + ".json";
			try {
				GuardConditionToJSONFileConverter.convertGuardConditionListToJSONFile(guardConditionList, guardConditionFileName);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
*/
	}

}
