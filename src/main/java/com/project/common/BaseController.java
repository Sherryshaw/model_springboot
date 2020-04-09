package com.project.common;

import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BaseController {
    @Resource
    protected DbContext dbContext;
    protected LocalDateTime NOW;

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
