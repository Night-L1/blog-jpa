package com.cfl.blog.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administer
 *
 * 配置druid数据源
 */
@Configuration
public class DruidConfig {

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource druid(){
        return new DruidDataSource();
    }


    /**
     * 1.配置一个管理后台的Servlet
     * @return bean
     */
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        Map<String,String> initparams = new HashMap<>();
        initparams.put("loginUsername","admin");
        initparams.put("loginPassword","123456");
        bean.setInitParameters(initparams);
        return bean;
    }

    /**
     * 2.配置一个web监控的filter
     * @return bean
     */
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String,String> initparams = new HashMap<>();
        initparams.put("exclusions","*.js,*.css,/druid");
        bean.setInitParameters(initparams);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }


}
