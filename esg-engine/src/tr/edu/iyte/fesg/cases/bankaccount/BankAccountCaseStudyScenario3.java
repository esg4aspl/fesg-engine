package tr.edu.iyte.fesg.cases.bankaccount;

import java.util.Set;

import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.fesg.cases.IncrementalTestSequenceCompositionUtilities;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraph;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraphEdge;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraphVertex;
import tr.edu.iyte.fesg.model.FeaturedESG;

public class BankAccountCaseStudyScenario3 extends BankAccountCaseStudyUtilities {
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
		coverageLenght = 4;
		incrementalBasisApproachID = 0;
		productID = 5;
		BankAccountCaseStudyUtilities.buildProductModel();
		FeaturedESG product5 = buildFeaturedESG();
		String coreESGNameOfPL = product5.getCoreESGName();
		ESG coreESGOfPL = product5.getCoreESG();

		FeatureBasedIncrementalProductGraph featureBasedIncrementalProductGraph = new FeatureBasedIncrementalProductGraph(
				"BankAccountPL");

		FeatureBasedIncrementalProductGraphVertex vertex5 = new FeatureBasedIncrementalProductGraphVertex(product5,
				true, true);

		FeaturedESG product3 = new FeaturedESG("bankAccountProduct-credit", coreESGNameOfPL);
		product3.setCoreESG(coreESGOfPL);
		FeatureBasedIncrementalProductGraphVertex vertex3 = new FeatureBasedIncrementalProductGraphVertex(product3,
				true, false);

		FeatureBasedIncrementalProductGraphEdge edge2 = new FeatureBasedIncrementalProductGraphEdge(vertex5, vertex3);
		Set<ESG> featureESGSet2 = IncrementalTestSequenceCompositionUtilities.buildNewFeatureESGSet("credit");
		edge2.addFeatureESGSet(featureESGSet2);

		featureBasedIncrementalProductGraph.addVertex(vertex5);
		featureBasedIncrementalProductGraph.addVertex(vertex3);

		featureBasedIncrementalProductGraph.addEdge(edge2);

		featureBasedIncrementalProductGraph.incrementalTestSequenceCompositionBFT(vertex5, coverageLenght,
				incrementalBasisApproachID);
		// featureBasedIncrementalProductGraph.fullTestSequenceCompositionBFT(vertex5);
		// featureBasedIncrementalProductGraph.fullESGGenerationBFT(vertex5);

	}

}
