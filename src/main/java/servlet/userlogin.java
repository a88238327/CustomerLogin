package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import Tuil.Util;
import entity.EventLog;
import entity.selectdata;
import entity.updateUser;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class userlogin
 */
@WebServlet("/userlogin")
public class userlogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public userlogin() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        response.setCharacterEncoding("utf8");
        if (request.getParameter("code") == null) {
            String url=request.getRequestURL().toString()+ "?"+(request.getQueryString()).toString();
            System.out.println(url);
            response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxafe6999b4d77754a&redirect_uri="+url+"&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
        } else {
            String code = request.getParameter("code");
            System.out.println(code);
            String result = Util.get("https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxafe6999b4d77754a&secret=85642ddb58fc2fc7b1c52720e298485e&code=" + code + "&grant_type=authorization_code ");
            JSONObject jsonObject = JSONObject.fromObject(result);
            System.out.println(jsonObject.getString("openid"));
            //通过openid查询该用户是否注册
            String openid = jsonObject.getString("openid");
            String phone = selectdata.getphone(openid);
            HttpSession session = request.getSession();
            if (!phone.equals("")) {
                session.setAttribute("phone", phone);
                session.setAttribute("openid", openid);
                String go = request.getParameter("go");
                System.out.println(go);
                if (go != null) {
                    if (go.equals("buybaoxian")) {
                        response.sendRedirect("https://cloud.hnjtbf.com/CustomerLogin/cars.html");
                    } else if (go.equals("mianfeinianshen")) {
                        response.sendRedirect("https://cloud.hnjtbf.com/CustomerLogin/shenqingchoose.html?servicename=%E5%85%8D%E8%B4%B9%E5%B9%B4%E5%AE%A1");
                    }
                } else {
                    response.sendRedirect("shouye.html");
                }


            } else {
                response.sendRedirect("zhuce.html");
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
