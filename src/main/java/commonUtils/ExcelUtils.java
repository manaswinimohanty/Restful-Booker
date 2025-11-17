package commonUtils;

import listener.TestListener;
import org.apache.poi.ss.usermodel.*;
import reports.ExtentManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExcelUtils {

    public static List<LinkedHashMap<String, String>> readExcelData(String excelFilePath, String sheetName){
        List<LinkedHashMap<String,String>>excelData=new ArrayList<>();
        try {
            Workbook workbook= WorkbookFactory.create(new File(excelFilePath));
            Sheet sheet=workbook.getSheet(sheetName);
            DataFormatter formatter=new DataFormatter();

            int noOfRows=sheet.getPhysicalNumberOfRows();
            List<String> excelHeaderList=new ArrayList<>();
            for(int i=0;i<noOfRows;i++){
                if(i==0){
                    int noOfCells=sheet.getRow(i).getPhysicalNumberOfCells();
                    for(int j=0;j<noOfCells;j++){
                        String excelHeaders=sheet.getRow(i).getCell(j).getStringCellValue();
                        excelHeaderList.add(excelHeaders);
                    }
                }
                else{
                    LinkedHashMap<String,String>map=new LinkedHashMap<>();
                    int noOfCells=sheet.getRow(i).getPhysicalNumberOfCells();
                    for(int j=0;j<noOfCells;j++){

                        Cell cell=sheet.getRow(i).getCell(j);
                        String cellValue=formatter.formatCellValue(cell);
                        map.put(excelHeaderList.get(j),cellValue);
                    }
                    excelData.add(map);
                }
            }
            excelData.stream().filter(item-> item.get("enabled").equalsIgnoreCase("y")).collect(Collectors.toList());

        } catch (IOException e) {
            ExtentManager.logException(e.getMessage());
        }

        return excelData;
    }


}
