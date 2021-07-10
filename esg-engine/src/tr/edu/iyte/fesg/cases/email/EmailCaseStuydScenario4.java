package tr.edu.iyte.fesg.cases.email;

import java.util.Set;

import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.fesg.cases.IncrementalTestSequenceCompositionUtilities;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraph;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraphEdge;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraphVertex;
import tr.edu.iyte.fesg.model.FeaturedESG;

public class EmailCaseStuydScenario4 extends EmailCaseStudyUtilities {
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
		coverageLenght = 2;
		incrementalBasisApproachID = 0;
		productID = 1;
		EmailCaseStudyUtilities.buildProductModel();
		
		FeaturedESG product0 = buildFeaturedESG();
		String coreESGNameOfPL = product0.getCoreESGName();
		ESG coreESGOfPL = product0.getCoreESG();
		FeatureBasedIncrementalProductGraph featureBasedIncrementalProductGraph = new FeatureBasedIncrementalProductGraph("EmailPL");
		
		FeatureBasedIncrementalProductGraphVertex vertex0 = new FeatureBasedIncrementalProductGraphVertex(product0, true, true);
		
		FeaturedESG product1 = new FeaturedESG("emailProduct-addressbookAutoresponderEncryptSign",coreESGNameOfPL);
		product1.setCoreESG(coreESGOfPL);
		FeatureBasedIncrementalProductGraphVertex vertex1 = new FeatureBasedIncrementalProductGraphVertex(product1,true, false);
		
		FeatureBasedIncrementalProductGraphEdge edge0 = new FeatureBasedIncrementalProductGraphEdge(vertex0, vertex1);
		Set<ESG> featureESGSet0 = IncrementalTestSequenceCompositionUtilities.buildNewFeatureESGSet("addressbook","autoresponder","keys","encrypt","sign");
		edge0.addFeatureESGSet(featureESGSet0);
		
		FeaturedESG product2 = new FeaturedESG("emailProduct-autoresponderForward",coreESGNameOfPL);
		product2.setCoreESG(coreESGOfPL);
		FeatureBasedIncrementalProductGraphVertex vertex2 = new FeatureBasedIncrementalProductGraphVertex(product2,true, false);
		
		FeatureBasedIncrementalProductGraphEdge edge1 = new FeatureBasedIncrementalProductGraphEdge(vertex0, vertex2);
		Set<ESG> featureESGSet1 = IncrementalTestSequenceCompositionUtilities.buildNewFeatureESGSet("autoresponder","forward");
		edge1.addFeatureESGSet(featureESGSet1);
		
		
		featureBasedIncrementalProductGraph.addVertex(vertex0);
		featureBasedIncrementalProductGraph.addVertex(vertex1);
		featureBasedIncrementalProductGraph.addVertex(vertex2);
		
		featureBasedIncrementalProductGraph.addEdge(edge0);
		featureBasedIncrementalProductGraph.addEdge(edge1);

		
		featureBasedIncrementalProductGraph.incrementalTestSequenceCompositionBFT(vertex0,coreESGIndex,incrementalBasisApproachID);
		//featureBasedIncrementalProductGraph.fullTestSequenceCompositionBFT(vertex0);
		//featureBasedIncrementalProductGraph.fullESGGenerationBFT(vertex0);

	}

}
