package poi;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.Picture;

public class ImageTest {
	public void readImage() {
		try {
			String fileDir = new File(ImageTest.class.getResource("").getFile(),"../../../WebContent/doc/").getCanonicalPath();
			FileInputStream in = new FileInputStream(new File(fileDir+"/image.doc"));
			//FileInputStream in = new FileInputStream(new File("C:/image.doc"));
			HWPFDocument doc = new HWPFDocument(in);
			PicturesTable pic = doc.getPicturesTable();
			List pictureList = pic.getAllPictures();
			System.out.println(pictureList.size());
			BufferedOutputStream output = null;
			for (int i = 0; i < pictureList.size(); i++) {
				Picture p = (Picture) pictureList.get(i);
				output = new BufferedOutputStream(new FileOutputStream(
						fileDir + "/img/" + (i + 1) + ".jpg"));
				output.write(p.getContent());
				output.flush();
				output.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void getImage() {
		try {
			String fileDir = new File(ImageTest.class.getResource("").getFile(),"../../../WebContent/doc/").getCanonicalPath();
			FileInputStream in = new FileInputStream(new File(fileDir+"/image.doc"));
			//FileInputStream in = new FileInputStream(new File(path));
			HWPFDocument doc = new HWPFDocument(in);
			// doc.
			PicturesTable pic = doc.getPicturesTable();

			List pictureList = pic.getAllPictures();
			System.out.println(pictureList.size());
			BufferedOutputStream output = null;
			for (int i = 0; i < pictureList.size(); i++) {
				Picture p = (Picture) pictureList.get(i);
				// System.out.println(p.get());
				System.out.println("x:" + p.getAspectRatioX());// x坐标
				System.out.println("y:" + p.getAspectRatioY());// y坐标
				System.out.println("height:" + p.getHeight());// 高度
				System.out.println("width:" + p.getWidth());// 宽读
				output = new BufferedOutputStream(new FileOutputStream(
						fileDir + "/img/" + (i + 1) + ".jpg"));
				output.write(p.getContent());
				output.flush();
				output.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String args[]) {
		new ImageTest().readImage();
	}

}
