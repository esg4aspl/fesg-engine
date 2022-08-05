package tr.edu.iyte.fesg.cases.sodavendingmachine;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.fesg.cases.TestCoverageAnalysingUtilities;
import tr.edu.iyte.fesg.conversion.EliminationConditionToJSONFileConverter;
import tr.edu.iyte.fesg.fullesgtoproductesg.BaseProductToCandidateProductESGSetBuilder;
import tr.edu.iyte.fesg.fullesgtoproductesg.EliminationConditionApplier;
import tr.edu.iyte.fesg.model.FeaturedESG;
import tr.edu.iyte.fesg.model.eliminationcondition.EliminationCondition;
import tr.edu.iyte.fesg.model.featuredependencytree.FeatureDependencyTreeBuilder;

public class SVMEliminationConditionJSONFileBuilderApp extends TestCoverageAnalysingUtilities {

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
		
		EliminationConditionApplier eliminationConditionApplier = new EliminationConditionApplier();
		FeatureDependencyTreeBuilder featureDependencyTreeBuilder = eliminationConditionApplier.getFeatureDependencyTreeBuilder(fullProductFESG);


		BaseProductToCandidateProductESGSetBuilder baseProductToCandidateProductESGSetBuilder = new BaseProductToCandidateProductESGSetBuilder(
				baseProductFESG, fullProductFESG, featureDependencyTreeBuilder);

		List<ESG> candidateFeatureESGList = baseProductToCandidateProductESGSetBuilder.getCandidateFeatureESGList();
		List<List<ESG>> featureCombinationList = baseProductToCandidateProductESGSetBuilder.getListOfFeatureESGCombinations(candidateFeatureESGList,3);
		Set<FeaturedESG> properFeaturedESGSet = baseProductToCandidateProductESGSetBuilder.getSetOfProperFeaturedESG(baseProductFESG, featureCombinationList);
		
		Set<Set<String>> toBeRemovedFeaturesSet = baseProductToCandidateProductESGSetBuilder.getToBeRemovedFeatureNamesSet(fullProductFESG, properFeaturedESGSet);
		System.out.println(toBeRemovedFeaturesSet.size());
		

		Set<List<EliminationCondition>> eliminationConditionListSet = baseProductToCandidateProductESGSetBuilder.getEliminationConditionListSet(fullProductFESG, toBeRemovedFeaturesSet);
		

		for(List<EliminationCondition> eliminationConditionList : eliminationConditionListSet) {
			String eliminationConditionName = baseProductToCandidateProductESGSetBuilder.getFileName(eliminationConditionList);
			String eliminationConditionFileName = SPLFolderName + "JSONFiles/svmProduct-eliminationCondition_"
					+ eliminationConditionName + ".json";
			try {
				EliminationConditionToJSONFileConverter.convertEliminationConditionListToJSONFile(eliminationConditionList, eliminationConditionFileName);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
