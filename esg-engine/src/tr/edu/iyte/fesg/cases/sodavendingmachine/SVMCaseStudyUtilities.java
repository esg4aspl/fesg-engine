package tr.edu.iyte.fesg.cases.sodavendingmachine;

import tr.edu.iyte.fesg.cases.CaseStudyFilePathCreationUtilities;

public abstract class SVMCaseStudyUtilities extends  CaseStudyFilePathCreationUtilities{
	
	public static void buildProductModel() {
		
		createCaseStudyFilePathObjects();
		switch(productID) {
			 case 1:{
				initializeCaseStudyFolderNames();
				svmProduct_payEUR();
				break;
			}case 2:{
				initializeCaseStudyFolderNames();
				svmProduct_payEURServeSoda();
				break;
			}case 3:{
				initializeCaseStudyFolderNames();
				svmProduct_free();
				break;
			}case 4:{
				initializeCaseStudyFolderNames();
				svmProduct_payUSD();
				break;
			}case 5:{
				initializeCaseStudyFolderNames();
				svmProduct_payUSDServeTea();
				break;
			}
			case 6:{
				initializeCaseStudyFolderNames();
				svmProduct_fullProduct();
				break;
			}
			case 7: {
				initializeCaseStudyFolderNames();
				svmProduct_baseProduct();
				break;
			}
		}
	}
	
	public static void initializeCaseStudyFolderNames() {
		esgFolderName.append("files/Cases/SodaVendingMachinePL");
		dotFolderName.append("files/DOTFiles/SodaVendingMachinePL");
	}
	
	public static void svmProduct_payEUR(){
		
		int numberOfFeatures = 5; 
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("svmProduct-payEUR");
		
		esgFileName[0] = "core_useful.mxe";
		esgFileName[1] = "payEUR.mxe";
		esgFileName[2] = "serveTea.mxe";
		esgFileName[3] = "serveSoda.mxe";
		esgFileName[4] = "cancel.mxe";

		esgName[0] = "core";
		esgName[1] = "payEUR";
		esgName[2] = "serveTea";
		esgName[3] = "serveSoda";
		esgName[4] = "cancel";
	}

	public static void svmProduct_payEURServeSoda(){
		
		int numberOfFeatures = 3; 
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("svmProduct-payEURServeSoda");

		esgFileName[0] = "core_useful.mxe";
		esgFileName[1] = "payEUR.mxe";
		esgFileName[2] = "serveSoda.mxe";


		esgName[0] = "core";
		esgName[1] = "payEUR";
		esgName[2] = "serveSoda";

	}

	public static void svmProduct_free() {
		
		int numberOfFeatures = 3; 
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("svmProduct-free");
		
		esgFileName[0] = "core_useful.mxe";
		esgFileName[1] = "free.mxe";
		esgFileName[2] = "serveSoda.mxe";

		esgName[0] = "core";
		esgName[1] = "free";
		esgName[2] = "serveSoda";
	}

	public static void svmProduct_payUSD() {
		
		int numberOfFeatures = 5; 
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("svmProduct-payUSD");
	
		esgFileName[0] = "core_useful.mxe";
		esgFileName[1] = "payUSD.mxe";
		esgFileName[2] = "serveTea.mxe";
		esgFileName[3] = "serveSoda.mxe";
		esgFileName[4] = "cancel.mxe";

		esgName[0] = "core";
		esgName[1] = "payUSD";
		esgName[2] = "serveTea";
		esgName[3] = "serveSoda";
		esgName[4] = "cancel";
	}

	public static void svmProduct_payUSDServeTea() {
		
		int numberOfFeatures = 4; 
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("svmProduct-payUSDServeTea");
		
		esgFileName[0] = "core_useful.mxe";
		esgFileName[1] = "payUSD.mxe";
		esgFileName[2] = "serveTea.mxe";
		esgFileName[3] = "cancel.mxe";

		esgName[0] = "core";
		esgName[1] = "payUSD";
		esgName[2] = "serveTea";
		esgName[3] = "cancel";
	}
	
	public static void svmProduct_fullProduct() {
		
		int numberOfFeatures = 7; 
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("svmProduct-fullProduct");
		
		esgFileName[0] = "core_useful.mxe";
		esgFileName[1] = "payUSD.mxe";
		esgFileName[2] = "payEUR.mxe";
		esgFileName[3] = "serveSoda.mxe";
		esgFileName[4] = "serveTea.mxe";
		esgFileName[5] = "cancel.mxe";
		esgFileName[6] = "free.mxe";

		esgName[0] = "core";
		esgName[1] = "payUSD";
		esgName[2] = "payEUR";
		esgName[3] = "serveSoda";
		esgName[4] = "serveTea";
		esgName[5] = "cancel";
		esgName[6] = "free";
	}
	
	public static void svmProduct_baseProduct() {
		
		int numberOfFeatures = 3; 
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("svmProduct-fullProduct");
		
		esgFileName[0] = "core_useful.mxe";
		esgFileName[1] = "payEUR.mxe";
		esgFileName[2] = "serveSoda.mxe";

		esgName[0] = "core";
		esgName[1] = "payEUR";
		esgName[2] = "serveSoda";

	}
	

}
