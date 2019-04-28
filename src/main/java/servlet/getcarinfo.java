package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.base64tofile;
import entity.file;
import entity.ocr;
import sun.misc.BASE64Decoder;

/**
 * Servlet implementation class getcarinfo
 */
@WebServlet("/getcarinfo")
public class getcarinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getcarinfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		String imgurl=request.getParameter("imgurl");
		String vehicle_license_side=request.getParameter("vehicle_license_side");
        String str="";
        //String imgBase64 = imgurl.replaceAll("data:image/jpeg;base64,","");
        BASE64Decoder d = new BASE64Decoder();
        byte[] data = d.decodeBuffer(imgurl);
		if(vehicle_license_side.equals("front"))
		{
			
			try {
				str=ocr.ocr_vehicleLicense_front(data);
				System.out.println(str);
			} catch (Exception e) {
				System.out.println(e);
			}
			response.getWriter().write(str);
			System.out.println(str);
		}
		else {
			try {
				str=ocr.ocr_vehicleLicense_back(data);
				System.out.println(str);
			} catch (Exception e) {
				System.out.println(e);
			}
			response.getWriter().write(str);
			System.out.println(str);
		}
		
	}

}
