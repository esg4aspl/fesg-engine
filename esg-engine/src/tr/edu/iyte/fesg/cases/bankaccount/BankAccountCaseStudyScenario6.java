package tr.edu.iyte.fesg.cases.bankaccount;


import java.util.Set;

import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.fesg.cases.IncrementalTestSequenceCompositionUtilities;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraph;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraphEdge;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraphVertex;
import tr.edu.iyte.fesg.model.FeaturedESG;

public class BankAccountCaseStudyScenario6 extends BankAccountCaseStudyUtilities {
	/**
	 * productID = 1 -> bankAccountProduct_baseProduct
	 * productID = 2 -> bankAccountProduct_cancellable
	 * productID = 3 -> bankAccountProduct_credit
	 * productID = 4 -> bankAccountProduct_dailyLimit
	 * productID = 5 -> bankAccountProduct_interest
	 * productID = 6 -> bankAccountProduct_overdraft
	 * productID = 7 -> bankAccountProduct_creditWithDailyLimit
	 * productID = 8 -> bankAccountProduct_overdraftWithInterest
	 * 
	 * initialize productID to build different products
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		coverageLenght = 10;
		incrementalBasisApproachID = 1;
		productID = 1;
		BankAccountCaseStudyUtilities.buildProductModel();
		FeaturedESG reusableFESG = buildFeaturedESG();
		String coreESGNameOfPL = reusableFESG.getCoreESGName();
		ESG coreESGOfPL = reusableFESG.getCoreESG();

		FeatureBasedIncrementalProductGraph featureBasedIncrementalProductGraph = new FeatureBasedIncrementalProductGraph(
				"BankAccountPL");

		FeatureBasedIncrementalProductGraphVertex start = new FeatureBasedIncrementalProductGraphVertex(reusableFESG,
				true, true);

		FeaturedESG product1 = new FeaturedESG("bankAccountProduct-cancellable", coreESGNameOfPL);
		product1.setCoreESG(coreESGOfPL);
		FeatureBasedIncrementalProductGraphVertex vertex1 = new FeatureBasedIncrementalProductGraphVertex(product1,
				true, false);

		FeatureBasedIncrementalProductGraphEdge edge1 = new FeatureBasedIncrementalProductGraphEdge(start, vertex1);
		Set<ESG> featureESGSet1 = IncrementalTestSequenceCompositionUtilities.buildNewFeatureESGSet("cancelDeposit",
				"cancelWithdraw");
		edge1.addFeatureESGSet(featureESGSet1);

		featureBasedIncrementalProductGraph.addVertex(start);
		featureBasedIncrementalProductGraph.addVertex(vertex1);

		featureBasedIncrementalProductGraph.addEdge(edge1);

		featureBasedIncrementalProductGraph.incrementalTestSequenceCompositionBFT(start, coverageLenght,
				incrementalBasisApproachID);

	}

}
