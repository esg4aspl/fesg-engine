package tr.edu.iyte.fesg.cases.bankaccount;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import tr.edu.iyte.esg.conversion.mxe.MXEFiletoESGConverter;
import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.esg.model.validation.ESGValidator;
import tr.edu.iyte.fesg.cases.TestCoverageAnalysingUtilities;
import tr.edu.iyte.fesg.cases.resultrecordingutilities.FullProductESGReductionTimeMeasurementWriter;
import tr.edu.iyte.fesg.conversion.JSONFileToEliminationConditionConverter;
import tr.edu.iyte.fesg.conversion.xml.FeaturedESGToConfigurationFileConverter;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.automaticbuild.FeatureModelParser;
import tr.edu.iyte.fesg.fullesgtoproductesg.EliminationConditionApplier;
import tr.edu.iyte.fesg.model.FeaturedESG;
import tr.edu.iyte.fesg.model.eliminationcondition.EliminationCondition;
import tr.edu.iyte.fesg.model.featuredependencytree.FeatureDependencyTreeBuilder;
import tr.edu.iyte.fesg.model.featuremodel.FeatureModel;


public class BankAccountEliminationConditionApp_Overdraft extends TestCoverageAnalysingUtilities  {

	public static void main(String[] args) {

		/*
		 * Full Product-Overdraft
		 * deposit -> cancelDeposit
		 * withdraw -> cancelWithdraw dailyLimit overdraft
		 * cancelWithdraw -> dailyLimit overdraft
		 * interest -> interestEstimation 
		 * dailyLimit -> overdraft 
		 */
		productID = 10;
		BankAccountCaseStudyUtilities.buildProductModel();
		FeaturedESG FESG = buildFeaturedESG();

		String SPLFolderName = "files/Cases/BankAccountPL/";
		String productType = "ba";
		String fullESGName = "fullProduct_Overdraft";
		String eliminationConditionFeatureName = "interest_interestEstimation_dailyLimit";

		String ESGfileName = SPLFolderName + "bankAccountProduct-" + fullESGName + ".mxe";
		String eliminationConditionFileName = SPLFolderName + "JSONFiles/bankAccountProduct-eliminationCondition_"
				+ eliminationConditionFeatureName + ".json";
		
		
		ESG ESG = null;
		try {
			ESG = MXEFiletoESGConverter.parseMXEFileForESGSimpleCreation(ESGfileName);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<EliminationCondition> eliminationConditionList = new LinkedList<EliminationCondition>();
		try {
			eliminationConditionList = JSONFileToEliminationConditionConverter.parseJSONFileForEliminationConditionCreation(eliminationConditionFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for (EliminationCondition eliminationCondition : eliminationConditionList) {
			System.out.println(eliminationCondition.toString());

		}

		EliminationConditionApplier eliminationConditionApplier = new EliminationConditionApplier();
		FeatureDependencyTreeBuilder featureDependencyTreeBuilder = eliminationConditionApplier.getFeatureDependencyTreeBuilder(FESG);
		
		double startTime1 = System.nanoTime();
		eliminationConditionApplier.applyEliminationCondition(ESG, featureDependencyTreeBuilder, eliminationConditionList);
		double stopTime1 = System.nanoTime();
		double timeElapsed1 = (stopTime1 - startTime1) ;
		timeElapsed1 = timeElapsed1 / (double) 1000000;
		System.out.println("Execution time of feature " + eliminationConditionFeatureName + " removal from full product " + fullESGName + "  in miliseconds  : "
				+ timeElapsed1);
		
		FullProductESGReductionTimeMeasurementWriter.writeTimeMeasurement(timeElapsed1, SPLFolderName, productType, fullESGName,
				eliminationConditionFeatureName);
		System.out.println("AFTER ELIMINATION CONDITION APPLYING");
		ESGValidator ESGValidator = new ESGValidator();
		ESGValidator.validate(eliminationConditionApplier.getResultingFullESG());
		
		FeaturedESG resultingFESG = eliminationConditionApplier.getResultingFESG();
		//System.out.println(resultingFESG.toString());
		
		String featureModelPath = "files/Cases/BankAccountPL/FullProductReduction/configs/model.xml";
		FeatureModelParser featureModelParser = new FeatureModelParser();
		FeatureModel featureModel = featureModelParser.parseXMLFileForFeatureModelCreation(featureModelPath);
		
		String configFilePath = "files/Cases/BankAccountPL/FullProductReduction/configs/" + productType + "-"
				+ fullESGName + "-" + eliminationConditionFeatureName + ".xml";
		
		FeaturedESGToConfigurationFileConverter.convertFeaturedESGToConfigurationFile(configFilePath, resultingFESG, featureModel);

	}

}
