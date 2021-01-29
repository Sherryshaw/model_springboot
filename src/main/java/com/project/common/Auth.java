package com.project.common;

import com.project.core.ResultGenerator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

@Component
public class Auth {
    public String login(String id,String password){
        UsernamePasswordToken token = new UsernamePasswordToken(id, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(token);
//            subject.checkRole("admin");
//            subject.checkPermissions("query", "add");
        } catch (UnknownAccountException e) {
            return Constants.ACCOUNT_UNKOWN;
        } catch (AuthenticationException e) {
            return Constants.LOGIN_ERROR;
        } catch (AuthorizationException e) {
            return Constants.ACCOUNT_AUTH_ERROR;
        }
        return null;
    }
}
