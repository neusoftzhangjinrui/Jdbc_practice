package com.jinrui.lesson02;

import com.jinrui.lesson02.utils.jdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Jinrui Zhang
 * @create 2021-02-10 20:07
 */
public class TestUpdate {
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = jdbcUtils.getConnection();//获取数据库连接
            st = conn.createStatement();//获取SQL的执行对象
            String sql = "update users set `NAME` = 'zhangjinrui',email  = '1072552872@qq.com' where id=1";
            int i = st.executeUpdate(sql);
            if (i > 0) {
                System.out.println("删除成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtils.release(conn, st, rs);
        }
    }
}
