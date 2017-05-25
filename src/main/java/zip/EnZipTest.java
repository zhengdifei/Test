package zip;

import java.io.BufferedInputStream; 
import java.io.BufferedOutputStream; 
import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileOutputStream; 
import java.util.zip.ZipEntry; 
import java.util.zip.ZipOutputStream; 

public class EnZipTest {
	static final int BUFFER = 2048; 

	public static void main(String argv[]) {
		try {
			BufferedInputStream origin = null; 
			FileOutputStream dest = new FileOutputStream("e:\\test\\myfiles.zip"); 
			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest)); 
			byte data[] = new byte[BUFFER]; 
			
			File f = new File("e:\\test\\com\\flying\\"); 
			File files[] = f.listFiles(); 
			for (int i = 0; i < files.length; i++) {
				FileInputStream fi = new FileInputStream(files[i]); 
				origin = new BufferedInputStream(fi, BUFFER); 
				ZipEntry entry = new ZipEntry(files[i].getName()); 
				out.putNextEntry(entry); 
				int count; 
				while ((count = origin.read(data, 0, BUFFER)) != -1) {
					out.write(data, 0, count); 
				}
				origin.close(); 
			}
			out.close(); 
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
}