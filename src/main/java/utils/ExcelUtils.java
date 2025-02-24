package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.File;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import net.datafaker.Faker;

public class ExcelUtils {
	
	public static List<LinkedHashMap<String,String>> getExcelDataAsListofMap(String excelFile,String sheetName) {
		
		List<LinkedHashMap<String,String>>excelDataAsList=new ArrayList<>();
		LinkedHashMap<String, String>excelMapData;
		
		try {
			Workbook workbook=WorkbookFactory.create(new File("./src/test/resources/TestData/"+excelFile));
			
			Sheet sheet = workbook.getSheet(sheetName);
			List<String>allExcelHeaders=new ArrayList<>();
			
			int totalNoRows=sheet.getPhysicalNumberOfRows();
			DataFormatter dateFormatter=new DataFormatter(); 
			
			for(int i=0; i< totalNoRows;i++) {
				
				excelMapData=new LinkedHashMap<String,String>();
			//retrieve excel headers present in first row and add the headers to a list
				if(i==0) {
					
					int totalCols= sheet.getRow(i).getPhysicalNumberOfCells();
					for(int j=0;j<totalCols;j++) {
						String headerName=sheet.getRow(i).getCell(j).getStringCellValue();
						allExcelHeaders.add(headerName);
					}
					
					
				}
				else {
					
					int totalCols= sheet.getRow(i).getPhysicalNumberOfCells();
					for(int j=0;j<totalCols;j++) {
						String cellValue=dateFormatter.formatCellValue(sheet.getRow(i).getCell(j));
						/*
						 * int size = 0; if(cellValue.contains("RandomNumber")) {
						 * if(cellValue.contains("_")) { size=Integer.parseInt(cellValue.split("_")[1]);
						 * }
						 * 
						 * cellValue= new Faker().number().digits(size);
						 * 
						 * }
						 */
						
						excelMapData.put(allExcelHeaders.get(j), cellValue)	;
						
					}
					
					
					excelDataAsList.add(excelMapData);
					
				} //end else 
				
				
			
				
			} //end for loop
				
			
			
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		excelDataAsList.stream().filter(item -> item.get("enabled").equalsIgnoreCase("y")).collect(Collectors.toList());
		return excelDataAsList;
	}


}
