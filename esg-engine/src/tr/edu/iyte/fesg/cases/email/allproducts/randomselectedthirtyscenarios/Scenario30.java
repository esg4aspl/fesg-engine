package tr.edu.iyte.fesg.cases.email.allproducts.randomselectedthirtyscenarios;

import java.util.Set;

import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.fesg.cases.IncrementalTestSequenceCompositionUtilities;
import tr.edu.iyte.fesg.cases.email.EmailCaseStudyUtilities;
import tr.edu.iyte.fesg.cases.email.allproducts.AllPossibleProductsGenerator;
import tr.edu.iyte.fesg.cases.email.allproducts.AllPossibleProductsGeneratorApp;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraph;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraphEdge;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraphVertex;
import tr.edu.iyte.fesg.model.FeaturedESG;

public class Scenario30
		extends EmailCaseStudyUtilities {

	public static void main(String[] args) {
		createCaseStudyFilePathObjects();
		initializeCaseStudyFolderNames();
		
		coverageLenght = 2;
		incrementalBasisApproachID = 1;		
		// PUC ID-Scenario ID-Existing Product ID
		existingProductESGName.append("ba-22-30-10");

		AllPossibleProductsGenerator allPossibleProductsGenerator = AllPossibleProductsGeneratorApp.getAllPossibleProductsGenerator();
		FeaturedESG reusableFESG = AllPossibleProductsGeneratorApp.findFeaturedESGByProductName(allPossibleProductsGenerator,"autoresponder_encrypt");
		reusableFESG.getFeaturedESGSet().forEach(e->System.out.print(e.getName() + " "));
		System.out.println();
		String coreESGNameOfPL = reusableFESG.getCoreESGName();
		ESG coreESGOfPL = reusableFESG.getCoreESG();

		FeatureBasedIncrementalProductGraph featureBasedIncrementalProductGraph = new FeatureBasedIncrementalProductGraph(
				"EmailPL");
		FeatureBasedIncrementalProductGraphVertex start = new FeatureBasedIncrementalProductGraphVertex(reusableFESG,true,true);
		
		FeaturedESG product1 = new FeaturedESG("autoresponder_encrypt_sign",coreESGNameOfPL);
		product1.setCoreESG(coreESGOfPL);
		FeatureBasedIncrementalProductGraphVertex vertex1 = new FeatureBasedIncrementalProductGraphVertex(product1, true,false);
		
		FeatureBasedIncrementalProductGraphEdge edge1 = new FeatureBasedIncrementalProductGraphEdge(start, vertex1);
		Set<ESG> featureESGSet1 = IncrementalTestSequenceCompositionUtilities.buildNewFeatureESGSet("sign");
		edge1.addFeatureESGSet(featureESGSet1);
		
		featureBasedIncrementalProductGraph.addVertex(start);
		featureBasedIncrementalProductGraph.addVertex(vertex1);
		

		featureBasedIncrementalProductGraph.addEdge(edge1);
		
		featureBasedIncrementalProductGraph.incrementalTestSequenceCompositionBFT(start,coverageLenght,incrementalBasisApproachID);
		
		coverageLenght = 3;
		featureBasedIncrementalProductGraph.incrementalTestSequenceCompositionBFT(start, coverageLenght,incrementalBasisApproachID);
		coverageLenght = 4;
		featureBasedIncrementalProductGraph.incrementalTestSequenceCompositionBFT(start, coverageLenght,incrementalBasisApproachID);
		
	}

}
