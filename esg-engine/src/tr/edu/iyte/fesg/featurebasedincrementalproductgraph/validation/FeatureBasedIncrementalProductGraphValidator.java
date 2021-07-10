package tr.edu.iyte.fesg.featurebasedincrementalproductgraph.validation;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraph;
import tr.edu.iyte.fesg.featurebasedincrementalproductgraph.FeatureBasedIncrementalProductGraphVertex;

public class FeatureBasedIncrementalProductGraphValidator {
	
	private FeatureBasedIncrementalProductGraph featureBasedIncrementalProductGraph;
	
	public FeatureBasedIncrementalProductGraphValidator(FeatureBasedIncrementalProductGraph featureBasedIncrementalProductGraph) {
		this.featureBasedIncrementalProductGraph = featureBasedIncrementalProductGraph;
	}
	
	
	public boolean validateProductConfigurationsInFeatureBasedIncrementalProductGraph(FeatureBasedIncrementalProductGraphVertex start) {
		boolean[] visited = new boolean[featureBasedIncrementalProductGraph.getVertexList().size()];
		Queue<FeatureBasedIncrementalProductGraphVertex> queue = new LinkedList<FeatureBasedIncrementalProductGraphVertex>();
		visited[featureBasedIncrementalProductGraph.getVertexList().indexOf(start)] = true;
		queue.add(start);
		boolean valid = true;
		while(!queue.isEmpty()) {
			FeatureBasedIncrementalProductGraphVertex current = queue.poll();
			
			FeatureBasedIncrementalProductGraphVertex adjacent;
			List<FeatureBasedIncrementalProductGraphVertex> adjacencyList =featureBasedIncrementalProductGraph.adjacentVertices(current);
			Iterator<FeatureBasedIncrementalProductGraphVertex> adjacentIterator = adjacencyList.iterator();
			
			while (adjacentIterator.hasNext()) {
				adjacent = adjacentIterator.next();
				if (!visited[featureBasedIncrementalProductGraph.getVertexList().indexOf(adjacent)]) {
					visited[featureBasedIncrementalProductGraph.getVertexList().indexOf(adjacent)] = true;
					queue.add(adjacent);
				}
				Set<ESG> featureESGSet = featureBasedIncrementalProductGraph.getOutgoingEdgeFeatureESGSet(current, adjacent);
				Set<ESG> featuredESGSetOfAdjacent = new LinkedHashSet<ESG>();				
				featuredESGSetOfAdjacent.addAll(current.getFeaturedESG().getFeaturedESGSet());
				featuredESGSetOfAdjacent.addAll(featureESGSet);
				valid = valid && featuredESGSetOfAdjacent.equals(adjacent.getFeaturedESG().getFeaturedESGSet());	 
				
			}
		}
		return valid;
		
	}
	
	

}
