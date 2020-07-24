package com.example.oracle.datasource.aop;

import com.example.oracle.datasource.annotation.MyDatasource;
import com.example.oracle.datasource.enums.DatasourceTypeEnum;
import com.example.oracle.datasource.multiple.DatasourceContextHolder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Aspect
@Component
@Slf4j
public class DatasourceAspect {

    @Pointcut("@annotation(com.example.oracle.datasource.annotation.MyDatasource)")
    public void pointcut() {
    }

    // 牛皮
    @Before("@annotation(datasource)&&pointcut()")
    public void doBefore(MyDatasource datasource) {
        log.info("选择数据源 {}", datasource.value().getName());
        DatasourceContextHolder.setDatasource(datasource.value().getName());

    }

    @After("pointcut()")
    public void clear() {
        System.out.println("结束 调节");
        DatasourceContextHolder.clear();

    }
}
