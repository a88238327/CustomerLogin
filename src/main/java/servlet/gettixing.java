package servlet;

import entity.insert;
import entity.selectdata;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/gettixing")
public class gettixing extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf8");
        request.setCharacterEncoding("utf8");
        HttpSession session=request.getSession();
        String phone=session.getAttribute("phone").toString();
        String carnum=request.getParameter("carnum");
        String res=selectdata.hasremind(carnum,phone);
        if (res.equals("")) {
            insert.addremind(carnum, phone);
            res = selectdata.hasremind(carnum, phone);
        }
        response.getWriter().write(res);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
