package com.example.oracle.rdbs;

import java.sql.*;
import java.util.Properties;

public class DataProvider {

    public Object update() {
        Connection connection = null;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String dbconfig = "jdbc:sqlserver://localhost:1433;DatabaseName=test";
        String name = "sa";
        String pwd = "Abc!@#123456";
        try {
            Class.forName(driver);
            Properties props = new Properties();
            props.setProperty("user", name);
            props.setProperty("password", pwd);
            connection = DriverManager.getConnection(dbconfig, props);
            String sql = "select * from liliangbin";
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            for (int i = 123; i < 1050; i++) {
                String sq = String.format("insert  into dbo.liliangbin values (%s,'%s',%s)", i, i + "name", i + 32);
                String testsql = "insert  into master.dbo.liliangbin(id,name,age) values (?,?,?)";
                PreparedStatement ps= connection.prepareStatement(testsql);
                ps.setInt(1,i);
                ps.setString(2,i+"name");
                ps.setInt(3,i+23);
                ps.executeUpdate();
    /*            System.out.println(sq);
                int result = stmt.executeUpdate(sq);
                System.out.println(result);*/
            }
            stmt.close();
            connection.commit();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return "class not found ";

        }
        return "test 完成";
    }

    public static void main(String[] args) {
        DataProvider dataProvider = new DataProvider();
//        dataProvider.update();
        String test = null;

    }
}
