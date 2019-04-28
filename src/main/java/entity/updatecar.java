package entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class updatecar {

	public static void insertinfo(String createtime, String pingpaixinghao, String fazhengriqi, String shiyongxingzhi,
			String fadongjihaoma, String haopaihaoma, String suoyouren, String zhuzhi, String zhuceriqi,
			String cheliangshibiedaihao, String cheliangleixing, String jiancejilu, String hedingzaizhiliang,
			String zhengbeizhiliang, String waiguochicun, String hedingzairenshu, String zongzhiliang, String danganhao,
			String selecttext, String phone) {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="insert into cars  (createtime,品牌型号,发证日期,使用性质,发动机号码,号牌号码,所有人,住址,注册日期,车辆识别代号,车辆类型,下次年检,核定载质量,整备质量,外廓尺寸,核定载人数,总质量,档案编号,车牌颜色,phone) values('"+createtime+"','"+pingpaixinghao+"','"+fazhengriqi+"','"+shiyongxingzhi+"','"+fadongjihaoma+"','"+haopaihaoma+"','"+suoyouren+"','"+zhuzhi+"','"+zhuceriqi+"','"+cheliangshibiedaihao+"','"+cheliangleixing+"','"+jiancejilu+"','"+hedingzaizhiliang+"','"+zhengbeizhiliang+"','"+waiguochicun+"','"+hedingzairenshu+"','"+zongzhiliang+"','"+danganhao+"','"+selecttext+"','"+phone+"')";//查询语句
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
