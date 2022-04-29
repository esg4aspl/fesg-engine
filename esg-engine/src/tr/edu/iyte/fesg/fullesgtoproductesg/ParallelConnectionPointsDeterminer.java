package tr.edu.iyte.fesg.fullesgtoproductesg;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.esg.model.Edge;
import tr.edu.iyte.esg.model.Vertex;
import tr.edu.iyte.fesg.model.FeaturedESG;
import tr.edu.iyte.fesg.model.featuredependencytree.FeatureDependencyTreeNode;
import tr.edu.iyte.fesg.testsequencecomposition.TestSequenceCompositionUtilities;

public class ParallelConnectionPointsDeterminer {
	
	public static Set<ESG> getParallelFeatureESGSet(ESG featureESG, FeaturedESG FESG){
		Set<String> parallelFeatureNameSet = getOptionalFeatureNames(featureESG);
		Set<ESG> parallelFeatureESGSet = new LinkedHashSet<ESG>();
		for(String featureName : parallelFeatureNameSet) {
			
			ESG parallelFeature = FESG.getFeatureESGByName(featureName);
			parallelFeatureESGSet.add(parallelFeature);
			//System.out.println("paralel feature " + featureName + " is added to set");
		}
		return parallelFeatureESGSet;
	}

	private static Set<String> getOptionalFeatureNames(ESG featureESG) {
		Set<String> optionalFeatureNameSet = new LinkedHashSet<String>();

		if (isEntryVerticesParallel(featureESG.getEntryVertexSet(), featureESG.getRealEdgeList())) {
			Iterator<Vertex> entryVertexSetIterator = featureESG.getEntryVertexSet().iterator();
			while (entryVertexSetIterator.hasNext()) {
				Vertex entryVertex = entryVertexSetIterator.next();
				String[] ary = TestSequenceCompositionUtilities
						.getESGAndEventNameInConnectionPoint(entryVertex.getEvent().getName());
				optionalFeatureNameSet.add(ary[0]);
			}
		} else if (isExitVerticesParallel(featureESG.getExitVertexSet(), featureESG.getRealEdgeList())) {
			Iterator<Vertex> exitVertexSetIterator = featureESG.getExitVertexSet().iterator();

			while (exitVertexSetIterator.hasNext()) {
				Vertex exitVertex = exitVertexSetIterator.next();
				String[] ary = TestSequenceCompositionUtilities
						.getESGAndEventNameInConnectionPoint(exitVertex.getEvent().getName());
				optionalFeatureNameSet.add(ary[0]);
			}
		}

		return optionalFeatureNameSet;
	}

	public static boolean hasParallelConnectionPoints(ESG featureESG) {
		return isEntryVerticesParallel(featureESG.getEntryVertexSet(), featureESG.getRealEdgeList())
				|| isExitVerticesParallel(featureESG.getExitVertexSet(), featureESG.getRealEdgeList());
	}

	private static boolean isAppropriateVertexSet(Set<Vertex> vertexSet) {

		Iterator<Vertex> vertexSetIterator = vertexSet.iterator();
		Set<String> candidateParallelFeatureNames = new LinkedHashSet<String>();

		while (vertexSetIterator.hasNext()) {
			Vertex vertex = vertexSetIterator.next();
			String eventName = vertex.getEvent().getName();
			String candidateFeatureName = TestSequenceCompositionUtilities
					.getESGAndEventNameInConnectionPoint(eventName)[0];
			candidateParallelFeatureNames.add(candidateFeatureName);
		}

		return candidateParallelFeatureNames.size() > 1;
	}

	private static boolean isEntryVerticesParallel(Set<Vertex> entryVertexSet, List<Edge> realEdgeList) {

		if (entryVertexSet.size() > 1) {
			Iterator<Vertex> entryVertexSetIterator = entryVertexSet.iterator();

			boolean allEntryVerticesAreConnectionPoints = true;
			while (entryVertexSetIterator.hasNext()) {
				Vertex entryVertex = entryVertexSetIterator.next();
				allEntryVerticesAreConnectionPoints = allEntryVerticesAreConnectionPoints
						&& TestSequenceCompositionUtilities.isConnectionPoint(entryVertex.getEvent().getName());
			}
			Set<Vertex> targetVertexSet = new LinkedHashSet<Vertex>();
			if (allEntryVerticesAreConnectionPoints) {
				if (isAppropriateVertexSet(entryVertexSet)) {
					Iterator<Edge> realEdgeIterator = realEdgeList.iterator();
					while (realEdgeIterator.hasNext()) {
						Edge realEdge = realEdgeIterator.next();
						Vertex source = realEdge.getSource();
						Vertex target = realEdge.getTarget();
						if (TestSequenceCompositionUtilities.isConnectionPoint(source.getEvent().getName())
								&& entryVertexSet.contains(source)) {
							targetVertexSet.add(target);
						}
					}
				}
			}

			return targetVertexSet.size() == 1;
		} else
			return false;

	}

	private static boolean isExitVerticesParallel(Set<Vertex> exitVertexSet, List<Edge> realEdgeList) {

		if (exitVertexSet.size() > 1) {
			Iterator<Vertex> exitVertexSetIterator = exitVertexSet.iterator();

			boolean allExitVerticesAreConnectionPoints = true;
			while (exitVertexSetIterator.hasNext()) {
				Vertex exitVertex = exitVertexSetIterator.next();
				allExitVerticesAreConnectionPoints = allExitVerticesAreConnectionPoints
						&& TestSequenceCompositionUtilities.isConnectionPoint(exitVertex.getEvent().getName());
			}
			Set<Vertex> sourceVertexSet = new LinkedHashSet<Vertex>();
			if (allExitVerticesAreConnectionPoints) {
				if (isAppropriateVertexSet(exitVertexSet)) {
					Iterator<Edge> realEdgeIterator = realEdgeList.iterator();
					while (realEdgeIterator.hasNext()) {
						Edge realEdge = realEdgeIterator.next();
						Vertex source = realEdge.getSource();
						Vertex target = realEdge.getTarget();
						if (TestSequenceCompositionUtilities.isConnectionPoint(target.getEvent().getName())
								&& exitVertexSet.contains(target)) {
							sourceVertexSet.add(source);
						}
					}
				}
			}

			return sourceVertexSet.size() == 1;
		} else
			return false;
	}

}
