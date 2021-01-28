package com.project.configurer;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.TextConfigurationRealm;
import org.apache.shiro.spring.config.web.autoconfigure.ShiroWebFilterConfiguration;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class ShiroConfig extends ShiroWebFilterConfiguration {

    /**
     * 验证用户
     * 可以声明多个Realm Bean，Shiro都会把它注入的
     * @return
     */
    @Bean
    public Realm realm() {
        TextConfigurationRealm realm = new TextConfigurationRealm();
        //添加两个用户
        //joe.coder=password 角色 user
        //jill.coder=password 角色 admin
        realm.setUserDefinitions("joe.coder=password,user\n" +
                "jill.coder=password,admin");

        //设置角色admin的权限是read,write
        //设置角色user的权限是read
        realm.setRoleDefinitions("admin=read,write\n" +
                "user=read");
        realm.setCachingEnabled(true);

        return realm;
    }

    /**
     * 配置shiro的url权限
     *
     * @return
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition("/login.html", "authc"); // need to accept POSTs from the login form
        chainDefinition.addPathDefinition("/logout", "logout");
        chainDefinition.addPathDefinition("/account-info", "perms[write]");
        chainDefinition.addPathDefinition("/account-info1", "roles[admin]");
        return chainDefinition;
    }

    @Override
    protected ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean factoryBean = super.shiroFilterFactoryBean();
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        //添加自定义的Filter,这里我随便new了一个filter
        factoryBean.setFilters(filterMap);
        return factoryBean;
    }
}