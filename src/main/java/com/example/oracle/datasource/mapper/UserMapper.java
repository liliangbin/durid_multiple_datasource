package com.example.oracle.datasource.mapper;

import com.example.oracle.datasource.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from li_user")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "age", column = "age")}
    )
    public List<User> getAllUser();

    @Insert("insert into li_user(id,name,age) values (#{id},#{name},#{age})")
    public void insert(int id,String name, int age);

    @Insert("insert into hello(id,name,age) values (#{id},#{name},#{age})")
    public void insertHello(int id,String name, int age);

}
