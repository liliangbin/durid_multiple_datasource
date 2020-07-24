package com.example.oracle.aop.innovation;

import java.lang.annotation.*;

/*
*
* @Target 用于描述注解的使用范围
取值
METHOD 用于描述方法。
PARAMETER 用于描述参数
TYPE 用于描述类或接口（甚至 enum ）*/
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE}) // 作用域
@Retention(RetentionPolicy.RUNTIME) // 注解保留的时间  只保留在源文件中，还是保留在class文件中，或是运行的时候
@Inherited
public @interface LiliangbinCl {

    String name() default "hello classname";

    String args() default " none";
}
