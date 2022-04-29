package tr.edu.iyte.fesg.fullesgtoproductesg;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.fesg.model.FeaturedESG;
import tr.edu.iyte.fesg.model.featuredependencytree.FeatureDependencyTree;
import tr.edu.iyte.fesg.model.featuredependencytree.FeatureDependencyTreeBuilder;
import tr.edu.iyte.fesg.model.featuredependencytree.FeatureDependencyTreeNode;

public class AdditionalRemovalDeterminer {

	private FeatureDependencyTreeBuilder featureDependencyTreeBuilder;

	public AdditionalRemovalDeterminer(FeatureDependencyTreeBuilder featureDependencyTreeBuilder) {
		this.featureDependencyTreeBuilder = featureDependencyTreeBuilder;
	}

	public FeatureDependencyTreeBuilder getFeatureDependencyTreeBuilder() {
		return featureDependencyTreeBuilder;
	}
	
	public Set<ESG> determineAdditionalRemovals(Set<ESG> toBeRemovedFeatureSet, FeaturedESG FESG) {

		FeatureDependencyTree featureDependencyTree = featureDependencyTreeBuilder.getFeatureDependencyTree();
		Set<ESG> finalToBeRemovedFeatureSet = new LinkedHashSet<ESG>();
		finalToBeRemovedFeatureSet.addAll(toBeRemovedFeatureSet);
		Set<FeatureDependencyTreeNode> candidateToBeRemovedFeatureDependencyTreeNode = new LinkedHashSet<FeatureDependencyTreeNode>();
		Iterator<ESG> toBeRemovedFeatureSetIterator = toBeRemovedFeatureSet.iterator();
		while (toBeRemovedFeatureSetIterator.hasNext()) {
			ESG toBeRemovedFeature = toBeRemovedFeatureSetIterator.next();
//System.out.println("candidate toBeRemovedFeature " + toBeRemovedFeature.getName());
			FeatureDependencyTreeNode featureDependencyTreeNode = featureDependencyTree
					.getNodeByLevelOrderSearch(toBeRemovedFeature.getName());
			List<FeatureDependencyTreeNode> childList = featureDependencyTree.getAllChildNodesOfANode(featureDependencyTreeNode);
			candidateToBeRemovedFeatureDependencyTreeNode.addAll(childList);
			
		}
//System.out.print("candidate to be removed ");
//candidateToBeRemovedFeatureDependencyTreeNode.forEach(e -> System.out.print(e.getFeatureESG().getName() + " "));
//System.out.println();
		determineFinalRemovals(finalToBeRemovedFeatureSet, candidateToBeRemovedFeatureDependencyTreeNode, FESG);
		return finalToBeRemovedFeatureSet;
	}
	
