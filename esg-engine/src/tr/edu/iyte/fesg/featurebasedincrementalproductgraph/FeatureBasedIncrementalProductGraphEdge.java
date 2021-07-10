package tr.edu.iyte.fesg.featurebasedincrementalproductgraph;

import java.util.LinkedHashSet;
import java.util.Set;

import tr.edu.iyte.esg.model.ESG;

public class FeatureBasedIncrementalProductGraphEdge {
	
	private Set<ESG> featureESGSet;
	private FeatureBasedIncrementalProductGraphVertex source;
	private FeatureBasedIncrementalProductGraphVertex target;
	
	public FeatureBasedIncrementalProductGraphEdge(FeatureBasedIncrementalProductGraphVertex source, FeatureBasedIncrementalProductGraphVertex target) {
		featureESGSet = new LinkedHashSet<ESG>();
		setSource(source);
		setTarget(target);
	}

	public Set<ESG> getFeatureESGSet() {
		return featureESGSet;
	}

	public void addFeatureESG(ESG featureESG) {
		this.featureESGSet.add(featureESG);
	}
	
	public void addFeatureESGSet(Set<ESG> featureESGSet) {
		this.featureESGSet.addAll(featureESGSet);
	}

	public FeatureBasedIncrementalProductGraphVertex getSource() {
		return source;
	}

	public void setSource(FeatureBasedIncrementalProductGraphVertex source) {
		this.source = source;
	}

	public FeatureBasedIncrementalProductGraphVertex getTarget() {
		return target;
	}

	public void setTarget(FeatureBasedIncrementalProductGraphVertex target) {
		this.target = target;
	}
	

}
