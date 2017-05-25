package photo;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TakePhoto
 */
public class TakePhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TakePhoto() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
    	PrintWriter out = response.getWriter();
    	if (request.getParameter("PHeight") != null && request.getParameter("PWidth") != null && request.getParameter("strBMP") != null)
          {
              try 
              {
            	  int height = Integer.valueOf((String)request.getParameter("PHeight"));
	              int width = Integer.valueOf((String)request.getParameter("PWidth"));
	              String strBmp = (String)request.getParameter("strBMP");
	              SaveBmp(BuildBitmap(width, height, strBmp),"c:\\", "1.jpeg");
	              out.print("RetMsg=true");
              }
              catch (Exception ee)
              {
                  out.print("RetMsg=false");
                  ee.printStackTrace();
              }
          }
    	out.close();
	}
	
	public BufferedImage BuildBitmap(int width, int height, String strBmp)
    {
    	BufferedImage tmpBmp = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);

    	String[] StmpBmp = strBmp.split(",");
        int pos = 0;
        int[] pixels = new int[width * height];

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                tmpBmp.setRGB(x, y, Integer.valueOf(StmpBmp[pos], 16));
                pos++;
            }
        }
        return tmpBmp;
    }
	
	public void SaveBmp(BufferedImage img,String filePath, String fileName) throws Exception
    {
        try {
			 FileOutputStream  fos = new FileOutputStream(filePath+fileName);
			 img.flush();
			 ImageIO.write(img, "jpeg", fos);
			 fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
