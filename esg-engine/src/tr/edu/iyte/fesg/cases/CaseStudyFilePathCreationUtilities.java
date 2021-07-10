package tr.edu.iyte.fesg.cases;

import tr.edu.iyte.fesg.model.FeaturedESG;
import tr.edu.iyte.fesg.model.FeaturedESGComposer;

public abstract class CaseStudyFilePathCreationUtilities {
	
	protected static StringBuilder esgFolderName;
	protected static String[] esgFileName;
	protected static String[] esgName;
	
	protected static String testCaseFileName;
	protected static String existingProduct_testCaseFileName;
	
	protected static StringBuilder fullComposition_testCaseFolderName;
	protected static StringBuilder fullComposition_testCaseFilePath;
	
	protected static StringBuilder incrementalComposition_testCaseFolderName;
	protected static StringBuilder incrementalComposition_exisitingProductTestCaseFolderName;
	protected static StringBuilder incrementalComposition_testCaseFilePath;
	protected static StringBuilder incrementalComposition_exisitingProductTestCaseFilePath;
	protected static StringBuilder incrementalComposition_testCaseMutationSequencesPath;
	
	protected static StringBuilder testGeneration_resultsFolderName;
	protected static StringBuilder testGeneration_testCaseFilePath;

	protected static StringBuilder fullComposition_coverageAnalysisFolderName;
	protected static StringBuilder fullComposition_coverageAnalysisFilePath;
	
	protected static StringBuilder incrementalComposition_coverageAnalysisFolderName;
	protected static StringBuilder incrementalComposition_coverageAnalysisFilePath;
	
	protected static StringBuilder fullComposition_resultsFolderName;
	protected static StringBuilder incrementalComposition_resultsFolderName;
	
	protected static StringBuilder productESGName;
	protected static StringBuilder existingProductESGName;
	protected static StringBuilder productESGFilePath;
	
	protected static StringBuilder dotFolderName;
	
	protected static int numberOfTransformations;
	protected static int coverageLenght;
	
	protected static String mxeFileExtension = ".mxe";
	protected static String txtFileExtension = ".txt";
	protected static String lengthTag = "_length";
	protected static String coverageAnalysisTag = "/coverageAnalysis_";
	
	protected static int coreESGIndex = 0;
	protected static int productID;
	
	protected static int incrementalBasisApproachID;
	
