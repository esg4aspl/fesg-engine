 package tr.edu.iyte.fesg.webapi;

import java.util.Set;

import tr.edu.iyte.esg.eventsequence.EventSequence;
import tr.edu.iyte.fesg.model.FeaturedESG;
import tr.edu.iyte.fesg.testsequencecomposition.ESGSequenceMap;
import tr.edu.iyte.fesg.testsequencecomposition.FullTestSequenceComposer;
import tr.edu.iyte.fesg.testsequencecomposition.TestSequenceCompositionUtilities;


public class TestSequenceCompositionAPI {
	
	
	public Set<EventSequence> composeTestSequences(FeaturedESG featuredESG, int coverageLenght) {
		
		ESGSequenceMap esgSequenceMap = new ESGSequenceMap();
		Set<EventSequence> cesSet = null;
		int numberOfTransformations = coverageLenght - 2;
		esgSequenceMap.generateESGSequenceMapFromFeaturedESG(featuredESG,numberOfTransformations);
		
		Set<String> startVertexEventNames = TestSequenceCompositionUtilities.getNextVertexEventNames(featuredESG, featuredESG.getCoreESGName(), "[");
		Set<String> finishVertexEventNames = TestSequenceCompositionUtilities.getPreviousVertexEventNames(featuredESG,featuredESG.getCoreESGName(), "]");
		
		FullTestSequenceComposer fullTestSequenceCompositionAlgorithm = new FullTestSequenceComposer(esgSequenceMap.getESGSequenceMap(), startVertexEventNames, finishVertexEventNames,coverageLenght);
		fullTestSequenceCompositionAlgorithm.compose();
		cesSet = fullTestSequenceCompositionAlgorithm.getCompleteSequences();

		return cesSet;
	}
}
