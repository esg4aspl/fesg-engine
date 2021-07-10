package tr.edu.iyte.fesg.cases.bankaccount;

import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.fesg.cases.FullESGValidatingUtilities;
import tr.edu.iyte.fesg.model.FeaturedESG;

public class BankAccountFullESGValidatorApp extends FullESGValidatingUtilities {
	
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
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		productID = 2;

		BankAccountCaseStudyUtilities.buildProductModel();
		FeaturedESG featuredESG = buildFeaturedESG();
		ESG fullESG = validateFullESG(featuredESG);
		buildDotFile(fullESG);

	}
	
	
	
}
