package doc.openOffice;

import java.io.File;


public class CurrPathUtil {

    public static void main(String[] args) {
        System.out.println(File.separator);
        System.out.println(CurrPathUtil.getCurProgramPath());
    }

    public static String getCurProgramPath(){
        String path = System.getProperty("user.dir");
        if(!path.endsWith(File.separator)){
            path += File.separator;
        }
        return path;
    }

    public void printTestPath(){
        String rootPath=getClass().getResource("/").getFile().toString();
        String currentPath1=getClass().getResource(".").getFile().toString();
        String currentPath2=getClass().getResource("").getFile().toString();
        String parentPath=getClass().getResource("../").getFile().toString();
        System.out.println("rootPath : " + rootPath);
        System.out.println("currentPath1 : " + currentPath1);
        System.out.println("currentPath2 : " + currentPath2);
        System.out.println("parentPath : " + parentPath);
        System.out.println("system.getProperty('user.dir') : " + System.getProperty("user.dir"));
    }
}