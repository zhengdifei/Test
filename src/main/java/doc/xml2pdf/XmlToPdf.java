package doc.xml2pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.hwpf.extractor.WordExtractor;

import java.io.*;

/**
 * Created by Administrator on 2017/5/25 0025.
 */
public class XmlToPdf {
    public static void main(String[] args) {
        try {
            InputStream in = new FileInputStream("d:\\template.doc");
            OutputStream out = new FileOutputStream("d:\\temp.pdf");

            WordExtractor wd = new WordExtractor(in);
            String text = wd.getText();

            Document pdf = new Document(PageSize.A4);
            PdfWriter.getInstance(pdf,out);

            pdf.open();
            pdf.add(new Paragraph(text));

            pdf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
