package tr.edu.iyte.fesg.cases.email;

import tr.edu.iyte.fesg.cases.CaseStudyFilePathCreationUtilities;

public abstract class EmailCaseStudyUtilities extends CaseStudyFilePathCreationUtilities {
	/**
	 * productID = 1 -> emailProduct_baseProduct
	 * productID = 2 -> emailProduct_addressbook
	 * productID = 3 -> emailProduct_addressbookAutoresponder
	 * productID = 4 -> emailProduct_addressbookAutoresponderEncrypt
	 * productID = 5 -> emailProduct_addressbookAutoresponderEncryptSign
	 * productID = 6 -> emailProduct_addressbookAutoresponderForward
	 * productID = 7 -> emailProduct_addressbookAutoresponderSign
	 * productID = 8 -> emailProduct_addressbookEncrypt
	 * productID = 9 -> emailProduct_autoresponder
	 * productID = 10 -> emailProduct_autoresponderEncrypt
	 * productID = 11 -> emailProduct_autoresponderForward
	 * productID = 12 -> emailProduct-encrypt
	 * productID = 13 -> emailProduct-forward
	 * 
	 * initialize productID to build different products
	 * 
	 * 
	 * operationID = 1 -> to extend a Featured ESG with new features 
	 * operationID = 2 -> to build a new Featured ESG with an existing Featured ESG and new features
	 * 
	 * initialize productNameInput to 1 if you want to enter a new name to the new product
	 * otherwise the new product's name will be created automatically.
	 * 
	 * You can enter as many features as you want
	 */
	public static void buildProductModel() {

		createCaseStudyFilePathObjects();

		switch (productID) {
		case 1: {
		initializeCaseStudyFolderNames();
		emailProduct_baseProduct();
		break;
		}
		case 2: {
			initializeCaseStudyFolderNames();
			emailProduct_addressbook();
			break;
		}
		case 3: {
			initializeCaseStudyFolderNames();
			emailProduct_addressbookAutoresponder();
			break;
		}
		case 4: {
			initializeCaseStudyFolderNames();
			emailProduct_addressbookAutoresponderEncrypt();
			break;
		}
		case 5: {
			initializeCaseStudyFolderNames();
			emailProduct_addressbookAutoresponderEncryptSign();
			break;
		}
		case 6: {
			initializeCaseStudyFolderNames();
			emailProduct_addressbookAutoresponderForward();
			break;
		}
		case 7: {
			initializeCaseStudyFolderNames();
			emailProduct_addressbookAutoresponderSign();
			break;
		}
		case 8: {
			initializeCaseStudyFolderNames();
			emailProduct_addressbookEncrypt();
			break;
		}
		case 9: {
			initializeCaseStudyFolderNames();
			emailProduct_autoresponder();
			break;
		}
		case 10: {
			initializeCaseStudyFolderNames();
			emailProduct_autoresponderEncrypt();
			break;
		}
		case 11: {
			initializeCaseStudyFolderNames();
			emailProduct_autoresponderForward();
			break;
		}
		case 12: {
			initializeCaseStudyFolderNames();
			emailProduct_encrypt();
			break;
		}
		case 13: {
			initializeCaseStudyFolderNames();
			emailProduct_forward();
			break;
		}
		}
	}

	public static void initializeCaseStudyFolderNames() {
		esgFolderName.append("files/Cases/EmailPL");
		dotFolderName.append("files/DOTFiles/EmailPL");
	}
	

	public static void emailProduct_baseProduct() {
		int numberOfFeatures = 1;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("emailProduct-baseProduct");

		esgFileName[0] = "core.mxe";

		esgName[0] = "core";


	}
	
	public static void emailProduct_addressbook() {
		int numberOfFeatures = 2;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("emailProduct-addressbook");

		esgFileName[0] = "core.mxe";
		esgFileName[1] = "addressbook.mxe";

		esgName[0] = "core";
		esgName[1] = "addressbook";

	}

	public static void emailProduct_addressbookAutoresponder() {
		int numberOfFeatures = 3;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("emailProduct-addressbookAutoresponder");

		esgFileName[0] = "core.mxe";
		esgFileName[1] = "addressbook.mxe";
		esgFileName[2] = "autoresponder.mxe";

		esgName[0] = "core";
		esgName[1] = "addressbook";
		esgName[2] = "autoresponder";

	}

