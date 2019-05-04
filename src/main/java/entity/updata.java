package entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class updata {

    public static void updatatixing(String phone, String carnum, String remind_content, String stauts) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动类
        String username = new DataUser().getUsername();//数据库用户名
        String DBpassword = new DataUser().getPassword();//数据库密码
        String sql = "update remind set stauts='"+stauts+"' where phone='"+phone+"' and 号牌号码='"+phone+"' and remmind_content='"+remind_content+"'";//查询语句
        System.out.println(sql);
        String DBurl = new DataUrl().getUrl();//连接数据库的地址
        try {
            Class.forName(driver);//加载驱动器类
            conn = DriverManager.getConnection(DBurl, username, DBpassword);//建立连接
            //建立处理的SQL语句
            pstmt = conn.prepareStatement(sql);
            System.out.println(pstmt.toString());
            int resultString = pstmt.executeUpdate();
            pstmt.close();//关闭SQL语句集
            conn.close();//关闭连接
            System.out.println("添加信息成功");
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
