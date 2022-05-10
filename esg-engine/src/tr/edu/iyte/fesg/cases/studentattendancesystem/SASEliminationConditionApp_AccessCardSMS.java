package tr.edu.iyte.fesg.cases.studentattendancesystem;

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
import tr.edu.iyte.fesg.conversion.xml.FeaturedESGToConfigurationFileConverter;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.automaticbuild.FeatureModelParser;
import tr.edu.iyte.fesg.fullesgtoproductesg.EliminationConditionApplier;
import tr.edu.iyte.fesg.model.FeaturedESG;
import tr.edu.iyte.fesg.model.featuredependencytree.FeatureDependencyTreeBuilder;
import tr.edu.iyte.fesg.model.featuremodel.FeatureModel;

public class SASEliminationConditionApp_AccessCardSMS extends TestCoverageAnalysingUtilities {

	public static void main(String[] args) {

		/*
		 * fullProduct_QRCodeSMS
		 */
		productID = 8;
		SASCaseStudyUtilities.buildProductModel();
		FeaturedESG FESG = buildFeaturedESG();
		
		String SPLFolderName = "files/Cases/StudentAttendanceSystemPL/";
		String productType = "sas";
		String fullESGName = "fullProduct_QRCodeSMS";
		String guardConditionFeatureName = "viewSchedule";

		//
		String ESGfileName = SPLFolderName + "sasProduct-" + fullESGName + ".mxe";
		String guardConditionFileName = SPLFolderName + "JSONFiles/sasProduct-guardCondition_"
				+ guardConditionFeatureName + ".json";

		ESG ESG = null;
		try {
			ESG = MXEFiletoESGConverter.parseMXEFileForESGSimpleCreation(ESGfileName);

		} catch (Exception e) {
			e.printStackTrace();
		}

		List<EliminationCondition> guardConditionList = new LinkedList<EliminationCondition>();
		try {
			guardConditionList = JSONFileToEliminationConditionConverter
					.parseJSONFileForEliminationConditionCreation(guardConditionFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for (EliminationCondition guardCondition : guardConditionList) {
			System.out.println(guardCondition.toString());

		}
		EliminationConditionApplier guardConditionApplier = new EliminationConditionApplier();
		FeatureDependencyTreeBuilder featureDependencyTreeBuilder = guardConditionApplier.getFeatureDependencyTreeBuilder(FESG);
		
		double startTime1 = System.nanoTime();
		guardConditionApplier.applyEliminationCondition(ESG, featureDependencyTreeBuilder, guardConditionList);
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
		
		String featureModelPath = "files/Cases/StudentAttendanceSystemPL/configs/model.xml";
		FeatureModelParser featureModelParser = new FeatureModelParser();
		FeatureModel featureModel = featureModelParser.parseXMLFileForFeatureModelCreation(featureModelPath);
		
		FeaturedESGToConfigurationFileConverter.convertFeaturedESGToConfigurationFile("", resultingFESG, featureModel);

	}

}
