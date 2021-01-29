package com.project.controller;

import com.project.common.BaseController;
import com.project.core.Result;
import com.project.core.ResultGenerator;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {

    @GetMapping("/login")
    public Result login(String username,String password) {
        String result= auth.login(username,password);
        if(result==null){
            return ResultGenerator.genSuccessResult();
        }else{
            return ResultGenerator.genFailResult(result);
        }
    }
    @RequiresRoles("admin")
    @GetMapping("/index")
    public Result index(){
        return ResultGenerator.genSuccessResult();
    }
    @RequiresRoles("user")
    @GetMapping("/index2")
    public Result index2(){
        return ResultGenerator.genSuccessResult();
    }

}
