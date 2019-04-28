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
 * Servlet implementation class sharesignup
 */
@WebServlet("/sharesignup")
public class sharesignup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sharesignup() {
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
		String type=request.getParameter("type");
		//check
		if(request.getParameter("type").equals("check"))
		{
			if(!CheckUser.checkusersms(phone,request))
			{
				String code=postSms.signup(phone);
				if(!code.equals(""))
				{
					HttpSession session=request.getSession();
					session.setAttribute("phone", phone);
					session.setAttribute("code", code);
					response.getWriter().write("true");	
				}
				else {
					response.getWriter().write("false");
				}
				
			}
			else {	
				response.getWriter().write("exists");
			}
		}
		//signup
		else {
			//验证号码不存在
			HttpSession session=request.getSession();
			String codestring=session.getAttribute("code").toString();
			String code=request.getParameter("code");
			String phone1=session.getAttribute("phone").toString();
			if(phone.equals(phone1))
			{
				if(codestring.equals(code))
				{
					if(!CheckUser.checkusersms(phone,request))
					{
						String share=request.getParameter("share");
						String password=request.getParameter("password");
						if(createUser.createuser_sales(phone, password,share))
						{
						
							session.setAttribute("phone", phone);
							session.setAttribute("outter", share);
							EventLog.create("Signup_share", phone, "");
							response.getWriter().write("true");
						}
					}
					else {
						EventLog.create("Signup_share", phone, "failure");
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
