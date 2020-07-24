package com.example.oracle.rdbs;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Clickhouse {
    public static final String CLICKHOUSE_URL_PATTERN = "jdbc:([^:]*)://([^:]+):([0-9]+)/([^?]+)?.*";

    public static Connection getConnection() throws Exception {
        String address = "jdbc:clickhouse://192.168.217.62:8123/datasets";
        Connection connection = null;
        String username = "default";
        String passw = "123456";
        Class.forName("ru.yandex.clickhouse.ClickHouseDriver");
        connection = DriverManager.getConnection(address, username, passw);
        return connection;
    }

    public static void main(String[] args) throws Exception {


        Connection connection = getConnection();
/*        for (int i = 0; i < 1090; i++) {
            String sql = "insert into liliangbin  values(?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, i);
            ps.setString(2, i + "name");
            ps.setInt(3, i);
            ps.executeUpdate();
        }*/

        getColumn(connection);
    }

    public static void getColumn(Connection connection) throws Exception {
        DatabaseMetaData metaData = connection.getMetaData();
        String schema = metaData.getConnection().getSchema();
        System.out.println(schema);
        ResultSet tables = metaData.getTables(null, schema, "%", new String[]{"TABLE"});
// 获取到的是数据类型  如何获取数据啦
        ResultSet colums = metaData.getColumns(null, schema, "liliangbin", "%");

        ResultSetMetaData metaData1 = tables.getMetaData();
        int columnCount = metaData1.getColumnCount();
        Map<String, Integer> result = new HashMap();
        for (int i = 0; i < columnCount; i++) {
            result.put(metaData1.getColumnLabel(i + 1).toUpperCase(), metaData1.getColumnType(i + 1));
        }
        int i = 0;
        int count = colums.getMetaData().getColumnCount();
        System.out.println(count);
        while (tables.next()) {
            i++;
            String table_name = tables.getString("TABLE_NAME");

        }
        while (colums.next()) {
            System.out.println(colums.getString("TYPE_NAME"));
            System.out.println(colums.getString("COLUMN_NAME"));

        }
    }

    public static void pama() {
        String jdbcUrl = "jdbc:clickhouse://192.168.217.62:8123/datasets";

        String pattern = "jdbc:([^:]*)://([^:]+):([0-9]+);DatabaseName=([^=]+)?.*";
        String oraclePattern = "jdbc:([^:]*):thin:@([/]{0,2})([^:]+):([0-9]+)([:|/])([^:]+)?.*";
        String allPattern = "jdbc:([^:]*):.*";

        String tempString = "jdbc:oracle:thin:@//127.0.0.1:49161/xe";
        Pattern p = Pattern.compile(allPattern);
        Matcher m = p.matcher(jdbcUrl);
        m.find();
        System.out.println(m.group(1));
        Pattern p2=Pattern.compile(CLICKHOUSE_URL_PATTERN);
        Matcher m2=p2.matcher(jdbcUrl);
        m2.find();
        System.out.println();
        for (int i = 0; i <m2.groupCount() ; i++) {
            System.out.println(m2.group(i+1));
        }

    }
}
