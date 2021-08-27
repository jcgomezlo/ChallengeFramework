package utils;
import utils.reader.ExcelUtils;

public class DataDeliver {

    private ExcelUtils excelUtils;
    private static DataDeliver instance;

    private DataDeliver(String path){
            excelUtils = new ExcelUtils(path);
    }

    public static DataDeliver getInstance(String path){
        if(instance == null){
            instance = new DataDeliver(path);
        }
        return instance;
    }

    public static void main(String[] args){
        DataDeliver data = DataDeliver.getInstance("data/data.xlsx");
        System.out.println(data.getData("Sheet1")[0][1]);
    }

    public  Object[][] getData(String sheet) {
        int numRows = excelUtils.getRowCount(sheet);
        int numCols = excelUtils.getColumnCount(sheet);
        Object[][] data = new Object[numRows-1][];

        for(int i = 1; i < numRows; i++){
            Object[] excelRow = new Object[numCols];
            for(int j = 0; j< numCols; j++){
                excelRow[j] = excelUtils.getCellData(sheet,i,j);
            }
            data[i-1] = excelRow;
        }
        return data;
    }
}
