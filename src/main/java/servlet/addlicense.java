package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.EventLog;
import entity.base64tofile;
import entity.file;
import entity.selectdata;
import entity.updatecar;

/**
 * Servlet implementation class addlicense
 */
@WebServlet("/addlicense")
public class addlicense extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addlicense() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		HttpSession session=request.getSession();
		String phone=session.getAttribute("phone").toString();
		String img_front=request.getParameter("img_front");
		String img_back=request.getParameter("img_back");
		String danganhao=request.getParameter("danganhao");
		String xingming=request.getParameter("xingming");
		String xingbie=request.getParameter("xingbie");
		String zhuzhi=request.getParameter("zhuzhi");
		String guoji=request.getParameter("guoji");
		String zhunjiachexing=request.getParameter("zhunjiachexing");
		String zhenghao=request.getParameter("zhenghao");
		String chucilingzhengriqi=request.getParameter("chucilingzhengriqi");
		String youxiaoqizhi=request.getParameter("youxiaoqizhi");
		
		if(selectdata.selectlicense(zhenghao))
		{
			response.getWriter().write("false");
		}
		else {
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置日期格式
			String createtime=df.format(new Date());
			updatedrivingLicense.insertinfo(createtime,xingming,xingbie,zhuzhi,guoji,zhunjiachexing,zhenghao,danganhao,chucilingzhengriqi,youxiaoqizhi,phone);
			response.getWriter().write("true");
			if(base64tofile.GenerateImage(img_front, file.driving_licencepath+phone+"_"+zhenghao+"_front.jpg"))
			{
				System.out.println(file.driving_licencepath+phone+"_"+zhenghao+"_front.jpg 正本行驶证存储成功");
				if(base64tofile.GenerateImage(img_back, file.driving_licencepath+phone+"_"+zhenghao+"_back.jpg"))
				{
					System.out.println(file.driving_licencepath+phone+"_"+zhenghao+"_front.jpg 副本行驶证存储成功");
					EventLog.create("addcar", phone, zhenghao);
				}
			}
		}
	}

}
