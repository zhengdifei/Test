package poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterProperties;
import org.apache.poi.hwpf.usermodel.HWPFList;
import org.apache.poi.hwpf.usermodel.ParagraphProperties;
import org.apache.poi.hwpf.usermodel.Range;

public class ColorTest {

	public void styleTest(){
		try {
			//读取word模板
			String fileDir = new File(ColorTest.class.getResource("").getFile(),"../../../WebContent/doc/").getCanonicalPath();
			FileInputStream in = new FileInputStream(new File(fileDir+"/blank.doc"));
			//FileInputStream in = new FileInputStream("C://blank.doc");
			HWPFDocument doc = new HWPFDocument(in);
			Range range = doc.getRange();
			CharacterProperties props = new CharacterProperties();

			// Set the font size in half points
			Range currentRange = range;

			// Slowly increase the font size
			for (int x = 8; x <= 64; x += 4) {
				// Set the half point size of the font
				props.setFontSize(x);
				currentRange = currentRange.insertAfter("Hello World!", props);
			}

			// Display Bold characters
			props.setBold(true);
			currentRange = currentRange.insertAfter("Bold", props);

			// Display Italic characters
			props.setItalic(true);
			currentRange = currentRange.insertAfter("Italic", props);

			// Display charcters with a Double Strikethrough
			props.setDoubleStrikeThrough(true);
			currentRange = currentRange.insertAfter("Double Strikethrough",
					props);

			// Insert an empty paragraph for readability
			currentRange = currentRange.insertAfter(new ParagraphProperties(),
					0);

			// Reset the character properties
			props = new CharacterProperties();
			props.setFontSize(32);

			// Create a numbered list
			HWPFList list = new HWPFList(true, doc.getStyleSheet());
			int listID = doc.registerList(list);

			// Insert a list entry
			currentRange = currentRange.insertAfter(new ParagraphProperties(),
					listID, 1, 0);
			props.setIco24(0xff0000);
			currentRange = currentRange.insertAfter("Blue list entry", props);

			// Insert another list entry
			currentRange = currentRange.insertAfter(new ParagraphProperties(),
					listID, 1, 0);
			props.setIco24(0xff);
			props.setFontSize(38);
			props.setCapitalized(true);
			currentRange = currentRange.insertAfter("larger red capitalized",
					props);

			// Last list entry
			currentRange = currentRange.insertAfter(new ParagraphProperties(),
					listID, 1, 0);
			props.setIco24(0);
			props.setCapitalized(false);
			props.setCharacterSpacing(150);
			props.setOutline(true);
			currentRange = currentRange.insertAfter("Large character spacing",
					props);

			// Write out the document
			FileOutputStream out = new FileOutputStream(fileDir+"/hello.doc");
			doc.write(out);
			out.flush();
			out.close();

		}

		catch (Throwable t)

		{

			t.printStackTrace();

		}
	}

	public static void main(String[] args) {
		new ColorTest().styleTest();
	}
}
