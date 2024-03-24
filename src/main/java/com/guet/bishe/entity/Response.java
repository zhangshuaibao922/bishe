package com.guet.bishe.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author cardo
 * @Version 1.0
 * @Description TODO
 * @date 2024/3/23 21:03
 */
@ApiModel(value = "返回值定义",description = "")
@Data
public class Response<T> {
    private int code=200;
    private String message;
    private T  data;
}
