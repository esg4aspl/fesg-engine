package tr.edu.iyte.fesg.testsequencecomposition;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import tr.edu.iyte.esg.eventsequence.EventSequence;
import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.esg.model.Vertex;
import tr.edu.iyte.esg.testgeneration.TestSuite;
import tr.edu.iyte.esg.testgeneration.TestSuiteGenerator;
import tr.edu.iyte.fesg.cases.resultrecordingutilities.IncrementalTestSequenceCompositionTimeMeasurer;
import tr.edu.iyte.fesg.featuredesgtransformation.FeaturedESGTransformer;
import tr.edu.iyte.fesg.featuredesgtransformation.FeaturedESGTransformerIncremental;
import tr.edu.iyte.fesg.model.FeaturedESG;

public class ESGSequenceMap {

	private Map<String, Set<EventSequence>> esgSequenceMap;

	public ESGSequenceMap() {
		esgSequenceMap = new LinkedHashMap<String, Set<EventSequence>>();
	}

	public Map<String, Set<EventSequence>> getESGSequenceMap() {
		return esgSequenceMap;
	}

	public void generateESGSequenceMapFromFeaturedESG(FeaturedESG featuredESG) {
		TestSuiteGenerator testSuiteGenerator = new TestSuiteGenerator();
		for (ESG ESG : featuredESG.getFeaturedESGSet()) {
			// System.out.println(ESG.getName());
			TestSuite testSuite = testSuiteGenerator.generateTestSuite(0, ESG);
			// System.out.println(testSuite.toString());
			for (EventSequence e : testSuite.getCompleteEventSequences()) {
				// System.out.println("FIRST " + e.toString());
				Iterator<Vertex> itr = e.getEventSequence().iterator();
				while (itr.hasNext()) {
					Vertex v = itr.next();
					String[] esg_ve = TestSequenceCompositionUtilities.splitVertexEventName(v.getEvent().getName());
					if (esg_ve[1].compareTo(TestSequenceCompositionUtilities.USEFULNESS_START) == 0
							|| esg_ve[1].compareTo(TestSequenceCompositionUtilities.USEFULNESS_FINISH) == 0
							|| esg_ve[1].compareTo("[") == 0 || esg_ve[1].compareTo("]") == 0) { // !!!
						itr.remove();
					}
				}
				// System.out.println("SECOND " + e.toString());
			}
			esgSequenceMap.put(ESG.getName(), testSuite.getCompleteEventSequences());
		}
	}

	public void generateESGSequenceMapFromFeaturedESG(FeaturedESG featuredESG, int coverageLength) {
		TestSuiteGenerator testSuiteGenerator = new TestSuiteGenerator();
		List<ESG> esgList = new LinkedList<ESG>();
		esgList.addAll(featuredESG.getFeaturedESGSet());
		FeaturedESGTransformer t = new FeaturedESGTransformer(esgList);
		int numberOfTransformations = coverageLength - 2;
		t.transform(numberOfTransformations);
		List<String> deletenames = new LinkedList<String>();
		deletenames.add(TestSequenceCompositionUtilities.USEFULNESS_START);
		deletenames.add(TestSequenceCompositionUtilities.USEFULNESS_FINISH);
		String sname = TestSequenceCompositionUtilities.VERTEXEVENTNAME_DELIMS.charAt(0)
				+ featuredESG.getCoreESG().getName() + TestSequenceCompositionUtilities.VERTEXEVENTNAME_DELIMS.charAt(1)
				+ "[" + TestSequenceCompositionUtilities.VERTEXEVENTNAME_DELIMS.charAt(2);
//System.out.println("sname " + sname);
		String fname = TestSequenceCompositionUtilities.VERTEXEVENTNAME_DELIMS.charAt(0)
				+ featuredESG.getCoreESG().getName() + TestSequenceCompositionUtilities.VERTEXEVENTNAME_DELIMS.charAt(1)
				+ "]" + TestSequenceCompositionUtilities.VERTEXEVENTNAME_DELIMS.charAt(2);
//System.out.println("fname " + fname);
		deletenames.add(sname);
		deletenames.add(fname);

		Iterator<ESG> sesgitr = t.getkESGList().iterator();
		Iterator<String> sesgnameitr = t.getESGNameList().iterator();
		while (sesgitr.hasNext()) {
			ESG sesg = sesgitr.next();
			String sesgname = sesgnameitr.next();
//System.out.println("*** "+sesgname);
			TestSuite testSuite = testSuiteGenerator.generateTestSuiteFromSequenceESG(sesg, coverageLength,
					deletenames);
//System.out.println(testSuite.toString());
			esgSequenceMap.put(sesgname, testSuite.getCompleteEventSequences());
		}
	}

