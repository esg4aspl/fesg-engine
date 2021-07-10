package tr.edu.iyte.fesg.cases.bankaccount;

import tr.edu.iyte.fesg.cases.DotFileCreationUtilities;
import tr.edu.iyte.fesg.model.FeaturedESG;

public class BankAccountDotFileBuilderApp extends DotFileCreationUtilities {
	/**
	 * productID = 1 -> bankAccountProduct_baseProduct
	 * productID = 2 -> bankAccountProduct_cancellable
	 * productID = 3 -> bankAccountProduct_credit
	 * productID = 4 -> bankAccountProduct_dailyLimit
	 * productID = 5 -> bankAccountProduct_interest
	 * productID = 6 -> bankAccountProduct_overdraft
	 * 
	 * initialize productID to build different products
	 * 
	 * @param args
	 */

	public static void main(String[] args) {

		productID = 4;

		BankAccountCaseStudyUtilities.buildProductModel();
		FeaturedESG featuredESG = buildFeaturedESG();
		buildDotFile(featuredESG);
	}

}
