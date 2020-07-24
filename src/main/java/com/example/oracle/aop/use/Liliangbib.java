package com.example.oracle.aop.use;

import com.alibaba.druid.support.http.WebStatFilter;
import com.example.oracle.aop.innovation.LiliangbinCl;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@LiliangbinCl
public class Liliangbib {
    @LiliangbinCl()
    public void test() {

    }



    @Bean(name = "filterRegistrationBean")
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        filterRegistrationBean.addInitParameter("principalCookieName","USER_COOKIE");
        filterRegistrationBean.addInitParameter("principalSessionName","USER_SESSION");
        filterRegistrationBean.addInitParameter("DruidWebStatFilter","/*");
        return filterRegistrationBean;
    }


}

