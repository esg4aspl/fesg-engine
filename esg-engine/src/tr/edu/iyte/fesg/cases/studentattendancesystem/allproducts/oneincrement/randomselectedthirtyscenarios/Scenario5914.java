package tr.edu.iyte.fesg.cases.studentattendancesystem.allproducts.oneincrement.randomselectedthirtyscenarios;

import java.util.Set;

import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.fesg.cases.IncrementalTestSequenceCompositionUtilities;
import tr.edu.iyte.fesg.cases.studentattendancesystem.StudentAttendanceSystemCaseStudyUtilities;
import tr.edu.iyte.fesg.cases.studentattendancesystem.allproducts.AllPossibleProductsGenerator;
import tr.edu.iyte.fesg.cases.studentattendancesystem.allproducts.AllPossibleProductsGeneratorApp;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraph;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraphEdge;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraphVertex;
import tr.edu.iyte.fesg.model.FeaturedESG;

public class Scenario5914 extends StudentAttendanceSystemCaseStudyUtilities {

	public static void main(String[] args) {
		createCaseStudyFilePathObjects();
		initializeCaseStudyFolderNames();

		coverageLenght = 2;
		incrementalBasisApproachID = 1;
		// PUC ID-Scenario ID-Existing Product ID
		existingProductESGName.append("sas-1432-5914-1380");

		AllPossibleProductsGenerator allPossibleProductsGenerator = AllPossibleProductsGeneratorApp
				.getAllPossibleProductsGenerator();
		FeaturedESG reusableFESG = AllPossibleProductsGeneratorApp.findFeaturedESGByProductName(
				allPossibleProductsGenerator,
				"viewSchedule_email_studentAccess_teacherAccess_addNewClass_updateClassDetail_addNewSchedule_monitorAttendanceStatus_deleteClass_accessCard");
		String coreESGNameOfPL = reusableFESG.getCoreESGName();
		ESG coreESGOfPL = reusableFESG.getCoreESG();

		FeatureBasedIncrementalProductGraph featureBasedIncrementalProductGraph = new FeatureBasedIncrementalProductGraph(
				"StudentAttendanceSystemPL");
		FeatureBasedIncrementalProductGraphVertex start = new FeatureBasedIncrementalProductGraphVertex(reusableFESG,
				true, true);

		FeaturedESG product1 = new FeaturedESG(
				"viewClass_viewSchedule_email_studentAccess_teacherAccess_addNewClass_updateClassDetail_addNewSchedule_monitorAttendanceStatus_deleteClass_accessCard",
				coreESGNameOfPL);
		product1.setCoreESG(coreESGOfPL);
		FeatureBasedIncrementalProductGraphVertex vertex1 = new FeatureBasedIncrementalProductGraphVertex(product1,
				true, false);

		FeatureBasedIncrementalProductGraphEdge edge1 = new FeatureBasedIncrementalProductGraphEdge(start, vertex1);
		Set<ESG> featureESGSet1 = IncrementalTestSequenceCompositionUtilities.buildNewFeatureESGSet("viewClass");
		edge1.addFeatureESGSet(featureESGSet1);

		featureBasedIncrementalProductGraph.addVertex(start);
		featureBasedIncrementalProductGraph.addVertex(vertex1);

		featureBasedIncrementalProductGraph.addEdge(edge1);

		featureBasedIncrementalProductGraph.incrementalTestSequenceCompositionBFT(start, coverageLenght,incrementalBasisApproachID);
		coverageLenght = 3;
		featureBasedIncrementalProductGraph.incrementalTestSequenceCompositionBFT(start, coverageLenght,incrementalBasisApproachID);
		coverageLenght = 4;
		featureBasedIncrementalProductGraph.incrementalTestSequenceCompositionBFT(start, coverageLenght,incrementalBasisApproachID);

	}

}
