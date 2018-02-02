package com.bocs.configuration;

import com.bocs.core.util.PropertiesUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

@Configuration
public class PropertiesConfig {


    @Bean
    public PropertiesUtil propertiesUtil(){
        PropertiesUtil propertiesUtil = new PropertiesUtil();
        try {
            propertiesUtil.setLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:*.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertiesUtil;
    }
}