package utils.reader;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.HashMap;
import java.util.Map;

public class ExcelUtils {

    private XSSFWorkbook workbook;
    private Map<String,Sheet> sheets;
    private DataFormatter formatter;

    public ExcelUtils(String path){
        try {
            workbook = new XSSFWorkbook(path);
            sheets = new HashMap<String, Sheet>();

            for(Sheet sheet: workbook) {
                sheets.put(sheet.getSheetName(),sheet);
            }

            formatter = new DataFormatter();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Object getCellData(String sheetName, int rowNum, int colNum){
            Object value = formatter.formatCellValue(sheets.get(sheetName).getRow(rowNum).getCell(colNum));
            return value;

    }

    public int getRowCount(String sheetName) {
            return sheets.get(sheetName).getPhysicalNumberOfRows();
    }

    public int getColumnCount(String sheetName) {
        return sheets.get(sheetName).getRow(0).getPhysicalNumberOfCells();
    }

}
