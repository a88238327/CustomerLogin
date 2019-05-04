package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.insert;
import entity.selectdata;

/**
 * Servlet implementation class accident
 */
@WebServlet("/accident")
public class accident extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public accident() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("utf8");
//        response.setCharacterEncoding("utf8");
//        HttpSession session = request.getSession();
//        String servicename = request.getParameter("servicename");
//        if (session.getAttribute("phone") != null) {
//            String phone = session.getAttribute("phone").toString();
//            //判断商业险是否购买是否在有效期内或者未有投保信息
//            if (selectdata.commercialEnable(phone)) {
//                response.sendRedirect("accident.html");
//            } else {
//                String eventcontent = servicename;
//                if (!selectdata.apponintment_exist(phone, eventcontent)) {
//                    //insert.appointment(phone, eventcontent);
//                }
//                //response.getWriter().write("<meta charset=\"UTF-8\"><script>alert(\"用户未满足要求，已为您安排工作人员，稍后联系您！\");location.href=\"shouye.html\";</script>");
//                request.getRequestDispatcher("tip1.html").forward(request, response);
//            }
//        } else {
//            response.sendRedirect("userlogin");
//        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        response.setCharacterEncoding("utf8");
        HttpSession session = request.getSession();
        String carnum = request.getParameter("carnum");
        String content = request.getParameter("content");
        if (session.getAttribute("phone") != null) {
            String phone = session.getAttribute("phone").toString();
            insert.appointment(phone, content,carnum);
            response.getWriter().write("true");
        } else {
            response.getWriter().write("needlogin");
        }
    }


}
