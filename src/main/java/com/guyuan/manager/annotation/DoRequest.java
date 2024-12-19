package com.guyuan.manager.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yangzhiyao
 * @description 需要请求服务端的注解
 * @date 2024/12/19
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DoRequest {
    String value() default "";
}
