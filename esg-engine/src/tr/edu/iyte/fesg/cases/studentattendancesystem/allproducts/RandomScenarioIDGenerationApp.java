package tr.edu.iyte.fesg.cases.studentattendancesystem.allproducts;

import java.util.Random;

public class RandomScenarioIDGenerationApp {

	public static void main(String[] args) {
		
		Random random = new Random();
		
		for(int i = 1; i < 6; i++) {
			
			int id = random.nextInt(10236);
			System.out.println(id);
		}


	}

}
