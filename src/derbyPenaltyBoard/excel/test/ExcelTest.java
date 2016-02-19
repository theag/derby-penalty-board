/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derbyPenaltyBoard.excel.test;

import derbyPenaltyBoard.Team;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author nbp184
 */
public class ExcelTest {
    
    private static final String folder = "C:\\Users\\nbp184\\Documents\\Test\\";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InvalidFormatException {
        String sheetName = "test sheet";
        String identifierLoc = "A1";
        String playerRange = "A2:A15";
        
        InputStream inp = new FileInputStream(folder +"workbook.xlsx");
        Workbook wb = WorkbookFactory.create(inp);
        inp.close();
        
        Team team = new Team(false);
        
        Sheet sheet = wb.getSheet(sheetName);
        
        Point p = getLocation(identifierLoc);
        Cell cell = sheet.getRow(p.x).getCell(p.y);
        team.identifier = cell.getStringCellValue();
        
        Range r = getRange(playerRange);
        
        if(r.isRowRange) {
            Row row = sheet.getRow(r.staticValue);
            
        }
    }
    
    private static Point getLocation(String excelLocation) {
        excelLocation = excelLocation.toUpperCase();
        int index = 0;
        for(; index < excelLocation.length(); index++) {
            if(excelLocation.charAt(index) < 'A') {
                break;
            }
        }
        Point p = new Point();
        p.x = Integer.parseInt(excelLocation.substring(index));
        p.y = 0;
        int pow = 1;
        for(int i = index-1; i >= 0; i--) {
            p.y += (excelLocation.charAt(i) - 'A' + 1)*pow;
            pow *= 26;
        }
        return p;
    }
    
    private static Range getRange(String excelRange) {
        int index = excelRange.indexOf(":");
        Point p1 = getLocation(excelRange.substring(0, index));
        Point p2 = getLocation(excelRange.substring(index+1));
        Range r = new Range();
        if(p1.y == p1.y) {
            r.startValue = p1.x;
            r.endValue = p2.x;
            r.staticValue = p1.y;
        } else if(p1.x == p2.x) {
            r.startValue = p1.y;
            r.endValue = p2.y;
            r.staticValue = p1.x;
            r.isRowRange = true;
        } else {
            r = null;
        }
        return r;
    }    
    
    private static class Range {
        public boolean isRowRange;
        public int startValue;
        public int endValue;
        public int staticValue;
        
        public Range() {
            this.startValue = 0;
            this.endValue = 0;
            this.staticValue = 0;
            this.isRowRange = false;
        }
        
        public Range(int startValue, int endValue, int staticValue) {
            this.startValue = startValue;
            this.endValue = endValue;
            this.staticValue = staticValue;
            this.isRowRange = false;
        }
        
        public Range(int startValue, int endValue, int staticValue, boolean isRowRange) {
            this.startValue = startValue;
            this.endValue = endValue;
            this.staticValue = staticValue;
            this.isRowRange = isRowRange;
        }
        
        public int size() {
            return endValue - startValue + 1;
        }
        
    }
    
}
