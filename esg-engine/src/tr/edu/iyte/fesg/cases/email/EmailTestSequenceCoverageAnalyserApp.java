package tr.edu.iyte.fesg.cases.email;

import java.util.Set;

import tr.edu.iyte.esg.eventsequence.EventSequence;
import tr.edu.iyte.fesg.cases.TestCoverageAnalysingUtilities;
import tr.edu.iyte.fesg.model.FeaturedESG;

public class EmailTestSequenceCoverageAnalyserApp extends TestCoverageAnalysingUtilities {

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
	
	
	public static void main(String[] args){
		productID = 3;
		coverageLenght = 2;
		
		EmailCaseStudyUtilities.buildProductModel();

		FeaturedESG featuredESG = buildFeaturedESG();
		Set<EventSequence> cesSet = composeSequencesFullyWithTimeMeasurement(featuredESG);
		writeFullTestCompositionResultsToFile(cesSet);
		analyseFullTestCompositionResultsCoverage(cesSet);
	}
	
}
