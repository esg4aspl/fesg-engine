package tr.edu.iyte.fesg.cases.bankaccount;

import java.io.IOException;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import tr.edu.iyte.esg.eventsequence.EventSequence;
import tr.edu.iyte.esg.eventsequence.EventSequenceUtilities;
import tr.edu.iyte.fesg.cases.IncrementalTestSequenceCompositionUtilities;
import tr.edu.iyte.fesg.model.FeaturedESG;

public class BankAccountIncrementalTestSequenceCompositionApp extends IncrementalTestSequenceCompositionUtilities {
	
	/**
	 * productID = 1 -> bankAccountProduct_baseProduct
	 * productID = 2 -> bankAccountProduct_cancellable
	 * productID = 3 -> bankAccountProduct_credit
	 * productID = 4 -> bankAccountProduct_dailyLimit
	 * productID = 5 -> bankAccountProduct_interest
	 * productID = 6 -> bankAccountProduct_overdraft
	 * productID = 7 -> bankAccountProduct_creditWithDailyLimit
	 * productID = 8 -> bankAccountProduct_overdraftWithInterest
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
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		productID = 1;
		coverageLenght = 2;
		operationID = 2;
		productNameInput = 1;

		BankAccountCaseStudyUtilities.buildProductModel();

		System.out.println(productESGName);
		FeaturedESG featuredESG = buildFeaturedESG();
		Set<EventSequence> cesSet = composeSequencesOfFeaturedESGWithNewFeatures(featuredESG, "cancelDeposit",
				"cancelWithdraw", "dailyLimit");
		EventSequenceUtilities.esgEventSequenceSetPrinter(cesSet);
		System.out.println(productESGName);
		writeIncrementalTestCompositionResultsToFile(cesSet);
		analyseIncrementalTestCompositionResultsCoverage(cesSet);
	}	
}



