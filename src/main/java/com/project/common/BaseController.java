package com.project.common;

import com.project.service.IMailService;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseController {
    @Resource
    protected DbContext dbContext;
    @Resource
    protected Auth auth;
    protected LocalDateTime NOW;
    @Resource
    protected IMailService mailService;


    protected LocalDate parseDate(String content) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        return LocalDate.parse(content, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    protected void ActionInit() {
        NOW = LocalDateTime.now();
    }
}
