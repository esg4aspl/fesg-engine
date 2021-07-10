package tr.edu.iyte.fesg.cases.sodavendingmachine;

import java.util.Set;

import tr.edu.iyte.esg.eventsequence.EventSequence;
import tr.edu.iyte.fesg.cases.IncrementalTestSequenceCompositionUtilities;
import tr.edu.iyte.fesg.model.FeaturedESG;

public class SVMIncrementalTestSequenceCompositionApp extends IncrementalTestSequenceCompositionUtilities {
	
	/**
	 * productID = 1 -> svmProduct_payEUR
	 * productID = 2 -> svmProduct_payEURServeSoda
	 * productID = 3 -> svmProduct_free
	 * productID = 4 -> svmProduct_payUSD
	 * productID = 5 -> svmProduct_payUSDServeSoda
	 * 
	 *initialize productID to build different products
	 * 
	 * operationID = 1 -> to extend a Featured ESG with new features 
	 * operationID = 2 -> to build a new Featured ESG with an existing Featured ESG and new features
	 * 
	 * initialize productNameInput to 1 if you want to enter a new name to the new product
	 * otherwise the new product's name will be created automatically.
	 * 
	 * You can enter as many features as you want
	 */
	
	public static void main(String[] args) {
		productID = 2;
		coverageLenght = 3;
		operationID = 1;
		productNameInput = 0;

		SVMCaseStudyUtilities.buildProductModel();
		FeaturedESG featuredESG = buildFeaturedESG();
		
		Set<EventSequence> cesSet = composeSequencesOfFeaturedESGWithNewFeatures(featuredESG, "serveTea");
		productESGName.setLength(0);
		productESGName.append("svmProduct_payEUR");
		writeIncrementalTestCompositionResultsToFile(cesSet);
		analyseIncrementalTestCompositionResultsCoverage(cesSet);
	}

}
