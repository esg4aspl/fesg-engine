package tr.edu.iyte.fesg.cases.bankaccount.allproducts;

import java.util.LinkedHashSet;

import java.util.Set;

import tr.edu.iyte.esg.conversion.ESGToRRGFileConversion;
import tr.edu.iyte.esg.conversion.json.JSONFileToESGConverter;
import tr.edu.iyte.esg.model.ESG;

import tr.edu.iyte.fesg.cases.bankaccount.BankAccountCaseStudyUtilities;

public class SingleModelTestGeneration extends BankAccountCaseStudyUtilities {

	public static void main(String[] args) {
		createCaseStudyFilePathObjects();
		initializeCaseStudyFolderNames();
		coverageLenght = 2;

		for (String productName : getPUCNamesSet()) {
			String fileName = esgFolderName + "/allProductsJSONFiles/" + productName + ".json";

			ESG ESG = JSONFileToESGConverter.parseJSONFileForESGCreation(fileName);
			/*
			 * TestSuiteGenerator testSuiteGenerator = new TestSuiteGenerator(); TestSuite
			 * testSuite = testSuiteGenerator.generateTestSuite(coverageLenght, ESG); try {
			 * EventSequenceUtilities.esgEventSequenceSetFileWriter(testSuite.
			 * getCompleteEventSequences(), esgFolderName +
			 * "/Results_SingleModel/TestSequences/" + ESG.getName() + "_length" +
			 * coverageLenght + ".txt"); } catch (IOException e) { // TODO Auto-generated
			 * catch block e.printStackTrace(); }
			 * 
			 * try {
			 * EventSequenceUtilities.esgEventSequenceSetFileWriterForMutationAnalysis(
			 * testSuite.getCompleteEventSequences(), esgFolderName +
			 * "/1RegFiles/sequences/" + productName + "_rrg-1seq-tb_length" +
			 * coverageLenght + "-SM_sequences.txt"); } catch (IOException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 */
			ESGToRRGFileConversion.convertESGToRRGFileWithoutPseudoEvents(ESG,
					esgFolderName + "/1RegFiles/productmodels/");

		}

	}

	private static Set<String> getPUCNamesSet() {

		Set<String> PUCNameSet = new LinkedHashSet<String>();

		PUCNameSet.add("cancelWithdraw");
		PUCNameSet.add("credit");
		PUCNameSet.add("cancelDeposit_interest");
		PUCNameSet.add("credit_interest");
		PUCNameSet.add("interest_cancelWithdraw");
		PUCNameSet.add("credit_cancelWithdraw");
		PUCNameSet.add("interest_interestEstimation");
		PUCNameSet.add("cancelWithdraw_dailyLimit");
		PUCNameSet.add("cancelDeposit_credit_interest");
		PUCNameSet.add("cancelDeposit_interest_cancelWithdraw");

		PUCNameSet.add("cancelDeposit_cancelWithdraw_dailyLimit");
		PUCNameSet.add("credit_interest_interestEstimation");
		PUCNameSet.add("cancelDeposit_credit_cancelWithdraw");
		PUCNameSet.add("interest_cancelWithdraw_interestEstimation");
		PUCNameSet.add("credit_cancelWithdraw_dailyLimit");
		PUCNameSet.add("cancelDeposit_interest_cancelWithdraw_interestEstimation");
		PUCNameSet.add("cancelDeposit_cancelWithdraw_dailyLimit_overdraft");
		PUCNameSet.add("cancelDeposit_credit_cancelWithdraw_dailyLimit");
		PUCNameSet.add("credit_interest_cancelWithdraw_interestEstimation");
		PUCNameSet.add("credit_interest_cancelWithdraw_dailyLimit");

		PUCNameSet.add("cancelDeposit_interest_cancelWithdraw_dailyLimit");
		PUCNameSet.add("interest_cancelWithdraw_dailyLimit_overdraft");
		PUCNameSet.add("cancelDeposit_credit_interest_cancelWithdraw_dailyLimit");
		PUCNameSet.add("cancelDeposit_interest_cancelWithdraw_dailyLimit_overdraft");
		PUCNameSet.add("cancelDeposit_credit_interest_cancelWithdraw_interestEstimation");
		PUCNameSet.add("cancelDeposit_interest_cancelWithdraw_dailyLimit_interestEstimation");
		PUCNameSet.add("credit_interest_cancelWithdraw_dailyLimit_interestEstimation");
		PUCNameSet.add("interest_cancelWithdraw_dailyLimit_interestEstimation_overdraft");
		PUCNameSet.add("cancelDeposit_interest_cancelWithdraw_dailyLimit_interestEstimation_overdraft");
		PUCNameSet.add("cancelDeposit_credit_interest_cancelWithdraw_dailyLimit_interestEstimation");

		System.out.println(PUCNameSet.size());

		return PUCNameSet;
	}

}
