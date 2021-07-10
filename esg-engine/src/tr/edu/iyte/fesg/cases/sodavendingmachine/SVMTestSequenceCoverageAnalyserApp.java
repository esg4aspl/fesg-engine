package tr.edu.iyte.fesg.cases.sodavendingmachine;

import java.util.Set;

import tr.edu.iyte.esg.eventsequence.EventSequence;
import tr.edu.iyte.fesg.cases.TestCoverageAnalysingUtilities;
import tr.edu.iyte.fesg.model.FeaturedESG;

public class SVMTestSequenceCoverageAnalyserApp extends TestCoverageAnalysingUtilities {
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
		productID = 3;
		coverageLenght = 4;
		SVMCaseStudyUtilities.buildProductModel();
		FeaturedESG featuredESG = buildFeaturedESG();
		
		Set<EventSequence> cesSet = composeSequencesFullyWithTimeMeasurement(featuredESG);
		
		writeFullTestCompositionResultsToFile(cesSet);
		analyseFullTestCompositionResultsCoverage(cesSet);
	}

}
