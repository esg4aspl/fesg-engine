package tr.edu.iyte.fesg.cases.elevator;

import java.io.IOException;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import tr.edu.iyte.esg.eventsequence.EventSequence;
import tr.edu.iyte.fesg.cases.IncrementalTestSequenceCompositionUtilities;
import tr.edu.iyte.fesg.model.FeaturedESG;

public class ElevatorIncrementalTestSequenceCompositionApp extends IncrementalTestSequenceCompositionUtilities{
	
	/**
	 * 
	 * productID = 1 -> elevatorProduct_baseProduct
	 * productID = 2 -> elevatorProduct_weight
	 * productID = 3 -> elevatorProduct_weightExecutiveFloor
	 * productID = 4 -> elevatorProduct_weightOverloaded
	 * productID = 5 -> elevatorProduct_fullProduct 
	 * 
	 * initialize productID to build different products
	 * 
	 * operationID = 1 -> to extend a Featured ESG with new features 
	 * operationID = 2 -> to build a new Featured ESG with an existing Featured ESG and new features
	 * 
	 * initialize productNameInput to 1 if you want to enter a new name to the new product
	 * otherwise the new product's name will be created automatically.
	 * 
	 * You can enter as many features as you want
	 */
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException{
		productID = 1;
		coverageLenght = 5;
		operationID = 2;
		productNameInput = 0;
		
		ElevatorCaseStudyUtilities.buildProductModel();

		FeaturedESG featuredESG = buildFeaturedESG();
		
		Set<EventSequence> cesSet = composeSequencesOfFeaturedESGWithNewFeatures(featuredESG,"executiveFloor");
		System.out.println("1st Feature added");
		System.out.println(cesSet.toString());
		featuredESG.setName("elevatorProduct_executiveFloor");
		 
		
		writeIncrementalTestCompositionResultsToFile(cesSet);
		analyseIncrementalTestCompositionResultsCoverage(cesSet);
	}
		

}



