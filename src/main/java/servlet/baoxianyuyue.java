package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/baoxianyuyue")
public class baoxianyuyue extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf8");
        response.setCharacterEncoding("utf8");
        HttpSession session = request.getSession();
//        if (session.getAttribute("phone")!=null)
//        {
        String phone = "17508910598";//session.getAttribute("phone").toString();
        String buybaoxian = request.getParameter("buybaoxian");
        System.out.println(buybaoxian);
        String carnum = request.getParameter("carnum");
        insert.addbaoxianyuyue(phone, buybaoxian, carnum);
        response.getWriter().write("true");
//        }else {
//            response.getWriter().write("needlogin");
//        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
