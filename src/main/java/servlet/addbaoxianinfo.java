package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;

import entity.base64tofile;
import entity.file;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/addbaoxianinfo")
public class addbaoxianinfo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf8");
        request.setCharacterEncoding("utf8");
        HttpSession session=request.getSession();
        if (session.getAttribute("phone")!=null)
        {
            String phone ="17508910598";//session.getAttribute("phone").toString();
            String img_jiaoqiang=request.getParameter("img_jiaoqiang");
            String img_shangye=request.getParameter("img_shangye");
            String buybaoxian=request.getParameter("buybaoxian");
            String carnum=request.getParameter("carnum");
            String jiaoqiangxian_date_start=request.getParameter("jiaoqiangxian_date_start");
            String jiaoqiangxian_date_end=request.getParameter("jiaoqiangxian_date_end");
            String shangyexian_date_start=request.getParameter("shangyexian_date_start");
            String shangyexian_date_end=request.getParameter("shangyexian_date_end");
            if (base64tofile.GenerateImage(img_jiaoqiang, file.jiaoqiangxian+carnum+"交强险.jpg")){
                System.out.println("交强险存储成功");
            }else {
                response.getWriter().write("false");
                return;
            }
            if (base64tofile.GenerateImage(img_shangye, file.shangyexian+carnum+"商业险.jpg")){
                System.out.println("商业险存储成功");
                return;
            }else {
                response.getWriter().write("false");
            }
            JSONObject jsonObject=JSONObject.fromObject(buybaoxian);
            Iterator<String> iterator=jsonObject.keys();
            while (iterator.hasNext()){
                String key=iterator.next();
                System.out.println(key);
                if (key.equals("交强险"))
                {
                    System.out.println(jsonObject.getString(key));
                    JSONArray jsonArray=JSONArray.fromObject(jsonObject.getString(key));
                    if (jsonArray.getString(0).equals("投保"))
                    {
                        String img_name=carnum+"交强险.jpg";
                        String Excluding_deductible=jsonArray.get(1).toString();
                        insert.addbaoxianinfo(key,Excluding_deductible,carnum,phone,jiaoqiangxian_date_start,jiaoqiangxian_date_end,jsonArray.getString(0),img_name);
                    }
                }
                else {
                    System.out.println(jsonObject.getString(key));
                    JSONArray jsonArray=JSONArray.fromObject(jsonObject.getString(key));
                    if (!jsonArray.getString(0).equals("不投保"))
                    {
                        String img_name=carnum+"商业险.jpg";
                        String Excluding_deductible=jsonArray.get(1).toString();
                        insert.addbaoxianinfo(key,Excluding_deductible,carnum,phone,shangyexian_date_start,shangyexian_date_end,jsonArray.getString(0),img_name);
                    }
                }
            }
            response.getWriter().write("true");

        }else {
            response.getWriter().write("needlogin");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
