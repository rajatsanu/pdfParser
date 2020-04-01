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
                if(file.getName().contains(".pdf")){
                    if (file.isFile()) {
                        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\" + currentFolderName +
                                "\\" + file.getName();
                        System.out.println("PDF to read : " + filePath);
                        String data = Module.convertPdfToText1(filePath);
                        if(!checkFileCreated){
                            checkFileCreated = true ;
                        //    ExcelModule.createTemplateExcel(excelPath,currentFolderName);
                        }
                       // System.out.println("data : \n " + data);
                        Extractor(currentFolderName,data);
                    }else{
                        System.out.println("File Not Present : " + file.getName());
                    }
                }
            }
        }


    }


    public static void Extractor(String currenFolderName, String data){
        String add1=null,add2=null,add3=null;
        switch (currenFolderName) {
            case "Sigma" :
            case "sigma" :
                add1 = Module.sigmaExtractDataAddress1(data);
                add2 = Module.sigmaExtractDataAddress2(data);
                add3 = Module.sigmaExtractDataAddress3(data);
                break;

            case "Nuvias" :
            case "nuvias" :
                add1 = Module.nuviasExtractDataAddress1(data);
                break;




        }



    }


}
