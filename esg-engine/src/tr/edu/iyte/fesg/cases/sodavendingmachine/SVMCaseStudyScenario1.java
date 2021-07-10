package tr.edu.iyte.fesg.cases.sodavendingmachine;

import java.util.Set;

import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.fesg.cases.IncrementalTestSequenceCompositionUtilities;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraph;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraphEdge;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraphVertex;
import tr.edu.iyte.fesg.model.FeaturedESG;

public class SVMCaseStudyScenario1 extends SVMCaseStudyUtilities {
	/**
	 * productID = 1 -> svmProduct_payEUR
	 * productID = 2 -> svmProduct_payEURServeSoda
	 * productID = 3 -> svmProduct_free
	 * productID = 4 -> svmProduct_payUSD
	 * productID = 5 -> svmProduct_payUSDServeTea
	 * 
	 *initialize productID to build different products
	 */
	public static void main(String[] args) {
		coverageLenght = 2;
		incrementalBasisApproachID = 0;
		productID = 2;
		SVMCaseStudyUtilities.buildProductModel();
		FeaturedESG product1 = buildFeaturedESG();
		
		String coreESGNameOfPL = product1.getCoreESGName();
		ESG coreESGOfPL = product1.getCoreESG();

		FeatureBasedIncrementalProductGraph featureBasedIncrementalProductGraph = new FeatureBasedIncrementalProductGraph(
				"SodaVendingMachinePL");

		FeatureBasedIncrementalProductGraphVertex vertex1 = new FeatureBasedIncrementalProductGraphVertex(product1,true,true);
		
		FeaturedESG product2 = new FeaturedESG("svmProduct-payEUR",coreESGNameOfPL);
		product2.setCoreESG(coreESGOfPL);
		FeatureBasedIncrementalProductGraphVertex vertex3 = new FeatureBasedIncrementalProductGraphVertex(product2, true,false);
		
		FeatureBasedIncrementalProductGraphEdge edge1 = new FeatureBasedIncrementalProductGraphEdge(vertex1, vertex3);
		Set<ESG> featureESGSet2 = IncrementalTestSequenceCompositionUtilities.buildNewFeatureESGSet("serveTea");
		edge1.addFeatureESGSet(featureESGSet2);
		
		featureBasedIncrementalProductGraph.addVertex(vertex1);
		featureBasedIncrementalProductGraph.addVertex(vertex3);
		

		featureBasedIncrementalProductGraph.addEdge(edge1);
		
		featureBasedIncrementalProductGraph.incrementalTestSequenceCompositionBFT(vertex1,coverageLenght,incrementalBasisApproachID);
		//featureBasedIncrementalProductGraph.fullTestSequenceCompositionBFT(vertex5);
		//featureBasedIncrementalProductGraph.fullESGGenerationBFT(vertex5);

	}

}
