package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Create_file;
import entity.DataUrl;
import entity.DataUser;
import entity.Read_File;
import entity.selectdata;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class shareservlet
 */
@WebServlet("/shareservlet")
public class shareservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public shareservlet() {
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
			String phone=session.getAttribute("phone").toString();
			Connection conn=null;
			Statement stmt=null;
			PreparedStatement pstmt	= null ;
			String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
			String username=new DataUser().getUsername();//数据库用户名
			String DBpassword=new DataUser().getPassword();//数据库密码
			String sql="select * from leaflet";//查询语句
			System.out.println(sql);
			String DBurl=new DataUrl().getUrl();//连接数据库的地址
			try{
				Class.forName(driver);//加载驱动器类
				conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
				//建立处理的SQL语句
				pstmt = conn.prepareStatement(sql) ;
				ResultSet rs=pstmt.executeQuery();
				
			//创建返回的json数组
			
			//创建目录结果集
			//创建地址队列
			List<HashMap<String , String >> list=new ArrayList<HashMap<String , String >>();
			try {
				while(rs.next())
				{
					HashMap<String , String > map=new HashMap<String, String>();
					//查询文件是否存在
					//文件存在
					if(Read_File.file_exists(phone,rs.getString("leaflet_name")))
					{
						map.put("src","http://cloud.hnjtbf.com//customer_img//qr//"+phone+rs.getString("leaflet_name"));
						list.add(map);
					}
					//文件不存在
					else
					{
						//创建目录对应的文件并存储
						if(Create_file.create_leaflet(phone,rs))
						{
							map.put("src","http://cloud.hnjtbf.com//customer_img//qr//"+phone+rs.getString("leaflet_name"));
							list.add(map);
						}
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HashMap<String , String > map=new HashMap<String, String>();
			map.put("src","http://cloud.hnjtbf.com//customer_img//qr//"+phone+"_qr.png");
			list.add(map);		
			
			JSONArray jsonObject=JSONArray.fromObject(list);
			System.out.println(jsonObject.toString());
			response.getWriter().write(jsonObject.toString());
			
		}catch (Exception e) {
			System.out.println(e);
		}
		
		}
		else {
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
