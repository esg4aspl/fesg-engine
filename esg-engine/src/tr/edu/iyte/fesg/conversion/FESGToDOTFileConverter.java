package tr.edu.iyte.fesg.conversion;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Set;

import tr.edu.iyte.esg.conversion.dot.ESGToDOTFileConverter;
import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.fesg.model.FeaturedESG;

public class FESGToDOTFileConverter {
	
	public static void buildDOTFileFromFeaturedESG(FeaturedESG fesg, String filePathAndName) {
		Writer fileWriter = null;
		try {
			fileWriter = new FileWriter(filePathAndName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter printWriter = new PrintWriter(fileWriter);
		
		printWriter.println("digraph " + ESGToDOTFileConverter.getProperStringForDotFormat(fesg.getName()) + " {");
		printWriter.println("graph [fontname=Arial, fontcolor=blue, fontsize=26];\n" + 
				"node [fixedsize=false,fontsize=26]");
		printWriter.println("rankdir=LR");
		printWriter.println("subgraph cluster {\n" + 
				"\n" + 
				"label = \" "+ ESGToDOTFileConverter.getProperStringForDotFormat(fesg.getName()) + "\";");
		
		for(ESG ESG:fesg.getFeaturedESGSet()) {
			System.out.println(ESG.getName());
			Set<String> esgContent = ESGToDOTFileConverter.getProperESGContentForDOTFormat(ESG);
			printWriter.println("subgraph cluster" + ESG.getID() +" {" );
			printWriter.println("rankdir=LR");
			
			for(String content : esgContent) {
				System.out.println(content);
				printWriter.println(content);
			}
			printWriter.println("}");
			
		}
		
		printWriter.println("}");
		printWriter.println("}");
		
		printWriter.close();
	}
}
