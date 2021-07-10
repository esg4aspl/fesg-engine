package tr.edu.iyte.fesg.applications;

import java.io.File;

import tr.edu.iyte.fesg.cases.resultrecordingutilities.TestSequenceModifier;

public class TestSequenceModifierApp {
	
	public static void main(String[] args) {
		
		File folder = new File("files/MXEFiles/StudentAttendanceSystemPL");
		TestSequenceModifier.listFilesForFolder(folder);
		
	}

}
