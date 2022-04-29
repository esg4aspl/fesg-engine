package tr.edu.iyte.fesg.fullesgtoproductesg;

import java.util.LinkedHashMap;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.esg.model.Edge;
import tr.edu.iyte.fesg.model.FeaturedESG;
import tr.edu.iyte.fesg.testsequencecomposition.TestSequenceCompositionUtilities;

public class DynamicEdgeMapping {

	private FeaturedESG FESG;
	private ESG fullESG;
	private Map<Edge, ESG> edgeFeatureESGMap;

	public DynamicEdgeMapping(FeaturedESG FESG, ESG fullESG) {
		this.FESG = FESG;
		this.fullESG = fullESG;
		edgeFeatureESGMap = new LinkedHashMap<Edge, ESG>();
	}

	public FeaturedESG getFeaturedESG() {
		return FESG;
	}

	public ESG getFullESG() {
		return fullESG;
	}

	public Map<Edge, ESG> getEdgeFeatureESGMap() {
		return edgeFeatureESGMap;
	}

	public void buildEdgeMap(List<ESG> orderedRemovalList) {

		for (ESG featureESG : FESG.getFeatureESGSet()) {
			int indexOfFeatureESG = indexOfESG(orderedRemovalList, featureESG.getName());
System.out.println("Current feature ESG " + featureESG.getName() + " indexOfFeatureESG " + indexOfFeatureESG);

			for (Edge realEdge : featureESG.getRealEdgeList()) {

				String sourceEventName = realEdge.getSource().getEvent().getName();
				boolean sourceIsConnectionPoint = TestSequenceCompositionUtilities.isConnectionPoint(sourceEventName);
				String targetEventName = realEdge.getTarget().getEvent().getName();
				boolean targetIsConnectionPoint = TestSequenceCompositionUtilities.isConnectionPoint(targetEventName);
				String esg = "";
				String event = "";
				Edge fullESGEdge = null;
				int indexOfConnectionPointESG = -2;
System.out.println("The edge is " + sourceEventName + " " + targetEventName);
				if (sourceIsConnectionPoint && !targetIsConnectionPoint) {
System.out.println("Source is connection point-target is not");
					String[] array = TestSequenceCompositionUtilities
							.getESGAndEventNameInConnectionPoint(sourceEventName);
					esg = array[0].trim();
					event = array[1].trim();
System.out.println("ESG " + esg + " event " + event);
					fullESGEdge = fullESG.getEdgeBySourceEventNameTargetEventName(event, targetEventName);

					if (esg.equals("core")) {
						edgeFeatureESGMap.put(fullESGEdge, featureESG);
System.out.println("Edge is mapped to " + featureESG.getName());
					} else {
						indexOfConnectionPointESG = indexOfESG(orderedRemovalList, esg);
System.out.println("Second cond indexOfConnectionPointESG " + indexOfConnectionPointESG);
						if (indexOfFeatureESG == -1) {
System.out.println("indexOfFeatureESG == -1");
							edgeFeatureESGMap.put(fullESGEdge, FESG.getFeatureESGByName(esg));
System.out.println("Edge is mapped to " + esg);
						} else if (indexOfConnectionPointESG == -1) {
System.out.println("indexOfConnectionPointESG == -1");
							edgeFeatureESGMap.put(fullESGEdge, featureESG);
System.out.println("Edge is mapped to " + featureESG.getName());
						} else if (indexOfFeatureESG < indexOfConnectionPointESG) {
System.out.println("indexOfFeatureESG < indexOfConnectionPointESG");
							edgeFeatureESGMap.put(fullESGEdge, featureESG);
System.out.println("Edge is mapped to " + featureESG.getName());
						} else if (indexOfFeatureESG > indexOfConnectionPointESG) {
System.out.println("indexOfFeatureESG > indexOfConnectionPointESG");
							edgeFeatureESGMap.put(fullESGEdge, FESG.getFeatureESGByName(esg));
System.out.println("Edge is mapped to " + esg);
						}
					}

				} else if (!sourceIsConnectionPoint && targetIsConnectionPoint) {
System.out.println("Source is not connection point-target is");
					String[] array = TestSequenceCompositionUtilities
							.getESGAndEventNameInConnectionPoint(targetEventName);
					esg = array[0].trim();
					event = array[1].trim();
System.out.println("ESG " + esg + " event " + event);
					fullESGEdge = fullESG.getEdgeBySourceEventNameTargetEventName(sourceEventName, event);

					if (esg.equals("core")) {
						edgeFeatureESGMap.put(fullESGEdge, featureESG);
System.out.println("Edge is mapped to " + featureESG.getName());
					} else {
						indexOfConnectionPointESG = indexOfESG(orderedRemovalList, esg);
System.out.println("Second cond indexOfConnectionPointESG " + indexOfConnectionPointESG);
						if (indexOfFeatureESG == -1) {
System.out.println("indexOfFeatureESG == -1");
							edgeFeatureESGMap.put(fullESGEdge, FESG.getFeatureESGByName(esg));
System.out.println("Edge is mapped to " + esg);
						} else if (indexOfConnectionPointESG == -1) {
System.out.println("indexOfConnectionPointESG == -1");
							edgeFeatureESGMap.put(fullESGEdge, featureESG);
System.out.println("Edge is mapped to " + featureESG.getName());
						} else if (indexOfFeatureESG < indexOfConnectionPointESG) {
System.out.println("indexOfFeatureESG < indexOfConnectionPointESG");
							edgeFeatureESGMap.put(fullESGEdge, featureESG);
System.out.println("Edge is mapped to " + featureESG.getName());
						} else if (indexOfFeatureESG > indexOfConnectionPointESG) {
System.out.println("indexOfFeatureESG > indexOfConnectionPointESG");
							edgeFeatureESGMap.put(fullESGEdge, FESG.getFeatureESGByName(esg));
System.out.println("Edge is mapped to " + esg);
						}
					}
				} else if (!sourceIsConnectionPoint && !targetIsConnectionPoint) {
System.out.println("critical edge det: else if (!sourceIsConnectionPoint && !targetIsConnectionPoint)");
					fullESGEdge = fullESG.getEdgeBySourceEventNameTargetEventName(sourceEventName, targetEventName);
					edgeFeatureESGMap.put(fullESGEdge, featureESG);
System.out.println("Edge is mapped to " + featureESG.getName());
				} else {
System.out.println("critical edge det: else both source and event are connection points");
				}
System.out.println("Edge is " + fullESGEdge.getSource().getEvent().getName() + " "+ fullESGEdge.getTarget().getEvent().getName());

			}
		}
		
		printEdgeMapping();
	}

	public void printEdgeMapping() {
		for (Entry<Edge, ESG> entry : edgeFeatureESGMap.entrySet()) {
			Edge edge = entry.getKey();
			ESG ESG = entry.getValue();
			System.out.println("The edge " + edge.toString() + " is mapped to " + ESG.getName());
		}
	}

	private int indexOfESG(List<ESG> orderedRemovalList, String ESGName) {

		for (ESG ESG : orderedRemovalList) {
			if (ESG.getName().equals(ESGName)) {
				return orderedRemovalList.indexOf(ESG);
			}
		}
		return -1;
	}
}
