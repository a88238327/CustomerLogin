package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.CheckUser;
import entity.EventLog;
import entity.selectdata;

/**
 * Servlet implementation class loginservlet
 */
@WebServlet("/loginservlet")
public class loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies=request.getCookies();
		   String phone = "";
		   String password = "";
		   if(cookies!=null)
		   {
		       for(int i=0;i<cookies.length;i++)
		       {
		           String name = cookies[i].getName();
			       if("phone".equals(name))
			       {
			    	   phone = cookies[i].getValue();
			       }
			       else if("password".equals(name))
			       {
		               password = cookies[i].getValue();
		           }
		       }
		       if(phone.equals("")&&password.equals(""))
			   {
				   response.sendRedirect("login.html");
			   }
		       else {
		    	   if(selectdata.userphonepassword(phone,password))
		    	   {
		    		   HttpSession session=request.getSession();
		    		   session.setAttribute("phone", phone);
		    		   response.sendRedirect("shouye.html");
		    	   }
		       }
		   }	   
		   else
		   {
			   response.sendRedirect("login.html");
		   }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		String phone=request.getParameter("phone");
		String password=request.getParameter("password");
		String check=request.getParameter("check");
		HttpSession session=request.getSession();
		if(CheckUser.checkuser(phone, password, request))
		{
			session.setAttribute("phone", phone);
			if(check.equals("true"))
			{
				Cookie cookie = new Cookie("phone", phone);
				Cookie cookie2 = new Cookie("password",password);
				//设置保存时间
				cookie.setMaxAge(30*24*60*60);
				cookie2.setMaxAge(30*24*60*60);
				//设置保存路径
				cookie.setPath(request.getContextPath()+"/");
				//添加到响应头
				response.addCookie(cookie);
				response.addCookie(cookie2);
			}
			EventLog.create("Login", phone, "password");
			response.getWriter().write("true");
	
		}else {
			EventLog.create("Login_Password", phone, "failure");
			response.getWriter().write("false");
		}
	}

}
