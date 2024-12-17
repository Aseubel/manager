package com.guyuan.manager.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author 杨之耀
 * @description 封装响应结果
 * @param <T>
 * @date 2024/12/3
 */
@Data
@Builder
@AllArgsConstructor
public final class Response<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 7000723935764546321L;

    private Integer code;
    private String info;
    private T data;

    public static <T> Response<T> success() {
        return Response.<T>builder().code(200).info("success").build();
    }

    public static <T> Response<T> success(T data) {
        return Response.<T>builder().code(200).info("success").data(data).build();
    }

    public static <T> Response<T> fail(String info) {
        return Response.<T>builder().code(500).info(info).build();
    }

}