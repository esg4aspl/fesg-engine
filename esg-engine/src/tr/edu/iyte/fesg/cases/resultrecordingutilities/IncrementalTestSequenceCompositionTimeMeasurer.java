package tr.edu.iyte.fesg.cases.resultrecordingutilities;

public class IncrementalTestSequenceCompositionTimeMeasurer {
	
	private static double fetauredESGTransformationTime;
	private static double featureSetTestSequenceGenerationTime;
	private static double incrementalTestSequenceCompositionTime;
	private static double totalIncrementalTestSequenceCompositionTime;
	
	
	public static double getFetauredESGTransformationTime() {
		return fetauredESGTransformationTime;
	}
	
	public static void setFetauredESGTransformationTime(double fetauredESGTransformationTime) {
		IncrementalTestSequenceCompositionTimeMeasurer.fetauredESGTransformationTime = measurement(fetauredESGTransformationTime);
	}
	
	public static double getFeatureSetTestSequenceGenerationTime() {
		return featureSetTestSequenceGenerationTime;
	}
	
	public static void setFeatureSetTestSequenceGenerationTime(double featureSetTestSequenceGenerationTime) {
		IncrementalTestSequenceCompositionTimeMeasurer.featureSetTestSequenceGenerationTime = measurement(featureSetTestSequenceGenerationTime);
	}
	
	public static double getIncrementalTestSequenceCompositionTime() {
		return incrementalTestSequenceCompositionTime;
	}
	
	public static void setIncrementalTestSequenceCompositionTime(double incrementalTestSequenceCompositionTime) {
		IncrementalTestSequenceCompositionTimeMeasurer.incrementalTestSequenceCompositionTime = measurement(incrementalTestSequenceCompositionTime);
	}
	
	public static double getTotalIncrementalTestSequenceCompositionTime() {
		return totalIncrementalTestSequenceCompositionTime;
	}

	public static void setTotalIncrementalTestSequenceCompositionTime(double totalIncrementalTestSequenceCompositionTime) {
		IncrementalTestSequenceCompositionTimeMeasurer.totalIncrementalTestSequenceCompositionTime = totalIncrementalTestSequenceCompositionTime;
	}
	
	public static double measurement(double time) {
		return time / (double) 1000000;
	}




	
	

}
