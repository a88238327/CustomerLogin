package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.CheckUser;

/**
 * Servlet implementation class selectopenid
 */
@WebServlet("/selectopenid")
public class selectopenid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public selectopenid() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		HttpSession session=request.getSession();
		String phone=session.getAttribute("phone").toString();
		if(CheckUser.checkuser_openid(phone))
		{
			response.sendRedirect("shouye.html");
		}
		else {
			response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxafe6999b4d77754a&redirect_uri=https://cloud.hnjtbf.com/CustomerLogin/bangdingweixinservlet&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
