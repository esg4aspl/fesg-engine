package tr.edu.iyte.fesg.cases.bankaccount;

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

public class BankAccountEliminationConditionJSONFileBuilderApp_Credit extends TestCoverageAnalysingUtilities {

	public static void main(String[] args) {

		String SPLFolderName = "files/Cases/BankAccountPL/";
		/*
		 * baseProduct
		 * core deposit withdraw
		 */
		productID = 1;
		BankAccountCaseStudyUtilities.buildProductModel();
		FeaturedESG baseProductFESG = buildFeaturedESG();
		/*
		 * Full Product-Credit
		 */
		productID = 9;
		BankAccountCaseStudyUtilities.buildProductModel();
		FeaturedESG fullProductFESG = buildFeaturedESG();
		
		EliminationConditionApplier guardConditionApplier = new EliminationConditionApplier();
		FeatureDependencyTreeBuilder featureDependencyTreeBuilder = guardConditionApplier.getFeatureDependencyTreeBuilder(fullProductFESG);


		BaseProductToCandidateProductESGSetBuilder baseProductToCandidateProductESGSetBuilder = new BaseProductToCandidateProductESGSetBuilder(
				baseProductFESG, fullProductFESG, featureDependencyTreeBuilder);

		List<ESG> candidateFeatureESGList = baseProductToCandidateProductESGSetBuilder.getCandidateFeatureESGList();
		List<List<ESG>> featureCombinationList = baseProductToCandidateProductESGSetBuilder.getListOfFeatureESGCombinations(candidateFeatureESGList,3);
		Set<FeaturedESG> properFeaturedESGSet = baseProductToCandidateProductESGSetBuilder.getSetOfProperFeaturedESG(baseProductFESG, featureCombinationList);
		
		Set<Set<String>> toBeRemovedFeaturesSet = baseProductToCandidateProductESGSetBuilder.getToBeRemovedFeatureNamesSet(fullProductFESG, properFeaturedESGSet);
		System.out.println(toBeRemovedFeaturesSet.size());
		

		Set<List<EliminationCondition>> guardConditionListSet = baseProductToCandidateProductESGSetBuilder.getEliminationConditionListSet(fullProductFESG, toBeRemovedFeaturesSet);
		
		
		for(List<EliminationCondition> guardConditionList : guardConditionListSet) {
			String guardConditionName = baseProductToCandidateProductESGSetBuilder.getFileName(guardConditionList);
			String guardConditionFileName = SPLFolderName + "JSONFiles/bankAccountProduct-guardCondition_"
					+ guardConditionName + ".json";
			try {
				EliminationConditionToJSONFileConverter.convertEliminationConditionListToJSONFile(guardConditionList, guardConditionFileName);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
