package tr.edu.iyte.fesg.fullesgtoproductesg;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.esg.model.Edge;
import tr.edu.iyte.esg.model.EdgeSimple;
import tr.edu.iyte.esg.model.Event;
import tr.edu.iyte.esg.model.EventSimple;
import tr.edu.iyte.esg.model.Vertex;
import tr.edu.iyte.esg.model.VertexSimple;
import tr.edu.iyte.esg.model.eliminationcondition.EliminationCondition;
import tr.edu.iyte.fesg.model.FeaturedESG;
import tr.edu.iyte.fesg.model.featuredependencytree.FeatureDependencyTreeBuilder;
import tr.edu.iyte.fesg.testsequencecomposition.TestSequenceCompositionUtilities;

public class EliminationConditionApplier {

	private FeaturedESG resultingFESG;
	private ESG resultingFullESG;

	public EliminationConditionApplier() {

	}

	public ESG getResultingFullESG() {
		return resultingFullESG;
	}

	public FeaturedESG getResultingFESG() {
		return resultingFESG;
	}

	public void applyEliminationCondition(ESG ESG, List<EliminationCondition> eliminationConditionList) {

		for (EliminationCondition eliminationCondition : eliminationConditionList) {
			for (Map.Entry<String, List<String>> edgeToBeRemoved : eliminationCondition.getEdgesToBeRemoved().entrySet()) {
				String source = edgeToBeRemoved.getKey();
				List<String> targetList = edgeToBeRemoved.getValue();

				for (String target : targetList) {
					Edge edge = ESG.getEdgeBySourceEventNameTargetEventName(source, target);
					ESG.removeEdge(edge);
				}
			}
		}
	}

	public void applyEliminationCondition(ESG fullESG, FeatureDependencyTreeBuilder featureDependencyTreeBuilder, List<EliminationCondition> eliminationConditionList) {

		ESG copyESG = new ESG(fullESG);
		// Map<Vertex, ESG> vertexFeatureESGMap = getVertexFeatureESGMap(copyESG, FESG);
		FeaturedESG FESG = featureDependencyTreeBuilder.getFeaturedESG();
		Set<ESG> toBeRemovedFeatureSet = determineToBeRemovedFeatureSet(FESG, eliminationConditionList);
		
		AdditionalRemovalDeterminer additionalRemovalDeterminer = getAdditionalRemovalDeterminer(
				featureDependencyTreeBuilder);
		Set<ESG> finalToBeRemovedFeatureSet = determineFinalToBeRemovedFeatureSet(toBeRemovedFeatureSet,FESG,
				additionalRemovalDeterminer);
		List<ESG> orderedRemovalList = orderFinalToBeRemovedFeatureSet(additionalRemovalDeterminer,
				finalToBeRemovedFeatureSet);
		Map<Edge, ESG> edgeFeatureESGMap = getEdgeFeatureESGMap(copyESG, FESG, orderedRemovalList);

		Iterator<Entry<Edge, ESG>> edgeFeatureESGMapEntrySetIterator = edgeFeatureESGMap.entrySet().iterator();
		while (edgeFeatureESGMapEntrySetIterator.hasNext()) {
			Entry<Edge, ESG> entry = edgeFeatureESGMapEntrySetIterator.next();
			Edge edge = entry.getKey();
			String sourceEventName = edge.getSource().getEvent().getName();
			String targetEventName = edge.getTarget().getEvent().getName();
			ESG ESG = entry.getValue();
			if (orderedRemovalList.contains(ESG)
					&& !isEdgeInOtherFeatures(ESG.getName(), sourceEventName, targetEventName, edgeFeatureESGMap)) {
				copyESG.removeEdge(edge);
				//System.out.println("Edge removed " + sourceEventName + " " + targetEventName);
			}
		}
		this.resultingFullESG = getResultESG(copyESG);
		this.resultingFESG = new FeaturedESG(FESG);
		for (ESG featureESG : finalToBeRemovedFeatureSet) {
			resultingFESG.removeFeatureESG(featureESG);
		}
	}