	/*
	 * n is coverage length
	 */
	public void generateESGSequenceMap1_NewFeatureESGSet(FeaturedESG featuredESG, Set<ESG> newFeatureESGSet,
			int coverageLength) {
		TestSuiteGenerator testSuiteGenerator = new TestSuiteGenerator();
		List<ESG> esgList = new LinkedList<ESG>();
		esgList.addAll(newFeatureESGSet);
		List<ESG> baseesgeList = new LinkedList<ESG>();
		baseesgeList.addAll(featuredESG.getFeaturedESGSet());
		FeaturedESGTransformerIncremental t = new FeaturedESGTransformerIncremental(esgList, baseesgeList);
		int numberOfTransformations = coverageLength - 2;
		double startTime1 = System.nanoTime();
		t.transform(numberOfTransformations);
		double stopTime1 = System.nanoTime();
		double timeElapsed1 = stopTime1 - startTime1;
		IncrementalTestSequenceCompositionTimeMeasurer.setFetauredESGTransformationTime(timeElapsed1);
		System.out.println("Execution time of incremental Featured ESG Transformation  in miliseconds  : "
				+ timeElapsed1 / (double) 1000000);

		List<String> deletenames = new LinkedList<String>();
		deletenames.add(TestSequenceCompositionUtilities.USEFULNESS_START);
		deletenames.add(TestSequenceCompositionUtilities.USEFULNESS_FINISH);
		String sname = TestSequenceCompositionUtilities.VERTEXEVENTNAME_DELIMS.charAt(0)
				+ featuredESG.getCoreESG().getName() + TestSequenceCompositionUtilities.VERTEXEVENTNAME_DELIMS.charAt(1)
				+ "[" + TestSequenceCompositionUtilities.VERTEXEVENTNAME_DELIMS.charAt(2);
//System.out.println("sname " + sname);
		String fname = TestSequenceCompositionUtilities.VERTEXEVENTNAME_DELIMS.charAt(0)
				+ featuredESG.getCoreESG().getName() + TestSequenceCompositionUtilities.VERTEXEVENTNAME_DELIMS.charAt(1)
				+ "]" + TestSequenceCompositionUtilities.VERTEXEVENTNAME_DELIMS.charAt(2);
//System.out.println("fname " + fname);
		deletenames.add(sname);
		deletenames.add(fname);

		Iterator<ESG> sesgitr = t.getkESGList().iterator();
		Iterator<String> sesgnameitr = t.getESGNameList().iterator();
		double startTime2 = System.nanoTime();
		while (sesgitr.hasNext()) {
			ESG sesg = sesgitr.next();
			String sesgname = sesgnameitr.next();

//System.out.println("*** "+sesgname);
			TestSuite testSuite = testSuiteGenerator.generateTestSuiteFromSequenceESG(sesg, coverageLength,
					deletenames);
//System.out.println(testSuite.toString());
			esgSequenceMap.put(sesgname, testSuite.getCompleteEventSequences());
		}
		double stopTime2 = System.nanoTime();
		double timeElapsed2 = stopTime2 - startTime2;
		IncrementalTestSequenceCompositionTimeMeasurer.setFeatureSetTestSequenceGenerationTime(timeElapsed2);
		System.out.println("Execution time of  Feature ESG set test sequence generation in miliseconds  : "
				+ timeElapsed2 / (double) 1000000);
	}

	public void generateESGSequenceMap1_NewFeatureESGSet(Set<ESG> newFeatureESGSet) {
		TestSuiteGenerator testSuiteGenerator = new TestSuiteGenerator();
		for (ESG ESG : newFeatureESGSet) {
			// System.out.println("NOW " + ESG.getName());
			TestSuite testSuite = testSuiteGenerator.generateTestSuite(ESG);
			// System.out.println(testSuite.toString());
			for (EventSequence e : testSuite.getCompleteEventSequences()) {
				// System.out.println("FIRST " + e.toString());
				Iterator<Vertex> itr = e.getEventSequence().iterator();
				while (itr.hasNext()) {
					Vertex v = itr.next();
					String[] esg_ve = TestSequenceCompositionUtilities.splitVertexEventName(v.getEvent().getName());
					if (esg_ve[1].compareTo(TestSequenceCompositionUtilities.USEFULNESS_START) == 0
							|| esg_ve[1].compareTo(TestSequenceCompositionUtilities.USEFULNESS_FINISH) == 0
							|| esg_ve[1].compareTo("[") == 0 || esg_ve[1].compareTo("]") == 0) { // !!!
						itr.remove();
					}
				}
				// System.out.println("SECOND " + e.toString());
			}
			esgSequenceMap.put(ESG.getName(), testSuite.getCompleteEventSequences());
		}
	}

	public String toString() {
		String str = "";

		for (String esgName : esgSequenceMap.keySet()) {
			str += esgName + "\n";

			for (EventSequence ES : esgSequenceMap.get(esgName)) {
				str += ES.toString() + "\n";
			}
			str += "\n";
		}

		return str;
	}

	public static Set<EventSequence> getAllEventSequencesSet(Map<String, Set<EventSequence>> esgSequenceMap) {
		Set<EventSequence> eventSequenceSet = new LinkedHashSet<EventSequence>();
		Iterator<String> esgNameIterator = esgSequenceMap.keySet().iterator();

		while (esgNameIterator.hasNext()) {
			String esgName = esgNameIterator.next();
			eventSequenceSet.addAll(esgSequenceMap.get(esgName));
		}

		return eventSequenceSet;
	}

}
