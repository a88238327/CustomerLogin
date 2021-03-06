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
 * Servlet implementation class bangdingweixin
 */
@WebServlet("/bangdingweixin")
public class bangdingweixin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bangdingweixin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		String phone =request.getParameter("phone");
		if(request.getParameter("code")==null)
		{
			System.out.println("请求code");
			response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxafe6999b4d77754a&redirect_uri=http://cloud2.hnjtbf.com/CustomerLogin/bangdingweixin?phone="+phone+"&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
		}
		else {
			System.out.println("获取code");
			String code=request.getParameter("code");
			System.out.println(code);
			String result=Util.get("https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxafe6999b4d77754a&secret=85642ddb58fc2fc7b1c52720e298485e&code="+code+"&grant_type=authorization_code ");
			JSONObject jsonObject=JSONObject.fromObject(result);
			System.out.println(result);
			System.out.println(jsonObject.getString("openid"));
			HttpSession session=request.getSession();
			System.out.println("获取手机号");
			if(updateUser.updateOpenid(phone,jsonObject.getString("openid").toString()))
			{
				System.out.println("绑定openid成功");
				EventLog.create("UpdataOpenid", phone, "");
				response.getWriter().write("<meta charset=\"UTF-8\"><script>alert(\"绑定成功\");location.href='https://cloud.hnjtbf.com/CustomerLogin/gerenzhongxin.html';</script>");
			}
			else {
				EventLog.create("UpdataOpenid", phone, "failure");
				response.getWriter().write("false");
			}
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
