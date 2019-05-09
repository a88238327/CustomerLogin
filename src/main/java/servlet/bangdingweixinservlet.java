package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Tuil.Util;
import entity.EventLog;
import entity.updateUser;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class bangdingweixinservlet
 */
@WebServlet("/bangdingweixinservlet")
public class bangdingweixinservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bangdingweixinservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		String code=request.getParameter("code");
		System.out.println(code);
		String result=Util.get("https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxafe6999b4d77754a&secret=85642ddb58fc2fc7b1c52720e298485e&code="+code+"&grant_type=authorization_code ");
		JSONObject jsonObject=JSONObject.fromObject(result);
		System.out.println(jsonObject.getString("openid"));
		HttpSession session=request.getSession();
		String phone=session.getAttribute("phone").toString();
		if(updateUser.updateOpenid(phone,jsonObject.getString("openid").toString()))
		{
			System.out.println("绑定openid成功");
			EventLog.create("UpdataOpenid", phone, "");
			response.sendRedirect("shouye.html");
		}
		else {
			EventLog.create("UpdataOpenid", phone, "failure");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		String APPID="wx76c29bd26cf0fd12";
//		String REDIRECT_URI="http://cloud2.hnjtbf.com/CustomerLogin/bangdingweixinservlet";
//		//String REDIRECT_URI="http://localhost:8080/CustomerLogin/bangdingweixin.html";
//		
//		String SCOPE="snsapi_base";
//		String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxafe6999b4d77754a&redirect_uri=cloud.hnjtbf.com/CustomerLogin/bangdingweixinservlet&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
//		System.out.println(url);
//		String str=Util.get(url);
//		response.getWriter().write("true");
	}

}
