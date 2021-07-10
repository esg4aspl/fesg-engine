 package tr.edu.iyte.fesg.cases.elevator;

import java.util.Set;

import tr.edu.iyte.esg.eventsequence.EventSequence;
import tr.edu.iyte.fesg.cases.TestCoverageAnalysingUtilities;
import tr.edu.iyte.fesg.model.FeaturedESG;


public class ElevatorTestSequenceCoverageAnalyserApp extends TestCoverageAnalysingUtilities {

	/**
	 * productID = 1 -> elevatorProduct_baseProduct
	 * productID = 2 -> elevatorProduct_weight
	 * productID = 3 -> elevatorProduct_weightExecutiveFloor
	 * productID = 4 -> elevatorProduct_weightOverloaded
	 * productID = 5 -> elevatorProduct_fullProduct 
	 * initialize productID to build different products
	 * 
	 * @param args
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public static void main(String[] args) {
		productID = 3;
		coverageLenght = 2;
		ElevatorCaseStudyUtilities.buildProductModel();
		FeaturedESG featuredESG = buildFeaturedESG();
		
		Set<EventSequence> cesSet = composeSequencesFully(featuredESG);
		
		writeFullTestCompositionResultsToFile(cesSet);
		analyseFullTestCompositionResultsCoverage(cesSet);
	}
}
