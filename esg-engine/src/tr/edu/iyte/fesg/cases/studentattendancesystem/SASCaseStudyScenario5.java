package tr.edu.iyte.fesg.cases.studentattendancesystem;

import java.util.Set;

import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.fesg.cases.IncrementalTestSequenceCompositionUtilities;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraph;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraphEdge;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraphVertex;
import tr.edu.iyte.fesg.model.FeaturedESG;

public class SASCaseStudyScenario5 extends StudentAttendanceSystemCaseStudyUtilities {

	/**
	 * productID = 1 -> sasProduct-studentUserBarcodeSMS
	 * productID = 2 -> sasProduct-teacherUserAccessCardEmail
	 * productID = 3 -> sasProduct-limitedStudentUserBarcodeSMS
	 * productID = 4 -> sasProduct-limitedteacherUserAccessCardEmail
	 * productID = 5 -> sasProduct-limitedteacherUserFingerprintEmail
	 * productID = 6 -> sasProduct-limitedTeacherUserQRCodeSMS
	 * productID = 7 -> sasProduct-bothUsersAccessCardEmail
	 * productID = 8 -> sasProduct-bothUsersBarcodeSMS
	 * productID = 9 -> sasProduct-bothUsersFingerprintEmail
	 * productID = 10 -> sasProduct-bothUsersQRCodeSMS
	 * productID = 11 -> sasProduct-substituteTeacherUserAccessCardEmail
	 * 
	 * 
	 * initialize productID to build different products
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		productID = 5;
		incrementalBasisApproachID = 0;
		coverageLenght = 4;
		StudentAttendanceSystemCaseStudyUtilities.buildProductModel();

		FeaturedESG product1 = buildFeaturedESG();

		String coreESGNameOfPL = product1.getCoreESGName();
		ESG coreESGOfPL = product1.getCoreESG();

		FeatureBasedIncrementalProductGraph featureBasedIncrementalProductGraph = new FeatureBasedIncrementalProductGraph(
				"StudentAttendanceSystemPL");

		FeatureBasedIncrementalProductGraphVertex vertex1 = new FeatureBasedIncrementalProductGraphVertex(product1,
				true, true);

		FeaturedESG product2 = new FeaturedESG("sasProduct-bothUsersFingerprintEmail", coreESGNameOfPL);
		product2.setCoreESG(coreESGOfPL);
		FeatureBasedIncrementalProductGraphVertex vertex2 = new FeatureBasedIncrementalProductGraphVertex(product2,
				true, false);

		FeatureBasedIncrementalProductGraphEdge edge1 = new FeatureBasedIncrementalProductGraphEdge(vertex1, vertex2);
		Set<ESG> featureESGSet1 = IncrementalTestSequenceCompositionUtilities.buildNewFeatureESGSet("studentAccess",
				"viewRecord", "monitorAttendanceStatus", "addNewSchedule", "editSchedule", "assignNewSchedule");
		edge1.addFeatureESGSet(featureESGSet1);

		FeaturedESG product3 = new FeaturedESG("sasProduct-limitedTeacherUserQRCodeSMS", coreESGNameOfPL);
		product3.setCoreESG(coreESGOfPL);
		FeatureBasedIncrementalProductGraphVertex vertex3 = new FeatureBasedIncrementalProductGraphVertex(product3,
				true, true);
		
		FeaturedESG product4 = new FeaturedESG("sasProduct-bothUsersQRCodeSMS", coreESGNameOfPL);
		product4.setCoreESG(coreESGOfPL);
		FeatureBasedIncrementalProductGraphVertex vertex4 = new FeatureBasedIncrementalProductGraphVertex(product4,
				true, false);

		
		FeatureBasedIncrementalProductGraphEdge edge2 = new FeatureBasedIncrementalProductGraphEdge(vertex3, vertex4);
		edge2.addFeatureESGSet(featureESGSet1);
		

		
		featureBasedIncrementalProductGraph.addVertex(vertex1);
		featureBasedIncrementalProductGraph.addVertex(vertex2);
		featureBasedIncrementalProductGraph.addVertex(vertex3);
		
		featureBasedIncrementalProductGraph.addEdge(edge1);
		featureBasedIncrementalProductGraph.addEdge(edge2);
		
		featureBasedIncrementalProductGraph.incrementalTestSequenceCompositionBFT(vertex1,coverageLenght,incrementalBasisApproachID);
		//featureBasedIncrementalProductGraph.fullTestSequenceCompositionBFT(vertex1);
		//featureBasedIncrementalProductGraph.fullESGGenerationBFT(vertex1);

	}

}
