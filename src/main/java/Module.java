import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import com.spire.pdf.*;
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
        writeDocumentToTxtFile(text, filePath.replace(".pdf", ".txt"));
        document.close();
        return filePath.replace(".pdf", ".txt");
    }

    public static String convertPdfToText1(String filePath) {
        StringBuilder sb = null;
        try {
            PdfDocument pdf = new PdfDocument();
            pdf.loadFromFile(filePath);
            sb = new StringBuilder();
            for (PdfPageBase page : (Iterable<PdfPageBase>) pdf.getPages()) {
                sb.append(page.extractText(true));
            }
            //writeDocumentToTxtFile(sb.toString(), filePath.replace(".pdf", ".txt"));
            pdf.close();
        } catch (Exception e) {
            System.out.println("In catch convertPdfToText1");
        }
        return sb.toString();
    }

    public static String readPDF(String filePath) throws IOException {
        File file = new File(filePath);
        PDDocument document = PDDocument.load(file);
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document);
        document.close();
        return text;
    }

    public static void writeDocumentToTxtFile(String text, String path) {
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

    public static void delFile(String path) {
        try {
            File file = new File(path);
            file.delete();
        } catch (Exception e) {
            //
        }
    }

    public static String returnConfigValue(String key) throws Exception {
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties";
        try {
            InputStream input = new FileInputStream(path);
            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty(key);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("");
        }
    }

    public static String sigmaExtractDataAddress1(String data) {
        String address = null;
        String[] lineOfPdf = data.split("\n");
        for (String line : lineOfPdf) {
            if (line.contains("Evaluation Warning") || line.equalsIgnoreCase("\r")
                || line.contains("Sigma Software Distribution")){
            } else if (line.contains("Telephone")) {
                break;
            } else {
                address = address + line.trim() + "\n";
            }
        }
        System.out.println("Address 1 : \n" + address.replace("null", ""));
        return address.replace("null", "");
    }

    public static String sigmaExtractDataAddress2(String data) {
        String address = null;
        String[] lineOfPdf = data.split("\n");
        outloop:
        for (int i = 0 ; i < lineOfPdf.length ; i++) {
            if (lineOfPdf[i].contains("Supplier Address")){
                for(int j = i + 1 ; j < lineOfPdf.length ; j++){
                    if(lineOfPdf[j].contains("Delivery Terms")){
                        break outloop;
                    }else{
                        String data1 = lineOfPdf[j].trim();
                        if(data1.length()>1){
                            String[] data1Array = data1.split("  ");
                            address = address + data1Array[0] + "\n" ;
                        }
                    }
                }
            }
        }
        System.out.println("Address 2 : \n" + address.replace("null", ""));
        return address.replace("null", "");
    }

    public static String sigmaExtractDataAddress3(String data) {
        String address = null;
        String[] lineOfPdf = data.split("\n");
        outloop:
        for (int i = 0 ; i < lineOfPdf.length ; i++) {
            if (lineOfPdf[i].contains("Supplier Address")){
                for(int j = i + 1 ; j < lineOfPdf.length ; j++){
                    if(lineOfPdf[j].contains("Delivery Terms")){
                        break outloop;
                    }else{
                        String data1 = lineOfPdf[j].trim();
                        if(data1.length()>1){
                            String[] data1Array = data1.split("  ");
                            address = address + data1Array[data1Array.length-1].trim() + "\n" ;
                        }
                    }
                }
            }
        }
        System.out.println("Address 3 : \n" + address.replace("null", ""));
        return address.replace("null", "");
    }

    public static String nuviasExtractDataAddress1(String data) {
        String address = null;


        return address;
    }


}
