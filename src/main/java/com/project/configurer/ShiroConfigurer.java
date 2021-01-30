package com.project.configurer;

import com.project.common.MyRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.spring.config.web.autoconfigure.ShiroWebFilterConfiguration;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
@Slf4j
public class ShiroConfigurer extends ShiroWebFilterConfiguration {

    @Value("${spring.redis.host}")
    String host;

    @Value("${spring.redis.port}")
    int port;


    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("manager") DefaultWebSecurityManager manager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(manager);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/**", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return factoryBean;
    }

    @Bean
    public DefaultWebSecurityManager manager(@Qualifier("myRealm") MyRealm myRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm);
        return manager;
    }

    @Bean
    public MyRealm myRealm() {
        return new MyRealm();
    }


}