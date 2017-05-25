package zip;

import java.io.BufferedInputStream;   
import java.io.BufferedOutputStream;
import java.io.File;   
import java.io.FileInputStream;   
import java.io.FileOutputStream;   
import java.util.Enumeration;
import java.util.zip.CRC32;   
import java.util.zip.CheckedOutputStream;   
import java.util.zip.ZipFile;
  
import java.util.zip.ZipEntry;   
import java.util.zip.ZipOutputStream;   
  
public class ZipCompressor {   
    static final int BUFFER = 8192;
  
    private ZipCompressor() {     
    }
    
    /**
     * 将多个文件夹打包成一个压缩文件
     * 
     * @param srcPathNames 需要压缩的文件夹
     * @param zipFile 压缩之后的文件
     */
    public static void compress(String[] srcPathNames,File zipFile) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(zipFile);   
            CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream,new CRC32());   
            ZipOutputStream out = new ZipOutputStream(cos);   
            String basedir = "";
            for(int m =0;m < srcPathNames.length;m++){
            	File file = new File(srcPathNames[m]);
            	
            	if (!file.exists()){
    	            throw new RuntimeException(srcPathNames[m] + "不存在！");   
    	        }   
                File[] fileChildren = file.listFiles();
                for(int i = 0;i<fileChildren.length;i++){
                	 compress(fileChildren[i], out, basedir);
                }
            }  
	        
            
            out.close();   
        } catch (Exception e) {   
            throw new RuntimeException(e);   
        }   
    }   
    /**
     * 将多个文件夹打包成一个jar文件
     * 
     * @param srcPathNames 需要压缩的文件夹
     * @param zipFile 压缩之后的文件
     */
    public static void jarCompress(String[] srcPathNames,File zipFile) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(zipFile);   
            CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream,new CRC32());   
            ZipOutputStream out = new ZipOutputStream(cos);   
            String basedir = "";
            for(int m =0;m < srcPathNames.length;m++){
            	File file = new File(srcPathNames[m]);
            	
            	if (!file.exists()){
    	            throw new RuntimeException(srcPathNames[m] + "不存在！");   
    	        }   
                File[] fileChildren = file.listFiles();
                for(int i = 0;i<fileChildren.length;i++){
                	 compress(fileChildren[i], out, basedir);
                }
            }  
	        
            
            out.close();   
        } catch (Exception e) {   
            throw new RuntimeException(e);   
        }   
    }   
    /**
     * 压缩文件
     * 
     * @param file 需要压缩的文件
     * @param out 输出流
     * @param basedir 目录
     */
    private static void compress(File file, ZipOutputStream out, String basedir) {   
        /* 判断是目录还是文件 */  
        if (file.isDirectory()) {   
            System.out.println("压缩：" + basedir + file.getName());   
            ZipCompressor.compressDirectory(file, out, basedir);   
        } else {   
            System.out.println("压缩：" + basedir + file.getName());   
            ZipCompressor.compressFile(file, out, basedir);   
        }   
    }   
  
    /** 压缩一个目录 */  
    private static void compressDirectory(File dir, ZipOutputStream out, String basedir) {   
        if (!dir.exists())   
            return;   
  
        File[] files = dir.listFiles();   
        for (int i = 0; i < files.length; i++) {   
            /* 递归 */  
            compress(files[i], out, basedir + dir.getName() + "/");   
        }   
    }   
  
    /** 压缩一个文件 */  
    private static void compressFile(File file, ZipOutputStream out, String basedir) {   
        if (!file.exists()) {   
            return;   
        }   
        try {   
            BufferedInputStream bis = new BufferedInputStream(   
                    new FileInputStream(file));   
            ZipEntry entry = new ZipEntry(basedir + file.getName());   
            out.putNextEntry(entry);   
            int count;   
            byte data[] = new byte[BUFFER];   
            while ((count = bis.read(data, 0, BUFFER)) != -1) {   
                out.write(data, 0, count);   
            }   
            bis.close();   
        } catch (Exception e) {   
            throw new RuntimeException(e);   
        }   
    }   
    /**
     * 解压文件
     * 
     * @param filePath 解压到的文件夹
     * @param srcfile 需要解压的zip文件
     */
    public static void deCompress(String filePath,File srcfile){
    	try {
    		ZipFile zipFile = new ZipFile(srcfile);
			Enumeration emu = zipFile.entries();
			int i=0; 
			while(emu.hasMoreElements()){
				ZipEntry entry = (ZipEntry)emu.nextElement(); 
				//会把目录作为一个file读出一次，所以只建立目录就可以，之下的文件还会被迭代到。
				if (entry.isDirectory()){
					new File(filePath +"/"+ entry.getName()).mkdirs(); 
					continue; 
				}
				BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(entry)); 
				System.out.println("解压到：" + filePath + "/" + entry.getName());   
				File file = new File(filePath + "/" + entry.getName()); 
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
    
	public static void main(String[] args) {
		ZipCompressor.compress(new String[]{"E:/test/classes"},new File("E:/tt/szhzip.jar"));
		//ZipCompressor.deCompress("E:/testzdf", new File("E:/szhzip.zip"));
	}
}