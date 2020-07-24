package com.example.oracle.datasource.entity;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

@Data
@AllArgsConstructor
@Mapper
public class User {
    private int id;
    private String name;
    private int age;


}
