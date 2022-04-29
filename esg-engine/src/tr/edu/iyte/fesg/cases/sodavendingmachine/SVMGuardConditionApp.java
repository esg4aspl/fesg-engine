package tr.edu.iyte.fesg.cases.sodavendingmachine;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import tr.edu.iyte.esg.conversion.json.JSONFileToGuardConditionConverter;
import tr.edu.iyte.esg.conversion.mxe.MXEFiletoESGConverter;
import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.esg.model.guardcondition.GuardCondition;
import tr.edu.iyte.esg.model.validation.ESGValidator;
import tr.edu.iyte.fesg.cases.TestCoverageAnalysingUtilities;
import tr.edu.iyte.fesg.cases.resultrecordingutilities.FullProductESGReductionTimeMeasurementWriter;
import tr.edu.iyte.fesg.conversion.xml.FeatureModelModifier;
import tr.edu.iyte.fesg.conversion.xml.FeaturedESGToConfigurationFileConverter;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.automaticbuild.FeatureModelParser;
import tr.edu.iyte.fesg.fullesgtoproductesg.GuardConditionApplier;
import tr.edu.iyte.fesg.model.FeaturedESG;
import tr.edu.iyte.fesg.model.featuredependencytree.FeatureDependencyTreeBuilder;
import tr.edu.iyte.fesg.model.featuremodel.FeatureModel;


public class SVMGuardConditionApp extends TestCoverageAnalysingUtilities  {

	public static void main(String[] args) {
				
		productID = 6;
		SVMCaseStudyUtilities.buildProductModel();
		FeaturedESG FESG = buildFeaturedESG();
		//System.out.println(FESG);
		
		String SPLFolderName = "files/Cases/SodaVendingMachinePL/";
		String productType = "svm";
		String fullESGName = "fullProduct";
		String guardConditionFeatureName = "cancel";

		String ESGfileName = SPLFolderName + "svmProduct-" + fullESGName + ".mxe";
		String guardConditionFileName = SPLFolderName + "JSONFiles/svmProduct-guardCondition_"
				+ guardConditionFeatureName + ".json";

		ESG ESG = null;
		try {
			ESG = MXEFiletoESGConverter.parseMXEFileForESGSimpleCreation(ESGfileName);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//System.out.println(ESG.toString());
		
		List<GuardCondition> guardConditionList = new LinkedList<GuardCondition>();
		try {
			guardConditionList = JSONFileToGuardConditionConverter.parseJSONFileForGuardConditionCreation(guardConditionFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for(GuardCondition guardCondition : guardConditionList) {
			System.out.println(guardCondition.toString());
			
		}
		GuardConditionApplier guardConditionApplier = new GuardConditionApplier();
		FeatureDependencyTreeBuilder featureDependencyTreeBuilder = guardConditionApplier.getFeatureDependencyTreeBuilder(FESG);
		
		double startTime1 = System.nanoTime();
		guardConditionApplier.applyGuardCondition(ESG, featureDependencyTreeBuilder, guardConditionList);
		double stopTime1 = System.nanoTime();
		double timeElapsed1 = (stopTime1 - startTime1) ;
		timeElapsed1 = timeElapsed1 / (double) 1000000;
		System.out.println("Execution time of feature " + guardConditionFeatureName + " removal from full product " + fullESGName + "  in miliseconds  : "
				+ timeElapsed1);
		
		FullProductESGReductionTimeMeasurementWriter.writeTimeMeasurement(timeElapsed1, SPLFolderName, productType, fullESGName,
				guardConditionFeatureName);
		
		System.out.println("AFTER GUARD CONDITION APPLYING");
		
		ESGValidator ESGValidator = new ESGValidator();
		ESGValidator.validate(guardConditionApplier.getResultingFullESG());
		
		FeaturedESG resultingFESG = guardConditionApplier.getResultingFESG();
		System.out.println(resultingFESG.toString());

		String featureModelPath = "files/Cases/SodaVendingMachinePL/configs/model.xml";
		FeatureModelParser featureModelParser = new FeatureModelParser();
		FeatureModel featureModel = featureModelParser.parseXMLFileForFeatureModelCreation(featureModelPath);
		
		FeaturedESGToConfigurationFileConverter.convertFeaturedESGToConfigurationFile("", resultingFESG, featureModel);

		
	}

}
