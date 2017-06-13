package doc.xml2pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;

import java.io.IOException;

/**
 * Created by Administrator on 2017/5/25 0025.
 */
public class MyFontProvider extends XMLWorkerFontProvider {

    public Font getFont(final String fontname, final String encoding,
                        final boolean embedded, final float size, final int style,
                        final BaseColor color) {
        BaseFont bf = null;
        try {
            bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
                    BaseFont.NOT_EMBEDDED);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Font font = new Font(bf, size, style, color);
        font.setColor(color);
        return font;
    }
}
