package tr.edu.iyte.fesg.cases.resultrecordingutilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class FullProductESGReductionTimeMeasurementWriter {

	public static void writeTimeMeasurement(double time, String SPLFolderName, String productType, String fullESGName,
			String guardConditionFeatureName) {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		
		String timeMeasurementFile = SPLFolderName + "TimeMeasurement/FullProductReductionTimeMeasurement/" + productType + "-"
				+ fullESGName + "-" + guardConditionFeatureName + ".csv";

		BufferedWriter writer = null;
		try {
			File file = new File(timeMeasurementFile);
			writer = new BufferedWriter(new FileWriter(file, true));
			if (file.length() > 0) {
				writer.append(df.format(time) + "\n");
			} else {
				writer.write("Reduction Time(ms)\n");
				writer.append(df.format(time) + "\n");
		
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
