package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.selectdata;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class gerenzhongxin
 */
@WebServlet("/gerenzhongxin")
public class gerenzhongxin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gerenzhongxin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		HttpSession session=request.getSession();
		if(session.getAttribute("phone")!=null)
		{
			String str=selectdata.getsharenumber(session.getAttribute("phone").toString());
			System.out.println(str);
			String phone=session.getAttribute("phone").toString();
			HashMap<String,String> map=new HashMap<>();
			map.put("phone",phone);
			map.put("count",str);
			JSONObject jsonObject=JSONObject.fromObject(map);

			response.getWriter().write(jsonObject.toString());
		}else {
			response.getWriter().write("phone_error");
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
