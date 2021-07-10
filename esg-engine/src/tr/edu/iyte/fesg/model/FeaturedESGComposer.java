package tr.edu.iyte.fesg.model;

import java.util.LinkedHashSet;
import java.util.Set;

import tr.edu.iyte.esg.conversion.ESGConversionUtilities;
import tr.edu.iyte.esg.model.ESG;

public class FeaturedESGComposer {

	private FeaturedESG featuredESG;

	public FeaturedESGComposer(String productESGName, String coreESGName) {
		featuredESG = new FeaturedESG(productESGName,coreESGName);
		featuredESG.setName(productESGName);
	}

	public FeaturedESG getFeaturedESG() {
		return featuredESG;
	}

	public FeaturedESG copmposeFeaturedESGFromMXEFile(String folderName, String[] esgFileName, String[] esgName) {
		buildFeaturedESGFromMXEFile(featuredESG, folderName, esgFileName, esgName);
		
		return featuredESG;
	}
	
	public static void buildFeaturedESGFromMXEFile(FeaturedESG featuredESG,String folderName, String[] esgFileName, String[] esgName) {
		
		for(int i=0; i < esgName.length; i++) {
			ESG ESG = null;
			ESG = ESGConversionUtilities.readESGFromMXEFile(folderName + "/" + esgFileName[i], i, esgName[i]);
			
			if(esgName[i].equals(featuredESG.getCoreESGName())) {
				featuredESG.setCoreESG(ESG);
			}else {
				featuredESG.addFeatureESG(ESG);
			}
		}
	}
	
	public static Set<ESG> buildNewFeatureESGSetFromMXEFile(String folderName, String[] esgFileName, String[] esgName)  {
		Set<ESG> newFeatureESGSet = new LinkedHashSet<ESG>();
		for (int i = 0; i < esgName.length; i++) {
			ESG ESG = null;
			ESG = ESGConversionUtilities.readESGFromMXEFile(folderName + "/" + esgFileName[i], i, esgName[i]);
			newFeatureESGSet.add(ESG);
		}
		return newFeatureESGSet;
		
	}

}
