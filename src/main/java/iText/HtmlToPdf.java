package iText;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.*;

/**
 * Created by Administrator on 2017/5/25 0025.
 */
public class HtmlToPdf {
    public static void main(String[] args) {
        String xmlFile = "d:\\template.html";
        String pdfFile = "d:\\temp.pdf";

        try {
            InputStream xmlFileStream = new FileInputStream(xmlFile);

            Document document = new Document();
            PdfWriter pdfWriter = PdfWriter.getInstance(document,new FileOutputStream(pdfFile));
            pdfWriter.setViewerPreferences(PdfWriter.HideToolbar);
            document.open();

            document.newPage();
            document.add(new Paragraph("new Page"));
            InputStreamReader isr = new InputStreamReader(xmlFileStream,"UTF-8");
            InputStream cssInput = null;
            XMLWorkerHelper.getInstance().parseXHtml(pdfWriter,document,isr);
            document.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
