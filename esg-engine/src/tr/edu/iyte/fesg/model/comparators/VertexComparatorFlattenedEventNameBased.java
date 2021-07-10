package tr.edu.iyte.fesg.model.comparators;

import java.util.Comparator;
import tr.edu.iyte.esg.model.Vertex;
import tr.edu.iyte.fesg.testsequencecomposition.TestSequenceCompositionUtilities;

public class VertexComparatorFlattenedEventNameBased implements Comparator<Vertex> {

	@Override
	public int compare(Vertex vertex0, Vertex vertex1) {
		String[] esg_ve0 = TestSequenceCompositionUtilities.splitVertexEventName(vertex0.getEvent().getName());
		String[] esg_ve1 = TestSequenceCompositionUtilities.splitVertexEventName(vertex1.getEvent().getName());
		return esg_ve0[1].compareTo(esg_ve1[1]);
	}
	
}
