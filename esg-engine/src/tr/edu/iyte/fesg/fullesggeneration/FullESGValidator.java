package tr.edu.iyte.fesg.fullesggeneration;

import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.esg.model.Model;
import tr.edu.iyte.esg.model.validation.ModelValidator;
import tr.edu.iyte.esg.model.validation.ValidationResult;
import tr.edu.iyte.fesg.model.FeaturedESG;

public class FullESGValidator {

	public ESG validateFullESG(FeaturedESG featuredESG) {

		FullESGGenerator fullESGGenerator = new FullESGGenerator(featuredESG.getFeaturedESGSet().size(), featuredESG);

		fullESGGenerator.generateFullESG();
		ESG fullESG = fullESGGenerator.getFullESG();
		Model model = new Model(featuredESG.getName());
		model.addESG(fullESG);

		System.out.println("Validation of " + featuredESG.getName());
		ModelValidator modelValidator = new ModelValidator();
		ValidationResult modelValidationResult = modelValidator.validate(model);
		System.out.println("Model " + modelValidationResult);

		return fullESG;
	}

}
