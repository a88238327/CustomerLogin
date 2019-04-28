package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.selectdata;

/**
 * Servlet implementation class getmarkers
 */
@WebServlet("/getmarkers")
public class getmarkers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getmarkers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf8");
		request.setCharacterEncoding("utf8");
		String servicename=request.getParameter("servicename");
		String city=request.getParameter("city");
		String jsonString=selectdata.getmarkers(servicename,city);
		System.out.println(jsonString.toString());
		response.getWriter().write(jsonString.toString());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf8");
		request.setCharacterEncoding("utf8");
		String phone=request.getParameter("phone");
		String servicename=request.getParameter("servicename");
		String jsonString=selectdata.getmarkersinfo(phone,servicename);
		System.out.println(jsonString.toString());
		response.getWriter().write(jsonString.toString());
	}

}
