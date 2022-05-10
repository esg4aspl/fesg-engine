package tr.edu.iyte.fesg.cases.bankaccount;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import tr.edu.iyte.esg.conversion.json.JSONFileToEliminationConditionConverter;
import tr.edu.iyte.esg.conversion.mxe.MXEFiletoESGConverter;
import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.esg.model.eliminationcondition.EliminationCondition;
import tr.edu.iyte.esg.model.validation.ESGValidator;
import tr.edu.iyte.fesg.cases.TestCoverageAnalysingUtilities;
import tr.edu.iyte.fesg.cases.resultrecordingutilities.FullProductESGReductionTimeMeasurementWriter;
import tr.edu.iyte.fesg.conversion.xml.FeatureModelModifier;
import tr.edu.iyte.fesg.conversion.xml.FeaturedESGToConfigurationFileConverter;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.automaticbuild.FeatureModelParser;
import tr.edu.iyte.fesg.fullesgtoproductesg.EliminationConditionApplier;
import tr.edu.iyte.fesg.model.FeaturedESG;
import tr.edu.iyte.fesg.model.featuredependencytree.FeatureDependencyTreeBuilder;
import tr.edu.iyte.fesg.model.featuremodel.FeatureModel;



public class BankAccountEliminationConditionApp_Credit extends TestCoverageAnalysingUtilities  {

	public static void main(String[] args) {

		/*
		 * Full Product-Credit
		 * deposit -> cancelDeposit
		 * withdraw -> cancelWithdraw dailyLimit
		 * cancelWithdraw -> dailyLimit 
		 * interest -> interestEstimation 
		 */
		productID = 9;
		BankAccountCaseStudyUtilities.buildProductModel();
		FeaturedESG FESG = buildFeaturedESG();
		
		String SPLFolderName = "files/Cases/BankAccountPL/";
		String productType = "ba";
		String fullESGName = "fullProduct_Credit";
		String eliminationConditionFeatureName = "credit_interestEstimation_dailyLimit";

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

		for(EliminationCondition guardCondition : eliminationConditionList) {
			System.out.println(guardCondition.toString());
			
		}

		EliminationConditionApplier guardConditionApplier = new EliminationConditionApplier();
		FeatureDependencyTreeBuilder featureDependencyTreeBuilder = guardConditionApplier.getFeatureDependencyTreeBuilder(FESG);
		
		double startTime1 = System.nanoTime();
		guardConditionApplier.applyEliminationCondition(ESG, featureDependencyTreeBuilder, eliminationConditionList);
		double stopTime1 = System.nanoTime();
		double timeElapsed1 = (stopTime1 - startTime1) ;
		timeElapsed1 = timeElapsed1 / (double) 1000000;
		System.out.println("Execution time of feature " + eliminationConditionFeatureName + " removal from full product " + fullESGName + "  in miliseconds  : "
				+ timeElapsed1);
		
		FullProductESGReductionTimeMeasurementWriter.writeTimeMeasurement(timeElapsed1, SPLFolderName, productType, fullESGName,
				eliminationConditionFeatureName);
		System.out.println("AFTER GUARD CONDITION APPLYING");
		
		ESGValidator ESGValidator = new ESGValidator();
		ESGValidator.validate(guardConditionApplier.getResultingFullESG());
		
		FeaturedESG resultingFESG = guardConditionApplier.getResultingFESG();
		//System.out.println(resultingFESG.toString());
		
		String featureModelPath = "files/Cases/BankAccountPL/FullProductReduction/configs/model.xml";
		FeatureModelParser featureModelParser = new FeatureModelParser();
		FeatureModel featureModel = featureModelParser.parseXMLFileForFeatureModelCreation(featureModelPath);
		
		String configFilePath = "files/Cases/BankAccountPL/FullProductReduction/configs/" + productType + "-"
				+ fullESGName + "-" + eliminationConditionFeatureName + ".xml";
		
		FeaturedESGToConfigurationFileConverter.convertFeaturedESGToConfigurationFile(configFilePath, resultingFESG, featureModel);

	}

}
