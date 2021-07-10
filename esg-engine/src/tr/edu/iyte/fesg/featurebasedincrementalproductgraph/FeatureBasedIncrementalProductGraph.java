package tr.edu.iyte.fesg.featurebasedincrementalproductgraph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import tr.edu.iyte.esg.eventsequence.EventSequence;
import tr.edu.iyte.esg.eventsequence.EventSequenceUtilties;
import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.esg.testgeneration.TestSuite;
import tr.edu.iyte.esg.testgeneration.TestSuiteGenerator;
import tr.edu.iyte.fesg.cases.TestCoverageAnalysingUtilities;
import tr.edu.iyte.fesg.cases.TestSequenceCompositionUtilties;
import tr.edu.iyte.fesg.fullesggeneration.FullESGGenerator;


public class FeatureBasedIncrementalProductGraph {
	
	private String name;
	private List<FeatureBasedIncrementalProductGraphVertex> vertexList;
	private List<FeatureBasedIncrementalProductGraphEdge> edgeList;
	
	public FeatureBasedIncrementalProductGraph() {
		vertexList = new LinkedList<FeatureBasedIncrementalProductGraphVertex>();
		edgeList = new LinkedList<FeatureBasedIncrementalProductGraphEdge>();
	}
	
	public FeatureBasedIncrementalProductGraph(String name) {
		vertexList = new LinkedList<FeatureBasedIncrementalProductGraphVertex>();
		edgeList = new LinkedList<FeatureBasedIncrementalProductGraphEdge>();
		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<FeatureBasedIncrementalProductGraphVertex> getVertexList() {
		return vertexList;
	}

	public void addVertex(FeatureBasedIncrementalProductGraphVertex vertex) {
		this.vertexList.add(vertex);
	}

	public List<FeatureBasedIncrementalProductGraphEdge> getEdgeList() {
		return edgeList;
	}

	public void addEdge(FeatureBasedIncrementalProductGraphEdge edge) {
		this.edgeList.add(edge);
	}
	
	public void incrementalTestSequenceCompositionBFT(FeatureBasedIncrementalProductGraphVertex start, int coverageLength, int incrementalBasisApproachID) {

		boolean visited[] = new boolean[vertexList.size()];
		Queue<FeatureBasedIncrementalProductGraphVertex> queue = new LinkedList<FeatureBasedIncrementalProductGraphVertex>();

		visited[vertexList.indexOf(start)] = true;
//System.out.println("[vertexList.indexOf(start) " + vertexList.indexOf(start));
		queue.add(start);
		while (queue.size() != 0) {
			start = queue.poll();
			
			if (start.isStart() && incrementalBasisApproachID == 0) {
				FullESGGenerator fullESGGenerator = new FullESGGenerator(0, start.getFeaturedESG());
				fullESGGenerator.generateFullESG();
				ESG productESG = fullESGGenerator.getFullESG();
				//System.out.println(productESG);
				TestSuiteGenerator testSuiteGenerator = new TestSuiteGenerator();
				TestSuite testSuite = testSuiteGenerator.generateTestSuite(coverageLength,productESG);
//System.out.println(testSuite.toString());
				start.getFeaturedESG().setCesSet(testSuite.getCompleteEventSequences());
			}else if(start.isStart() && incrementalBasisApproachID == 1) {
				Set<EventSequence> fullCESSet = TestSequenceCompositionUtilties
						.composeSequencesFully(start.getFeaturedESG());
EventSequenceUtilties.esgEventSequenceSetPrinter(fullCESSet);
TestCoverageAnalysingUtilities.writeExistingproductTestCompositionResultsToFile(fullCESSet);
				start.getFeaturedESG().setCesSet(fullCESSet);
			}
			FeatureBasedIncrementalProductGraphVertex adjacent;
			List<FeatureBasedIncrementalProductGraphVertex> adjacencyList = adjacentVertices(start);
			Iterator<FeatureBasedIncrementalProductGraphVertex> adjacentIterator = adjacencyList.iterator();
			while (adjacentIterator.hasNext()) {
				adjacent = adjacentIterator.next();
				if (!visited[vertexList.indexOf(adjacent)]) {
					visited[vertexList.indexOf(adjacent)] = true;
					queue.add(adjacent);
				}
				adjacent.getFeaturedESG().setCesSet(start.getFeaturedESG().getCesSet());
				adjacent.getFeaturedESG().getFeatureESGSet().addAll(start.getFeaturedESG().getFeaturedESGSet());
				/*
				 * System.out.print("ADJACENT Features ");
				 * adjacent.getFeaturedESG().getFeaturedESGSet().forEach(e->System.out.print(e.
				 * getName() + " ")); System.out.println();
				 */
				Set<ESG> featureESGSet = getOutgoingEdgeFeatureESGSet(start, adjacent);
				adjacent.incrementalTestSequenceComposition(featureESGSet);
				// featureESGSet.forEach(e->System.out.print("EDGE" + e.getName()));
			}
		}
	}
	
	public void fullTestSequenceCompositionBFT(FeatureBasedIncrementalProductGraphVertex start) {
		boolean visited[] = new boolean[vertexList.size()];
		Queue<FeatureBasedIncrementalProductGraphVertex> queue = new LinkedList<FeatureBasedIncrementalProductGraphVertex>();
		visited[vertexList.indexOf(start)] = true;
		queue.add(start);
		while (queue.size() != 0) {
			start = queue.poll();

			if (start.isProduct()) {
				start.fullTestSequenceComposition();
			}
			FeatureBasedIncrementalProductGraphVertex adjacent;
			List<FeatureBasedIncrementalProductGraphVertex> adjacencyList = adjacentVertices(start);
			Iterator<FeatureBasedIncrementalProductGraphVertex> adjacentIterator = adjacencyList.iterator();
			while (adjacentIterator.hasNext()) {
				adjacent = adjacentIterator.next();
				if (!visited[vertexList.indexOf(adjacent)]) {
					visited[vertexList.indexOf(adjacent)] = true;
					queue.add(adjacent);
				}
				Set<ESG> featureESGSet = getOutgoingEdgeFeatureESGSet(start, adjacent);
				for(ESG featureESG : featureESGSet) {
					adjacent.getFeaturedESG().addFeatureESG(featureESG);
				}
				adjacent.fullTestSequenceComposition();
			}
		}
	}
	
	public void fullESGGenerationBFT(FeatureBasedIncrementalProductGraphVertex start) {
		boolean visited[] = new boolean[vertexList.size()];
		Queue<FeatureBasedIncrementalProductGraphVertex> queue = new LinkedList<FeatureBasedIncrementalProductGraphVertex>();
		
		visited[vertexList.indexOf(start)] = true;
		queue.add(start);
		while (queue.size() != 0) {
			start = queue.poll();
				if (start.isProduct()) {
					start.fullESGGeneration();
				}
			FeatureBasedIncrementalProductGraphVertex adjacent;
			List<FeatureBasedIncrementalProductGraphVertex> adjacencyList = adjacentVertices(start);
			Iterator<FeatureBasedIncrementalProductGraphVertex> adjacentIterator = adjacencyList.iterator();
			while (adjacentIterator.hasNext()) {
				adjacent = adjacentIterator.next();
				if (!visited[vertexList.indexOf(adjacent)]) {
					visited[vertexList.indexOf(adjacent)] = true;
					queue.add(adjacent);
				}
				Set<ESG> featureESGSet = getOutgoingEdgeFeatureESGSet(start, adjacent);
				for(ESG featureESG : featureESGSet) {
					adjacent.getFeaturedESG().addFeatureESG(featureESG);
				}
				adjacent.fullESGGeneration();
			}
		}
	}
	
	public List<FeatureBasedIncrementalProductGraphVertex> adjacentVertices(FeatureBasedIncrementalProductGraphVertex vertex){
		List<FeatureBasedIncrementalProductGraphVertex> adjacentVertexList = new LinkedList<FeatureBasedIncrementalProductGraphVertex>();
		
		Iterator<FeatureBasedIncrementalProductGraphEdge> edgeIterator = edgeList.iterator();
		while(edgeIterator.hasNext()) {
			FeatureBasedIncrementalProductGraphEdge edge = edgeIterator.next();
			if(edge.getSource().equals(vertex)) {
				adjacentVertexList.add(edge.getTarget());
			}
		}
		return adjacentVertexList;
	}
	
	public Set<ESG> getOutgoingEdgeFeatureESGSet(FeatureBasedIncrementalProductGraphVertex source, FeatureBasedIncrementalProductGraphVertex target) {
		Iterator<FeatureBasedIncrementalProductGraphEdge> edgeIterator = edgeList.iterator();
		
		while(edgeIterator.hasNext()) {
			FeatureBasedIncrementalProductGraphEdge edge = edgeIterator.next();
			if(edge.getSource().equals(source) && edge.getTarget().equals(target)) {
				return edge.getFeatureESGSet();
			}
		}
		
		return null;
	}
	

}
