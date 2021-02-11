package com.jinrui.lesson02;

import com.jinrui.lesson02.utils.jdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Jinrui Zhang
 * @create 2021-02-11 13:01
 */
public class SQL注入 {
    public static void main(String[] args) {
//login("zhangsan","123456");
        login("'or'1=1","'or'1=1");//有技巧的输入
    }
    //登录业务
    public static void login(String username,String password){
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = jdbcUtils.getConnection();
            st = conn.createStatement();
            //sql
            String sql = "select * from users where `NAME` ='"+username+"'and `password`='"+password+"'";
            rs = st.executeQuery(sql);//查询完毕会返回一个结果集
            while (rs.next()) {
                System.out.println(rs.getString("NAME"));
                System.out.println(rs.getString("password"));
                System.out.println("================================");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtils.release(conn, st, rs);
        }
    }
}
