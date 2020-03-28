import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;
import java.util.Properties;

/**
 * Class Name : Module
 * Description : This will contain all utility methods
 */
public class Module {

    public static String convertPdfToTxtFile(String filePath) throws IOException {
        File file = new File(filePath);
        PDDocument document = PDDocument.load(file);
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document);
        writeDocumentToTxtFile(text,filePath.replace(".pdf",".txt"));
        document.close();
        return filePath.replace(".pdf",".txt");
    }

    public static String readPDF(String filePath) throws IOException {
        File file = new File(filePath);
        PDDocument document = PDDocument.load(file);
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document);
        document.close();
        return text;
    }

    public static void writeDocumentToTxtFile(String text, String path){
        try {
            delFile(path);
            File newTextFile = new File(path);
            FileWriter fw = new FileWriter(newTextFile);
            fw.write(text);
            fw.close();
        } catch (IOException iox) {
            iox.printStackTrace();
        }
    }

    public static void delFile(String path){
        try {
            File file = new File(path);
            file.delete();
        }catch(Exception e){
            //
        }
    }

    public static String returnConfigValue(String key) throws Exception {
        String path = System.getProperty("user.dir")+"\\src\\main\\resources\\config.properties" ;
        try (InputStream input = new FileInputStream(path)) {
            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty(key);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("");
        }
    }



}
