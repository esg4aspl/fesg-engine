package tr.edu.iyte.fesg.fullesggeneration;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import java.util.Map;
import java.util.Set;

import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.esg.model.Edge;
import tr.edu.iyte.esg.model.EdgeSimple;
import tr.edu.iyte.esg.model.Event;
import tr.edu.iyte.esg.model.EventSimple;
import tr.edu.iyte.esg.model.Vertex;
import tr.edu.iyte.esg.model.VertexSimple;
import tr.edu.iyte.fesg.model.FeaturedESG;
import tr.edu.iyte.fesg.testsequencecomposition.TestSequenceCompositionUtilities;

public class FullESGGenerator {

	private ESG fullESG;
	private FeaturedESG featuredESG;
	private Map<String, String> eventNameESGNameMap;

	public FullESGGenerator(int fullESGID, FeaturedESG featuredESG) {
		fullESG = new ESG(fullESGID, featuredESG.getName());
		this.featuredESG = featuredESG;
		eventNameESGNameMap = new LinkedHashMap<String, String>();
	}

	public ESG getFullESG() {
		return fullESG;
	}

	public void setFullESG(ESG fullESG) {
		this.fullESG = fullESG;
	}

	public Map<String, String> getEventNameESGNameMap() {
		return eventNameESGNameMap;
	}

	public FeaturedESG getFeaturedESG() {
		return featuredESG;
	}

	public void generateFullESG() {

		addFullESGPseudoVertices();
		Set<ESG> featuredESGSet = featuredESG.getFeaturedESGSet();
		Iterator<ESG> esgListIterator = featuredESGSet.iterator();
		Set<String> eventNameSet = new LinkedHashSet<String>();
		Set<String> vertexIDSet = new LinkedHashSet<String>();

		while (esgListIterator.hasNext()) {
			ESG currentESG = esgListIterator.next();

			if (currentESG.getID() == 0) {
				coreEGS(currentESG, eventNameSet, vertexIDSet);
			} else {
				// vertexIDSet.forEach(e->System.out.println(e));
				featureEGS(currentESG, eventNameSet, vertexIDSet);
			}

		}
	}

	private void addFullESGPseudoVertices() {
		Event pseudoStart = new EventSimple(fullESG.getNextEventID(), "[");
		Event pseudoEnd = new EventSimple(fullESG.getNextEventID(), "]");
		fullESG.addEvent(pseudoStart);
		fullESG.addEvent(pseudoEnd);

		fullESG.addVertex(new VertexSimple(fullESG.getNextVertexID(), pseudoStart));
		fullESG.addVertex(new VertexSimple(fullESG.getNextVertexID(), pseudoEnd));
	}

	private void featureEGS(ESG currentESG, Set<String> eventNameSet, Set<String> vertexIDSet) {

		Iterator<Edge> edgeListIterator = currentESG.getEdgeList().iterator();

		while (edgeListIterator.hasNext()) {
			Edge currentEdge = edgeListIterator.next();

			if (currentEdge.getSource().isPseudoStartVertex()) {
				if (!TestSequenceCompositionUtilities.isConnectionPoint(currentEdge.getTarget().getEvent().getName())) {

				}
			}
			if (currentEdge.getTarget().isPseudoEndVertex()) {
				if (!TestSequenceCompositionUtilities.isConnectionPoint(currentEdge.getSource().getEvent().getName())) {

				}
			} else {
				if (TestSequenceCompositionUtilities.isConnectionPoint(currentEdge.getSource().getEvent().getName())) {

					Vertex source = null;
					Vertex target = null;

					String sourceEventName = TestSequenceCompositionUtilities
							.getESGAndEventNameInConnectionPoint(currentEdge.getSource().getEvent().getName())[1];

					source = findVertexByEventName(sourceEventName);

					if (!eventNameSet.contains(currentEdge.getTarget().getEvent().getName())
							&& !currentEdge.getTarget().getEvent().getName().equals("]")) {

						eventNameSet.add(currentEdge.getTarget().getEvent().getName());
						target = addNewEventAndVertex(currentEdge.getTarget().getEvent().getName());

					} else {
						target = findVertexByEventName(currentEdge.getTarget().getEvent().getName());

					}

					if (!(source == null) && !(target == null)) {
						addNewEdge(source, target, vertexIDSet);

					}
				} else if (TestSequenceCompositionUtilities
						.isConnectionPoint(currentEdge.getTarget().getEvent().getName())
						&& !currentEdge.getSource().getEvent().getName().equals("[")) {
					Vertex source = null;
					Vertex target = null;
					if (!eventNameSet.contains(currentEdge.getSource().getEvent().getName())) {

						eventNameSet.add(currentEdge.getSource().getEvent().getName());
						source = addNewEventAndVertex(currentEdge.getSource().getEvent().getName());
					} else {

						source = findVertexByEventName(currentEdge.getSource().getEvent().getName());
					}

					String targetEventName = TestSequenceCompositionUtilities
							.getESGAndEventNameInConnectionPoint(currentEdge.getTarget().getEvent().getName())[1];
					target = findVertexByEventName(targetEventName);

					if (!(source == null) && !(target == null)) {
						addNewEdge(source, target, vertexIDSet);
					}
				} else if (!TestSequenceCompositionUtilities
						.isConnectionPoint(currentEdge.getSource().getEvent().getName())
						&& !TestSequenceCompositionUtilities
								.isConnectionPoint(currentEdge.getTarget().getEvent().getName())) {
					Vertex source = null;
					Vertex target = null;

					if (!eventNameSet.contains(currentEdge.getSource().getEvent().getName())) {
						eventNameSet.add(currentEdge.getSource().getEvent().getName());
						source = addNewEventAndVertex(currentEdge.getSource().getEvent().getName());
					} else {
						source = findVertexByEventName(currentEdge.getSource().getEvent().getName());
					}

					if (!eventNameSet.contains(currentEdge.getTarget().getEvent().getName())) {
						eventNameSet.add(currentEdge.getTarget().getEvent().getName());
						target = addNewEventAndVertex(currentEdge.getTarget().getEvent().getName());
					} else {
						target = findVertexByEventName(currentEdge.getTarget().getEvent().getName());
					}

					if (!(source == null) && !(target == null)) {
						addNewEdge(source, target, vertexIDSet);
					}
				}
			}
		}
	}

