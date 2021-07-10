package tr.edu.iyte.fesg.model.featuremodel;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class FeatureModel {

	private String softwareProductLineName;
	private Set<Feature> featureSet;
	private Feature root;
	private Map<Feature, Set<Feature>> xorFeatures;
	private Map<Feature, Set<Feature>> orFeatures;
	private Set<Connector> connConstraints;
	
	private Set<Implication> impConstraints;
	

	public FeatureModel() {
		featureSet = new LinkedHashSet<Feature>();
		xorFeatures = new LinkedHashMap<Feature, Set<Feature>>();
		orFeatures = new LinkedHashMap<Feature, Set<Feature>>();
		connConstraints = new LinkedHashSet<Connector>();
		impConstraints = new LinkedHashSet<Implication>();
	}

	public String getSoftwareProductLineName() {
		return softwareProductLineName;
	}

	public Set<Feature> getFeatureSet() {
		return featureSet;
	}
	

	public boolean addFeature(Feature feature) {
		if (!featureSet.contains(feature))
			return featureSet.add(feature);
		else
			return false;
	}

	public Feature getRoot() {
		return root;
	}

	public void setRoot(Feature root) {
		this.root = root;
		this.softwareProductLineName = root.getName();
	}

	public Map<Feature, Set<Feature>> getXORFeatures() {
		return xorFeatures;
	}

	public void addXORFeature(Feature parent, Feature child) {
		if (xorFeatures.containsKey(parent)) {
			xorFeatures.get(parent).add(child);
		} else {
			Set<Feature> featureSet = new LinkedHashSet<Feature>();
			featureSet.add(child);
			xorFeatures.put(parent, featureSet);
		}
	}

	public Map<Feature, Set<Feature>> getORFeatures() {
		return orFeatures;
	}

	public void addORFeature(Feature parent, Feature child) {

		if (orFeatures.containsKey(parent)) {
			orFeatures.get(parent).add(child);
		} else {
			Set<Feature> featureSet = new LinkedHashSet<Feature>();
			featureSet.add(child);
			orFeatures.put(parent, featureSet);
		}

	}

	public Set<Connector> getConnConstraints() {
		return connConstraints;
	}

	public void addConnConstraint(Connector constraint) {
		
		this.connConstraints.add(constraint);
	}
	
	public Set<Implication> getImpConstraints() {
		return impConstraints;
	}

	public void addImpConstraint(Implication impConstraint) {
	
		this.impConstraints.add(impConstraint);
	}
	
	public Feature findFeatureByName(String name) {

		Iterator<Feature> featureSetIterator = featureSet.iterator();
		while (featureSetIterator.hasNext()) {
			Feature feature = featureSetIterator.next();

			if (feature.getName().equals(name)) {
				return feature;
			}
		}

		return null;

	}
	
	public Connector findConnectorByFeatureName(String str) {
	
		for(Connector connector : connConstraints) {
			if(connector.searchKey().equals(str)) {
				return connector;
			}
		}
		
		return null;
		
	}
	

	
	@Override
	public String toString() {
		
		String str = softwareProductLineName + "\n";
		str += featureSetToString();
		str += mapToString("OR FEATURES", "OR", orFeatures);
		str += mapToString("ALTERNATIVE FEATURES", "ALT", xorFeatures);
		str += constraintsToString("CONSTRAINTS");	
		
		return str;
	}
	
	private String featureSetToString() {
		
		String str = "";
		for(Feature feature : featureSet) {
			str += feature.toString() + "\n";
		}
		
		return str;
	}
	
	private String mapToString(String header,String abr, Map<Feature,Set<Feature>> featureMap) {
		
		String str = header +  "\n";
		for(Feature key : featureMap.keySet()) {
			str += key.getName().toUpperCase() + " " + abr + " ";
			for(Feature value : featureMap.get(key)) {
				str += value.getName() + " ";
			}
			str += "\n";
		}
		str += "\n";
		
		
		return str;
		
	}
	
	private String constraintsToString(String header) {
		String str = header +  "\n";
		
		for(Connector connector : connConstraints) {
			str += connector.toString() + "\n";
		}
		
		for(Implication implication: impConstraints) {
			str += implication.toString() + "\n";
		}
		
		return str;
	}

}
