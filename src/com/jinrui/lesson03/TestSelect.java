package com.jinrui.lesson03;
import com.jinrui.lesson02.utils.jdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Jinrui Zhang
 * @create 2021-02-11 15:22
 */
public class TestSelect {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st= null;
        ResultSet rs = null;
        try {
            conn = jdbcUtils.getConnection();
            String sql = "select * from users where id = ?";//编写SQL
            st = conn.prepareStatement(sql);//预编译
            st.setInt(1,2);//传递参数
            //执行
            rs = st.executeQuery();//执行

            if (rs.next()){
                System.out.println(rs.getString("NAME"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcUtils.release(conn,st,rs);
        }

    }
}
