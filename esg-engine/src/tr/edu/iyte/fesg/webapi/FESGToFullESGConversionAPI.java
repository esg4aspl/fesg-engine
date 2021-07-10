package tr.edu.iyte.fesg.webapi;

import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.fesg.fullesggeneration.FullESGGenerator;
import tr.edu.iyte.fesg.model.FeaturedESG;

public class FESGToFullESGConversionAPI {
	
	
	public ESG convertFESGToFullESG(FeaturedESG featuredESG) {

		FullESGGenerator fullESGGenerator = new FullESGGenerator(featuredESG.getFeaturedESGSet().size(), featuredESG);

		fullESGGenerator.generateFullESG();
		ESG fullESG = fullESGGenerator.getFullESG();
		
		return fullESG;
	}

}
