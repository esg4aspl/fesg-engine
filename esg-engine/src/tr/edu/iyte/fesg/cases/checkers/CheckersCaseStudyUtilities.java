package tr.edu.iyte.fesg.cases.checkers;

import tr.edu.iyte.fesg.cases.CaseStudyFilePathCreationUtilities;

public abstract class CheckersCaseStudyUtilities extends CaseStudyFilePathCreationUtilities {
	
	public static void buildProductModel() {
		createCaseStudyFilePathObjects();
		switch (productID) {
		case 1: {
			initializeCaseStudyFolderNames();
			productBaseCheckers();
			break;
		}
		case 2: {
			initializeCaseStudyFolderNames();
			productAmericanCheckers();
			break;
		}
		case 3: {
			initializeCaseStudyFolderNames();
			productSpanishCheckers();
			break;
		}
		}
	}

	public static void initializeCaseStudyFolderNames() {
		esgFolderName.append("files/Cases/CheckersPL");
		dotFolderName.append("files/DOTFiles/CheckersPL");
	}
	
	public static void productBaseCheckers()  {
		int numberOfFeatures = 4; 
		
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("ProductBaseCheckers");
		
		esgFileName[0] = "CoreAmericanTypeCheckers_useful.mxe";
		esgFileName[1] = "FeaturePawnMarch.mxe";
		esgFileName[2] = "FeaturePawnJump.mxe";
		esgFileName[3] = "FeaturePawnCapture.mxe";

		esgName[0] = "CoreAmericanTypeCheckers";
		esgName[1] = "FeaturePawnMarch";
		esgName[2] = "FeaturePawnJump";
		esgName[3] = "FeaturePawnCapture";
	}
	
	public static void productAmericanCheckers() {
		
		int numberOfFeatures = 8;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("ProductAmericanCheckers");
		
		esgFileName[0] = "CoreAmericanTypeCheckers_useful.mxe";
		esgFileName[1] = "FeaturePawnMarch.mxe";
		esgFileName[2] = "FeatureKingMarch.mxe";
		esgFileName[3] = "FeaturePawnJump.mxe";
		esgFileName[4] = "FeatureKingJump.mxe";
		esgFileName[5] = "FeaturePawnCapture.mxe";
		esgFileName[6] = "FeatureKingCapture.mxe";
		esgFileName[7] = "FeaturePromoteToKing.mxe";

		esgName[0] = "CoreAmericanTypeCheckers";
		esgName[1] = "FeaturePawnMarch";
		esgName[2] = "FeatureKingMarch";
		esgName[3] = "FeaturePawnJump";
		esgName[4] = "FeatureKingJump";
		esgName[5] = "FeaturePawnCapture";
		esgName[6] = "FeatureKingCapture";
		esgName[7] = "FeaturePromoteToKing";
	}
	
	public static void productSpanishCheckers()  {
		
		int numberOfFeatures = 8;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("ProductSpanishCheckers");
		
		esgFileName[0] = "CoreAmericanTypeCheckers_useful.mxe";
		esgFileName[1] = "FeaturePawnMarch.mxe";
		esgFileName[2] = "FeatureQueenMarch.mxe";
		esgFileName[3] = "FeaturePawnJump.mxe";
		esgFileName[4] = "FeatureQueenJump.mxe";
		esgFileName[5] = "FeaturePawnCapture.mxe";
		esgFileName[6] = "FeatureQueenCapture.mxe";
		esgFileName[7] = "FeaturePromoteToQueen.mxe";

		esgName[0] = "CoreAmericanTypeCheckers";
		esgName[1] = "FeaturePawnMarch";
		esgName[2] = "FeatureQueenMarch";
		esgName[3] = "FeaturePawnJump";
		esgName[4] = "FeatureQueenJump";
		esgName[5] = "FeaturePawnCapture";
		esgName[6] = "FeatureQueenCapture";
		esgName[7] = "FeaturePromoteToQueen";
	}
	

}
