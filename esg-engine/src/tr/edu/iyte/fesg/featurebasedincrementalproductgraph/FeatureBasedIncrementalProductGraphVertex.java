package tr.edu.iyte.fesg.featurebasedincrementalproductgraph;

import java.util.Set;

import tr.edu.iyte.esg.eventsequence.EventSequence;
import tr.edu.iyte.esg.eventsequence.EventSequenceUtilities;
import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.esg.testgeneration.TestSuiteGenerator;
import tr.edu.iyte.fesg.cases.FullESGValidatingUtilities;
import tr.edu.iyte.fesg.cases.IncrementalTestSequenceCompositionUtilities;
import tr.edu.iyte.fesg.cases.TestSequenceCompositionUtilties;
import tr.edu.iyte.fesg.model.FeaturedESG;

public class FeatureBasedIncrementalProductGraphVertex {
	
	private boolean isProduct;
	private boolean isStart;
	private FeaturedESG featuredESG;
	
	public FeatureBasedIncrementalProductGraphVertex() {
		
	}
	
	public FeatureBasedIncrementalProductGraphVertex(FeaturedESG featuredESG,boolean isProduct,boolean isStart) {
		setFeaturedESG(featuredESG);
		setAsProduct(isProduct);
		setAsStart(isStart);
	}
	
	public boolean isProduct() {
		return isProduct;
	}
	public void setAsProduct(boolean isProduct) {
		this.isProduct = isProduct;
	}
	public boolean isStart() {
		return isStart;
	}

	public void setAsStart(boolean isStart) {
		this.isStart = isStart;
	}
	public FeaturedESG getFeaturedESG() {
		return featuredESG;
	}
	public void setFeaturedESG(FeaturedESG featuredESG) {
		this.featuredESG = featuredESG;
	}
	
	public void incrementalTestSequenceComposition(Set<ESG> featureESGSet) {
		/*System.out.print("AFTER Feature Addition ");
		featuredESG.getFeaturedESGSet().forEach(e->System.out.print(e.getName() + " "));
		System.out.println();*/
		IncrementalTestSequenceCompositionUtilities.composeSequencesIncrementally(featuredESG, featureESGSet);
		Set<EventSequence> incrementalCESSet = featuredESG.getCesSet();
		EventSequenceUtilities.esgEventSequenceSetPrinter(incrementalCESSet, "The sequences that are generated by Incremental Test Sequence Composition");
	
		//TestCoverageAnalysingUtilities.writeIncrementalTestCompositionResultsToFile(incrementalCESSet,featuredESG.getName());
		//TestCoverageAnalysingUtilities.analyseIncrementalTestCompositionResultsCoverage(incrementalCESSet);
		
	}
	
	public void fullTestSequenceComposition() {
		Set<EventSequence> fullCESSet = TestSequenceCompositionUtilties.composeSequencesFullyWithTimeMeasurement(featuredESG);
		EventSequenceUtilities.esgEventSequenceSetPrinter(fullCESSet, "The sequences that are generated by Full Test Sequence Composition");
	}
	
	public void fullESGGeneration() {
		ESG fullESG = FullESGValidatingUtilities.validateFullESG(featuredESG);
		TestSuiteGenerator testSuiteGenerator = new TestSuiteGenerator();
		double startTime = System.nanoTime();
		testSuiteGenerator.generateTestSuite(fullESG);
		double stopTime = System.nanoTime();
		double timeElapsed = stopTime - startTime;
		System.out.println("Test generation time of Full ESG in miliseconds  : " + timeElapsed / (double) 1000000);
	}




}
