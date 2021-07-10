package tr.edu.iyte.fesg.cases.elevator;

import java.io.IOException;

import tr.edu.iyte.fesg.cases.DotFileCreationUtilities;
import tr.edu.iyte.fesg.model.FeaturedESG;


public class ElevatorDOTFileBuilderApp extends DotFileCreationUtilities{
	
	/**
	 * 
	 * productID = 1 -> elevatorProduct_baseProduct
	 * productID = 2 -> elevatorProduct_weight
	 * productID = 3 -> elevatorProduct_weightExecutiveFloor
	 * productID = 4 -> elevatorProduct_weightOverloaded
	 * productID = 5 -> elevatorProduct_fullProduct 	 
	 * 
	 * initialize productID to build different products

	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		productID = 1;

		ElevatorCaseStudyUtilities.buildProductModel();

		FeaturedESG featuredESG = buildFeaturedESG();
		buildDotFile(featuredESG);

	}


}
