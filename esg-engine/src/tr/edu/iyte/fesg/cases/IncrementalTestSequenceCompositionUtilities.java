package tr.edu.iyte.fesg.cases;

import java.util.Scanner;
import java.util.Set;

import tr.edu.iyte.esg.eventsequence.EventSequence;
import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.fesg.cases.resultrecordingutilities.CompositionTimeMeasurementWriter;
import tr.edu.iyte.fesg.cases.resultrecordingutilities.IncrementalTestSequenceCompositionTimeMeasurer;
import tr.edu.iyte.fesg.model.FeaturedESG;
import tr.edu.iyte.fesg.model.FeaturedESGComposer;
import tr.edu.iyte.fesg.testsequencecomposition.ESGSequenceMap;
import tr.edu.iyte.fesg.testsequencecomposition.FESGEventSequenceUtilities;
import tr.edu.iyte.fesg.testsequencecomposition.TestSequenceCompositionUtilities;
import tr.edu.iyte.fesg.testsequencecomposition.incremental.IncrementalTestSequenceComposer;

public abstract class IncrementalTestSequenceCompositionUtilities extends TestCoverageAnalysingUtilities {

	protected static int operationID;
	protected static int productNameInput;
	private static String[] newFeatureESGName;

	public static Set<EventSequence> composeSequencesIncrementally(FeaturedESG featuredESG, Set<ESG> newFeatureESGSet) {
//System.out.println("FeaturedESG " + featuredESG.getName());
		Set<EventSequence> cesSet = featuredESG.getCesSet();
		// featuredESG.getFeatureESGSet().addAll(newFeatureESGSet); //!!!
		ESGSequenceMap esgSequenceMapOfNewFeatures = new ESGSequenceMap();

		esgSequenceMapOfNewFeatures.generateESGSequenceMap1_NewFeatureESGSet(featuredESG, newFeatureESGSet,
				coverageLenght);
		featuredESG.getFeatureESGSet().addAll(newFeatureESGSet); // !!!
		Set<String> startVertexEventNames = TestSequenceCompositionUtilities.getNextVertexEventNames(featuredESG,
				featuredESG.getCoreESGName(), "[");
		Set<String> finishVertexEventNames = TestSequenceCompositionUtilities.getPreviousVertexEventNames(featuredESG,
				featuredESG.getCoreESGName(), "]");
		FESGEventSequenceUtilities.esgTestSequenceMapPrinter(esgSequenceMapOfNewFeatures.getESGSequenceMap(), "Increment");

		IncrementalTestSequenceComposer incrementalTestSequenceComposer = new IncrementalTestSequenceComposer(cesSet,
				esgSequenceMapOfNewFeatures.getESGSequenceMap(), startVertexEventNames, finishVertexEventNames,
				coverageLenght);

		double startTime2 = System.nanoTime();
		incrementalTestSequenceComposer.compose();
		Set<EventSequence> newCESSet = incrementalTestSequenceComposer.getCompleteSequences();
		double stopTime2 = System.nanoTime();
		double timeElapsed2 = stopTime2 - startTime2;
		System.out.println(
				"Execution time of incremental test composition in miliseconds  : " + timeElapsed2 / (double) 1000000);
		IncrementalTestSequenceCompositionTimeMeasurer incrementalTestSequenceCompositionTimeMeasurer = new IncrementalTestSequenceCompositionTimeMeasurer();
		IncrementalTestSequenceCompositionTimeMeasurer.setIncrementalTestSequenceCompositionTime(timeElapsed2);
		double transformationTime = IncrementalTestSequenceCompositionTimeMeasurer.getFetauredESGTransformationTime();
		double testSequenceGenerationTime = IncrementalTestSequenceCompositionTimeMeasurer
				.getFeatureSetTestSequenceGenerationTime();
		double compositionTime = IncrementalTestSequenceCompositionTimeMeasurer
				.getIncrementalTestSequenceCompositionTime();
		double timeElapsed = transformationTime + testSequenceGenerationTime + compositionTime;
		IncrementalTestSequenceCompositionTimeMeasurer.setTotalIncrementalTestSequenceCompositionTime(timeElapsed);
		System.out.println("Total execution time of incremental test composition in miliseconds  : "
				+ IncrementalTestSequenceCompositionTimeMeasurer.getTotalIncrementalTestSequenceCompositionTime()
				+ "\n\n");

		String approach = "";
		if (incrementalBasisApproachID == 0) {
			approach = "INCSM";
		} else {
			approach = "INC";
		}

		CompositionTimeMeasurementWriter.writeTimeMeasurement(esgFolderName.toString(), featuredESG.getName(), approach,
				coverageLenght, incrementalTestSequenceCompositionTimeMeasurer);

		newCESSet.addAll(cesSet);
		featuredESG.setCesSet(newCESSet);

		return newCESSet;
	}

