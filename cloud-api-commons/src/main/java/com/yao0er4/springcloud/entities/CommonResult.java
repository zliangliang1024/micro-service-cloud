package com.yao0er4.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> implements Serializable {

    private int code;
    private String msg;
    private T data;

    public CommonResult(int code, String msg) {
        this(code, msg, null);
    }
}
