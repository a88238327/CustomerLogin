package servlet;

import entity.selectdata;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/getcarbaoxianinfo")
public class getcarbaoxianinfo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        response.setCharacterEncoding("utf8");
        String carnum=request.getParameter("carnum");
        HashMap<String,String> map=new HashMap<>();
        map=selectdata.getcarsinfo(carnum);
        String str= selectdata.getcarbaoxianinfo(carnum);
        map.put("insurance",str);
        JSONObject jsonObject=JSONObject.fromObject(map);
        response.getWriter().write(jsonObject.toString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
