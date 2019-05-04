package entity;

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

	public static void appointment(String phone,String eventcontent,String carnum) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置日期格式
		String createtime=df.format(new Date());
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="insert into appointment (createtime,phone,eventcontent,status,号牌号码) values('"+createtime+"','"+phone+"','"+eventcontent+"','未处理','"+carnum+"')";//查询语句
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

    public static void addbaoxianinfo(String createtime,String insurance, String Excluding_deductible, String carnum, String phone, String startdata, String enddata, String type, String img_name) {
		Connection conn=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="insert into insurance (createtime,insurance,Excluding_deductible,号牌号码,phone,startdata,enddata,type,img_name) values('"+createtime+"','"+insurance+"','"+Excluding_deductible+"','"+carnum+"','"+phone+"','"+startdata+"','"+enddata+"','"+type+"','"+img_name+"')";//查询语句
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

    public static void addremind(String carnum, String phone) {
		Connection conn=null;
		PreparedStatement pstmt	= null ;
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置日期格式
		String createtime=df.format(new Date());
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql1="insert into remind (createtime,phone,号牌号码,remmind_content,stauts) values('"+createtime+"','"+phone+"','"+carnum+"','车辆年审提醒','false')";//查询语句
		String sql2="insert into remind (createtime,phone,号牌号码,remmind_content,stauts) values('"+createtime+"','"+phone+"','"+carnum+"','保险到期提醒','false')";//查询语句
		String sql3="insert into remind (createtime,phone,号牌号码,remmind_content,stauts) values('"+createtime+"','"+phone+"','"+carnum+"','驾照年审提醒','false')";//查询语句
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		try{
		    Class.forName(driver);//加载驱动器类
		    conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
		    //建立处理的SQL语句
			conn.setAutoCommit(false);
		    pstmt = conn.prepareStatement(sql1) ;
			pstmt.executeUpdate();
		    pstmt = conn.prepareStatement(sql2) ;
			pstmt.executeUpdate();
		    pstmt = conn.prepareStatement(sql3) ;
			pstmt.executeUpdate();
		    System.out.println(pstmt.toString());

			conn.commit();
			conn.setAutoCommit(true);
		    pstmt.close();//关闭SQL语句集
		    conn.close();//关闭连接
		    System.out.println("添加信息成功");
		}catch (Exception e) {
		    System.out.println(e);
		}
    }
}
