package zip;

import java.io.BufferedInputStream; 
import java.io.BufferedOutputStream; 
import java.io.File; 
import java.io.FileOutputStream; 
import java.util.Enumeration; 
import java.util.zip.ZipEntry; 
import java.util.zip.ZipFile; 

public class DeZipTest {
	static final int BUFFER = 2048; 

	public static void main(String argv[]) {
		try {
			String fileName = "E:\\test\\myfiles.zip"; 
			String filePath = "E:\\test\\"; 
			ZipFile zipFile = new ZipFile(fileName); 
			Enumeration emu = zipFile.entries(); 
			int i=0; 
			while(emu.hasMoreElements()){
				ZipEntry entry = (ZipEntry)emu.nextElement(); 
				//会把目录作为一个file读出一次，所以只建立目录就可以，之下的文件还会被迭代到。
				if (entry.isDirectory()){
					new File(filePath + entry.getName()).mkdirs(); 
					continue; 
				}
				BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(entry)); 
				File file = new File(filePath + entry.getName()); 
				//加入这个的原因是zipfile读取文件是随机读取的，这就造成可能先读取一个文件
				//而这个文件所在的目录还没有出现过，所以要建出目录来。
				File parent = file.getParentFile(); 
				if(parent != null && (!parent.exists())){
					parent.mkdirs(); 
				}
				FileOutputStream fos = new FileOutputStream(file); 
				BufferedOutputStream bos = new BufferedOutputStream(fos,BUFFER); 
				int count; 
				byte data[] = new byte[BUFFER]; 
				while ((count = bis.read(data, 0, BUFFER)) != -1){
					bos.write(data, 0, count); 
				}
				bos.flush(); 
				bos.close(); 
				bis.close(); 
			}
			zipFile.close(); 
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
} 