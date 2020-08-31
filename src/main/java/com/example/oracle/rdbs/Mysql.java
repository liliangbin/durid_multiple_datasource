package com.example.oracle.rdbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class Mysql {
    public static void main(String[] args) {
        String con = "jdbc:mysql://forcebing.top:3306/liliangbin?characterEncoding=utf-8";
        Connection connection = null;
        String driver = "com.mysql.cj.jdbc.Driver";
        String name = "llb";
        String pwd = "123456";
        try {
            Class.forName(driver);
            Properties props = new Properties();
            props.setProperty("user", name);
            props.setProperty("password", pwd);
            connection = DriverManager.getConnection(con, props);
            System.out.println(connection.getSchema());
            for (int i = 1080; i < 1090; i++) {
                String sql = "insert into liliangbin  values(?,?,?,?)";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, i);
                ps.setString(2, i + "name");
                ps.setDouble(3, 2.67);
                ps.setInt(4,0);
                ps.executeUpdate();
            }
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
