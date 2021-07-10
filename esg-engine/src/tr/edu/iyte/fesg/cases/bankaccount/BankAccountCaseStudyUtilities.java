package tr.edu.iyte.fesg.cases.bankaccount;

import tr.edu.iyte.fesg.cases.CaseStudyFilePathCreationUtilities;

public abstract class BankAccountCaseStudyUtilities extends CaseStudyFilePathCreationUtilities {

		
	public static void buildProductModel() {
		createCaseStudyFilePathObjects();
		switch (productID) {
		case 1: {
			initializeCaseStudyFolderNames();
			bankAccountProduct_baseProduct();
			break;
		}
		case 2: {
			initializeCaseStudyFolderNames();
			bankAccountProduct_cancellable();
			break;
		}
		case 3: {
			initializeCaseStudyFolderNames();
			bankAccountProduct_credit();
			break;
		}
		case 4: {
			initializeCaseStudyFolderNames();
			bankAccountProduct_dailyLimit();
			break;
		}
		case 5: {
			initializeCaseStudyFolderNames();
			bankAccountProduct_interest();
			break;
		}
		case 6: {
			initializeCaseStudyFolderNames();
			bankAccountProduct_overdraft();
			break;
		}
		case 7: {
			initializeCaseStudyFolderNames();
			bankAccountProduct_creditWithDailyLimit();
			break;
		}
		case 8: {
			initializeCaseStudyFolderNames();
			bankAccountProduct_overdraftWithInterest();
			break;
		}
		}
	}

	public static void initializeCaseStudyFolderNames() {
		esgFolderName.append("files/Cases/BankAccountPL");
		dotFolderName.append("files/DOTFiles/BankAccountPL");
	}
	
	public static void bankAccountProduct_baseProduct() {
		int numberOfFeatures = 3;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("bankAccountProduct-baseProduct");

		esgFileName[0] = "core.mxe";
		esgFileName[1] = "deposit.mxe";
		esgFileName[2] = "withdraw.mxe";
		esgName[0] = "core";
		esgName[1] = "deposit";
		esgName[2] = "withdraw";
		
	}

	public static void bankAccountProduct_cancellable() {
		int numberOfFeatures = 5;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("bankAccountProduct-cancellable");

		esgFileName[0] = "core.mxe";
		esgFileName[1] = "deposit.mxe";
		esgFileName[2] = "withdraw.mxe";
		esgFileName[3] = "cancelDeposit.mxe";
		esgFileName[4] = "cancelWithdraw.mxe";
		
		esgName[0] = "core";
		esgName[1] = "deposit";
		esgName[2] = "withdraw";
		esgName[3] = "cancelDeposit";
		esgName[4] = "cancelWithdraw";

	}

	public static void bankAccountProduct_credit() {
		int numberOfFeatures = 8;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("bankAccountProduct-credit");

		esgFileName[0] = "core.mxe";
		esgFileName[1] = "deposit.mxe";
		esgFileName[2] = "withdraw.mxe";
		esgFileName[3] = "cancelDeposit.mxe";
		esgFileName[4] = "cancelWithdraw.mxe";
		esgFileName[5] = "credit.mxe";
		esgFileName[6] = "interest.mxe";
		esgFileName[7] = "interestEstimation.mxe";
		
		esgName[0] = "core";
		esgName[1] = "deposit";
		esgName[2] = "withdraw";
		esgName[3] = "cancelDeposit";
		esgName[4] = "cancelWithdraw";
		esgName[5] = "credit";
		esgName[6] = "interest";
		esgName[7] = "interestEstimation";

	}

	public static void bankAccountProduct_dailyLimit() {
		int numberOfFeatures = 6;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("bankAccountProduct-dailyLimit");

		esgFileName[0] = "core.mxe";
		esgFileName[1] = "deposit.mxe";
		esgFileName[2] = "withdraw.mxe";
		esgFileName[3] = "cancelDeposit.mxe";
		esgFileName[4] = "cancelWithdraw.mxe";
		esgFileName[5] = "dailyLimit.mxe";

		
		esgName[0] = "core";
		esgName[1] = "deposit";
		esgName[2] = "withdraw";
		esgName[3] = "cancelDeposit";
		esgName[4] = "cancelWithdraw";
		esgName[5] = "dailyLimit";


	}

	public static void bankAccountProduct_interest() {
		int numberOfFeatures = 5;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("bankAccountProduct-interest");

		esgFileName[0] = "core.mxe";
		esgFileName[1] = "deposit.mxe";
		esgFileName[2] = "withdraw.mxe";
		esgFileName[3] = "interest.mxe";
		esgFileName[4] = "interestEstimation.mxe";
		
		esgName[0] = "core";
		esgName[1] = "deposit";
		esgName[2] = "withdraw";
		esgName[3] = "interest";
		esgName[4] = "interestEstimation";
	}

	public static void bankAccountProduct_overdraft() {
		int numberOfFeatures = 6;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("bankAccountProduct-overdraft");

		esgFileName[0] = "core.mxe";
		esgFileName[1] = "deposit.mxe";
		esgFileName[2] = "withdraw.mxe";
		esgFileName[3] = "cancelWithdraw.mxe";
		esgFileName[4] = "dailyLimit.mxe";
		esgFileName[5] = "overdraft.mxe";

		
		esgName[0] = "core";
		esgName[1] = "deposit";
		esgName[2] = "withdraw";
		esgName[3] = "cancelWithdraw";
		esgName[4] = "dailyLimit";
		esgName[5] = "overdraft";

	}
	
	public static void bankAccountProduct_creditWithDailyLimit() {
		int numberOfFeatures = 9;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("bankAccountProduct-creditWithDailyLimit");

		esgFileName[0] = "core.mxe";
		esgFileName[1] = "deposit.mxe";
		esgFileName[2] = "withdraw.mxe";
		esgFileName[3] = "cancelDeposit.mxe";
		esgFileName[4] = "cancelWithdraw.mxe";
		esgFileName[5] = "credit.mxe";
		esgFileName[6] = "interest.mxe";
		esgFileName[7] = "interestEstimation.mxe";
		esgFileName[8] = "dailyLimit.mxe";
		
		esgName[0] = "core";
		esgName[1] = "deposit";
		esgName[2] = "withdraw";
		esgName[3] = "cancelDeposit";
		esgName[4] = "cancelWithdraw";
		esgName[5] = "credit";
		esgName[6] = "interest";
		esgName[7] = "interestEstimation";
		esgName[8] = "dailyLimit";

	}
	
	public static void bankAccountProduct_overdraftWithInterest() {
		int numberOfFeatures = 8;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("bankAccountProduct-overdraftWithInterest");

		esgFileName[0] = "core.mxe";
		esgFileName[1] = "deposit.mxe";
		esgFileName[2] = "withdraw.mxe";
		esgFileName[3] = "cancelWithdraw.mxe";
		esgFileName[4] = "dailyLimit.mxe";
		esgFileName[5] = "overdraft.mxe";
		esgFileName[6] = "interest.mxe";
		esgFileName[7] = "interestEstimation.mxe";

		
		esgName[0] = "core";
		esgName[1] = "deposit";
		esgName[2] = "withdraw";
		esgName[3] = "cancelWithdraw";
		esgName[4] = "dailyLimit";
		esgName[5] = "overdraft";
		esgName[6] = "interest";
		esgName[7] = "interestEstimation";

	}
}
