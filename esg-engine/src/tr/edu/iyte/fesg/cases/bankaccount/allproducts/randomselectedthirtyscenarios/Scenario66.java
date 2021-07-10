package tr.edu.iyte.fesg.cases.bankaccount.allproducts.randomselectedthirtyscenarios;

import java.util.Set;

import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.fesg.cases.IncrementalTestSequenceCompositionUtilities;
import tr.edu.iyte.fesg.cases.bankaccount.BankAccountCaseStudyUtilities;
import tr.edu.iyte.fesg.cases.bankaccount.allproducts.AllPossibleProductsGenerator;
import tr.edu.iyte.fesg.cases.bankaccount.allproducts.AllPossibleProductsGeneratorApp;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraph;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraphEdge;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraphVertex;
import tr.edu.iyte.fesg.model.FeaturedESG;

public class Scenario66
		extends BankAccountCaseStudyUtilities {

	public static void main(String[] args) {
		createCaseStudyFilePathObjects();
		initializeCaseStudyFolderNames();
		
		coverageLenght = 2;
		incrementalBasisApproachID = 1;
		// PUC ID-Scenario ID-Existing Product ID
		existingProductESGName.append("ba-30-66-24");

		AllPossibleProductsGenerator allPossibleProductsGenerator = AllPossibleProductsGeneratorApp.getAllPossibleProductsGenerator();
		FeaturedESG reusableFESG = AllPossibleProductsGeneratorApp.findFeaturedESGByProductName(allPossibleProductsGenerator,"credit");
		String coreESGNameOfPL = reusableFESG.getCoreESGName();
		ESG coreESGOfPL = reusableFESG.getCoreESG();

		FeatureBasedIncrementalProductGraph featureBasedIncrementalProductGraph = new FeatureBasedIncrementalProductGraph(
				"BankAccountPL");
		FeatureBasedIncrementalProductGraphVertex start = new FeatureBasedIncrementalProductGraphVertex(reusableFESG,true,true);
		
		FeaturedESG product1 = new FeaturedESG("credit_cancelWithdraw",coreESGNameOfPL);
		product1.setCoreESG(coreESGOfPL);
		FeatureBasedIncrementalProductGraphVertex vertex1 = new FeatureBasedIncrementalProductGraphVertex(product1, true,false);
		
		FeatureBasedIncrementalProductGraphEdge edge1 = new FeatureBasedIncrementalProductGraphEdge(start, vertex1);
		Set<ESG> featureESGSet1 = IncrementalTestSequenceCompositionUtilities.buildNewFeatureESGSet("cancelWithdraw");
		edge1.addFeatureESGSet(featureESGSet1);
		
		featureBasedIncrementalProductGraph.addVertex(start);
		featureBasedIncrementalProductGraph.addVertex(vertex1);
		

		featureBasedIncrementalProductGraph.addEdge(edge1);
		
		featureBasedIncrementalProductGraph.incrementalTestSequenceCompositionBFT(start,coverageLenght,incrementalBasisApproachID);

		
		
	}

}
