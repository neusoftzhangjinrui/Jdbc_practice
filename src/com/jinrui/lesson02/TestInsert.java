package com.jinrui.lesson02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jinrui.lesson02.utils.jdbcUtils;

/**
 * @author Jinrui Zhang
 * @create 2021-02-10 19:51
 */
public class TestInsert {
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = jdbcUtils.getConnection();//获取数据库连接
            st = conn.createStatement();//获取SQL的执行对象
            String sql = "insert into users(id, `NAME`, `PASSWORD`, `email`, `birthday`) values (4,'jinrui','123456','1072552872@qq.com','2020-01-01')";
            int i = st.executeUpdate(sql);
            if (i > 0) {
                System.out.println("插入成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtils.release(conn, st, rs);
        }
    }
}
