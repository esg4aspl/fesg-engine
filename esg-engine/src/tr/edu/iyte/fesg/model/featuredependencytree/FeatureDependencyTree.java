package tr.edu.iyte.fesg.model.featuredependencytree;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import tr.edu.iyte.esg.model.ESG;

public class FeatureDependencyTree {

	private FeatureDependencyTreeNode root;
	private Set<String> featureNameSet;
	private List<FeatureDependencyTreeNode> nodeList;
	

	public FeatureDependencyTree(FeatureDependencyTreeNode root) {
		this.root = root;
		this.root.setLevel(1);
		this.featureNameSet = new LinkedHashSet<String>();
		this.nodeList = new LinkedList<FeatureDependencyTreeNode>();
	}

	public FeatureDependencyTreeNode getRoot() {
		return root;
	}

	public Set<String> getFeatureNameSet() {
		return featureNameSet;
	}
	
	public List<FeatureDependencyTreeNode> getNodeList() {
		return nodeList;
	}
	
	public void addNode(FeatureDependencyTreeNode node) {
		nodeList.add(node);
		featureNameSet.add(node.getFeatureESG().getName());
	}
	
	public void printLevelOrderTraversal() {

		if (root != null) {
			Queue<FeatureDependencyTreeNode> queue = new LinkedList<FeatureDependencyTreeNode>();
			queue.offer(root);

			while (!queue.isEmpty()) {
				int len = queue.size();

				for (int i = 0; i < len; i++) {
					FeatureDependencyTreeNode node = queue.poll();
					ESG featureESG = node.getFeatureESG();
					System.out.print(featureESG.getName() + " level:" + node.getLevel() + " ");

					for (FeatureDependencyTreeNode childFeature : node.getChildList()) {
						queue.offer(childFeature);
					}
				}
				System.out.println();
			}

		}
	}

	public FeatureDependencyTreeNode getNodeByLevelOrderSearch(String featureName) {

		if (root != null) {
			Queue<FeatureDependencyTreeNode> queue = new LinkedList<FeatureDependencyTreeNode>();
			queue.offer(root);

			while (!queue.isEmpty()) {
				int len = queue.size();
				for (int i = 0; i < len; i++) {
					FeatureDependencyTreeNode node = queue.poll();
					ESG featureESG = node.getFeatureESG();

					if (featureESG.getName().equals(featureName)) {
						return node;
					}
					for (FeatureDependencyTreeNode childFeature : node.getChildList()) {
						queue.offer(childFeature);
					}
				}
			}

		}

		return null;
	}
	
	public List<FeatureDependencyTreeNode> getAllChildNodesOfANode(FeatureDependencyTreeNode featureDependencyTreeNode) {

		List<FeatureDependencyTreeNode> allChildNodes = new LinkedList<FeatureDependencyTreeNode>();
		if (featureDependencyTreeNode != null) {
			Queue<FeatureDependencyTreeNode> queue = new LinkedList<FeatureDependencyTreeNode>();
			queue.offer(featureDependencyTreeNode);

			while (!queue.isEmpty()) {
				int len = queue.size();
				for (int i = 0; i < len; i++) {
					FeatureDependencyTreeNode node = queue.poll();
					
					if(!node.equals(featureDependencyTreeNode)) {
						allChildNodes.add(node);
					}
					
					for (FeatureDependencyTreeNode childFeature : node.getChildList()) {
						queue.offer(childFeature);
					}
				}
			}

		}

		return allChildNodes;
	}




}
