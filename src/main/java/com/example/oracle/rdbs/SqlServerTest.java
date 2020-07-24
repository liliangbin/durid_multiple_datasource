package com.example.oracle.rdbs;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class SqlServerTest {

    public static Connection getDruidConnection(String user, String password, String url)
            throws Exception {
        DruidDataSource ds = new DruidDataSource();
        ds.setUsername(user);
        ds.setPassword(password);
        ds.setUrl(url);
        ds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return ds.getConnection();
    }

    public static void main(String[] args) throws Exception {
        String user = "sa";
        String password = "Abc!@#123456";
        String jdbcUrl = "jdbc:sqlserver://localhost:1433;databaseName=test";
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        Map<String, String> conf = new HashMap();
        conf.put(DruidDataSourceFactory.PROP_DRIVERCLASSNAME, driver);
        conf.put(DruidDataSourceFactory.PROP_URL, jdbcUrl);
        conf.put(DruidDataSourceFactory.PROP_USERNAME, user);
        conf.put(DruidDataSourceFactory.PROP_PASSWORD, password);
        conf.put(DruidDataSourceFactory.PROP_INITIALSIZE, "3");
        conf.put(DruidDataSourceFactory.PROP_MAXWAIT, "10000");
        conf.put(DruidDataSourceFactory.PROP_TESTWHILEIDLE,"true");
        conf.put(DruidDataSourceFactory.PROP_TESTONBORROW,"true");
        conf.put(DruidDataSourceFactory.PROP_LOGABANDONED,"true");
        conf.put(DruidDataSourceFactory.PROP_REMOVEABANDONED,"true");
        conf.put(DruidDataSourceFactory.PROP_REMOVEABANDONEDTIMEOUT,"180");
        conf.put(DruidDataSourceFactory.PROP_VALIDATIONQUERY,"select 1");
        DruidDataSource druidDS = (DruidDataSource) DruidDataSourceFactory.createDataSource(conf);
        druidDS.setBreakAfterAcquireFailure(true);
        druidDS.setConnectionErrorRetryAttempts(5);

        Connection connection = druidDS.getConnection();
        //getDruidConnection(user, password, jdbcUrl);
        System.out.println("11111");
    }
}