	public static void emailProduct_addressbookAutoresponderEncrypt() {
		int numberOfFeatures = 5;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("emailProduct-addressbookAutoresponderEncrypt");

		esgFileName[0] = "core.mxe";
		esgFileName[1] = "addressbook.mxe";
		esgFileName[2] = "autoresponder.mxe";
		esgFileName[3] = "keys.mxe";
		esgFileName[4] = "encrypt.mxe";

		esgName[0] = "core";
		esgName[1] = "addressbook";
		esgName[2] = "autoresponder";
		esgName[3] = "keys";
		esgName[4] = "encrypt";

	}

	public static void emailProduct_addressbookAutoresponderEncryptSign() {
		int numberOfFeatures = 6;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("emailProduct-addressbookAutoresponderEncryptSign");

		esgFileName[0] = "core.mxe";
		esgFileName[1] = "addressbook.mxe";
		esgFileName[2] = "autoresponder.mxe";
		esgFileName[3] = "keys.mxe";
		esgFileName[4] = "encrypt.mxe";
		esgFileName[5] = "sign.mxe";

		esgName[0] = "core";
		esgName[1] = "addressbook";
		esgName[2] = "autoresponder";
		esgName[3] = "keys";
		esgName[4] = "encrypt";
		esgName[5] = "sign";

	}

	public static void emailProduct_addressbookAutoresponderForward() {
		int numberOfFeatures = 4;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("emailProduct-addressbookAutoresponderForward");

		esgFileName[0] = "core.mxe";
		esgFileName[1] = "addressbook.mxe";
		esgFileName[2] = "autoresponder.mxe";
		esgFileName[3] = "forward.mxe";

		esgName[0] = "core";
		esgName[1] = "addressbook";
		esgName[2] = "autoresponder";
		esgName[3] = "forward";

	}

	public static void emailProduct_addressbookAutoresponderSign() {
		int numberOfFeatures = 4;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("emailProduct-addressbookAutoresponderSign");

		esgFileName[0] = "core.mxe";
		esgFileName[1] = "addressbook.mxe";
		esgFileName[2] = "autoresponder.mxe";
		esgFileName[3] = "sign.mxe";

		esgName[0] = "core";
		esgName[1] = "addressbook";
		esgName[2] = "autoresponder";
		esgName[3] = "sign";

	}

	public static void emailProduct_addressbookEncrypt() {
		int numberOfFeatures = 4;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("emailProduct-addressbookEncrypt");

		esgFileName[0] = "core.mxe";
		esgFileName[1] = "addressbook.mxe";
		esgFileName[2] = "keys.mxe";
		esgFileName[3] = "encrypt.mxe";

		esgName[0] = "core";
		esgName[1] = "addressbook";
		esgName[2] = "keys";
		esgName[3] = "encrypt";

	}

	public static void emailProduct_autoresponder() {
		int numberOfFeatures = 2;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("emailProduct-autoresponder");

		esgFileName[0] = "core.mxe";
		esgFileName[1] = "autoresponder.mxe";

		esgName[0] = "core";
		esgName[1] = "autoresponder";
	}

	public static void emailProduct_autoresponderEncrypt() {
		int numberOfFeatures = 4;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("emailProduct-autoresponderEncrypt");

		esgFileName[0] = "core.mxe";
		esgFileName[1] = "autoresponder.mxe";
		esgFileName[2] = "keys.mxe";
		esgFileName[3] = "encrypt.mxe";

		esgName[0] = "core";
		esgName[1] = "autoresponder";
		esgName[2] = "keys";
		esgName[3] = "encrypt";

	}

	public static void emailProduct_autoresponderForward() {
		int numberOfFeatures = 3;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("emailProduct-autoresponderForward");

		esgFileName[0] = "core.mxe";
		esgFileName[1] = "autoresponder.mxe";
		esgFileName[2] = "forward.mxe";

		esgName[0] = "core";
		esgName[1] = "autoresponder";
		esgName[2] = "forward";
	}
	
	public static void emailProduct_encrypt() {
		int numberOfFeatures = 3;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("emailProduct-encrypt");

		esgFileName[0] = "core.mxe";
		esgFileName[1] = "keys.mxe";
		esgFileName[2] = "encrypt.mxe";

		esgName[0] = "core";
		esgName[1] = "keys";
		esgName[2] = "encrypt";
	}
	
	public static void emailProduct_forward() {
		int numberOfFeatures = 3;
		esgFileName = new String[numberOfFeatures];
		esgName = new String[numberOfFeatures];
		productESGName.append("emailProduct-forward");

		esgFileName[0] = "core.mxe";
		esgFileName[1] = "forward.mxe";
		

		esgName[0] = "core";
		esgName[1] = "forward";
		
	}

}