	protected static void createCaseStudyFilePathObjects() {
		esgFolderName = new StringBuilder();
		
		fullComposition_testCaseFolderName = new StringBuilder();
		fullComposition_testCaseFilePath = new StringBuilder();
		fullComposition_coverageAnalysisFolderName = new StringBuilder();
		fullComposition_coverageAnalysisFilePath = new StringBuilder();
		fullComposition_resultsFolderName = new StringBuilder();
		
		incrementalComposition_testCaseFolderName = new StringBuilder();
		incrementalComposition_testCaseFilePath = new StringBuilder();
		incrementalComposition_testCaseMutationSequencesPath = new StringBuilder();
		incrementalComposition_coverageAnalysisFolderName = new StringBuilder();
		incrementalComposition_coverageAnalysisFilePath = new StringBuilder();
		incrementalComposition_resultsFolderName = new StringBuilder();
		incrementalComposition_exisitingProductTestCaseFolderName = new StringBuilder();
		incrementalComposition_exisitingProductTestCaseFilePath= new StringBuilder();
		
		testGeneration_resultsFolderName = new StringBuilder();
		testGeneration_testCaseFilePath = new StringBuilder();

		productESGFilePath = new StringBuilder();
		productESGName = new StringBuilder();
		existingProductESGName  = new StringBuilder(); 
		dotFolderName = new StringBuilder();
		numberOfTransformations = coverageLenght - 2;
	}
	
	
	protected static void buildResultFilePath() {
		
		productESGFilePath.append(esgFolderName + "/" + productESGName + mxeFileExtension);
		
		fullComposition_resultsFolderName.append(esgFolderName + "/Results_FullTestSequenceComposition/");
		fullComposition_testCaseFolderName.append(fullComposition_resultsFolderName + "/TestSequences/");
		fullComposition_coverageAnalysisFolderName.append(fullComposition_resultsFolderName + "/CoverageAnalysis/");
		
		incrementalComposition_resultsFolderName.append(esgFolderName + "/Results_IncrementalTestSequenceComposition/");
		incrementalComposition_testCaseFolderName.append(incrementalComposition_resultsFolderName + "/TestSequences/");
		incrementalComposition_coverageAnalysisFolderName.append(incrementalComposition_resultsFolderName + "/CoverageAnalysis/");
		incrementalComposition_exisitingProductTestCaseFolderName.append(incrementalComposition_resultsFolderName + "/ExistingProductTestSequences/");
		
		testCaseFileName = productESGName + lengthTag + coverageLenght + txtFileExtension;
		existingProduct_testCaseFileName = existingProductESGName + lengthTag + coverageLenght + txtFileExtension;
		
		testGeneration_resultsFolderName.append(esgFolderName + "/Results_SingleModel/TestSequences");
		testGeneration_testCaseFilePath.append(testGeneration_resultsFolderName + "/" + testCaseFileName);
		
		
		fullComposition_testCaseFilePath.append(fullComposition_testCaseFolderName + "/" + testCaseFileName);
		fullComposition_coverageAnalysisFilePath.append(fullComposition_coverageAnalysisFolderName + coverageAnalysisTag + productESGName + lengthTag + coverageLenght + txtFileExtension);
		
		String incrementalTestCaseFileName = productESGName.toString();
		
		incrementalComposition_testCaseMutationSequencesPath.append(esgFolderName + "/1RegFiles/sequences/INC/" + productESGName.toString() + "_rrg-1seq-tb_length" + coverageLenght);
		if(incrementalBasisApproachID == 0) {
			//incrementalTestCaseFileName += "-INCSM" + lengthTag + coverageLenght + txtFileExtension;
			incrementalTestCaseFileName += "-INC" + lengthTag + coverageLenght + txtFileExtension;
			incrementalComposition_testCaseMutationSequencesPath.append("-INC");
		}else if(incrementalBasisApproachID == 1) {
			//incrementalTestCaseFileName += "-INCFULL" + lengthTag + coverageLenght + txtFileExtension;
			incrementalTestCaseFileName += "-INC" + lengthTag + coverageLenght + txtFileExtension;
			incrementalComposition_testCaseMutationSequencesPath.append("-INC");
		}
		
		incrementalComposition_testCaseFilePath.append(incrementalComposition_testCaseFolderName + "/" + incrementalTestCaseFileName);
		incrementalComposition_exisitingProductTestCaseFilePath.append(incrementalComposition_exisitingProductTestCaseFolderName + existingProduct_testCaseFileName);
		
		incrementalComposition_testCaseMutationSequencesPath.append("_sequences" + txtFileExtension);
		incrementalComposition_coverageAnalysisFilePath.append(incrementalComposition_coverageAnalysisFolderName + coverageAnalysisTag + incrementalTestCaseFileName);
		
	}
	
	protected static void avoidRepeat() {
		productESGFilePath.setLength(0);
		testCaseFileName = "";
		
		fullComposition_resultsFolderName.setLength(0);
		fullComposition_testCaseFolderName.setLength(0);
		fullComposition_coverageAnalysisFolderName.setLength(0);
		
		incrementalComposition_resultsFolderName.setLength(0);
		incrementalComposition_testCaseFolderName.setLength(0);
		incrementalComposition_coverageAnalysisFolderName.setLength(0);
		incrementalComposition_testCaseMutationSequencesPath.setLength(0);
		incrementalComposition_exisitingProductTestCaseFolderName.setLength(0);
		incrementalComposition_exisitingProductTestCaseFilePath.setLength(0);
		
		testGeneration_resultsFolderName.setLength(0);
		testGeneration_testCaseFilePath.setLength(0);
		
		fullComposition_testCaseFilePath.setLength(0);
		fullComposition_coverageAnalysisFilePath.setLength(0);
		
		incrementalComposition_testCaseFilePath.setLength(0);
		incrementalComposition_coverageAnalysisFilePath.setLength(0);
	}
		
	protected static FeaturedESG buildFeaturedESG() {
		FeaturedESGComposer featuredESGComposer = new FeaturedESGComposer(productESGName.toString(),esgName[coreESGIndex]);
		FeaturedESG featuredESG = featuredESGComposer.copmposeFeaturedESGFromMXEFile(esgFolderName.toString(), esgFileName, esgName);
		
		return featuredESG;
	}

}
