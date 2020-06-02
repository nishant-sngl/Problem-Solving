package Utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sun.tools.jconsole.inspector.XSheet;

import java.io.*;
import java.util.*;

public class XlsUtil {
    private FileInputStream fis;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    String workbookName;

    public XlsUtil(String workbookName){
        this.workbookName = workbookName;
    }

    public Object[][] getXlsData(int index) throws IOException {
        fis = new FileInputStream(new File(workbookName));
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheetAt(0);
        int lastRow = sheet.getLastRowNum();
        Object[][] list = new Object[lastRow+1][1];
        Row row;
        Row headRow = sheet.getRow(0);
        for(int i=1;i<lastRow+1;i++){
            row = sheet.getRow(i);
            Map<String, String> map = new HashMap<>();
            int lastCol = row.getLastCellNum();
            for(int j=1;j<lastCol;j++){
                map.put(headRow.getCell(j).getStringCellValue(), row.getCell(j).getStringCellValue());
            }
            list[i][0] = map;
            System.out.println(map.toString());
        }
        return list;
    }
}
