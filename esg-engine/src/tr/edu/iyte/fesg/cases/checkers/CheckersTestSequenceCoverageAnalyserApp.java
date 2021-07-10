package tr.edu.iyte.fesg.cases.checkers;

import java.util.Set;

import tr.edu.iyte.esg.eventsequence.EventSequence;
import tr.edu.iyte.fesg.cases.TestCoverageAnalysingUtilities;
import tr.edu.iyte.fesg.model.FeaturedESG;



public class CheckersTestSequenceCoverageAnalyserApp extends TestCoverageAnalysingUtilities {
	
	/**
	 * productID = 1 -> ProductBaseCheckers
	 * productID = 2 -> ProductAmericanCheckers
	 * productID = 3 -> ProductSpanishCheckers
	 * 
	 *initialize productID to build different products
	 * 
	 */
	public static void main(String[] args) {
		productID = 1;
		coverageLenght = 4;
		CheckersCaseStudyUtilities.buildProductModel();
		FeaturedESG featuredESG = buildFeaturedESG();
		
		Set<EventSequence> cesSet = composeSequencesFully(featuredESG);
		
		writeFullTestCompositionResultsToFile(cesSet);
		analyseFullTestCompositionResultsCoverage(cesSet);
	}

}
