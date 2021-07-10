package tr.edu.iyte.fesg.cases;

import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.fesg.fullesggeneration.FullESGValidator;
import tr.edu.iyte.fesg.model.FeaturedESG;

public abstract class FullESGValidatingUtilities extends DotFileCreationUtilities {

	public static ESG validateFullESG(FeaturedESG featuredESG) {

		FullESGValidator fullESGValidator = new FullESGValidator();

		double startTime = System.nanoTime();
		ESG fullESG = fullESGValidator.validateFullESG(featuredESG);
		double stopTime = System.nanoTime();
		double timeElapsed = stopTime - startTime;
		System.out.println("Execution time of Full ESG Validation in miliseconds  : " + timeElapsed / (double) 1000000);
		
		return fullESG;

	}

}
