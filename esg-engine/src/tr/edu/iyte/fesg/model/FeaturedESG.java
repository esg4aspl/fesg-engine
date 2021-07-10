package tr.edu.iyte.fesg.model;


import java.util.LinkedHashSet;
import java.util.Set;

import tr.edu.iyte.esg.eventsequence.EventSequence;
import tr.edu.iyte.esg.model.ESG;


public class FeaturedESG {
	
	private String name;
	private String coreESGName;
	private ESG coreESG;
	private Set<ESG> featureESGSet;
	private Set<EventSequence> cesSet;
	

	public FeaturedESG(String productESGName,String coreESGName) {
		this.coreESGName = coreESGName;
		this.name = productESGName;
		featureESGSet = new LinkedHashSet<ESG>();
		this.cesSet = new LinkedHashSet<EventSequence>();
	}

	public FeaturedESG(FeaturedESG featuredESG) {
		this.coreESGName = featuredESG.getCoreESGName();
		this.coreESG = featuredESG.getCoreESG();
		this.featureESGSet = new LinkedHashSet<ESG>();
		this.cesSet = new LinkedHashSet<EventSequence>();
		this.cesSet.addAll(featuredESG.getCesSet());
		for(ESG ESG : featuredESG.getFeatureESGSet()) {
			this.featureESGSet.add(ESG);
		}
	}
	
	public ESG getCoreESG() {
		return coreESG;
	}
	
	public void setCoreESG(ESG coreESG) {
		
		if(coreESG.getName().equals(coreESGName)) {
			this.coreESG = coreESG;
		}
	}

	public Set<ESG> getFeatureESGSet() {
		return featureESGSet;
	}

	public void addFeatureESG(ESG featureESG) {
		this.featureESGSet.add(featureESG);
	}

	public String getCoreESGName() {
		return coreESGName;
	}
	
	public void setCoreESGName(String coreESGName) {
		this.coreESGName = coreESGName;
	}
	
	public Set<EventSequence> getCesSet() {
		return cesSet;
	}

	public void setCesSet(Set<EventSequence> cesSet) {
		this.cesSet = cesSet;
	}
	
	public Set<ESG> getFeaturedESGSet(){
		Set<ESG> featuredESGSet = new LinkedHashSet<ESG>();
		
		featuredESGSet.add(coreESG);
		featuredESGSet.addAll(featureESGSet);
		
		return featuredESGSet;
	}
	
	public ESG getFeatureESGByName(String featureESGName) {
		ESG featureESG = null;
		for(ESG ESG : getFeaturedESGSet()) {
			if(ESG.getName().equals(featureESGName)) {
				featureESG = ESG;
			}
		}
		
		return featureESG;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		String str = "";
		
		for(ESG ESG: getFeaturedESGSet()) {
			str += ESG.toString() + "\n";
		}
		
		return str;
		
	}
}
