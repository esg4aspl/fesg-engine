package tr.edu.iyte.esg.applications;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import tr.edu.iyte.esg.conversion.json.JSONFileToEliminationConditionConverter;
import tr.edu.iyte.esg.conversion.mxe.MXEFiletoESGConverter;
import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.esg.model.eliminationcondition.EliminationCondition;
import tr.edu.iyte.esg.model.validation.ESGValidator;
import tr.edu.iyte.fesg.fullesgtoproductesg.EliminationConditionApplier;

public class EliminationConditionApp {

	public static void main(String[] args) {
		
		String ESGfileName = "files/MXEFiles/DooApplication.mxe";
		ESG ESG = null;
		try {
			ESG = MXEFiletoESGConverter.parseMXEFileForESGSimpleCreation(ESGfileName);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(ESG.toString());
		
		String eliminationConditionFileName = "files/JSONFiles/DoorApplicationGuardConditionResults.json";
		
		List<EliminationCondition> eliminationConditionList = new LinkedList<EliminationCondition>();
		try {
			eliminationConditionList = JSONFileToEliminationConditionConverter.parseJSONFileForEliminationConditionCreation(eliminationConditionFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for(EliminationCondition eliminationCondition : eliminationConditionList) {
			System.out.println(eliminationCondition.toString());
			
		}
		EliminationConditionApplier eliminationConditionApplier = new EliminationConditionApplier();
		eliminationConditionApplier.applyEliminationCondition(ESG, eliminationConditionList);
		
		System.out.println("AFTER ELIMINATION CONDITION APPLYING");
		
		ESGValidator ESGValidator = new ESGValidator();
		ESGValidator.validate(ESG);
	
	}

}
