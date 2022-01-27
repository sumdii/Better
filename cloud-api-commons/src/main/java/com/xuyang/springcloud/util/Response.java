package com.xuyang.springcloud.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description Response class
 * @Date 2022/1/25
 * @Author xuyang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private Integer code;
    private String msg;
    private T data;

    public Response(Integer code, String msg) {
        this(code, msg, null);
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(200, "success", data);
    }

    public static <T> Response<T> fail(T data) {
        return new Response<>(500, "fail", data);
    }
}
