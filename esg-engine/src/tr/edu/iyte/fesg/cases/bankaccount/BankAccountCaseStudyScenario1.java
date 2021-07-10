package tr.edu.iyte.fesg.cases.bankaccount;

import java.util.Set;

import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.fesg.cases.IncrementalTestSequenceCompositionUtilities;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraph;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraphEdge;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraphVertex;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.validation.FeatureBasedIncrementalProductGraphValidator;
import tr.edu.iyte.fesg.model.FeaturedESG;

public class BankAccountCaseStudyScenario1 extends BankAccountCaseStudyUtilities {
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
		productID = 1;
		incrementalBasisApproachID = 0;
		BankAccountCaseStudyUtilities.buildProductModel();
		FeaturedESG product1 = buildFeaturedESG();
		
		String coreESGNameOfPL = product1.getCoreESGName();
		ESG coreESGOfPL = product1.getCoreESG();

		FeatureBasedIncrementalProductGraph featureBasedIncrementalProductGraph = new FeatureBasedIncrementalProductGraph(
				"BankAccountPL");

		FeatureBasedIncrementalProductGraphVertex vertex1 = new FeatureBasedIncrementalProductGraphVertex(product1,true,true);

		FeaturedESG product2 = new FeaturedESG("bankAccountProduct-cancellable", coreESGNameOfPL);
		product2.setCoreESG(coreESGOfPL);
		FeatureBasedIncrementalProductGraphVertex vertex2 = new FeatureBasedIncrementalProductGraphVertex(product2,true,false);

		FeatureBasedIncrementalProductGraphEdge edge1 = new FeatureBasedIncrementalProductGraphEdge(vertex1, vertex2);
		Set<ESG> featureESGSet1 = IncrementalTestSequenceCompositionUtilities.buildNewFeatureESGSet("cancelDeposit","cancelWithdraw");
		edge1.addFeatureESGSet(featureESGSet1);
		
		FeaturedESG product3 = new FeaturedESG("bankAccountProduct-credit",coreESGNameOfPL);
		product3.setCoreESG(coreESGOfPL);
		FeatureBasedIncrementalProductGraphVertex vertex3 = new FeatureBasedIncrementalProductGraphVertex(product3, true,false);
		
		FeatureBasedIncrementalProductGraphEdge edge2 = new FeatureBasedIncrementalProductGraphEdge(vertex1, vertex3);
		Set<ESG> featureESGSet2 = IncrementalTestSequenceCompositionUtilities.buildNewFeatureESGSet("cancelDeposit","cancelWithdraw","credit","interest","interestEstimation");
		edge2.addFeatureESGSet(featureESGSet2);
		
		
		FeaturedESG product4 = new FeaturedESG("bankAccountProduct-dailyLimit",coreESGNameOfPL);
		product4.setCoreESG(coreESGOfPL);
		FeatureBasedIncrementalProductGraphVertex vertex4 = new FeatureBasedIncrementalProductGraphVertex(product4, true,false);
		
		FeatureBasedIncrementalProductGraphEdge edge3 = new FeatureBasedIncrementalProductGraphEdge(vertex1, vertex4);
		Set<ESG> featureESGSet3 = IncrementalTestSequenceCompositionUtilities.buildNewFeatureESGSet("cancelDeposit","cancelWithdraw","dailyLimit");
		edge3.addFeatureESGSet(featureESGSet3);
		
		FeaturedESG product5 = new FeaturedESG("bankAccountProduct-interest",coreESGNameOfPL);
		product5.setCoreESG(coreESGOfPL);
		FeatureBasedIncrementalProductGraphVertex vertex5 = new FeatureBasedIncrementalProductGraphVertex(product5, true,false);
		
		FeatureBasedIncrementalProductGraphEdge edge4 = new FeatureBasedIncrementalProductGraphEdge(vertex1, vertex5);
		Set<ESG> featureESGSet4 = IncrementalTestSequenceCompositionUtilities.buildNewFeatureESGSet("interest","interestEstimation");
		edge4.addFeatureESGSet(featureESGSet4);
		
		
		FeaturedESG product6 = new FeaturedESG("bankAccountProduct-overdraft",coreESGNameOfPL);
		product6.setCoreESG(coreESGOfPL);
		FeatureBasedIncrementalProductGraphVertex vertex6 = new FeatureBasedIncrementalProductGraphVertex(product6, true,false);
		
		FeatureBasedIncrementalProductGraphEdge edge5 = new FeatureBasedIncrementalProductGraphEdge(vertex1, vertex6);
		Set<ESG> featureESGSet5 = IncrementalTestSequenceCompositionUtilities.buildNewFeatureESGSet("cancelWithdraw","dailyLimit","overdraft");
		edge5.addFeatureESGSet(featureESGSet5);

		featureBasedIncrementalProductGraph.addVertex(vertex1);
		featureBasedIncrementalProductGraph.addVertex(vertex2);
		featureBasedIncrementalProductGraph.addVertex(vertex3);
		featureBasedIncrementalProductGraph.addVertex(vertex4);
		featureBasedIncrementalProductGraph.addVertex(vertex5);
		featureBasedIncrementalProductGraph.addVertex(vertex6);
		
		featureBasedIncrementalProductGraph.addEdge(edge1);
		featureBasedIncrementalProductGraph.addEdge(edge2);
		featureBasedIncrementalProductGraph.addEdge(edge3);
		featureBasedIncrementalProductGraph.addEdge(edge4);
		featureBasedIncrementalProductGraph.addEdge(edge5);
		
		featureBasedIncrementalProductGraph.incrementalTestSequenceCompositionBFT(vertex1,coverageLenght,incrementalBasisApproachID);
		
		FeatureBasedIncrementalProductGraphValidator featureBasedIncrementalProductGraphValidator = new FeatureBasedIncrementalProductGraphValidator(featureBasedIncrementalProductGraph);
		System.out.println(featureBasedIncrementalProductGraphValidator.validateProductConfigurationsInFeatureBasedIncrementalProductGraph(vertex1));

	}

}
