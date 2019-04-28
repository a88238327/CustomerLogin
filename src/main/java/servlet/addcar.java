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
import entity.WeiXinUtil;
import entity.base64tofile;
import entity.file;
import entity.getToken;
import entity.ocr;
import entity.selectdata;
import entity.updatecar;
import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;

/**
 * Servlet implementation class addcar
 */
@WebServlet("/addcar")
public class addcar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addcar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		HttpSession session=request.getSession();
		String phone=session.getAttribute("phone").toString();
		String selecttext=request.getParameter("selecttext");
		String pingpaixinghao=request.getParameter("pingpaixinghao");
		String fazhengriqi=request.getParameter("fazhengriqi");
		String shiyongxingzhi=request.getParameter("shiyongxingzhi");
		String fadongjihaoma=request.getParameter("fadongjihaoma");
		String haopaihaoma=request.getParameter("haopaihaoma");
		String suoyouren=request.getParameter("suoyouren");
		String zhuzhi=request.getParameter("zhuzhi");
		String zhuceriqi=request.getParameter("zhuceriqi");
		String cheliangshibiedaihao=request.getParameter("cheliangshibiedaihao");
		String cheliangleixing=request.getParameter("cheliangleixing");
		String jiancejilu=request.getParameter("jiancejilu");
		String hedingzaizhiliang=request.getParameter("hedingzaizhiliang");
		String zhengbeizhiliang=request.getParameter("zhengbeizhiliang");
		String waiguochicun=request.getParameter("waiguochicun");
		String hedingzairenshu=request.getParameter("hedingzairenshu");
		String zongzhiliang=request.getParameter("zongzhiliang");
		String zhunqianyinzongzhiliang=request.getParameter("zhunqianyinzongzhiliang");
		String danganhao=request.getParameter("danganhao");
		String imgurl_front=request.getParameter("img_front");
		String imgurl_back=request.getParameter("img_back");
		if(selectdata.selectcarnum(haopaihaoma))
		{
			response.getWriter().write("false");
		}
		else {
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置日期格式
			String createtime=df.format(new Date());
			updatecar.insertinfo(createtime,pingpaixinghao,fazhengriqi,shiyongxingzhi,fadongjihaoma,haopaihaoma,suoyouren,zhuzhi,zhuceriqi,cheliangshibiedaihao,cheliangleixing,jiancejilu.replace("-", "/"),hedingzaizhiliang,zhengbeizhiliang,waiguochicun,hedingzairenshu,zongzhiliang,danganhao,selecttext,phone);
			response.getWriter().write("true");
			if(base64tofile.GenerateImage(imgurl_front, file.vehicle_licensepath+phone+"_"+haopaihaoma+"_front.jpg"))
			{
				System.out.println(file.vehicle_licensepath+phone+"_"+haopaihaoma+"_front.jpg 正本行驶证存储成功");
				if(base64tofile.GenerateImage(imgurl_back, file.vehicle_licensepath+phone+"_"+haopaihaoma+"_back.jpg"))
				{
					System.out.println(file.vehicle_licensepath+phone+"_"+haopaihaoma+"_front.jpg 副本行驶证存储成功");
					EventLog.create("addcar", phone, haopaihaoma);
				}
			}
		}
	}

}
