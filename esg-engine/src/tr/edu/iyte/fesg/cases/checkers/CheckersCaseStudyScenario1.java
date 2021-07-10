package tr.edu.iyte.fesg.cases.checkers;

import java.util.Set;

import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.fesg.cases.IncrementalTestSequenceCompositionUtilities;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraph;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraphEdge;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraphVertex;
import tr.edu.iyte.fesg.model.FeaturedESG;

public class CheckersCaseStudyScenario1 extends CheckersCaseStudyUtilities {
	
	
	public static void main(String[] args) {
		coverageLenght = 2;
		incrementalBasisApproachID = 1;
		productID = 1;
		CheckersCaseStudyUtilities.buildProductModel();
		FeaturedESG product0 = buildFeaturedESG();
		String coreESGNameOfPL = product0.getCoreESGName();
		ESG coreESGOfPL = product0.getCoreESG();
		
		FeatureBasedIncrementalProductGraph featureBasedIncrementalProductGraph = new FeatureBasedIncrementalProductGraph(
				"CheckersPL");
		
		FeatureBasedIncrementalProductGraphVertex vertex0 = new FeatureBasedIncrementalProductGraphVertex(product0,
				true,true);

		FeaturedESG product1 = new FeaturedESG("ProductAmericanCheckers", coreESGNameOfPL);
		product1.setCoreESG(coreESGOfPL);
		FeatureBasedIncrementalProductGraphVertex vertex1 = new FeatureBasedIncrementalProductGraphVertex(product1,
				true,false);
		
		FeatureBasedIncrementalProductGraphEdge edge0 = new FeatureBasedIncrementalProductGraphEdge(vertex0, vertex1);
		Set<ESG> featureESGSet0 = IncrementalTestSequenceCompositionUtilities.buildNewFeatureESGSet("FeatureKingMarch","FeatureKingJump","FeatureKingCapture","FeaturePromoteToKing");
		edge0.addFeatureESGSet(featureESGSet0);
		
		FeaturedESG product2 = new FeaturedESG("ProductSpanishCheckers",coreESGNameOfPL);
		product2.setCoreESG(coreESGOfPL);
		FeatureBasedIncrementalProductGraphVertex vertex2 = new FeatureBasedIncrementalProductGraphVertex(product2, true,false);
		
		FeatureBasedIncrementalProductGraphEdge edge1 = new FeatureBasedIncrementalProductGraphEdge(vertex0, vertex2);
		Set<ESG> featureESGSet1 = IncrementalTestSequenceCompositionUtilities.buildNewFeatureESGSet("FeatureQueenMarch","FeatureQueenJump","FeatureQueenCapture","FeaturePromoteToQueen");
		edge1.addFeatureESGSet(featureESGSet1);
		
		featureBasedIncrementalProductGraph.addVertex(vertex0);
		featureBasedIncrementalProductGraph.addVertex(vertex1);
		featureBasedIncrementalProductGraph.addVertex(vertex2);
		
		featureBasedIncrementalProductGraph.addEdge(edge0);
		featureBasedIncrementalProductGraph.addEdge(edge1);
		
		featureBasedIncrementalProductGraph.incrementalTestSequenceCompositionBFT(vertex0,coverageLenght,incrementalBasisApproachID);
		//featureBasedIncrementalProductGraph.fullTestSequenceCompositionBFT(vertex0);
		//featureBasedIncrementalProductGraph.fullESGGenerationBFT(vertex0);

		
	}

}
