package com.jinrui.lesson01;

import java.sql.*;

/**
 * @author Jinrui Zhang
 * @create 2021-02-09 18:55
 */
public class JdbcFirstDemo {
    public static void main(String[] args) throws Exception {
        //1、加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");//固定写法，加载驱动
        //2、用户信息和url
        String url = "jdbc:mysql://localhost:3306/jdbcstudy?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8";
        String username = "root";
        String password = "123456";
        //3、连接成功，数据库对象 Connection 代表数据库
        Connection conn = DriverManager.getConnection(url, username, password);
        //4、执行SQL对象 statement 执行SQL的对象
        Statement st = conn.createStatement();

        //5、执行SQL的对象去执行SQL，可能存在结果，查看返回结果
        String sql = "select * from users";
        int num = st.executeUpdate(sql);
        if (num>0){
            System.out.println("插入成功");
        }
        ResultSet resultSet = st.executeQuery(sql);
        while (resultSet.next()) {
            System.out.println("id = " + resultSet.getObject("id"));
            System.out.println("name = " + resultSet.getObject("NAME"));
            System.out.println("pwd = " + resultSet.getObject("PASSWORD"));
            System.out.println("EMAIL = " + resultSet.getObject("email"));
            System.out.println("birth = " + resultSet.getObject("birthday"));
            System.out.println("=====================================================");
        }
        //6、释放连接
        resultSet.close();
        st.close();
        conn.close();

    }
}
