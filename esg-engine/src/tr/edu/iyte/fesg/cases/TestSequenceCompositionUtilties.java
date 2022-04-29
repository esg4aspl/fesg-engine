package tr.edu.iyte.fesg.cases;

import java.util.Set;

import tr.edu.iyte.esg.eventsequence.EventSequence;
import tr.edu.iyte.esg.eventsequence.EventSequenceUtilities;
import tr.edu.iyte.fesg.model.FeaturedESG;
import tr.edu.iyte.fesg.testsequencecomposition.ESGSequenceMap;
import tr.edu.iyte.fesg.testsequencecomposition.FullTestSequenceComposer;
import tr.edu.iyte.fesg.testsequencecomposition.TestSequenceCompositionUtilities;

public class TestSequenceCompositionUtilties extends CaseStudyFilePathCreationUtilities {
	
	
	public static Set<EventSequence> composeSequencesFully(FeaturedESG featuredESG) {
		ESGSequenceMap esgSequenceMap = new ESGSequenceMap();
		Set<EventSequence> cesSet = null;
		esgSequenceMap.generateESGSequenceMapFromFeaturedESG(featuredESG,coverageLenght);
//EventSequenceUtilities.esgTestSequenceMapPrinter(esgSequenceMap.getESGSequenceMap(), "ESG SEQUENCE MAP OF FULL TEST SEQUENCE COMPOSITION");
		
		/*
		 * Start and finish vertices are found.
		 */
		Set<String> startVertexEventNames = TestSequenceCompositionUtilities.getNextVertexEventNames(featuredESG,
				featuredESG.getCoreESGName(), "[");
//System.out.print("startVertexEventNames: "); startVertexEventNames.forEach(e -> System.out.print(e + ", ")); System.out.println();
		
		
		Set<String> finishVertexEventNames = TestSequenceCompositionUtilities.getPreviousVertexEventNames(featuredESG,
				featuredESG.getCoreESGName(), "]");
//System.out.print("finishVertexEventNames: "); finishVertexEventNames.forEach(e -> System.out.print(e + ", "));System.out.println();
		
		FullTestSequenceComposer fullTestSequenceCompositionAlgorithm = new FullTestSequenceComposer(
				esgSequenceMap.getESGSequenceMap(), startVertexEventNames, finishVertexEventNames, coverageLenght);
		fullTestSequenceCompositionAlgorithm.compose();
		
		cesSet = fullTestSequenceCompositionAlgorithm.getCompleteSequences();

//EventSequenceUtilities.esgEventSequenceSetPrinter(cesSet);

		return cesSet;
	}
	
	
	public static Set<EventSequence> composeSequencesFullyWithTimeMeasurement(FeaturedESG featuredESG) {
		

		ESGSequenceMap esgSequenceMap = new ESGSequenceMap();
		Set<EventSequence> cesSet = null;
		double startTime1 = System.nanoTime();
		esgSequenceMap.generateESGSequenceMapFromFeaturedESG(featuredESG,numberOfTransformations);
		//EventSequenceUtilities.esgTestSequenceMapPrinter(esgSequenceMap.getESGSequenceMap(), "ESG SEQUENCE MAP");

		/*
		 * Start and finish vertices are found.
		 */
		Set<String> startVertexEventNames = TestSequenceCompositionUtilities.getNextVertexEventNames(featuredESG, featuredESG.getCoreESGName(), "[");
//System.out.print("startVertexEventNames: ");startVertexEventNames.forEach(e->System.out.print(e + ", "));System.out.println();
		Set<String> finishVertexEventNames = TestSequenceCompositionUtilities.getPreviousVertexEventNames(featuredESG,featuredESG.getCoreESGName(), "]");
//System.out.print("finishVertexEventNames: ");finishVertexEventNames.forEach(e->System.out.print(e + ", "));System.out.println();
		double stopTime1 = System.nanoTime();
		double timeElapsed1 = stopTime1 - startTime1;
		System.out.println("Execution time of necessary object generation for test composition  in miliseconds  : "
				+ timeElapsed1 / (double) 1000000);
		
		FullTestSequenceComposer fullTestSequenceCompositionAlgorithm = new FullTestSequenceComposer(esgSequenceMap.getESGSequenceMap(), startVertexEventNames, finishVertexEventNames,coverageLenght);

		double startTime2 = System.nanoTime();
		fullTestSequenceCompositionAlgorithm.compose();
		cesSet = fullTestSequenceCompositionAlgorithm.getCompleteSequences();
		//EventSequenceUtilities.esgEventSequenceSetPrinter(cesSet);
		double stopTime2 = System.nanoTime();
		double timeElapsed2 = stopTime2 - startTime2;
		System.out.println("Execution time of test composition in miliseconds  : "
				+ timeElapsed2 / (double) 1000000);
		
		double timeElapsed = timeElapsed1 + timeElapsed2;

		System.out.println("Execution time of full test composition in miliseconds  : "
				+ timeElapsed / (double) 1000000);
		
		return cesSet;

	}

}
