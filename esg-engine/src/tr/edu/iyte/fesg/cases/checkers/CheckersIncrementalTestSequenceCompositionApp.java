package tr.edu.iyte.fesg.cases.checkers;

import java.io.IOException;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import tr.edu.iyte.esg.eventsequence.EventSequence;
import tr.edu.iyte.fesg.cases.IncrementalTestSequenceCompositionUtilities;
import tr.edu.iyte.fesg.model.FeaturedESG;

public class CheckersIncrementalTestSequenceCompositionApp extends IncrementalTestSequenceCompositionUtilities {
	
	/**
	 * productID = 1 -> ProductBaseCheckers
	 * productID = 2 -> ProductAmericanCheckers
	 * productID = 3 -> ProductSpanishCheckers
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
	
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		productID = 1;
		coverageLenght = 3;
		operationID = 1;
		productNameInput = 0;

		CheckersCaseStudyUtilities.buildProductModel();
		FeaturedESG featuredESG = buildFeaturedESG();
		
		Set<EventSequence> cesSet = composeSequencesOfFeaturedESGWithNewFeatures(featuredESG, "FeatureKingMarch",
				"FeatureKingJump", "FeatureKingCapture", "FeaturePromoteToKing");
		
		productESGName.setLength(0);
		productESGName.append("ProductAmericanCheckers");
		
		writeIncrementalTestCompositionResultsToFile(cesSet);
		analyseIncrementalTestCompositionResultsCoverage(cesSet);
	}

}
