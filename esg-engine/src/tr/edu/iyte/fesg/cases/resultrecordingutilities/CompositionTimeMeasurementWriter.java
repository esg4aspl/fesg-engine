package tr.edu.iyte.fesg.cases.resultrecordingutilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CompositionTimeMeasurementWriter {

	public static void writeTimeMeasurement(String splFolderName, String splFileName, String approach,
			int coverageLength,
			IncrementalTestSequenceCompositionTimeMeasurer incrementalTestSequenceCompositionTimeMeasurer) {

		String timeFilePath = splFolderName + "/TimeMeasurement/" + splFileName + "_length" + coverageLength + "_"
				+ approach + ".csv";
		BufferedWriter writer;

		try {
			File file = new File(timeFilePath);
			writer = new BufferedWriter(new FileWriter(file, true));
			if (file.length() > 0) {
				writer.write(Double
						.toString(IncrementalTestSequenceCompositionTimeMeasurer.getFetauredESGTransformationTime())
						+ ","
						+ Double.toString(IncrementalTestSequenceCompositionTimeMeasurer
								.getFeatureSetTestSequenceGenerationTime())
						+ ","
						+ Double.toString(IncrementalTestSequenceCompositionTimeMeasurer
								.getIncrementalTestSequenceCompositionTime())
						+ "," + Double.toString(IncrementalTestSequenceCompositionTimeMeasurer
								.getTotalIncrementalTestSequenceCompositionTime())
						+ "\n");
				writer.close();
			} else {
				writer.write("Transformation" + "," + "Generation" + "," + "Composition" + "," + "Total" + "\n");
				writer.write(Double
						.toString(IncrementalTestSequenceCompositionTimeMeasurer.getFetauredESGTransformationTime())
						+ ","
						+ Double.toString(IncrementalTestSequenceCompositionTimeMeasurer
								.getFeatureSetTestSequenceGenerationTime())
						+ ","
						+ Double.toString(IncrementalTestSequenceCompositionTimeMeasurer
								.getIncrementalTestSequenceCompositionTime())
						+ "," + Double.toString(IncrementalTestSequenceCompositionTimeMeasurer
								.getTotalIncrementalTestSequenceCompositionTime())
						+ "\n");
				writer.close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void writeTimeMeasurement(String splFolderName, String splFileName, String approach,
			int coverageLength, double time) {

		String timeFilePath = splFolderName + "/TimeMeasurement/" + splFileName + "_length" + coverageLength + "_"
				+ approach + ".csv";
		BufferedWriter writer;

		try {
			File file = new File(timeFilePath);
			writer = new BufferedWriter(new FileWriter(file, true));
			if (file.length() > 0) {
				writer.write("," + time);
				writer.close();
			} else {
				writer.write(Double.toString(time));
				writer.close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
