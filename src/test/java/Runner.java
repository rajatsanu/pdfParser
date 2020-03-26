import java.io.IOException;

public class Runner {

    public static void main(String[] args) throws IOException {

        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\Test.pdf";
        System.out.println("PDF to read : " + filePath);
        Module.convertPdfToTxtFile(filePath);

    }

}