	private boolean isEdgeInOtherFeatures(String featureName, String sourceEventName, String targetEventName,
			Map<Edge, ESG> edgeFeatureESGMap) {
		Set<ESG> ESGSet = new LinkedHashSet<ESG>();
		for (Map.Entry<Edge, ESG> entry : edgeFeatureESGMap.entrySet()) {
			Edge edge = entry.getKey();
			ESG feature = entry.getValue();

			if (edge.getSource().getEvent().getName().equals(sourceEventName)
					&& edge.getTarget().getEvent().getName().equals(targetEventName)
					&& !feature.getName().equals(featureName)) {
				ESGSet.add(feature);
			}
		}

		return !ESGSet.isEmpty();
	}

	private boolean isEventExistInESG(ESG resultESG, String eventName) {

		for (Event event : resultESG.getEventList()) {
			String name = event.getName();
			if (name.equals(eventName)) {
				return true;
			}
		}

		return false;
	}

	private ESG getResultESG(ESG copyESG) {

		ESG resultESG = new ESG(copyESG.getID(), copyESG.getName());

		for (Edge edge : copyESG.getEdgeList()) {

			String sourceEventName = edge.getSource().getEvent().getName();
//System.out.println("Current source " + sourceEventName);
			Vertex sourceVertex = null;
			Vertex targetVertex = null;
			if (!isEventExistInESG(resultESG, sourceEventName)) {
//System.out.println("Source is added as a vertex ");
				Event sourceEvent = new EventSimple(resultESG.getNextEventID(), sourceEventName);
				resultESG.addEvent(sourceEvent);

				sourceVertex = new VertexSimple(resultESG.getNextVertexID(), sourceEvent);
				resultESG.addVertex(sourceVertex);
			} else {
				sourceVertex = resultESG.getVertexByEventName(sourceEventName);
			}

			String targetEventName = edge.getTarget().getEvent().getName();
//System.out.println("Current target " + targetEventName);

			if (!isEventExistInESG(resultESG, targetEventName)) {
//System.out.println("Target is added as a vertex ");
				Event targetEvent = new EventSimple(resultESG.getNextEventID(), targetEventName);
				resultESG.addEvent(targetEvent);

				targetVertex = new VertexSimple(resultESG.getNextVertexID(), targetEvent);
				resultESG.addVertex(targetVertex);
			} else {
				targetVertex = resultESG.getVertexByEventName(targetEventName);
			}

			Edge newEdge = new EdgeSimple(resultESG.getNextEdgeID(), sourceVertex, targetVertex);
			resultESG.addEdge(newEdge);
//System.out.println(sourceEventName + "-" + targetEventName + " is added to resultESG");
		}

		return resultESG;

	}

	private Set<ESG> determineToBeRemovedFeatureSet(FeaturedESG FESG, List<EliminationCondition> eliminationConditionList) {
		Set<ESG> toBeRemovedFeatureSet = new LinkedHashSet<ESG>();

		Iterator<EliminationCondition> eliminationConditionListIterator = eliminationConditionList.iterator();
		while (eliminationConditionListIterator.hasNext()) {
			EliminationCondition eliminationCondition = eliminationConditionListIterator.next();
			if (!eliminationCondition.isResult()) {
				String featureName = eliminationCondition.getConditionName();

				Iterator<ESG> featureESGIterator = FESG.getFeaturedESGSet().iterator();
				while (featureESGIterator.hasNext()) {
					ESG featureESG = featureESGIterator.next();
					if (featureESG.getName().equals(featureName)) {
						toBeRemovedFeatureSet.add(featureESG);
					}
				}
			}
		}
		return toBeRemovedFeatureSet;
	}

