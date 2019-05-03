package servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import entity.DataUrl;
import entity.DataUser;
import net.sf.json.JSONObject;

public class insert {

	public static void appointment(String phone,String eventcontent) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置日期格式
		String createtime=df.format(new Date());
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="insert into appointment (createtime,phone,eventcontent,status) values('"+createtime+"','"+phone+"','"+eventcontent+"','未处理')";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			System.out.println(pstmt.toString());
			int resultString=pstmt.executeUpdate();
			pstmt.close();//关闭SQL语句集
			conn.close();//关闭连接
			System.out.println("添加信息成功");
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

    public static void addbaoxianyuyue(String phone, String buybaoxian, String carnum) {
		Connection conn=null;
		PreparedStatement pstmt	= null ;
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置日期格式
		String createtime=df.format(new Date());
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="insert into baoxianyuyue (createtime,phone,号牌号码,insurance,stauts) values('"+createtime+"','"+phone+"','"+carnum+"','"+buybaoxian+"','未处理')";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		try{
		    Class.forName(driver);//加载驱动器类
		    conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
		    //建立处理的SQL语句
		    pstmt = conn.prepareStatement(sql) ;
		    System.out.println(pstmt.toString());
		    int resultString=pstmt.executeUpdate();
		    pstmt.close();//关闭SQL语句集
		    conn.close();//关闭连接
		    System.out.println("添加信息成功");
		}catch (Exception e) {
		    System.out.println(e);
		}
    }

}
