package com.example.oracle.datasource.controller;

import com.example.oracle.datasource.annotation.MyDatasource;
import com.example.oracle.datasource.entity.User;
import com.example.oracle.datasource.enums.DatasourceTypeEnum;
import com.example.oracle.datasource.mapper.UserMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    @MyDatasource(DatasourceTypeEnum.MYSQL)
    public Object insert() {
        int a = (int) (Math.random() * 1000 + 1000);
        userMapper.insert(a, "hh", 33);
        return "none";
    }

    @GetMapping("/sqlserver")
    @MyDatasource(DatasourceTypeEnum.SQLSERVER)
    public Object getsqlserver() {
        int a = (int) (Math.random() * 1000 + 1000);
        userMapper.insertHello(a, "miaomiao", 44);
        return "none";
    }

    @GetMapping("/all")
    @MyDatasource(DatasourceTypeEnum.MYSQL)
    public Object getsqlse() {
        List<User> users = new ArrayList<>();
        users = userMapper.getAllUser();
        users.stream().forEach(e -> System.out.println(e.getName()));
        return "nondf";
    }

    @GetMapping("/click")
    @MyDatasource(DatasourceTypeEnum.CLICKHOUSE)
    public Object insertClifd() {
        int a = (int) (Math.random() * 1000 + 1000);
        userMapper.insert(a, "hh", 33);
        return "none";
    }

    @GetMapping("/click_all")
    @MyDatasource(DatasourceTypeEnum.CLICKHOUSE)
    public Object getsqlseAll() {
        List<User> users = new ArrayList<>();
        users = userMapper.getAllUser();
        users.stream().forEach(e -> System.out.println(e.getName()));
        return "nondf";
    }

}
