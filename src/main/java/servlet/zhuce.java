package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.CheckUser;
import entity.EventLog;
import entity.createUser;
import entity.postSms;

/**
 * Servlet implementation class zhuce
 */
@WebServlet("/zhuce")
public class zhuce extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public zhuce() {
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
		String phone=request.getParameter("phone");
		String password=request.getParameter("password");
		String type=request.getParameter("type");
		//check
		if(request.getParameter("type").equals("check"))
		{
			//存在返回true
			if(CheckUser.checkusersms(phone,request))
			{
				response.getWriter().write("exists");

			}
			else {
				String code=postSms.signup(phone);
				HttpSession session=request.getSession();
				if(!code.equals(""))
				{
					session.setAttribute("code", code);
					session.setAttribute("phone", phone);
					System.out.println(code);
					response.getWriter().write("true");
				}
				else {
					response.getWriter().write("false");
				}
			}
		}
		//signup
		System.out.println(request.getParameter("type"));
		if(request.getParameter("type").equals("sign up")) {
			//验证号码不存在
			HttpSession session=request.getSession();
			String codestring=session.getAttribute("code").toString();
			String code=request.getParameter("code");
			String phone1=session.getAttribute("phone").toString();
			if(phone.equals(phone1))
			{
				if(code.equals(codestring))
				{
					if(!CheckUser.checkusersms(phone,request))
					{
						if(createUser.createuser(phone, password))
						{
							session.setAttribute("phone", phone);
							session.setAttribute("outter", "");
							EventLog.create("SignUp", phone, "success");
							response.getWriter().write("true");
						}
						
					}
					else {
						EventLog.create("SignUp", phone, "failure");
						response.getWriter().write("false");
					}
				}
				else {
					response.getWriter().write("code_error");
				}
				
			}
			else {
				response.getWriter().write("phone_error");
			}
			
		}
		
	}
	

}
