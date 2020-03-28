import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Runner {

    public static void main(String[] args) throws Exception {

        //   String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\Test.pdf";
        //   System.out.println("PDF to read : " + filePath);
        //   Module.convertPdfToTxtFile(filePath);

        String folderName = Module.returnConfigValue("folderToProcess");
        String[] arrayFolder = folderName.split(",");

        boolean checkFileCreated = false, firstColumnRequired = false ;
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HHmm").format(new Date());
        String excelPath = System.getProperty("user.dir") + "\\src\\main\\resources\\output\\Result_" + timeStamp + ".xlsx";

        for (String currentFolderName : arrayFolder) {
            System.out.println("Current Folder Name : " + currentFolderName);
            String folderPath = System.getProperty("user.dir") + "\\src\\main\\resources\\" + currentFolderName + "\\";
            File folder = new File(folderPath);
            File[] listOfFiles = folder.listFiles();
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\" + currentFolderName +
                            "\\" + file.getName();
                    System.out.println("PDF to read : " + filePath);
                    String textPath = Module.convertPdfToTxtFile(filePath);
                    String pdfValueAsString = Module.readPDF(filePath);
                    if(!checkFileCreated){
                        checkFileCreated = true ;
                        ExcelModule.createTemplateExcel(excelPath,currentFolderName);
                    }
                    System.out.println("pdfValueAsString \n" + pdfValueAsString);
                }
            }
        }


    }

}
