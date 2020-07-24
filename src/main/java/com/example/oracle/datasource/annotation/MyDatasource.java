package com.example.oracle.datasource.annotation;

import com.example.oracle.datasource.enums.DatasourceTypeEnum;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface MyDatasource {
    DatasourceTypeEnum value() default DatasourceTypeEnum.MYSQL;
}
