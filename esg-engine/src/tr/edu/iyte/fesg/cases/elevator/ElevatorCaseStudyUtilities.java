package tr.edu.iyte.fesg.cases.elevator;

import tr.edu.iyte.fesg.cases.CaseStudyFilePathCreationUtilities;

public abstract class ElevatorCaseStudyUtilities extends CaseStudyFilePathCreationUtilities {

	public static void buildProductModel() {
		createCaseStudyFilePathObjects();
		
		switch (productID) {
		case 1: {
			initializeCaseStudyFolderNames();
			elevatorProduct_baseProduct();
			break;
		}
		case 2: {
			initializeCaseStudyFolderNames();
			elevatorProduct_weight();
			break;
		}
		case 3: {
			initializeCaseStudyFolderNames();
			elevatorProduct_weightExecutiveFloor();
			break;
		}
		case 4: {
			initializeCaseStudyFolderNames();
			elevatorProduct_weightOverloaded();
			break;
		}
		case 5: {
			initializeCaseStudyFolderNames();
			elevatorProduct_fullProduct();
			break;
		}
		}
	}

	public static void initializeCaseStudyFolderNames() {
		esgFolderName.append("files/Cases/ElevatorPL");
		dotFolderName.append("files/DOTFiles/ElevatorPL");
	}

	public static void elevatorProduct_baseProduct() {
		int numberOfFeatures = 1;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("elevatorProduct_baseProduct");

		esgFileName[0] = "core.mxe";

		esgName[0] = "core";
	}

	public static void elevatorProduct_weight() {
		int numberOfFeatures = 2;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("elevatorProduct_weight");

		esgFileName[0] = "core.mxe";
		esgFileName[1] = "weight.mxe";

		esgName[0] = "core";
		esgName[1] = "weight";

	}

	public static void elevatorProduct_weightExecutiveFloor() {
		int numberOfFeatures = 3;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("elevatorProduct_weightExecutiveFloor");

		esgFileName[0] = "core.mxe";
		esgFileName[1] = "weight.mxe";
		esgFileName[2] = "executiveFloor.mxe";

		esgName[0] = "core";
		esgName[1] = "weight";
		esgName[2] = "executiveFloor";

	}

	public static void elevatorProduct_weightOverloaded() {
		int numberOfFeatures = 3;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("elevatorProduct_weightOverloaded");

		esgFileName[0] = "core.mxe";
		esgFileName[1] = "weight.mxe";
		esgFileName[2] = "overloaded.mxe";

		esgName[0] = "core";
		esgName[1] = "weight";
		esgName[2] = "overloaded";

	}

	public static void elevatorProduct_fullProduct() {
		int numberOfFeatures = 4;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("elevatorProduct_fullProduct");

		esgFileName[0] = "core.mxe";
		esgFileName[1] = "weight.mxe";
		esgFileName[2] = "overloaded.mxe";
		esgFileName[3] = "executiveFloor.mxe";

		esgName[0] = "core";
		esgName[1] = "weight";
		esgName[2] = "overloaded";
		esgName[3] = "executiveFloor";

	}
}
