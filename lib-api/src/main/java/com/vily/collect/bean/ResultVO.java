package com.vily.collect.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code;
    private String message;
    private T data;

    public static <T> ResultVO<T> ok(T data) {
        return new ResultVO(200,"成功",data);
    }

    public static <T> ResultVO<T> error(Integer code, String msg) {
        return error(code,msg,null);
    }

    public static <T> ResultVO<T> error(Integer code, String msg, T t) {
        return new ResultVO(code, msg, t);
    }

}
