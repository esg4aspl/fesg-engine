package tr.edu.iyte.fesg.cases.email;

import java.util.Set;

import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.fesg.cases.IncrementalTestSequenceCompositionUtilities;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraph;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraphEdge;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraphVertex;
import tr.edu.iyte.fesg.model.FeaturedESG;

public class EmailCaseStuydScenario1 extends EmailCaseStudyUtilities {
	/**
	 * productID = 1 -> emailProduct_baseProduct
	 * productID = 2 -> emailProduct_addressbook
	 * productID = 3 -> emailProduct_addressbookAutoresponder
	 * productID = 4 -> emailProduct_addressbookAutoresponderEncrypt
	 * productID = 5 -> emailProduct_addressbookAutoresponderEncryptSign
	 * productID = 6 -> emailProduct_addressbookAutoresponderForward
	 * productID = 7 -> emailProduct_addressbookAutoresponderSign
	 * productID = 8 -> emailProduct_addressbookEncrypt
	 * productID = 9 -> emailProduct_autoresponder
	 * productID = 10 -> emailProduct_autoresponderEncrypt
	 * productID = 11 -> emailProduct_autoresponderForward
	 * productID = 12 -> emailProduct-encrypt
	 * productID = 13 -> emailProduct-forward
	 * 
	 * initialize productID to build different products
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		coverageLenght = 4;
		incrementalBasisApproachID = 0;
		productID = 1;
		EmailCaseStudyUtilities.buildProductModel();
		FeaturedESG product0 = buildFeaturedESG();
		String coreESGNameOfPL = product0.getCoreESGName();
		ESG coreESGOfPL = product0.getCoreESG();
		FeatureBasedIncrementalProductGraph featureBasedIncrementalProductGraph = new FeatureBasedIncrementalProductGraph("EmailPL");
		
		FeatureBasedIncrementalProductGraphVertex vertex0 = new FeatureBasedIncrementalProductGraphVertex(product0, true,true);
		
		FeaturedESG product1 = new FeaturedESG("emailProduct-addressbook",coreESGNameOfPL);
		product1.setCoreESG(coreESGOfPL);
		FeatureBasedIncrementalProductGraphVertex vertex1 = new FeatureBasedIncrementalProductGraphVertex(product1,true,false);
		
		FeatureBasedIncrementalProductGraphEdge edge0 = new FeatureBasedIncrementalProductGraphEdge(vertex0, vertex1);
		Set<ESG> featureESGSet0 = IncrementalTestSequenceCompositionUtilities.buildNewFeatureESGSet("addressbook");
		edge0.addFeatureESGSet(featureESGSet0);
		
		FeaturedESG product2 = new FeaturedESG("emailProduct-addressbookAutoresponder",coreESGNameOfPL);
		product2.setCoreESG(coreESGOfPL);
		FeatureBasedIncrementalProductGraphVertex vertex2 = new FeatureBasedIncrementalProductGraphVertex(product2,true,false);
		
		FeatureBasedIncrementalProductGraphEdge edge1 = new FeatureBasedIncrementalProductGraphEdge(vertex1, vertex2);
		Set<ESG> featureESGSet1 = IncrementalTestSequenceCompositionUtilities.buildNewFeatureESGSet("autoresponder");
		edge1.addFeatureESGSet(featureESGSet1);
		
		FeaturedESG product3 = new FeaturedESG("emailProduct-addressbookAutoresponderEncrypt",coreESGNameOfPL);
		product3.setCoreESG(coreESGOfPL);
		FeatureBasedIncrementalProductGraphVertex vertex3 = new FeatureBasedIncrementalProductGraphVertex(product3,true,false);
		
		FeatureBasedIncrementalProductGraphEdge edge2 = new FeatureBasedIncrementalProductGraphEdge(vertex2, vertex3);
		Set<ESG> featureESGSet2 = IncrementalTestSequenceCompositionUtilities.buildNewFeatureESGSet("keys","encrypt");
		edge2.addFeatureESGSet(featureESGSet2);
		
		FeaturedESG product4 = new FeaturedESG("emailProduct-addressbookAutoresponderEncryptSign",coreESGNameOfPL);
		product4.setCoreESG(coreESGOfPL);
		FeatureBasedIncrementalProductGraphVertex vertex4 = new FeatureBasedIncrementalProductGraphVertex(product4,true,false);
		
		FeatureBasedIncrementalProductGraphEdge edge3 = new FeatureBasedIncrementalProductGraphEdge(vertex3, vertex4);
		Set<ESG> featureESGSet3 = IncrementalTestSequenceCompositionUtilities.buildNewFeatureESGSet("sign");
		edge3.addFeatureESGSet(featureESGSet3);
		
		FeaturedESG product5 = new FeaturedESG("emailProduct-addressbookAutoresponderForward",coreESGNameOfPL);
		product5.setCoreESG(coreESGOfPL);
		FeatureBasedIncrementalProductGraphVertex vertex5 = new FeatureBasedIncrementalProductGraphVertex(product5,true,false);
		
		FeatureBasedIncrementalProductGraphEdge edge4 = new FeatureBasedIncrementalProductGraphEdge(vertex2, vertex5);
		Set<ESG> featureESGSet4 = IncrementalTestSequenceCompositionUtilities.buildNewFeatureESGSet("forward");
		edge4.addFeatureESGSet(featureESGSet4);
		
		FeaturedESG product6 = new FeaturedESG("emailProduct-addressbookAutoresponderSign",coreESGNameOfPL);
		product6.setCoreESG(coreESGOfPL);
		FeatureBasedIncrementalProductGraphVertex vertex6 = new FeatureBasedIncrementalProductGraphVertex(product6,true,false);
		
		FeatureBasedIncrementalProductGraphEdge edge5 = new FeatureBasedIncrementalProductGraphEdge(vertex2, vertex6);
		Set<ESG> featureESGSet5 = IncrementalTestSequenceCompositionUtilities.buildNewFeatureESGSet("sign");
		edge5.addFeatureESGSet(featureESGSet5);
		
		featureBasedIncrementalProductGraph.addVertex(vertex0);
		featureBasedIncrementalProductGraph.addVertex(vertex1);
		featureBasedIncrementalProductGraph.addVertex(vertex2);
		featureBasedIncrementalProductGraph.addVertex(vertex3);
		featureBasedIncrementalProductGraph.addVertex(vertex4);
		featureBasedIncrementalProductGraph.addVertex(vertex5);
		featureBasedIncrementalProductGraph.addVertex(vertex6);
		
		featureBasedIncrementalProductGraph.addEdge(edge0);
		featureBasedIncrementalProductGraph.addEdge(edge1);
		featureBasedIncrementalProductGraph.addEdge(edge2);
		featureBasedIncrementalProductGraph.addEdge(edge3);
		featureBasedIncrementalProductGraph.addEdge(edge4);
		featureBasedIncrementalProductGraph.addEdge(edge5);
		
		featureBasedIncrementalProductGraph.incrementalTestSequenceCompositionBFT(vertex0,coverageLenght,incrementalBasisApproachID);
		//featureBasedIncrementalProductGraph.fullTestSequenceCompositionBFT(vertex0);
		//featureBasedIncrementalProductGraph.fullESGGenerationBFT(vertex0);

	}

}