	private Set<ESG> determineFinalToBeRemovedFeatureSet(Set<ESG> toBeRemovedFeatureSet,FeaturedESG FESG,
			AdditionalRemovalDeterminer additionalRemovalDeterminer) {

		Set<ESG> finalToBeRemovedFeatureSet = additionalRemovalDeterminer
				.determineAdditionalRemovals(toBeRemovedFeatureSet,FESG);

		return finalToBeRemovedFeatureSet;
	}

	public FeatureDependencyTreeBuilder getFeatureDependencyTreeBuilder(FeaturedESG FESG) {
		FeatureDependencyTreeBuilder featureDependencyTreeBuilder = new FeatureDependencyTreeBuilder(FESG);
		featureDependencyTreeBuilder.buildFeatureDependencyTree();
		return featureDependencyTreeBuilder;
	}

	private AdditionalRemovalDeterminer getAdditionalRemovalDeterminer(
			FeatureDependencyTreeBuilder featureDependencyTreeBuilder) {
		AdditionalRemovalDeterminer additionalRemovalDeterminer = new AdditionalRemovalDeterminer(
				featureDependencyTreeBuilder);
		return additionalRemovalDeterminer;
	}

	private List<ESG> orderFinalToBeRemovedFeatureSet(AdditionalRemovalDeterminer additionalRemovalDeterminer,
			Set<ESG> finalToBeRemovedFeatureSet) {
		List<ESG> orderedRemovalList = additionalRemovalDeterminer.determineRemovalOrder(finalToBeRemovedFeatureSet);
		return orderedRemovalList;
	}

	private Map<Edge, ESG> getEdgeFeatureESGMap(ESG copyESG, FeaturedESG FESG, List<ESG> orderedRemovalList) {

		DynamicEdgeMapping dynamicEdgeMapping = new DynamicEdgeMapping(FESG, copyESG);
		dynamicEdgeMapping.buildEdgeMap(orderedRemovalList);
		Map<Edge, ESG> edgeFeatureESGMap = dynamicEdgeMapping.getEdgeFeatureESGMap();

		return edgeFeatureESGMap;
	}
	
	
	private boolean isVertexInOtherFeatures(String featureName, String vertexEventName,
			Map<Vertex, ESG> vertexFeatureESGMap) {
		Set<ESG> ESGSet = new LinkedHashSet<ESG>();
		for (Map.Entry<Vertex, ESG> entry : vertexFeatureESGMap.entrySet()) {
			Vertex vertex = entry.getKey();
			ESG featureESG = entry.getValue();
			if (vertex.getEvent().getName().equals(vertexEventName) && !featureESG.getName().equals(featureName)) {
				ESGSet.add(featureESG);
			}
		}
		return !ESGSet.isEmpty();
	}
	

	
	private Map<Vertex, ESG> getVertexFeatureESGMap(ESG fullESG, FeaturedESG FESG) {

		Map<Vertex, ESG> vertexFeatureESGMap = new LinkedHashMap<Vertex, ESG>();
		for (ESG featureESG : FESG.getFeaturedESGSet()) {
			//System.out.println("feature " + featureESG.getName());
			for (Vertex realVertex : featureESG.getRealVertexList()) {
				String eventName = realVertex.getEvent().getName().trim();
				// System.out.println("eventName " + eventName);
				boolean isConnectionPoint = TestSequenceCompositionUtilities.isConnectionPoint(eventName);
				if (!isConnectionPoint && !eventName.equals("[[") && !eventName.equals("]]")) {
					Vertex fullESGVertex = fullESG.getVertexByEventName(eventName);
					//System.out.println("fullESGVertex " + fullESGVertex.getEvent().getName());
					vertexFeatureESGMap.put(fullESGVertex, featureESG);
				}
			}
		}

		return vertexFeatureESGMap;
	}

	private void printVertexFeatureESGMap(Map<Vertex, ESG> vertexFeatureESGMap) {

		for (Entry<Vertex, ESG> entry : vertexFeatureESGMap.entrySet()) {
			System.out.println(entry.getKey().toString() + " -> " + entry.getValue().getName());
		}
	}
	

}
