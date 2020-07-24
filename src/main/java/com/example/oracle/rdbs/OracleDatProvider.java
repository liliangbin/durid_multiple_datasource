package com.example.oracle.rdbs;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class OracleDatProvider {
    /**
     * 数据可书画测试
     * @return
     */
    public Object connectDatabase() {
        Connection connection = null;
        String driver = "oracle.jdbc.OracleDriver";
        String dbconfig = "jdbc:oracle:thin:@127.0.0.1:49161:xe";
        String name = "system";
        String pwd = "oracle";

        try {
            Class.forName(driver);
            Properties props = new Properties();
            props.setProperty("user", name);
            props.setProperty("password", pwd);
            connection = DriverManager.getConnection(dbconfig, props);
            String sql = "select * from liliangbin";
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
/*            for (int i = 56; i < 67; i++) {
                String sq = String.format("insert  into LILIANGBIN values (%s,'%s',%s)", i, i + "name", i + 32);
                System.out.println(sq);
                int result = stmt.executeUpdate(sq);
                System.out.println(result);
            }*/
            System.out.println();
            stmt.close();
            DatabaseMetaData metaData = connection.getMetaData();
            String schema = metaData.getConnection().getSchema();
            System.out.println(schema);
            ResultSet tables = metaData.getTables(null, "SYSTEM", "%", new String[]{"TABLE"});
            ResultSetMetaData metaData1 = tables.getMetaData();
            int columnCount = metaData1.getColumnCount();
            Map<String, Integer> result = new HashMap();
            for (int i = 0; i < columnCount; i++) {
                result.put(metaData1.getColumnLabel(i + 1).toUpperCase(), metaData1.getColumnType(i + 1));
                System.out.println(metaData1.getColumnLabel(i+1));
            }
            int i = 0;
/*            while (tables.next()) {
                i++;
                String table_name = tables.getString("TABLE_NAME");

                System.out.println(table_name);
            }*/
            System.out.println(i);

            ResultSet colus = metaData.getColumns("xe", "SYSTEM", "LILIANGBIN", "%");
            ResultSetMetaData resultSetMetaData = colus.getMetaData();
            int count = resultSetMetaData.getColumnCount();
            System.out.println("column  types");
            for (int j = 0; j < count; j++) {
                System.out.println(resultSetMetaData.getColumnLabel(j + 1));
                System.out.println(resultSetMetaData.getColumnType(j + 1));
            }
            while (colus.next()) {
                System.out.println(colus.getString("COLUMN_NAME"));
                System.out.println(colus.getString("TYPE_NAME"));
                String comment = colus.getString("REMARKS");
                if (null != comment) {
                    System.out.println(comment);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return "class not found ";

        }

        return "test 完成";
    }

    public static void main(String[] args) {
/*
        String oracle_line = "jdbc:oracle:thin:@127.0.0.1:49161:xe";
        String sqsserver_line = "jdbc:sqlserver://localhost:1433;DatabaseName=test";
        String mysql = "jdbc:mysql:fabric://s1.rds.sdp.nd:32271/dev_mysql_bd_visual_report?fabricServerGroup=my_group_s2_3306&fabricUsername=admin&fabricPassword=ZB5dGGTJtsC8t7OQ&characterEncoding=utf-8&autoReconnect=true&useUnicode=true";

        String pattern = "jdbc:([^:]*)://([^:]+):([0-9]+);DatabaseName=([^=]+)?.*";
        String oraclePattern = "jdbc:([^:]*):thin:@([/]{0,2})([^:]+):([0-9]+)([:|/])([^:]+)?.*";
        String allPattern = "jdbc:([^:]*):.*";

        String tempString = "jdbc:oracle:thin:@//127.0.0.1:49161/xe";
        Pattern p = Pattern.compile(allPattern);
        Matcher m = p.matcher(tempString);
        m.find();
        System.out.println(m.group(1));
        Pattern pattern1 = Pattern.compile(oraclePattern);
        Matcher m1 = pattern1.matcher(tempString);
        m1.find();
        for (int i = 0; i <m1.groupCount() ; i++) {
            System.out.println(m1.group(i+1)+"   "+(i+1));
        }
*/

        OracleDatProvider o = new OracleDatProvider();
        o.connectDatabase();
    }
}
