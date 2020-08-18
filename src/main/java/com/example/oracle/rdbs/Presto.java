package com.example.oracle.rdbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Presto {

    public static void main(String[] args) throws Exception {
        Class.forName("com.facebook.presto.jdbc.PrestoDriver");
        Connection connection = DriverManager.getConnection("jdbc:presto://localhost:8081/mysql/liliangbin", "presto", null);
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("show tables ");
        while (rs.next()) {
            System.out.println("rs.getString(1) = " + rs.getString(1));
        }
        rs.close();
        connection.close();
    }

}
