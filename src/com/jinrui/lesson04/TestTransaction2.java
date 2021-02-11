package com.jinrui.lesson04;

import com.jinrui.lesson02.utils.jdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Jinrui Zhang
 * @create 2021-02-11 16:41
 */
public class TestTransaction2 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = jdbcUtils.getConnection();
            //关闭数据库的自动提交，自动会开启事务
            conn.setAutoCommit(false);//开启事务
            //
            String sql1 = "update account set money = money-100 where name = 'A'";
            st = conn.prepareStatement(sql1);
            st.executeUpdate();
            int x = 1 / 0;//报错
            String sql2 = "update account set money = money+100 where name = 'B'";
            st = conn.prepareStatement(sql2);
            st.executeUpdate();
            //业务完毕，提交事务
            conn.commit();
            System.out.println("成功！");
        } catch (SQLException e) {
            try {
                conn.rollback();//如果失败会回滚事务
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            //如果失败，则默认回滚
            e.printStackTrace();

        } finally {
            jdbcUtils.release(conn, st, rs);
        }


    }
}
