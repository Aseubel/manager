package com.guyuan.manager.config;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.lang.Matcher;
import cn.hutool.core.util.StrUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDateTime;

/**
 * @author 杨之耀
 * @date 2024/12/3
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public Converter<String, LocalDateTime> stringLocalDateTimeConverter() {
        return new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(String source) {
                if (StrUtil.isAllCharMatch(source, new Matcher<Character>() {
                    @Override
                    public boolean match(Character c) {
                        return Character.isDigit(c); // 检查字符是否为数字
                    }
                })) {
                    return LocalDateTimeUtil.of(Long.parseLong(source));
                } else {
                    return DateUtil.parseLocalDateTime(source);
                }
            }
        };

    }

}
