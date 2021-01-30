package com.project.configurer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Configuration
public class MapObjectConfigurer {
    @Bean
    public Formatter<LocalDateTime> localDateTimeFormatter() {
        return getLocalDateTimeFormatter();
    }

    static Formatter<LocalDateTime> getLocalDateTimeFormatter() {
        return new Formatter<LocalDateTime>() {
            @Override
            public String print(LocalDateTime localDateTime, Locale locale) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                return formatter.format(localDateTime);
            }

            @Override
            public LocalDateTime parse(String text, Locale locale) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                return LocalDateTime.parse(text, formatter);
            }
        };
    }

}
