package com.project.common;

import com.project.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class MyRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前登录对象
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();

        //设置角色
        Set<String> roles = new HashSet<>();
        roles.add(account.getRole());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);

        //设置权限
        info.addStringPermission(account.getPerms());
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //useful
//        Account account = accountService.findByUsername(token.getUsername());
//        if(account != null){
//            return new SimpleAuthenticationInfo(account,account.getPassword(),getName());
//        }
//        return null;
        //test
        Account account = new Account();
        account.setUsername(token.getUsername());
        account.setPassword("1");
        account.setPerms("w,r");
        account.setRole("admin");
        return new SimpleAuthenticationInfo(account, account.getPassword(), getName());
    }
}
