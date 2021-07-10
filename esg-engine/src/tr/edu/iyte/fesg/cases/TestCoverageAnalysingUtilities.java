package tr.edu.iyte.fesg.cases;

import java.io.IOException;
import java.util.Set;

import tr.edu.iyte.esg.conversion.json.*;
import tr.edu.iyte.esg.coverageanalysis.TestSequenceCoverageAnalyser;
import tr.edu.iyte.esg.eventsequence.EventSequence;
import tr.edu.iyte.esg.eventsequence.EventSequenceUtilties;
import tr.edu.iyte.esg.model.ESG;



public abstract class TestCoverageAnalysingUtilities extends TestSequenceCompositionUtilties {
			
	public static void writeFullTestCompositionResultsToFile(Set<EventSequence> cesSet) {
		buildResultFilePath();
		
		try {
			EventSequenceUtilties.esgEventSequenceSetFileWriter(cesSet, fullComposition_testCaseFilePath.toString());
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	public static void writeIncrementalTestCompositionResultsToFile(Set<EventSequence> cesSet) {
		buildResultFilePath();
		
		try {
			EventSequenceUtilties.esgEventSequenceSetFileWriter(cesSet, incrementalComposition_testCaseFilePath.toString());
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	
	public static void writeIncrementalTestCompositionResultsToFile(Set<EventSequence> cesSet, String name) {
		avoidRepeat();
		productESGName.setLength(0);
		productESGName.append(name);
		
		buildResultFilePath();
		
		try {
			EventSequenceUtilties.esgEventSequenceSetFileWriter(cesSet, incrementalComposition_testCaseFilePath.toString());
		} catch (IOException e) {

			e.printStackTrace();
		}
	
		try {
			EventSequenceUtilties.esgEventSequenceSetFileWriterForMutationAnalysis(cesSet, incrementalComposition_testCaseMutationSequencesPath.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void writeExistingproductTestCompositionResultsToFile(Set<EventSequence> cesSet) {
		avoidRepeat();
		buildResultFilePath();
		
		try {
			EventSequenceUtilties.esgEventSequenceSetFileWriter(cesSet, incrementalComposition_exisitingProductTestCaseFilePath.toString());
		} catch (IOException e) {

			e.printStackTrace();
		}
	

	}
	
	public static void writeTestSequencesToFile(Set<EventSequence> cesSet) {
		buildResultFilePath();
		
		try {
			EventSequenceUtilties.esgEventSequenceSetFileWriter(cesSet, testGeneration_testCaseFilePath.toString());
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	public static void analyseFullTestCompositionResultsCoverage(Set<EventSequence> cesSet) {
		ESG productESG = TestSequenceCoverageAnalyser.buildESGFromMXEFile(productESGFilePath.toString());
		TestSequenceCoverageAnalyser.analyseCoverage(numberOfTransformations, productESG, cesSet, fullComposition_coverageAnalysisFilePath.toString());
	}
	
	public static void analyseIncrementalTestCompositionResultsCoverage(Set<EventSequence> cesSet) {
		
		String folderPath = esgFolderName + "/allProductsJSONFiles/";
//System.out.println(esgFolderName + "/allProductsJSONFiles/" + productESGName.toString());
		ESG productESG = JSONFileToESGConverter.parseJSONFileForESGCreation(folderPath + productESGName.toString() + ".json");
		TestSequenceCoverageAnalyser.analyseCoverage(coverageLenght, productESG, cesSet, incrementalComposition_coverageAnalysisFilePath.toString());
	}

}
