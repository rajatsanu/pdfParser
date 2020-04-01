import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;

public class ExcelModule {

    public static void createTemplateExcel(String excelPathName,String sheetName){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(sheetName);
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


    public static CellStyle headerCellStyle(XSSFWorkbook workbook){
        XSSFFont font= workbook.createFont();
        font.setFontHeightInPoints((short)10);
        font.setFontName("Arial");
        font.setColor(IndexedColors.WHITE.getIndex());
        font.setBold(true);
        font.setItalic(false);

        CellStyle style=workbook.createCellStyle();;
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFont(font);

        return style;
    }


}
