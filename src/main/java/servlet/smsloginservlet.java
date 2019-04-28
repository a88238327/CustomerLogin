package servlet;
import entity.CheckUser;
import entity.EventLog;
import entity.Sms;
import entity.postSms;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;

/**
 * Servlet implementation class smsloginservlet
 */
@WebServlet("/smsloginservlet")
public class smsloginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public smsloginservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		String phone=request.getParameter("phone");	
		HttpSession session=request.getSession();
		//验证用户是否存在
		if(request.getParameter("type").equals("check"))
		{
			String phone1=session.getAttribute("phone").toString();
			if(phone.equals(phone1))
			{
				String code=request.getParameter("code");
				String codString=session.getAttribute("code").toString();
				if(code.equals(codString))
				{
					if(CheckUser.checkusersms(phone,request))
					{
						session.setAttribute("phone", phone);
						EventLog.create("SmsLogin", phone, "success");
						response.getWriter().write("true");
					}
					else {
						EventLog.create("SmsLogin", phone, "failure");
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
		else {
			String code1=postSms.login(phone);
			session.setAttribute("phone", phone);
			
			session.setAttribute("code", code1);
			response.getWriter().write("true");
		}
	}
}
