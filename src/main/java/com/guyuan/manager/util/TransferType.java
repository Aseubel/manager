package com.guyuan.manager.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * @author yangzhiyao
 * @description 转换类型工具类
 * @date 2024/12/6
 */
@Component
public class TransferType {

    public static String convertGender(Integer gender) {
        return switch (gender) {
            case 0 -> "未选择";
            case 1 -> "男";
            case 2 -> "女";
            default -> null;
        };
    }

    public static Integer convertGender(String gender) {
        return switch (gender) {
            case "未选择" -> 0;
            case "男" -> 1;
            case "女" -> 2;
            default -> null;
        };
    }

    public static String convertTime(LocalDate time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return Optional.ofNullable(time).map(formatter::format).orElse("");
    }

    public static LocalDate convertTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return Optional.ofNullable(time).map(formatter::parse).map(LocalDate::from).orElse(null);
    }
}
