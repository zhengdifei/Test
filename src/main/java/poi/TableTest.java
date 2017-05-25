package poi;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.StyleSheet;
import org.apache.poi.hwpf.usermodel.CharacterProperties;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class TableTest {

	public void parseTable(){
		try {
			String fileDir = new File(TableTest.class.getResource("").getFile(),"../../../WebContent/doc/").getCanonicalPath();
			FileInputStream in = new FileInputStream(new File(fileDir+"/table.doc"));
            //FileInputStream in = new FileInputStream("C:\\table.doc");// 载入文档
            POIFSFileSystem pfs = new POIFSFileSystem(in);
            HWPFDocument hwpf = new HWPFDocument(pfs);
            Range range = hwpf.getRange();// 得到文档的读取范围
            int paraNum = range.numParagraphs();//根据回车键，得到段落数量

            int m = 0; // 数组下标
            String[] ret = new String[paraNum];
            for (int i = 0; i < paraNum; ++i) {
                // 从每一段落中获取文字,每一段是一个回车
                Paragraph p = range.getParagraph(i);
                for (int d = 0; d < p.numCharacterRuns(); d++) {
                    CharacterRun run = p.getCharacterRun(d);
                    //System.out.println(run.text());
                }
                boolean test = p.isInTable(); // 判断该Paragraph是否在word的表格中
                if (test == true) {
                    Table table = range.getTable(p); // 通过第一个在table中的Paragraph来获取整个table
                    int numRow = table.numRows(); // 获取table中的行数
                    for (int j = 0; j < numRow; j++) {
                        TableRow tablerow = table.getRow(j); // 获得一行
                        int numbercell = tablerow.numCells(); // 通过tablerow获取单元格个数
                        for (int k = 0; k < numbercell; k++) {
                            TableCell tablexell = tablerow.getCell(k); // 获得单元格
                            int p1 = tablexell.numParagraphs();
                            // 获取单元格中的Paragraph的个数
                            String str = "";
                            for (int l = 0; l < p1; l++) {
                                Paragraph para = tablexell.getParagraph(l);
                                str = str + para.text().trim(); // 得到单元格中的内容
                                //System.out.println(str);
                            }
                            i++;
                            for (int n = m; n < paraNum;) {
                                ret[n] = str; // 将单元格中的内容方入数组元素中
                                break;
                            }
                            m++;
                        }
                        i++;
                    }
                }
            }
            for (int i = 0; i < paraNum; i++) {
                System.out.println(ret[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public void parse2Table(){
		try {
			String fileDir = new File(TableTest.class.getResource("").getFile(),"../../../WebContent/doc/").getCanonicalPath();
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(
            		fileDir + "/table.doc"));
            HWPFDocument doc = new HWPFDocument(fs);

            Range range = doc.getOverallRange();
            TableIterator ti = new TableIterator(range);
//            Table table = null;

            while (ti.hasNext()) {
                System.out.println("Getting table!");
                Table table = (Table)ti.next();
                System.out.println("Number of rows: " + table.numRows());

                for (int a = 0; a < table.numRows(); a++) {
                    TableRow row = table.getRow(a);
                    System.out.println("\tTable row number: " + a);

                    for (int b = 0; b < row.numCells(); b++) {
                        System.out.println("\t\tTable cell number: " + b);
                        TableCell tablecell = row.getCell(b);

                        for (int c = 0; c < tablecell.numParagraphs(); c++) {
                            Paragraph tablepara = tablecell.getParagraph(c);

                            for (int d = 0; d < tablepara.numCharacterRuns(); d++) {
                                CharacterRun run = tablepara.getCharacterRun(d);
                                System.out.println("\t\tText: \"" + run.text()
                                        + "\"");
                            }
                        }
                    }
                }
                System.out.println("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public void insertTable(){
        try {
			   String fileDir = new File(TableTest.class.getResource("").getFile(),"../../../WebContent/doc/").getCanonicalPath();
               FileInputStream in = new FileInputStream(new File(fileDir + "/table.doc"));
               POIFSFileSystem pfs = new POIFSFileSystem(in);
               HWPFDocument hwpf = new HWPFDocument(pfs);
               Range range = hwpf.getRange();
               StyleSheet styleSheet = hwpf.getStyleSheet();
               TableIterator it = new TableIterator(range);
               // 遍历一个DOC中的所有表格
               while (it.hasNext()) {
                   Table tb = (Table) it.next();
                   System.out.println(tb.numRows());
                   // 遍历表格的行
//                   for (int i = 0; i < tb.numRows(); i++) {
//                       TableRow tr = tb.getRow(i);
//                       // 遍历表格的列
//                       System.out.println(tr.numCells());
//                       for (int j = 0; j < tr.numCells(); j++) {
//                           // 往表格中插入数据
//                           TableCell td = tr.getCell(j);
//                      //    CharacterRun chr1= td.getCharacterRun(0);
//                   //       System.out.println("td–"+chr1.getFontName());
//
//                           String text = "第" + i + "行第" + j + "列";
//
//                           int p = td.numParagraphs();
//                           System.out.println("td.numParagraphs :"+p);
//                           for(int a=0;a<p;a++)
//                           {
//                           Paragraph para =td.getParagraph(a);
//                           para.insertBefore(text+"  ");
//                         ///  para.insertAfter(text);
//                           int q=para.numCharacterRuns();
//                           System.out.println("numCharacterRuns–"+q);
//                           for(int b=0;b<q;b++)
//                           {
//                         CharacterRun chr=  para.getCharacterRun(b);
//
//                        System.out.println("para.getCharacterRun– "+ chr.getFontName()+"  text–"+chr.text().trim()+"  str–"+chr.toString());
//
//                     //      ParagraphProperties pp = new ParagraphProperties();
//
//                       //    if(j%2==0){
//
//                       //    }
//                           }
//                       }
//                       }
//                   }
               }

        //       WordExtractor we=new WordExtractor(in);
               String text=range.text();
               System.out.println(text);

               // 在表格外面插入内容
               CharacterProperties cp = new CharacterProperties();
               cp.setBold(true);
               cp.setCharacterSpacing(10);
               cp.setChse(cp.SPRM_CHARSCALE);
               cp.setCapitalized(true);
               int p=range.numParagraphs();
               for(int i=0;i<p;i++)
               {
               String name="飞舞";
               String age= "23";
               Paragraph para=range.getParagraph(i);
           //     System.out.println(p);
            //    para.insertAfter("–插入 成功!!",cp);
               para.replaceText("姓名：————————————","姓名："+name);

               para.replaceText("年龄：————————————","年龄："+age);
               CharacterRun chr=para.getCharacterRun(0);
               System.out.println("第"+i+"段: "+chr.text());
            }

               System.out.println("\n插入后 生成新文档demo1—————————————–\n");
               text=range.text();
               System.out.println(text);

//               File outputFile = new File("C:/456.doc");
//               OutputStream output = new FileOutputStream(outputFile);
//               hwpf.write(output);
//               output.close();
//

               // byte b[] = content.getBytes("ISO-8859-1″);
             //  String content="这是固定的字符2″;
               byte b[] = text.getBytes();

               ByteArrayInputStream bais = new ByteArrayInputStream(b);

               POIFSFileSystem fs = new POIFSFileSystem();
               DirectoryEntry directory = fs.getRoot();

               DocumentEntry de = directory.createDocument("WordDocument", bais);
               FileOutputStream ostream = new FileOutputStream(fileDir + "/table1.doc");

               fs.writeFilesystem(ostream);

               bais.close();
               ostream.close();

           } catch (Exception ex) {
               ex.printStackTrace();
           }

	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TableTest().insertTable();
	}

}
