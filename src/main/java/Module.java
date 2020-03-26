import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Module {

    public static void convertPdfToTxtFile(String filePath) throws IOException {
        File file = new File(filePath);
        PDDocument document = PDDocument.load(file);
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document);
        writeDocumentToTxtFile(text,filePath.replace(".pdf",".txt"));
        document.close();
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

}
