package tr.edu.iyte.fesg.cases.email;

import java.util.Set;

import tr.edu.iyte.esg.eventsequence.EventSequence;
import tr.edu.iyte.fesg.cases.IncrementalTestSequenceCompositionUtilities;
import tr.edu.iyte.fesg.model.FeaturedESG;


public class EmailIncrementalTestSequenceCompositionApp extends IncrementalTestSequenceCompositionUtilities {	

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
	 * operationID = 1 -> to extend a Featured ESG with new features 
	 * operationID = 2 -> to build a new Featured ESG with an existing Featured ESG and new features
	 * 
	 * initialize productNameInput to 1 if you want to enter a new name to the new product
	 * otherwise the new product's name will be created automatically.
	 * 
	 * You can enter as many features as you want
	 */
	
	public static void main(String[] args){
		productID = 1;
		coverageLenght = 4;
		operationID = 1;
		productNameInput = 1;
		
		
		EmailCaseStudyUtilities.buildProductModel();
		FeaturedESG featuredESG = buildFeaturedESG();
		
		Set<EventSequence> cesSet = composeSequencesOfFeaturedESGWithNewFeatures(featuredESG,"forward");		
		
		writeIncrementalTestCompositionResultsToFile(cesSet);
		analyseIncrementalTestCompositionResultsCoverage(cesSet);
	}
		

	
}



