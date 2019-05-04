package servlet;

import entity.updata;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/updatatixing")
public class updatatixing extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf8");
        request.setCharacterEncoding("utf8");
        HttpSession session=request.getSession();
        if (session.getAttribute("phone")!=null)
        {
            String phone=session.getAttribute("phone").toString();
            String carnum=request.getParameter("carnum");
            String remind_content=request.getParameter("remind_content");
            String stauts=request.getParameter("stauts");
            updata.updatatixing(phone,carnum,remind_content,stauts);
            response.getWriter().write("true");
        }else {
            response.getWriter().write("needlogin");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
