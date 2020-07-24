package com.example.oracle.datasource.multiple;


public class DatasourceContextHolder {
    public final static ThreadLocal<String> contextHolder = new InheritableThreadLocal<>();

    public static String getDatasource() {
        return contextHolder.get();
    }

    public static void setDatasource(String db) {
        contextHolder.set(db
        );
    }

    public static void clear() {
        contextHolder.remove();
    }
}
