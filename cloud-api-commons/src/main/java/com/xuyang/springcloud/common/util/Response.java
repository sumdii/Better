package com.xuyang.springcloud.common.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description Response class
 * @Date 2022/1/25
 * @Author xuyang
 */
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Response<T> {
    private Integer code;
    private String msg;
    private T data;

    public static <T> Response<T> success(T data) {
        return new Response<>(200, "success", data);
    }

    public static <T> Response<T> fail(T data) {
        return new Response<>(500, "fail", data);
    }
}
