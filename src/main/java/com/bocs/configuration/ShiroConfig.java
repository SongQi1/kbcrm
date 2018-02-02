package com.bocs.configuration;

import com.bocs.core.shiro.MyRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:<p> </p>
 * Created by songqi on 2017/8/27.
 */
@Configuration
public class ShiroConfig {

    @Bean
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


    @Bean
    public DefaultWebSecurityManager securityManager(MyRealm myRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm);
      //  securityManager.setCacheManager(new RedisCacheManager());
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager){

        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        shiroFilter.setLoginUrl("/unauthorized");
        shiroFilter.setUnauthorizedUrl("/forbidden");
        Map<String, String> map = new HashMap<>();

        map.put("/","anon");
//        map.put("/login","anon");
//        map.put("/*.ico","anon");
//        map.put("/*.png","anon");
//        map.put("/*.jpg","anon");
//        map.put("/css/**","anon");
//        map.put("/images/**","anon");
//        map.put("/js/**","anon");
//        map.put("/swagger*","anon");
//        map.put("/druid/*","anon");
//        map.put("/unauthorized","anon");
//        map.put("/regin","anon");
//        map.put("/registerPage","anon");
//        map.put("/resetPassword*","anon");
//        map.put("/forgetPassword*","anon");
//        map.put("/*/api-docs","anon");
//        map.put("/management","anon");
//        map.put("/**","authc");
        shiroFilter.setFilterChainDefinitionMap(map);
        return shiroFilter;
    }
}
