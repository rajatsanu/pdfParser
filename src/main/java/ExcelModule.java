import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ExcelModule {

    public static void createTemplateExcel(String excelPathName,String sheetName){
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet(sheetName);
        int rowNum = 0;
        System.out.println("Creating excel");
        Row row = sheet.createRow(rowNum);
        CellStyle headerStyle = headerCellStyle(workbook);
        Cell cell = row.createCell(0);
        cell.setCellValue("FileName");
        cell.setCellStyle(headerStyle);
        cell = row.createCell(1);
        cell.setCellValue("Address 1");
        cell.setCellStyle(headerStyle);
        cell = row.createCell(2);
        cell.setCellValue("Address 2");
        cell.setCellStyle(headerStyle);
        cell = row.createCell(3);
        cell.setCellValue("Address 3");
        cell.setCellStyle(headerStyle);
        try {
            createOutputFolder();
            FileOutputStream outputStream = new FileOutputStream(new File(excelPathName));
            workbook.write(outputStream);
            workbook.close();
        } catch (Exception e) {
            System.out.println("In catch createTemplateExcel");
        }
    }

    public static void createOutputFolder(){
        String outputFolderPath = System.getProperty("user.dir") + "\\src\\main\\resources\\output\\" ;
        File directory = new File(String.valueOf(outputFolderPath));
        if(!directory.exists()){
            directory.mkdir();
            System.out.println("Output Folder Create successfully... ");
        }
    }


    public static CellStyle headerCellStyle(Workbook workbook){
        Font font= workbook.createFont();
        font.setFontHeightInPoints((short)10);
        font.setFontName("Arial");
        font.setColor(IndexedColors.WHITE.getIndex());
        font.setBold(true);
        font.setItalic(false);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setFont(font);

        return headerStyle;
    }

    public static void updateDataExcel(String[] address,int rowCount, String path, String sheetName){
        try{
            Workbook myExcelBook = new HSSFWorkbook(new FileInputStream(path));
            Sheet myExcelSheet = myExcelBook.getSheet(sheetName);
            Row row = myExcelSheet.createRow(rowCount);
            CellStyle style = dataCellStyle(myExcelBook,rowCount);
            Cell cell = row.createCell(0);
            cell.setCellValue(address[3]);
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue(address[0]);
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue(address[1]);
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue(address[2]);
            cell.setCellStyle(style);
            FileOutputStream outputStream = new FileOutputStream(new File(path));
            myExcelBook.write(outputStream);
            myExcelBook.close();
        }catch (Exception e){

        }
    }

    public static CellStyle dataCellStyle(Workbook workbook, int rowCount){
        Font font= workbook.createFont();
        font.setFontHeightInPoints((short)10);
        font.setFontName("Arial");
        font.setColor(IndexedColors.BLACK.getIndex());
        font.setBold(false);
        font.setItalic(false);

        CellStyle headerStyle = workbook.createCellStyle();
        if(rowCount%2==0) {
            headerStyle.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        }else{
            headerStyle.setFillBackgroundColor(IndexedColors.OLIVE_GREEN.getIndex());
        }
        headerStyle.setFont(font);

        return headerStyle;
    }



}
