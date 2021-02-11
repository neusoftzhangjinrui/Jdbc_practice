package com.jinrui.lesson03;

import com.jinrui.lesson02.utils.jdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Jinrui Zhang
 * @create 2021-02-11 15:16
 */
public class TestUpdate {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = jdbcUtils.getConnection();
            //区别
            //使用？占位符代替参数
            String sql = "update users set `NAME` = ?  where id = ? ;";
            st = conn.prepareStatement(sql);//预编译SQL,先写SQL，然后执行
            //手动参数赋值
            st.setString(1, "津瑞");
            st.setInt(2, 1);

            //执行
            int i = st.executeUpdate();
            if (i > 0) {
                System.out.println("更新成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtils.release(conn, st, null);
        }
    }
}
