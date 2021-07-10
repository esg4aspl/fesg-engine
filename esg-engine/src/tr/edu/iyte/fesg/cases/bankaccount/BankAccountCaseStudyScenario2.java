package tr.edu.iyte.fesg.cases.bankaccount;

import java.util.Set;

import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.fesg.cases.IncrementalTestSequenceCompositionUtilities;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraph;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraphEdge;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraphVertex;
import tr.edu.iyte.fesg.model.FeaturedESG;

public class BankAccountCaseStudyScenario2 extends BankAccountCaseStudyUtilities {
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
		coverageLenght = 2;
		incrementalBasisApproachID = 1;
		productID = 2;
		BankAccountCaseStudyUtilities.buildProductModel();
		FeaturedESG product2 = buildFeaturedESG();

		String coreESGNameOfPL = product2.getCoreESGName();
		ESG coreESGOfPL = product2.getCoreESG();

		FeatureBasedIncrementalProductGraph featureBasedIncrementalProductGraph = new FeatureBasedIncrementalProductGraph(
				"BankAccountPL");

		FeatureBasedIncrementalProductGraphVertex vertex2 = new FeatureBasedIncrementalProductGraphVertex(product2,
				true, true);

		FeaturedESG product3 = new FeaturedESG("bankAccountProduct-credit", coreESGNameOfPL);
		product3.setCoreESG(coreESGOfPL);
		FeatureBasedIncrementalProductGraphVertex vertex3 = new FeatureBasedIncrementalProductGraphVertex(product3,
				true, false);

		FeatureBasedIncrementalProductGraphEdge edge2 = new FeatureBasedIncrementalProductGraphEdge(vertex2, vertex3);
		Set<ESG> featureESGSet2 = IncrementalTestSequenceCompositionUtilities.buildNewFeatureESGSet("credit",
				"interest", "interestEstimation");
		edge2.addFeatureESGSet(featureESGSet2);

		FeaturedESG product4 = new FeaturedESG("bankAccountProduct_dailyLimit", coreESGNameOfPL);
		product4.setCoreESG(coreESGOfPL);
		FeatureBasedIncrementalProductGraphVertex vertex4 = new FeatureBasedIncrementalProductGraphVertex(product4,
				true, false);

		FeatureBasedIncrementalProductGraphEdge edge3 = new FeatureBasedIncrementalProductGraphEdge(vertex2, vertex4);
		Set<ESG> featureESGSet3 = IncrementalTestSequenceCompositionUtilities.buildNewFeatureESGSet("dailyLimit");
		edge3.addFeatureESGSet(featureESGSet3);

		featureBasedIncrementalProductGraph.addVertex(vertex2);
		featureBasedIncrementalProductGraph.addVertex(vertex3);
		featureBasedIncrementalProductGraph.addVertex(vertex4);

		featureBasedIncrementalProductGraph.addEdge(edge2);
		featureBasedIncrementalProductGraph.addEdge(edge3);

		featureBasedIncrementalProductGraph.incrementalTestSequenceCompositionBFT(vertex2, coverageLenght,
				incrementalBasisApproachID);
	}

}
