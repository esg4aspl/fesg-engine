package tr.edu.iyte.fesg.cases;

import tr.edu.iyte.esg.conversion.dot.ESGToDOTFileConverter;
import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.fesg.conversion.FESGToDOTFileConverter;
import tr.edu.iyte.fesg.model.FeaturedESG;


public abstract class DotFileCreationUtilities extends CaseStudyFilePathCreationUtilities {
	
	protected static StringBuilder dotFilePath;
	protected static StringBuilder dotFileName;
	protected static String dotFileExtension = ".dot";
	
	private static void createDotFileObjects(String fileName) {
		dotFilePath = new StringBuilder();
		dotFileName = new StringBuilder();
		dotFileName.append(fileName);
		dotFilePath.append(dotFolderName + "/" + dotFileName + dotFileExtension);
		System.out.println(dotFilePath);
	}
	
	protected static void buildDotFile(FeaturedESG featuredESG) {
		createDotFileObjects(featuredESG.getName());
		FESGToDOTFileConverter.buildDOTFileFromFeaturedESG(featuredESG, dotFilePath.toString());
	}
	
	protected static void buildDotFile(ESG ESG) {
		createDotFileObjects(ESG.getName());
		ESGToDOTFileConverter.buildDOTFileFromESG(ESG, dotFilePath.toString());
	}

}
