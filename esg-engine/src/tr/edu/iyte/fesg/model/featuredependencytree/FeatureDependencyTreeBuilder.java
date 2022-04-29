package tr.edu.iyte.fesg.model.featuredependencytree;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.esg.model.Vertex;
import tr.edu.iyte.fesg.model.FeaturedESG;
import tr.edu.iyte.fesg.testsequencecomposition.TestSequenceCompositionUtilities;

public class FeatureDependencyTreeBuilder {

	private FeaturedESG featuredESG;
	private Map<ESG, Set<ESG>> featureESGParentFeatureESGSetMap;
	private FeatureDependencyTree featureDependencyTree;

	public FeatureDependencyTreeBuilder(FeaturedESG featuredESG) {
		this.featuredESG = featuredESG;
		featureESGParentFeatureESGSetMap = new LinkedHashMap<ESG, Set<ESG>>();
		ESG core = featuredESG.getCoreESG();
		FeatureDependencyTreeNode root = new FeatureDependencyTreeNode(core);
		featureDependencyTree = new FeatureDependencyTree(root);
		featureDependencyTree.addNode(root);
	}

	public FeaturedESG getFeaturedESG() {
		return featuredESG;
	}

	public Map<ESG, Set<ESG>> getFeatureESGParentFeatureESGSetMap() {
		return featureESGParentFeatureESGSetMap;
	}
	
	public FeatureDependencyTree getFeatureDependencyTree() {
		return featureDependencyTree;
	}
		
	public void buildFeatureDependencyTree() {
		buildFeatureESGParentFeatureESGSetMap();
		
		FeatureDependencyTreeNode root = featureDependencyTree.getRoot();
		
		for (Entry<ESG, Set<ESG>> entry : featureESGParentFeatureESGSetMap.entrySet()) {
			ESG featureESG = entry.getKey();
			Set<ESG> parentFeatureESGSet = entry.getValue();
			FeatureDependencyTreeNode featureDependencyTreeNode = null;
			
			if(featureDependencyTree.getFeatureNameSet().contains(featureESG.getName())) {
				featureDependencyTreeNode = featureDependencyTree.getNodeByLevelOrderSearch(featureESG.getName());
			}else {
				featureDependencyTreeNode = new FeatureDependencyTreeNode(featureESG);
			}
			
			if(featureDependencyTreeNode != null) {
				featureDependencyTree.addNode(featureDependencyTreeNode);
			}
			
			if (parentFeatureESGSet.size() == 1) {
				for (ESG fESG : parentFeatureESGSet) {
					if (fESG.getName().equals("core")) {
						root.addChild(featureDependencyTreeNode);
						featureDependencyTreeNode.setLevel(root.getLevel() + 1);
					} else {
						FeatureDependencyTreeNode foundNode = featureDependencyTree
								.getNodeByLevelOrderSearch(fESG.getName());
						foundNode.addChild(featureDependencyTreeNode);
						featureDependencyTreeNode.setLevel(foundNode.getLevel() + 1);
					}
				}
			} else {
				for (ESG fESG : parentFeatureESGSet) {
					if (!fESG.getName().equals("core")) {
						FeatureDependencyTreeNode foundNode = featureDependencyTree
								.getNodeByLevelOrderSearch(fESG.getName());
						foundNode.addChild(featureDependencyTreeNode);
						featureDependencyTreeNode.setLevel(foundNode.getLevel() + 1);
					}
				}
			}

		}
		
		//System.out.println(featureDependencyTree.getNodeList().size());
	}
	
	public void printFeatureESGParentFeatureESGSetMap() {
		for (Entry<ESG, Set<ESG>> entry : featureESGParentFeatureESGSetMap.entrySet()) {
			ESG featureESG = entry.getKey();
			Set<ESG> parentFeatureESGSet = entry.getValue();
			System.out.print(featureESG.getName() + "-> parent(s): ");
			parentFeatureESGSet.forEach(ESG->System.out.print(ESG.getName() + " "));
			System.out.println();
			
		}
	}
	
	private void buildFeatureESGParentFeatureESGSetMap() {
		Iterator<ESG> featureESGSetItearator = featuredESG.getFeaturedESGSet().iterator();

		while (featureESGSetItearator.hasNext()) {
			ESG featureESG = featureESGSetItearator.next();
			//System.out.println("Current ESG: " + featureESG.getName());
			Iterator<Vertex> vertexListIterator = featureESG.getVertexList().iterator();
			Set<ESG> parentFeatureESGSet = new LinkedHashSet<ESG>();
			while (vertexListIterator.hasNext()) {
				Vertex vertex = vertexListIterator.next();
				if (TestSequenceCompositionUtilities.isConnectionPoint(vertex.getEvent().getName())) {
					String[] ESGNameEventName = TestSequenceCompositionUtilities
							.getESGAndEventNameInConnectionPoint(vertex.getEvent().getName());
					String featureName = ESGNameEventName[0].trim();
					
					for(ESG fESG : featuredESG.getFeaturedESGSet()) {
						
						if(fESG.getName().equals(featureName)) {
							parentFeatureESGSet.add(fESG);
							//System.out.println("Parent feature of " + featureESG.getName() + " " + fESG.getName());
						}
					}
				}
			}
			processParentFeatureESGSet(parentFeatureESGSet);
			if(!parentFeatureESGSet.isEmpty()) {
				featureESGParentFeatureESGSetMap.put(featureESG, parentFeatureESGSet);
			}
		}
	}
	
	private void processParentFeatureESGSet(Set<ESG> parentFeatureESGSet) {
		
		if(parentFeatureESGSet.size() > 1) {
			Iterator<ESG> parentFeatureESGSetIterator = parentFeatureESGSet.iterator();
			while(parentFeatureESGSetIterator.hasNext()) {
				ESG parentFeatureESG = parentFeatureESGSetIterator.next();
				
				if(parentFeatureESG.getName().equals("core")) {
					parentFeatureESGSetIterator.remove();
				}
			}
		}
		
	}

}
