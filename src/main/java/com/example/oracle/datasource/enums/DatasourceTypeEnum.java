package com.example.oracle.datasource.enums;

public enum DatasourceTypeEnum {

    MYSQL("mysql"),
    SQLSERVER("sqlserver"),
    CLICKHOUSE("clickhouse");
    String name;

    DatasourceTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
