package com.bocs.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.bocs.core.dynamicdatasource.ChooseDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:<p> </p>
 * Created by songqi on 2017/7/27.
 */


@Configuration
public class DataSourceConfig {

    protected final Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private Environment env;

    public DruidDataSource writeDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.druid.write.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.druid.write.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.druid.write.password"));
        dataSource.setDriverClassName(env.getProperty("spring.datasource.druid.write.driver-class-name"));

        return dataSource;
    }

    public DruidDataSource readDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.druid.read.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.druid.read.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.druid.read.password"));
        dataSource.setDriverClassName(env.getProperty("spring.datasource.druid.read.driver-class-name"));
        return dataSource;
    }

    @Bean
    public ChooseDataSource chooseDataSource(){
        ChooseDataSource dataSource = new ChooseDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSource.setDefaultTargetDataSource(writeDataSource());
        dataSourceMap.put("write", writeDataSource());
        dataSourceMap.put("read", readDataSource());
        dataSource.setTargetDataSources(dataSourceMap);
        Map<String, String> methodMap = new HashMap<>();
        methodMap.put("write",",add,insert,create,update,delete,remove,");
        methodMap.put("read",",get,select,count,list,query,");
        dataSource.setMethodType(methodMap);
        return dataSource;
    }



}