	private void determineFinalRemovals(Set<ESG> finalToBeRemovedFeatureSet,
			Set<FeatureDependencyTreeNode> candidateToBeRemovedFeatureDependencyTreeNode, FeaturedESG FESG) {

		Map<ESG, Set<ESG>> featureESGParentFeatureESGSetMap = featureDependencyTreeBuilder
				.getFeatureESGParentFeatureESGSetMap();
		Iterator<FeatureDependencyTreeNode> candidateToBeRemovedFeatureDependencyTreeNodeIterator = candidateToBeRemovedFeatureDependencyTreeNode
				.iterator();
		while (candidateToBeRemovedFeatureDependencyTreeNodeIterator.hasNext()) {
			FeatureDependencyTreeNode currentNode = candidateToBeRemovedFeatureDependencyTreeNodeIterator.next();
			ESG featureESG = currentNode.getFeatureESG();
			ESG fESG = FESG.getFeatureESGByName(featureESG.getName());
			
//System.out.print("Current candidate node " + featureESG.getName());
			if (!fESG.equals(null) && !finalToBeRemovedFeatureSet.contains(featureESG)) {
//System.out.print(" is not on final removals: ");
				boolean hasParallelConnectionPoints = ParallelConnectionPointsDeterminer
						.hasParallelConnectionPoints(featureESG);
//System.out.println(" hasParallelConnectionPoints-> " + hasParallelConnectionPoints);
				if (!hasParallelConnectionPoints) {
					finalToBeRemovedFeatureSet.add(featureESG);
//System.out.println(featureESG.getName() + " is added to the finalToBeRemovedFeatureSet");
				} else {			

					Set<ESG> parentFeatureSet = featureESGParentFeatureESGSetMap.get(featureESG);
//System.out.print("Parent features of " + featureESG.getName() + " :");parentFeatureSet.forEach(e -> System.out.print(e.getName() + " "));System.out.println();
					boolean allParentsAreRemoved = finalToBeRemovedFeatureSet.containsAll(parentFeatureSet);
					if (allParentsAreRemoved) {
//System.out.println("All parent features will be removed " + allParentsAreRemoved);
						finalToBeRemovedFeatureSet.add(featureESG);
					} else {
						Set<ESG> parallelFeatureESGSet = ParallelConnectionPointsDeterminer
								.getParallelFeatureESGSet(featureESG, FESG);
						boolean allParallelFeaturesAreRemoved = finalToBeRemovedFeatureSet
								.containsAll(parallelFeatureESGSet);
						if (allParallelFeaturesAreRemoved) {
//System.out.println("All parallel parent features will be removed " + allParallelFeaturesAreRemoved);
							finalToBeRemovedFeatureSet.add(featureESG);
						}
						Set<ESG> nonParallelParentESGSet = new LinkedHashSet<ESG>();
						nonParallelParentESGSet.addAll(parentFeatureSet);
						nonParallelParentESGSet.removeAll(parallelFeatureESGSet);
//System.out.print("nonParallelParentESGSet: ");nonParallelParentESGSet.forEach(e -> System.out.print(e.getName() + " "));System.out.println();
						if (!nonParallelParentESGSet.isEmpty() && finalToBeRemovedFeatureSet.containsAll(nonParallelParentESGSet)) {
//System.out.println("All nonParallelParentESGSet is removed");
							finalToBeRemovedFeatureSet.add(featureESG);
						} else {
							boolean isOneOfNonParallelParentESGRemoved = false;
							for (ESG nonParallelParentESG : nonParallelParentESGSet) {
								isOneOfNonParallelParentESGRemoved = isOneOfNonParallelParentESGRemoved
										|| finalToBeRemovedFeatureSet.contains(nonParallelParentESG);
							}

							if (isOneOfNonParallelParentESGRemoved) {
//System.out.println("isOneOfNonParallelParentESGRemoved");
								finalToBeRemovedFeatureSet.add(featureESG);
							}
						}
							


						
						
					}
				}
			}else {
//System.out.println(" is on removals");
			}
		}

	}

	public List<ESG> determineRemovalOrder(Set<ESG> finalToBeRemovedFeatureSet) {
		List<FeatureDependencyTreeNode> orderedFeatureDependencyTreeNodeList = determineRemovalOrderOfFeatureDependencyTreeNodes(
				finalToBeRemovedFeatureSet);
		List<ESG> orderedRemovalList = new LinkedList<ESG>();
		Iterator<FeatureDependencyTreeNode> orderedFeatureDependencyTreeNodeListIterator = orderedFeatureDependencyTreeNodeList
				.iterator();
		while (orderedFeatureDependencyTreeNodeListIterator.hasNext()) {
			FeatureDependencyTreeNode node = orderedFeatureDependencyTreeNodeListIterator.next();
			orderedRemovalList.add(node.getFeatureESG());
		}
System.out.print("Ordered to be removed ");orderedRemovalList.forEach(e -> System.out.print(e.getName() + " "));System.out.println();

		return orderedRemovalList;
	}

	@SuppressWarnings("unchecked")
	private List<FeatureDependencyTreeNode> determineRemovalOrderOfFeatureDependencyTreeNodes(
			Set<ESG> finalToBeRemovedFeatureSet) {
		FeatureDependencyTree featureDependencyTree = featureDependencyTreeBuilder.getFeatureDependencyTree();
		List<FeatureDependencyTreeNode> orderedFeatureDependencyTreeNodeList = new LinkedList<FeatureDependencyTreeNode>();

		Iterator<ESG> finalToBeRemovedFeatureSetIterator = finalToBeRemovedFeatureSet.iterator();
		while (finalToBeRemovedFeatureSetIterator.hasNext()) {
			ESG ESG = finalToBeRemovedFeatureSetIterator.next();
			FeatureDependencyTreeNode foundNode = featureDependencyTree.getNodeByLevelOrderSearch(ESG.getName());
			orderedFeatureDependencyTreeNodeList.add(foundNode);
		}
		Collections.sort(orderedFeatureDependencyTreeNodeList);

		return orderedFeatureDependencyTreeNodeList;
	}

}
