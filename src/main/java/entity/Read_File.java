package entity;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Read_File {

	public static boolean file_exists(String phone,String filename) {
		File file=new File(img_path.path+phone+filename);
		if(file.exists())
		{
			System.out.println("存在");
			return true;
		}
		else {
			return false;
		}
		
	}
	public static String getFileurl(String phone,String filename) {
		String str=img_path.path+phone+filename;
		return str;
		
	}
	public static String get_qrpath(String phone) throws Exception {
		if(!file_exists(phone, "_qr.png"))
		{
			
			String qrurl;
			//创建本地二维码
			String qr_path=img_path.path+phone+"_qr.png";
			//System.out.println(img_path.path+phone+"_qr.png");
			//qr_path=get_qr_img.download(qrurl, img_path.path+phone+"_qr.png");
			File logoFile = new File(img_path.path+"chejiawang.jpg");
	        File QrCodeFile = new File(qr_path);
	        String url = "http://cloud.hnjtbf.com/CustomerLogin/sharesignup.html?share="+phone;
	        String note = "";
			Create_QR_img.create(logoFile, QrCodeFile, url, note);
			return qr_path;
		}
		else {
			String str=img_path.path+phone+"_qr.png";
			return str;
		}
		
		
	}
	private static String select_qrurl(String phone) throws Exception {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt	= null ;
		String qr_urlString;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
		String username=new DataUser().getUsername();//数据库用户名
		String DBpassword=new DataUser().getPassword();//数据库密码
		String sql="select * from manager where phone_number="+"\'"+phone+"\'";//查询语句
		System.out.println(sql);
		String DBurl=new DataUrl().getUrl();//连接数据库的地址
		try{
			Class.forName(driver);//加载驱动器类
			conn=DriverManager.getConnection(DBurl,username,DBpassword);//建立连接
			//建立处理的SQL语句
			pstmt = conn.prepareStatement(sql) ;
			ResultSet rs=pstmt.executeQuery();
			if(rs.next())
			{
				qr_urlString=rs.getString("qr_url");
				
				pstmt.close();//关闭SQL语句集
				conn.close();//关闭连接
				return qr_urlString;
			}
	
			
		}catch (Exception e) {
		// TODO: handle exception
		System.out.println("查询qr_url失败");
		pstmt.close();//关闭SQL语句集
		conn.close();//关闭连接
		}
		return null;
	}
	public static boolean get_shanghuqr(String phone) {
		if(!file_exists(phone, "_shanghuma.png"))
		{
			
			String qrurl;
			//创建本地二维码
			String qr_path=img_path.path+phone+"_shanghuma.png";
			//System.out.println(img_path.path+phone+"_qr.png");
			//qr_path=get_qr_img.download(qrurl, img_path.path+phone+"_qr.png");
			File logoFile = new File(img_path.path+"chejiawang.jpg");
	        File QrCodeFile = new File(qr_path);
	        String url = "http://cloud.hnjtbf.com/managerLogin/sellersjoin.html?outter ="+phone;
	        String note = "";
			Create_QR_img.create(logoFile, QrCodeFile, url, note);
			return true;
		}
		else {
			String str=img_path.path+phone+"_qr.png";
			return false;
		}
		
	}
}

