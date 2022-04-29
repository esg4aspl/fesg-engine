package tr.edu.iyte.fesg.model.featuredependencytree;

import java.util.LinkedList;
import java.util.List;

import tr.edu.iyte.esg.model.ESG;

public class FeatureDependencyTreeNode implements Comparable {
	
	private ESG featureESG;
	private List<FeatureDependencyTreeNode> childList;
	private int level;
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public FeatureDependencyTreeNode(ESG featureESG) {
		this.featureESG = featureESG;
		childList = new LinkedList<FeatureDependencyTreeNode>();
	}
	
	public FeatureDependencyTreeNode(ESG featureESG, List<FeatureDependencyTreeNode> childList) {
		this.featureESG = featureESG;
		this.childList = childList;
	}
	
	public ESG getFeatureESG() {
		return featureESG;
	}

	public List<FeatureDependencyTreeNode> getChildList() {
		return childList;
	}
	public void addChild(FeatureDependencyTreeNode child) {
		
		if(child != null) {
			this.childList.add(child);
		}
	}

	@Override
	public int compareTo(Object o) {
		FeatureDependencyTreeNode parameterNode = (FeatureDependencyTreeNode) o;
		if(this.getLevel() < parameterNode.getLevel()) {
			return -1;
		}else if(this.getLevel() > parameterNode.getLevel()) {
			return 1;
		}else
			return 0;
	}


	
	
	
	

}
