package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

import entity.DB_Info;
import entity.fuli;
import entity.shouyeCarousel;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class shouye
 */
@WebServlet("/shouye")
public class shouye extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public shouye() {
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
		//String imgpath="C:\\apache-tomcat-7.0.92\\webapps\\qr_img\\";
		
		JSONArray jsonObject1=JSONArray.fromObject(getcarousel());
		JSONArray jsonObject2=JSONArray.fromObject(getfuli());
		JSONArray jsonObject3=JSONArray.fromObject(getzengzhi());
		System.out.println(jsonObject1.toString());
		System.out.println(jsonObject2.toString());
		System.out.println(jsonObject3.toString());
		HashMap<String , List> map=new HashMap<String, List>();
		map.put("carousel", jsonObject1);
		map.put("fuli", jsonObject2);
		map.put("zengzhi", jsonObject3);
		JSONObject jsonObject4=JSONObject.fromObject(map);
		System.out.println(jsonObject4.toString());
		response.getWriter().write(jsonObject4.toString());
		
		
		
	}
	
	private Object getzengzhi() {
		String imgpath="http://cloud.hnjtbf.com//img//shouye//";
		List list2 = new ArrayList<fuli>();
		Connection conn1=null;
		Statement stmt1=null;
		ResultSet rs1= null ;
		PreparedStatement pstmt1= null ;
		DB_Info db_Info=new DB_Info();
		String driver1=db_Info.getDriver();//驱动类
		String UN1=db_Info.getUsername();//数据库用户名
		String PW1=db_Info.getPassword();//数据库密码
		String url1=db_Info.getUrl();//连接数据库的地址
		String sql1="select * from zengzhi" ;//查询语句
		
		try{
			Class.forName(driver1);//加载驱动器类
			conn1=DriverManager.getConnection(url1,UN1,PW1);//建立连接
			//建立处理的SQL语句
			pstmt1 = conn1.prepareStatement(sql1) ;
			System.out.println(sql1);
			rs1=pstmt1.executeQuery();//查询数据库并生成结果集
			while (rs1.next()) {
				fuli f=new fuli(rs1.getString("href"), imgpath+rs1.getString("src"),rs1.getString("title"));
				System.out.println("ok");
				list2.add(f);
			}
			rs1.close();//关闭结果集
			pstmt1.close();//关闭SQL语句集
			conn1.close();//关闭连接
		}catch (Exception e) {
		}
		return list2;
	}

	public List getcarousel() {
		List list1 = new ArrayList<shouyeCarousel>();
		String imgpath="http://cloud.hnjtbf.com//img//shouye//";
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs= null ;
		PreparedStatement pstmt	= null ;
		DB_Info db_Info=new DB_Info();
		String driver=db_Info.getDriver();//驱动类
		String UN=db_Info.getUsername();//数据库用户名
		String PW=db_Info.getPassword();//数据库密码
		String sql="select * from carousel where at='shouye'" ;//查询语句
		String url=db_Info.getUrl();//连接数据库的地址
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(url,UN,PW);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(sql);
			rs=pstmt.executeQuery();//查询数据库并生成结果集
			while (rs.next()) {
				shouyeCarousel carousel=new shouyeCarousel(rs.getString("href"), imgpath+rs.getString("src"));
				list1.add(carousel);
			}
			rs.close();//关闭结果集
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
		}catch (Exception e) {
		}
		return list1;
	
	}
	public List getfuli() {
		String imgpath="http://cloud.hnjtbf.com//img//shouye//";
		List list2 = new ArrayList<fuli>();
		Connection conn1=null;
		Statement stmt1=null;
		ResultSet rs1= null ;
		PreparedStatement pstmt1= null ;
		DB_Info db_Info=new DB_Info();
		String driver1=db_Info.getDriver();//驱动类
		String UN1=db_Info.getUsername();//数据库用户名
		String PW1=db_Info.getPassword();//数据库密码
		String url1=db_Info.getUrl();//连接数据库的地址
		String sql1="select * from fuli ORDER BY sn" ;//查询语句
		
		try{
			Class.forName(driver1);//加载驱动器类
			conn1=DriverManager.getConnection(url1,UN1,PW1);//建立连接
			//建立处理的SQL语句
			pstmt1 = conn1.prepareStatement(sql1) ;
			System.out.println(sql1);
			rs1=pstmt1.executeQuery();//查询数据库并生成结果集
			while (rs1.next()) {
				fuli f=new fuli(rs1.getString("href"), imgpath+rs1.getString("src"),rs1.getString("title"));
				System.out.println("ok");
				list2.add(f);
			}
			rs1.close();//关闭结果集
			pstmt1.close();//关闭SQL语句集
			conn1.close();//关闭连接
		}catch (Exception e) {
		}
		return list2;
	}
}
