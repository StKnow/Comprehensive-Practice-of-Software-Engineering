package com.neusoft.elm.util;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.PreparedStatement;

import java.sql.Connection;


public class DBUtil {

    //  private static final String URL="jdbc:mysql://localhost:3306/elm?characterEncoding=utf-8";
    private static final String URL = "jdbc:mysql://localhost:3306/elm?characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    private static final ThreadLocal<Connection> TL = new ThreadLocal<Connection>();

    //获取
    public static Connection getConnection() {
        Connection con = null;
        con = TL.get();
        if (con == null) {
            con = createConnection();
            TL.set(con);
        }
        return con;
    }

    //开启一个事务
    public static void beginTransaction() throws Exception {
        Connection con = null;
        con = TL.get();
        if (con == null) {
            con = createConnection();
            TL.set(con);
        }
        con.setAutoCommit(false);
    }

    //提交一个事物
    public static void commitTransaction() throws Exception {
        Connection con = TL.get();
        con.commit();
    }

    //回滚一个事务
    public static void rollbackTransaction() throws Exception {
        Connection con = TL.get();
        con.rollback();
    }


    // 关闭各种资源
    public static void close(ResultSet rs, PreparedStatement pst) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(PreparedStatement pst) {
        try {

            if (pst != null) {
                pst.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close() {
        Connection con = TL.get();
        try {
            if (con != null) {
                con.close();
            }
            TL.remove();//不remove容易造成内存溢出等问题。
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //创建
    private static Connection createConnection() {
        Connection con = null;
        if (con == null) {


            try {
                Class.forName(DRIVER);
                con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return con;
    }


}
