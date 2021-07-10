package tr.edu.iyte.fesg.cases.elevator;

public class ElevatorCaseStudyAutomaticBuildUtilities extends ElevatorCaseStudyUtilities{
	
	private static String featureModelFilePath;
	private static String configFilePath;
	private static String configFolder = "/configs/";
	private static String xmlFileExtension = ".xml";
	
	
	public static void initializeConfigFolderNames() {
		initializeCaseStudyFolderNames();
		setFeatureModelFilePath(esgFolderName + configFolder + "model" + xmlFileExtension);
		
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

	public static String getFeatureModelFilePath() {
		return featureModelFilePath;
	}

	public static void setFeatureModelFilePath(String featureModelFilePath) {
		ElevatorCaseStudyAutomaticBuildUtilities.featureModelFilePath = featureModelFilePath;
	}

	public static String getConfigFilePath() {
		return configFilePath;
	}

	public static void setConfigFilePath(String configFilePath) {
		ElevatorCaseStudyAutomaticBuildUtilities.configFilePath = configFilePath;
	}
}
