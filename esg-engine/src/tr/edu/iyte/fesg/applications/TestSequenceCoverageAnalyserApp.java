package tr.edu.iyte.fesg.applications;

import java.io.IOException;


import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import tr.edu.iyte.esg.coverageanalysis.*;

public class TestSequenceCoverageAnalyserApp {
	
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		
		int coverageLength = 3;
		String ESGFilePath = "files/MXEFiles/BankAccountPL/bankAccountProduct-cancellable.mxe";
		String testCaseFilePath = "files/MXEFiles/BankAccountPL/Results_SingleModel/TestSequences/bankAccountProduct-cancellable_length" + coverageLength + ".txt";	
		String coverageAnalysisFilePath = "files/coverageAnalysis_bankAccountProduct-cancellable" + "_length" + coverageLength + ".txt";
		
		TestSequenceCoverageAnalyser.analyseCoverageFromFile(coverageLength, ESGFilePath, testCaseFilePath, coverageAnalysisFilePath );

	}

}
