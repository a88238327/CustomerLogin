package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.EventLog;

/**
 * Servlet implementation class service
 */
@WebServlet("/service")
public class service extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public service() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		String servicename=request.getParameter("servicename");
		System.out.println(servicename);
		String url="";
		HttpSession session=request.getSession();
		if(session.getAttribute("phone")!=null)
		{
			String phone=session.getAttribute("phone").toString();
			if(servicename.equals("5折洗车"))
			{
				url="services.html?servicename="+servicename;
				System.out.println(url);
				EventLog.create("click", phone, "5折洗车");
				request.getRequestDispatcher(url).forward(request, response);
			}
			else if(servicename.equals("89折加油"))
			{
				url="http://www.youxinuk.com/carFamily.php?mod=me";
				System.out.println(url);
				EventLog.create("click", phone, "89折加油");
				response.sendRedirect(url);
			}
			else if(servicename.equals("7折维修")){
				url="services.html?servicename="+servicename;
				System.out.println(url);
				EventLog.create("click", phone, "7折维修");
				request.getRequestDispatcher(url).forward(request, response);
			}
			else if(servicename.equals("免费玻璃水")){
				url="services.html?servicename="+servicename;
				System.out.println(url);
				EventLog.create("click", phone, "免费玻璃水");
				request.getRequestDispatcher(url).forward(request, response);
			}
			else if(servicename.equals("车辆保养")){
				url="services.html?servicename="+servicename;
				System.out.println(url);
				EventLog.create("click", phone, "车辆保养");
				request.getRequestDispatcher(url).forward(request, response);
			}
			else if(servicename.equals("特色美食")){
				url="services.html?servicename="+servicename;
				System.out.println(url);
				EventLog.create("click", phone, "特色美食");
				response.sendRedirect("https://touch.go.qunar.com/food?bd_source=qunar_index");
			}
			else if(servicename.equals("海鲜套餐")){
				url="services.html?servicename="+servicename;
				System.out.println(url);
				EventLog.create("click", phone, "海鲜套餐");
				response.sendRedirect("https://huodong.ctrip.com/activity/search/?keyword=海鲜套餐");
			}
			else if(servicename.equals("景点门票")){
				url="services.html?servicename="+servicename;
				System.out.println(url);
				EventLog.create("click", phone, "景点门票");
				response.sendRedirect("http://touch.piao.qunar.com/?bd_source=baidupz&ouid=");
			}
			else if(servicename.equals("一日游")){
				url="services.html?servicename="+servicename;
				System.out.println(url);
				EventLog.create("click", phone, "一日游");
				response.sendRedirect("https://piao.qunar.com/touch/channel/onedaytour/index.htm?cat=in_track%3Dt_onedaytravelsy");
			}
			else if(servicename.equals("热带水果")){
				url="services.html?servicename="+servicename;
				System.out.println(url);
				EventLog.create("click", phone, "热带水果");
				response.sendRedirect("https://touch.go.qunar.com/food?bd_source=qunar_index&from=search&query=%E7%83%AD%E5%B8%A6%E6%B0%B4%E6%9E%9C&destId=300188&destName=%E4%B8%89%E4%BA%9A&destType=1&label=");
			}
			else if(servicename.equals("游艇飞机")){
				url="services.html?servicename="+servicename;
				System.out.println(url);
				EventLog.create("click", phone, "游艇飞机");
				response.sendRedirect("https://m.ctrip.com/webapp/ship?utmSource=busindex");
			}
			else if(servicename.equals("高尔夫")){
				url="services.html?servicename="+servicename;
				System.out.println(url);
				EventLog.create("click", phone, "高尔夫");
				response.sendRedirect("https://m.ctrip.com/webapp/activity/dest/k-keyword-0?target=activity&userinput=%E9%AB%98%E5%B0%94%E5%A4%AB&keyword=%E9%AB%98%E5%B0%94%E5%A4%AB&searchfrom=&ctm_ref=act_search_s_ukn&asstotal=11");
			}
			else if(servicename.equals("特产礼包")){
				url="services.html?servicename="+servicename;
				System.out.println(url);
				EventLog.create("click", phone, "特产礼包");
				response.sendRedirect("https://huodong.ctrip.com/activity/search/?keyword=%25e7%2589%25b9%25e4%25ba%25a7");
			}
			else if(servicename.equals("租车包车")){
				url="services.html?servicename="+servicename;
				System.out.println(url);
				EventLog.create("click", phone, "租车包车");
				response.sendRedirect("https://zuche.qunar.com/h5/isd/index.html?sf=14389&popup=close");
			}
			else if(servicename.equals("酒店住宿")){
				url="services.html?servicename="+servicename;
				System.out.println(url);
				EventLog.create("click", phone, "酒店住宿");
				response.sendRedirect("http://touch.qunar.com/h5/group/grouplist#tuan.city#");				
			}
			else if(servicename.equals("婚纱旅拍")){
				url="services.html?servicename="+servicename;
				System.out.println(url);
				EventLog.create("click", phone, "婚纱旅拍");
				response.sendRedirect("https://www.bj520.com/#baidu&PZ");
			}
			else if(servicename.equals("深度游玩")){
				url="services.html?servicename="+servicename;
				System.out.println(url);
				EventLog.create("click", phone, "深度游玩");
				response.sendRedirect("https://touch.dujia.qunar.com/tuan_list.qnr?et=home_group_t&it=nappt_guonei&arrive_type=domestic#/page=home/category=%E5%BD%93%E5%9C%B0%E6%B8%B8/mobFunction=%E8%A7%82%E5%85%89%E6%97%85%E6%B8%B8%2C%E7%89%B9%E8%89%B2%E4%BD%93%E9%AA%8C%2C%E6%99%AF%E9%85%92");
			}
			else if(servicename.equals("私人订制")){
				url="services.html?servicename="+servicename;
				System.out.println(url);
				EventLog.create("click", phone, "私人订制");
				response.sendRedirect("https://touch.dujia.qunar.com/freetrip/index.qunar?dep=&et=home_free_t&it=FreetripTouchin");
			}
			else if(servicename.equals("海上游玩")){
				url="services.html?servicename="+servicename;
				System.out.println(url);
				EventLog.create("click", phone, "海上游玩");
				response.sendRedirect("https://touch.dujia.qunar.com/p/cr/home?et=home_yl_t#/");
			}
			else if(servicename.equals("购车租车")){
				url="services.html?servicename="+servicename;
				System.out.println(url);
				EventLog.create("click", phone, "购车租车");
				request.getRequestDispatcher("shouye.html").forward(request, response);
			}
			else if(servicename.equals("一键救援")){
				url="services.html?servicename="+servicename;
				System.out.println(url);
				EventLog.create("click", phone, "一键救援");
				request.getRequestDispatcher(url).forward(request, response);
			}
						
			
		}
		else {
			response.sendRedirect("login.html");
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
