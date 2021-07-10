package tr.edu.iyte.fesg.cases.elevator;

import java.util.Set;

import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.fesg.cases.IncrementalTestSequenceCompositionUtilities;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraph;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraphEdge;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraphVertex;
import tr.edu.iyte.fesg.model.FeaturedESG;

public class ElevatorCaseStudyScenario2 extends ElevatorCaseStudyUtilities {
	
	public static void main(String[] args) {
		coverageLenght = 2;
		productID = 1;
		ElevatorCaseStudyUtilities.buildProductModel();
		FeaturedESG product0 = buildFeaturedESG();
		String coreESGNameOfPL = product0.getCoreESGName();
		ESG coreESGOfPL = product0.getCoreESG();
		FeatureBasedIncrementalProductGraph featureBasedIncrementalProductGraph = new FeatureBasedIncrementalProductGraph("ElevatorPL");
		
		FeatureBasedIncrementalProductGraphVertex vertex0 = new FeatureBasedIncrementalProductGraphVertex(product0, true,true);
		
		
		FeaturedESG product1 = new FeaturedESG("elevatorProduct_fullProduct",coreESGNameOfPL);
		product1.setCoreESG(coreESGOfPL);
		FeatureBasedIncrementalProductGraphVertex vertex1 = new FeatureBasedIncrementalProductGraphVertex(product1,true,false);
		
		FeatureBasedIncrementalProductGraphEdge edge0 = new FeatureBasedIncrementalProductGraphEdge(vertex0, vertex1);
		Set<ESG> featureESGSet0 = IncrementalTestSequenceCompositionUtilities.buildNewFeatureESGSet("weight","executiveFloor","overloaded");
		edge0.addFeatureESGSet(featureESGSet0);
		
		
		featureBasedIncrementalProductGraph.addVertex(vertex0);
		featureBasedIncrementalProductGraph.addVertex(vertex1);
		
		featureBasedIncrementalProductGraph.addEdge(edge0);
		
		featureBasedIncrementalProductGraph.incrementalTestSequenceCompositionBFT(vertex0,coverageLenght,incrementalBasisApproachID);
		//featureBasedIncrementalProductGraph.fullTestSequenceCompositionBFT(vertex0);
		//featureBasedIncrementalProductGraph.fullESGGenerationBFT(vertex0);
	}

}
