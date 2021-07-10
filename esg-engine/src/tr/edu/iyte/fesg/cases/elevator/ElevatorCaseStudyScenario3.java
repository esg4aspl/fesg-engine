package tr.edu.iyte.fesg.cases.elevator;

import java.util.Set;

import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.fesg.cases.IncrementalTestSequenceCompositionUtilities;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraph;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraphEdge;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraphVertex;
import tr.edu.iyte.fesg.model.FeaturedESG;

public class ElevatorCaseStudyScenario3 extends ElevatorCaseStudyUtilities {

	public static void main(String[] args) {
		coverageLenght = 2;
		productID = 1;
		ElevatorCaseStudyUtilities.buildProductModel();
		FeaturedESG product0 = buildFeaturedESG();
		String coreESGNameOfPL = product0.getCoreESGName();
		ESG coreESGOfPL = product0.getCoreESG();
		FeatureBasedIncrementalProductGraph featureBasedIncrementalProductGraph = new FeatureBasedIncrementalProductGraph("ElevatorPL");
		
		FeatureBasedIncrementalProductGraphVertex vertex0 = new FeatureBasedIncrementalProductGraphVertex(product0, true,true);
		
		
		FeaturedESG product1 = new FeaturedESG("elevatorProduct_weightExecutiveFloor",coreESGNameOfPL);
		product1.setCoreESG(coreESGOfPL);
		FeatureBasedIncrementalProductGraphVertex vertex1 = new FeatureBasedIncrementalProductGraphVertex(product1,true,false);
		
		FeatureBasedIncrementalProductGraphEdge edge0 = new FeatureBasedIncrementalProductGraphEdge(vertex0, vertex1);
		Set<ESG> featureESGSet0 = IncrementalTestSequenceCompositionUtilities.buildNewFeatureESGSet("weight","executiveFloor");
		edge0.addFeatureESGSet(featureESGSet0);
		
		FeaturedESG product2 = new FeaturedESG("elevatorProduct_weightOverloaded",coreESGNameOfPL);
		product2.setCoreESG(coreESGOfPL);
		FeatureBasedIncrementalProductGraphVertex vertex2 = new FeatureBasedIncrementalProductGraphVertex(product2,true,false);
		
		FeatureBasedIncrementalProductGraphEdge edge1 = new FeatureBasedIncrementalProductGraphEdge(vertex0, vertex2);
		Set<ESG> featureESGSet1 = IncrementalTestSequenceCompositionUtilities.buildNewFeatureESGSet("weight","overloaded");
		edge1.addFeatureESGSet(featureESGSet1);
		
		FeaturedESG product3 = new FeaturedESG("elevatorProduct_fullProduct",coreESGNameOfPL);
		product3.setCoreESG(coreESGOfPL);
		FeatureBasedIncrementalProductGraphVertex vertex3 = new FeatureBasedIncrementalProductGraphVertex(product3,true,false);
		
		FeatureBasedIncrementalProductGraphEdge edge2 = new FeatureBasedIncrementalProductGraphEdge(vertex1, vertex3);
		Set<ESG> featureESGSet2 = IncrementalTestSequenceCompositionUtilities.buildNewFeatureESGSet("overloaded");
		edge2.addFeatureESGSet(featureESGSet2);
		
		FeatureBasedIncrementalProductGraphEdge edge3 = new FeatureBasedIncrementalProductGraphEdge(vertex2, vertex3);
		Set<ESG> featureESGSet3 = IncrementalTestSequenceCompositionUtilities.buildNewFeatureESGSet("executiveFloor");
		edge3.addFeatureESGSet(featureESGSet3);
		
		featureBasedIncrementalProductGraph.addVertex(vertex0);
		featureBasedIncrementalProductGraph.addVertex(vertex1);
		featureBasedIncrementalProductGraph.addVertex(vertex2);
		featureBasedIncrementalProductGraph.addVertex(vertex3);

		featureBasedIncrementalProductGraph.addEdge(edge0);
		featureBasedIncrementalProductGraph.addEdge(edge1);
		featureBasedIncrementalProductGraph.addEdge(edge2);
		featureBasedIncrementalProductGraph.addEdge(edge3);

		
		featureBasedIncrementalProductGraph.incrementalTestSequenceCompositionBFT(vertex0,coverageLenght,incrementalBasisApproachID);
		//featureBasedIncrementalProductGraph.fullTestSequenceCompositionBFT(vertex0);
		//featureBasedIncrementalProductGraph.fullESGGenerationBFT(vertex0);
	}

}
