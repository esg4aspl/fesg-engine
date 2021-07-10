package tr.edu.iyte.fesg.cases.email.allproducts;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import tr.edu.iyte.esg.conversion.mxe.MXEFiletoESGConverter;
import tr.edu.iyte.esg.model.ESG;

import tr.edu.iyte.fesg.cases.CaseStudyFilePathCreationUtilities;
import tr.edu.iyte.fesg.cases.TestCoverageAnalysingUtilities;

public class FeatureESGReporter extends TestCoverageAnalysingUtilities {

	private static String filePath = "files/Cases/EmailPL/";

	public static void main(String[] args) {

		String fatureESGFileName = "sign";

		ESG ESG = null;
		try {
			ESG = MXEFiletoESGConverter.parseMXEFileForESGSimpleCreation(
					filePath + fatureESGFileName + CaseStudyFilePathCreationUtilities.mxeFileExtension);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(fatureESGFileName);
		System.out.println("Number of vertices:" + ESG.getVertexList().size());
		System.out.println("Number of real vertices:" + ESG.getRealVertexList().size());
		System.out.println("Number of edges:" + ESG.getEdgeList().size());
		System.out.println("Number of real edges:" + ESG.getRealEdgeList().size());

	}

}
