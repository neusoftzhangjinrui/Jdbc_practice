package com.jinrui.lesson03;

import com.jinrui.lesson02.utils.jdbcUtils;

import java.sql.*;

/**
 * @author Jinrui Zhang
 * @create 2021-02-11 13:01
 */
public class SQL注入 {
    public static void main(String[] args) {
//        login("lisi","123456");
        login("'' or 1=1", "123456");//有技巧的输入
    }

    //登录业务
    public static void login(String username, String password) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = jdbcUtils.getConnection();
            // prepareStatement 防止SQL注入的本质，把传递进来的参数当做字符
            //假设其中存在转义字符，就直接忽略,或者被直接转义
            //sql
            String sql = "select * from users where `NAME` = ? and `PASSWORD` = ?";//mybatis
            st = conn.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
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
