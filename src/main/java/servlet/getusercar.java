package servlet;

import entity.selectdata;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/getusercar")
public class getusercar extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf8");
        response.setCharacterEncoding("utf8");
        HttpSession session=request.getSession();
        if(session.getAttribute("phone")!=null)
        {
            String phone=session.getAttribute("phone").toString();
            String res= selectdata.getcars(phone);
            response.getWriter().write(res);

        }else {
            response.getWriter().write("needlogin");
        }
    }
}
