package entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class selectdata {

	public static boolean userphone(String phone) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select * from sellers where phone='"+phone+"'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next())
			{
				rs.close();
				pstmt.close();//关闭SQL语句集
				conn.close();//关闭连接
				return true;//手机号已存在
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
		}catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public static boolean userphonepassword(String phone, String password) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select * from customer where USER_FORM_INFO_FLAG_MOBILE='"+phone+"' and password='"+password+"'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next())
			{
				rs.close();
				pstmt.close();//关闭SQL语句集
				conn.close();//关闭连接
				return true;//手机号已存在
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
		}catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public static boolean usermaster(String phone) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select mastername from sellers where phone='"+phone+"' and mastername is not null";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next())
			{
				rs.close();
				pstmt.close();//关闭SQL语句集
				conn.close();//关闭连接
				return true;//手机号已存在
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
		}catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public static String getservice(String phone) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select name from seller_service where sellerphone='"+phone+"' and enable='1'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		HashMap<String , String > map=new HashMap<String, String>();
		List list= new ArrayList();	
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next())
			{			
	
				list.add(rs.getString("name"));
//				return true;//手机号已存在
			}
			if(list.isEmpty())
			{
				rs.close();
				pstmt.close();//关闭SQL语句集
				conn.close();//关闭连接
				return "null";
			}
		
			
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		JSONArray jsonObject=JSONArray.fromObject(list);
		System.out.println(jsonObject.toString());
		return jsonObject.toString();
	}

	

	public static boolean usercar(String number) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select 号牌号码 from usercars where 号牌号码='"+number+"'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();	
			if(rs.next())
			{
				return true;
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
		}catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	public static String getuserphone(String number) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select phone from usercars where 号牌号码='"+number+"'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();	
			if(rs.next())
			{
				String phone=rs.getString("phone");
				return phone;
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
		}catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public static String getopenid(String userphone) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select openid from customer where USER_FORM_INFO_FLAG_MOBILE='"+userphone+"'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();	
			if(rs.next())
			{
				String openid=rs.getString("openid");
				return openid;
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
		}catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public static String getsellername(String sellerphone) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select name from sellers where phone='"+sellerphone+"'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();	
			if(rs.next())
			{
				String name=rs.getString("name");
				return name;
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
		}catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public static String getsellerphone(String orderID) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select sellerphone from userorder where orderID='"+orderID+"'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();	
			if(rs.next())
			{
				String sellerphone=rs.getString("sellerphone");
				return sellerphone;
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
		}catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public static String getsellerinfo(String sellerphone) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select name,touxiang from sellers where phone='"+sellerphone+"'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();	
			if(rs.next())
			{
				HashMap<String , String > map=new HashMap<String, String>();
				map.put("name", rs.getString("name"));
				map.put("touxiang", rs.getString("touxiang"));
				JSONObject jsonObject=JSONObject.fromObject(map);
				return jsonObject.toString();
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
		}catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public static boolean selectevaluation(String orderID) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select orderID from evaluation where orderID='"+orderID+"'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();	
			if(rs.next())
			{
				rs.close();
				pstmt.close();//关闭SQL语句集
				conn.close();//关闭连接
				return false;
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
		}catch (Exception e) {
			System.out.println(e);
		}
		return true;
	}

	public static String getqr(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public static boolean selectcarnum(String haopaihaoma) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select 号牌号码 from cars where 号牌号码='"+haopaihaoma+"'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();	
			if(rs.next())
			{
				rs.close();
				pstmt.close();//关闭SQL语句集
				conn.close();//关闭连接
				return true;
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
		}catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	public static String carbassinfo(String phone) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select 号牌号码,车牌颜色 from cars where phone='"+phone+"'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		ArrayList<HashMap<String , String >> list=new ArrayList<HashMap<String,String>>();
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();	
			while(rs.next())
			{
				HashMap<String , String > map=new HashMap<String, String>();
				map.put("number", rs.getString("号牌号码"));
				map.put("color", rs.getString("车牌颜色"));
				list.add(map);
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
			if(list.isEmpty())
			{
				return "";
			}
			
			JSONArray jsonArray=JSONArray.fromObject(list);
			return jsonArray.toString();
			
		}catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public static String getmarkers(String servicename, String city) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select sellers.phone,sellers.lat,sellers.lng from sellers,seller_service where sellers.phone=seller_service.sellerphone and seller_service.enable=1 and seller_service.name='"+servicename+"' and sellers.city='"+city+"'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		ArrayList<HashMap<String , String >> list=new ArrayList<HashMap<String,String>>();
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();	
			while(rs.next())
			{
				HashMap<String , String > map=new HashMap<String, String>();
				map.put("phone", rs.getString("phone"));
				map.put("lat", rs.getString("lat"));
				map.put("lng", rs.getString("lng"));
				list.add(map);
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
			if(list.isEmpty())
			{
				return "";
			}
			
			JSONArray jsonArray=JSONArray.fromObject(list);
			return jsonArray.toString();
			
		}catch (Exception e) {
			System.out.println(e);
		}
		
		return null;
	}

	public static String getmarkersinfo(String phone,String servicename) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select name,address,sellerID,touxiang from sellers where phone='"+phone+"'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		HashMap<String , String > map=new HashMap<String, String>();
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();	
			if(rs.next())
			{
				
				map.put("name", rs.getString("name"));
				map.put("address", rs.getString("address"));
				map.put("sellerID", rs.getString("sellerID"));
				map.put("touxiang", rs.getString("touxiang"));
				map.put("compelet", selectdata.getcountcomplete(phone, servicename));
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
			JSONObject jsonObject=JSONObject.fromObject(map);
			return jsonObject.toString();
			
		}catch (Exception e) {
			System.out.println(e);
		}
		return "";
	}

	public static String getuserorders(String phone) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select sellerphone,name,orderID,status from userorder where userphone='"+phone+"'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		ArrayList<HashMap<String , String >> list=new ArrayList<HashMap<String,String>>();
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();	
			while(rs.next())
			{
				HashMap<String , String > map=new HashMap<String, String>();
				map.put("touxiang", selectdata.getsellertouxiang(rs.getString("sellerphone")));
				map.put("name", selectdata.getsellername(rs.getString("sellerphone")));
				map.put("status", rs.getString("status"));
				map.put("content", rs.getString("name"));
				map.put("orderID", rs.getString("orderID"));
				map.put("evaluation", selectdata.getevaluation(rs.getString("orderID")));
				list.add(map);
			}
			if(list.isEmpty())
			{
				return "";
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
			JSONArray jsonObject=JSONArray.fromObject(list);
			return jsonObject.toString();
			
		}catch (Exception e) {
			System.out.println(e);
		}
		return "";
	}

	private static String getevaluation(String orderID) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select orderID from evaluation where orderID='"+orderID+"'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		HashMap<String , String > map=new HashMap<String, String>();
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();	
			if(rs.next())
			{
				return "true";
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
			
		}catch (Exception e) {
			System.out.println(e);
		}
		return "false";
	}

	private static String getsellertouxiang(String phone) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select touxiang from sellers where phone='"+phone+"'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		HashMap<String , String > map=new HashMap<String, String>();
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();	
			if(rs.next())
			{
				return rs.getString("touxiang");
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
			JSONObject jsonObject=JSONObject.fromObject(map);
			return jsonObject.toString();
			
		}catch (Exception e) {
			System.out.println(e);
		}
		return "";
	}

	public static String getsharenumber(String phone) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select count(openid) as count from customer where share='"+phone+"' and openid is not null";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		HashMap<String , String > map=new HashMap<String, String>();
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();	
			if(rs.next())
			{
				return rs.getString("count");
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
			JSONObject jsonObject=JSONObject.fromObject(map);
			return jsonObject.toString();
			
		}catch (Exception e) {
			System.out.println(e);
		}
		return "";
	}

	public static String getphone(String openid) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select USER_FORM_INFO_FLAG_MOBILE from customer where openid='"+openid+"' and USER_FORM_INFO_FLAG_MOBILE is not null";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		HashMap<String , String > map=new HashMap<String, String>();
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();	
			if(rs.next())
			{
				return rs.getString("USER_FORM_INFO_FLAG_MOBILE");
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
			JSONObject jsonObject=JSONObject.fromObject(map);
			return jsonObject.toString();
			
		}catch (Exception e) {
			System.out.println(e);
		}
		return "";
	}

	public static boolean commercialEnable(String phone) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select max(createtime) from insurance where phone='"+phone+"' and type='commercial'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();	
			if(rs.next())
			{
				//判断是否在一年有效期内
				if(ValidDate.isValidDate(rs.getString("createtime"),365))
				{
					return true;
				}
				else {
					return false;
				}
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接					
		}catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	public static boolean apponintment_exist(String phone, String eventcontent) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select phone from appointment where phone='"+phone+"' and status='未处理' and eventcontent='"+eventcontent+"'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();	
			if(rs.next())
			{
				return true;
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接					
		}catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	public static boolean selectlicense(String zhenghao) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select 证号 from driving_licence where 证号='"+zhenghao+"'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();	
			if(rs.next())
			{
				rs.close();
				pstmt.close();//关闭SQL语句集
				conn.close();//关闭连接
				return true;
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
		}catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	public static String driving_licenceinfo(String phone) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select 姓名,性别,准驾车型,初次领证日期,有效期至,档案号,证号 from driving_licence where phone='"+phone+"'";//查询语句
		System.out.println(sql);
		HashMap<String , String > map=new HashMap<String, String>();
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();	
			while(rs.next())
			{
				
				map.put("姓名", rs.getString("姓名"));
				map.put("性别", rs.getString("性别"));
				map.put("准驾车型", rs.getString("准驾车型"));
				map.put("初次领证日期", rs.getString("初次领证日期"));
				map.put("有效期至", rs.getString("有效期至"));
				map.put("档案号", rs.getString("档案号"));
				map.put("证号", rs.getString("证号"));
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
			if(map.isEmpty())
			{
				return "";
			}
			
			JSONObject jsonObject=JSONObject.fromObject(map);
			return jsonObject.toString();
			
		}catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public static String getlist(String servicename, String city) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select sellers.name,sellers.touxiang,sellers.address,sellers.sellerID,sellers.phone from sellers,seller_service where sellers.phone=seller_service.sellerphone and seller_service.enable=1 and seller_service.name='"+servicename+"' and sellers.city='"+city+"'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		ArrayList<HashMap<String , String >> list=new ArrayList<HashMap<String,String>>();
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();	
			while(rs.next())
			{
				HashMap<String , String > map=new HashMap<String, String>();
				map.put("name", rs.getString("name"));
				map.put("touxiang", rs.getString("touxiang"));
				map.put("address", rs.getString("address"));
				map.put("sellerID", rs.getString("sellerID"));
				map.put("complete", selectdata.getcountcomplete(rs.getString("phone"),servicename));
				list.add(map);
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
			if(list.isEmpty())
			{
				return "";
			}
			
			JSONArray jsonArray=JSONArray.fromObject(list);
			return jsonArray.toString();
			
		}catch (Exception e) {
			System.out.println(e);
		}
		
		return null;
	}

	private static String getcountcomplete(String phone, String servicename) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select count(sellerphone) as count from userorder where sellerphone='"+phone+"' and name='"+servicename+"'";//查询语句
		System.out.println(sql);
		String count="0";
		HashMap<String , String > map=new HashMap<String, String>();
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();	
			if(rs.next())
			{
				count=rs.getString("count");
			}
			
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
			
		}catch (Exception e) {
			System.out.println(e);
		}
		return count;
	}

	public static String getsellerinfo_forstore(String sellerID) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select name,address,servicephone,touxiang,lat,lng from sellers where sellerID='"+sellerID+"'";//查询语句
		System.out.println(sql);
		HashMap<String , String > map=new HashMap<String, String>();
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();	
			while(rs.next())
			{
				ArrayList<HashMap<String , String >> list=new ArrayList<HashMap<String,String>>();
				HashMap<String , String > map2=new HashMap<String, String>();
				map2.put("src", rs.getString("touxiang"));
				list.add(map2);
				JSONArray jsonArray2=JSONArray.fromObject(list);
				System.out.println(jsonArray2.toString());
				map.put("name", rs.getString("name"));
				map.put("touxiang", jsonArray2.toString());
				map.put("address", rs.getString("address"));
				map.put("phone", rs.getString("servicephone"));
				map.put("lat", rs.getString("lat"));
				map.put("lng", rs.getString("lng"));
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
			if(map.isEmpty())
			{
				return "";
			}
			
			JSONObject jsonObject=JSONObject.fromObject(map);
			return jsonObject.toString();
			
		}catch (Exception e) {
			System.out.println(e);
		}
		
		return null;
	}


    public static String getcars(String phone) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select 号牌号码,品牌型号 from cars where phone='"+phone+"'";//查询语句
		System.out.println(sql);
		ArrayList<HashMap<String,String>> list=new ArrayList<>();
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				HashMap<String , String > map=new HashMap<String, String>();
				map.put("号牌号码", rs.getString("号牌号码"));
				map.put("品牌型号", rs.getString("品牌型号"));
				list.add(map);
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
			if(list.isEmpty())
			{
				return "nocar";
			}
			JSONArray jsonArray=JSONArray.fromObject(list);
			return jsonArray.toString();

		}catch (Exception e) {
			System.out.println(e);
		}

		return null;
    }

	public static String getcarbaoxianinfo(String carnum) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select cars.号牌号码,cars.品牌型号,insurance,Excluding_deductible,type from cars,insurance where cars.号牌号码='"+carnum+"' and cars.号牌号码=insurance.号牌号码 and insurance.createtime=(select max(createtime) from insurance where 号牌号码='"+carnum+"' )";//查询语句
		System.out.println(sql);
		HashMap<String , String > map=new HashMap<String, String>();
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{

				map.put("号牌号码", rs.getString("号牌号码"));
				map.put("品牌型号", rs.getString("号牌号码"));
				map.put("insurance", rs.getString("insurance"));
				map.put("Excluding_deductible", rs.getString("Excluding_deductible"));
				map.put("type", rs.getString("type"));
			}
			rs.close();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
			if(map.isEmpty())
			{
				return "noinsurance";
			}
			JSONObject jsonObject=JSONObject.fromObject(map);
			return jsonObject.toString();

		}catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}
}
