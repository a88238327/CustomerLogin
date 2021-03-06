package entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventLog {

	public static boolean create(String event,String user,String content) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置日期格式
		String createtime="\'"+df.format(new Date())+"\'";
		String phone=",\'"+user+"\'";
		String event1=",\'"+event+"\'";
		String event_content=",\'"+content+"\'";
		String str=createtime+phone+event1+event_content;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="insert into user_event (createtime,phone,event,event_content) values ("+str+")";//查询语句
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
			return true;

		}catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
}