	public static Set<EventSequence> composeSequencesOfFeaturedESGWithNewFeatures(FeaturedESG featuredESG,
			String... newFeatureESGNameArray) {
		newFeatureESGName = newFeatureESGNameArray;
		String newProductESGName = getProductESGName();
		setProductESGName(newProductESGName);
		featuredESG.setName(newProductESGName);
		Set<EventSequence> cesSet = null;
		cesSet = composeSequences(featuredESG);
		return cesSet;
	}

	private static Set<EventSequence> composeSequences(FeaturedESG featuredESG) {

		/*
		 * Full test sequence composition of Featured ESG is done. Complete Event
		 * Sequence set of Featured ESG is assigned.
		 */
		Set<EventSequence> cesSet = composeSequencesFully(featuredESG);
		featuredESG.setCesSet(cesSet);

		/*
		 * New Feature ESG Set is built
		 */
		Set<ESG> newFeatureESGSet = buildNewFeatureESGSet(newFeatureESGName);
		// newFeatureESGSet.forEach(e->System.out.println(e.getName()));
		IncrementalTestSequenceComposer incrementalTestSequenceComposer = new IncrementalTestSequenceComposer();

		if (operationID == 1) {
			/*
			 * A Featured ESG is extended with new Features.
			 */
			incrementalTestSequenceComposer.extendFeaturedESGWithNewFeaturesIncrementally(featuredESG, newFeatureESGSet,
					coverageLenght);
		} else {
			/*
			 * A new Featured ESG is built with new Features.
			 */
			featuredESG = incrementalTestSequenceComposer.buildNewFeaturedESGWithNewFeaturesIncrementally(featuredESG,
					newFeatureESGSet, coverageLenght);
		}

		return featuredESG.getCesSet();
	}

	/*
	 * DO NOT DELETE THIS METHOD, IT IS COMMENTED TO REMOVE WARNING private static
	 * Set<EventSequence>
	 * composeSequencesIncrementallyWithTimeMeasurement(FeaturedESG featuredESG) {
	 * 
	 * 
	 * Set<EventSequence> cesSet = composeSequencesFully(featuredESG);
	 * featuredESG.setCesSet(cesSet); Set<ESG> newFeatureESGSet =
	 * buildNewFeatureESGSet(newFeatureESGName);
	 * 
	 * return composeSequencesIncrementally(featuredESG,newFeatureESGSet); }
	 */
	public static String getProductESGName() {

		if (productNameInput == 1) {
			System.out.print("Enter the new product name: ");
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			String newProductName = scanner.nextLine();
			return newProductName;
		} else {
			return buildNewProductESGName();
		}
	}

	private static String buildNewProductESGName() {
		String newProductName = productESGName.toString();

		for (String featureName : newFeatureESGName) {
			newProductName += featureName.substring(0, 1).toUpperCase() + featureName.substring(1);
		}

		return newProductName;
	}

	public static void setProductESGName(String newProductName) {
		productESGName.setLength(0);
		productESGName.append(newProductName);
	}

	public static Set<ESG> buildNewFeatureESGSet(String... newFeatureESGNameArray) {
		String[] featureESGFileName = new String[newFeatureESGNameArray.length];
		for (int i = 0; i < newFeatureESGNameArray.length; i++) {
			featureESGFileName[i] = newFeatureESGNameArray[i] + ".mxe";
		}
		Set<ESG> newFeatureESGSet = FeaturedESGComposer.buildNewFeatureESGSetFromMXEFile(esgFolderName.toString(),
				featureESGFileName, newFeatureESGNameArray);
		return newFeatureESGSet;
	}
}
