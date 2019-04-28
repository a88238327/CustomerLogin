package servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import entity.DataUrl;
import entity.DataUser;

public class updatedrivingLicense {

	public static void insertinfo(String createtime, String xingming, String xingbie, String zhuzhi, String guoji,
			String zhunjiachexing, String zhenghao, String danganhao, String chucilingzhengriqi, String youxiaoqizhi, String phone) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// 驱动类
		String username = new DataUser().getUsername();// 数据库用户名
		String DBpassword = new DataUser().getPassword();// 数据库密码
		String sql = "insert into driving_licence (createtime,姓名,性别,住址,国籍,准驾车型,证号,档案号,初次领证日期,有效期至,phone) values('"+createtime+"','"+xingming+"','"+xingbie+"','"+zhuzhi+"','"+guoji+"','"+zhunjiachexing+"','"+zhenghao+"','"+danganhao+"','"+chucilingzhengriqi+"','"+youxiaoqizhi+"','"+phone+"')";// 查询语句
		System.out.println(sql);
		String DBurl = new DataUrl().getUrl();// 连接数据库的地址
		try {
			Class.forName(driver);// 加载驱动器类
			conn = DriverManager.getConnection(DBurl, username, DBpassword);// 建立连接
			// 建立处理的SQL语句
			pstmt = conn.prepareStatement(sql);
			System.out.println(pstmt.toString());
			int resultString = pstmt.executeUpdate();
			pstmt.close();// 关闭SQL语句集
			conn.close();// 关闭连接
			System.out.println("添加信息成功");
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
