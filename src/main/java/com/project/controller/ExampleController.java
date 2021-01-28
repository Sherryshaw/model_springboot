package com.project.controller;

import com.project.common.Constants;
import com.project.core.Result;
import com.project.core.ResultGenerator;
import com.project.util.RedisUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Controller
@RestController
@RequestMapping("/example")
public class ExampleController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    RedisUtil redisUtil;

    @GetMapping("/test01")
    public Result test01() {
        UsernamePasswordToken token = new UsernamePasswordToken("root", "1234");

        redisUtil=new RedisUtil(stringRedisTemplate);
        System.out.println(token);
        redisUtil.append("root",token.toString());
        if (redisUtil.hasKey("root"))
            return ResultGenerator.genSuccessResult();
        else
            return ResultGenerator.genFailResult(Constants.DATA_NOT_FOUND);
    }

    @GetMapping("/test02")
    public Result test02() {
        redisUtil=new RedisUtil(stringRedisTemplate);
        if (redisUtil.hasKey("root"))
            return ResultGenerator.genSuccessResult(redisUtil.get("root"));
        else
            return ResultGenerator.genFailResult(Constants.DATA_NOT_FOUND);
    }
}
