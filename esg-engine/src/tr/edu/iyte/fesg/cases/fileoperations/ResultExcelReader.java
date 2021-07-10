package tr.edu.iyte.fesg.cases.fileoperations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.LinkedHashMap;
import java.util.Map;


import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ResultExcelReader {
	
	
	private static Map<String,AbstractMap.SimpleEntry<Integer, Integer>> productNameScenarioIDProductIDMap;
	
	public static void main(String[] args) {
		
	}
	public static XSSFSheet getExcelSheet(String filePath, String sheetName){
		 File file = new File(filePath);
		 FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XSSFWorkbook wb = null;
		 try {
			wb = new XSSFWorkbook(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 XSSFSheet sheet = wb.getSheet(sheetName);
		return sheet;
	}
	
	public static Map<String,AbstractMap.SimpleEntry<Integer, Integer>> readResultExcel(String filePath){
		productNameScenarioIDProductIDMap = new LinkedHashMap<>();
		XSSFSheet sheet = getExcelSheet(filePath, "evaluated scenarios");
		System.out.println(sheet.getSheetName());
		return productNameScenarioIDProductIDMap;
	}
	
	

}