	private void coreEGS(ESG currentESG, Set<String> eventNameSet, Set<String> vertexIDSet) {

		Iterator<Edge> edgeListIterator = currentESG.getEdgeList().iterator();

		while (edgeListIterator.hasNext()) {
			Edge currentEdge = edgeListIterator.next();
			if (currentEdge.getSource().isPseudoStartVertex()) {

				if (!currentEdge.getTarget().getEvent().getName().equals("[[")) {

					Vertex target = null;
					if (!eventNameSet.contains(currentEdge.getTarget().getEvent().getName())) {
						eventNameSet.add(currentEdge.getTarget().getEvent().getName());
						target = addNewEventAndVertex(currentEdge.getTarget().getEvent().getName());
					} else {
						target = findVertexByEventName(currentEdge.getTarget().getEvent().getName());
					}
					if (!(target == null)) {

						addNewEdge(fullESG.getPseudoStartVertex(), target, vertexIDSet);
					}

				}
			} else if (currentEdge.getTarget().isPseudoEndVertex()) {

				if (!currentEdge.getSource().getEvent().getName().equals("]]")) {

					Vertex source = null;
					if (!eventNameSet.contains(currentEdge.getSource().getEvent().getName())) {
						eventNameSet.add(currentEdge.getSource().getEvent().getName());
						source = addNewEventAndVertex(currentEdge.getSource().getEvent().getName());
					} else {
						source = findVertexByEventName(currentEdge.getSource().getEvent().getName());
					}
					if (!(source == null)) {
						addNewEdge(source, fullESG.getPseudoEndVertex(), vertexIDSet);
					}
				}
			} else if (currentEdge.getSource().getEvent().getName().equals("[[")) {

				if (!eventNameSet.contains(currentEdge.getTarget().getEvent().getName())) {
					eventNameSet.add(currentEdge.getTarget().getEvent().getName());
					addNewEventAndVertex(currentEdge.getTarget().getEvent().getName());
				}

			} else if (currentEdge.getTarget().getEvent().getName().equals("]]")) {
				if (!eventNameSet.contains(currentEdge.getSource().getEvent().getName())) {
					eventNameSet.add(currentEdge.getSource().getEvent().getName());
					addNewEventAndVertex(currentEdge.getSource().getEvent().getName());
				}
			} else {

				Vertex source = null;
				Vertex target = null;

				if (!eventNameSet.contains(currentEdge.getSource().getEvent().getName())) {

					eventNameSet.add(currentEdge.getSource().getEvent().getName());
					source = addNewEventAndVertex(currentEdge.getSource().getEvent().getName());
				} else {
					source = findVertexByEventName(currentEdge.getSource().getEvent().getName());
				}

				if (!eventNameSet.contains(currentEdge.getTarget().getEvent().getName())) {

					eventNameSet.add(currentEdge.getTarget().getEvent().getName());
					target = addNewEventAndVertex(currentEdge.getTarget().getEvent().getName());

				} else {
					target = findVertexByEventName(currentEdge.getTarget().getEvent().getName());
				}

				if (!(source == null) && !(target == null)) {
					addNewEdge(source, target, vertexIDSet);
				}
			}
		}

	}

	public Vertex findVertexByEventName(String eventName) {

		Vertex found = null;
		for (Vertex vertex : fullESG.getVertexList()) {
			if (vertex.getEvent().getName().trim().equals(eventName.trim())) {
				found = vertex;
			}
		}

		return found;
	}

	private Vertex addNewEventAndVertex(String eventName) {
		Vertex newVertex = null;

		Event newEvent = new EventSimple(fullESG.getNextEventID(), eventName);
		fullESG.addEvent(newEvent);
		newVertex = new VertexSimple(fullESG.getNextVertexID(), newEvent);
		fullESG.addVertex(newVertex);

		return newVertex;
	}

	private void addNewEdge(Vertex source, Vertex target, Set<String> vertexIDSet) {

		String vertexID = "<" + source.getID() + "-" + target.getID() + ">";
		if (!vertexIDSet.contains(vertexID)) {
			Edge newEdge = new EdgeSimple(fullESG.getNextEdgeID(), source, target);
			fullESG.addEdge(newEdge);
			vertexIDSet.add(vertexID);
		}
	}

}
