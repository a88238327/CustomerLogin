package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

import Tuil.Util;
import entity.CheckUser;
import entity.EventLog;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class wechatlogin
 */
@WebServlet("/wechatlogin")
public class wechatlogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public wechatlogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		response.setContentType("text/html;charset=UTF-8");
		String code=request.getParameter("code");
		System.out.println(code);
		String result=Util.get("https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxafe6999b4d77754a&secret=85642ddb58fc2fc7b1c52720e298485e&code="+code+"&grant_type=authorization_code ");
		JSONObject jsonObject=JSONObject.fromObject(result);
		System.out.println(jsonObject.getString("openid"));
		System.out.println(result);
		String openid=jsonObject.getString("openid");
		HttpSession session=request.getSession();
		if(!CheckUser.checkuseropenid(openid).equals("")) {
			session.setAttribute("phone", CheckUser.checkuseropenid(openid));
			EventLog.create("Login_Wechat", CheckUser.checkuseropenid(openid), "success");
			response.sendRedirect("shouye.html");
		}
		else {
			EventLog.create("SmsLogin", CheckUser.checkuseropenid(openid), "failure");
			response.getWriter().write("<script>alert('未登录过，请先登录一次');location.href='login.html'</script>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
