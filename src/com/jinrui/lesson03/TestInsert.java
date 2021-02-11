package com.jinrui.lesson03;
import com.jinrui.lesson02.utils.jdbcUtils;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Jinrui Zhang
 * @create 2021-02-11 15:02
 */
public class TestInsert {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;

        try {
             conn = jdbcUtils.getConnection();
             //区别
            //使用？占位符代替参数
            String sql = "insert into users(id, `NAME`, `PASSWORD`, `email`, `birthday`) values (?,?,?,?,?)";
            st = conn.prepareStatement(sql);//预编译SQL,先写SQL，然后执行
            //手动参数赋值
            st.setInt(1,4);
            st.setString(2,"jinrui");
            st.setString(3,"145253");
            st.setString(4,"1072552872@qq.com");
            //注意点：sql.Date 数据库  java.sql.Date()
            //       util.Date Java new Date().getTime() 获得时间戳
            st.setDate(5,new java.sql.Date(new Date().getTime()));
            //执行
            int i = st.executeUpdate();
            if (i>0){
                System.out.println("插入成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcUtils.release(conn,st,null);
        }
    }
}
